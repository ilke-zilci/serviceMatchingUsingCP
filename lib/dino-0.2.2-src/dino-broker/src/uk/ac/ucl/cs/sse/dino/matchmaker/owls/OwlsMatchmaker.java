package uk.ac.ucl.cs.sse.dino.matchmaker.owls;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mindswap.owl.OWLCache;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owls.service.Service;
import org.mindswap.pellet.exceptions.InconsistentOntologyException;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.matchmaker.AbstractServiceMatcher;
import uk.ac.ucl.cs.sse.dino.matchmaker.ExtractedServiceInformation;
import uk.ac.ucl.cs.sse.dino.matchmaker.IncompatibleServiceMatcherException;
import uk.ac.ucl.cs.sse.dino.matchmaker.MatchmakerServiceRegistrationException;

/**
 * Matches OWL-S profiles against OWL-S service descriptions. Implements a very
 * simple matching algorithm which simply checks that input and output
 * parameters in the profile have the same types as those in the service
 * description.
 * 
 */
public class OwlsMatchmaker extends AbstractServiceMatcher {
	private Map<URI, OwlsExtractedServiceInformation> requirementCache = new HashMap<URI, OwlsExtractedServiceInformation>(
			50);

	private Map<URI, ServiceData> implementationCache = new HashMap<URI, ServiceData>();

	private OWLKnowledgeBase kb = OWLFactory.createKB();

	private static class ServiceData {
		private ServiceImplementation implementation;

		private OwlsExtractedServiceInformation extractedInfo;

		private ServiceData(ServiceImplementation implementation,
				OwlsExtractedServiceInformation extractedInfo) {
			this.implementation = implementation;
			this.extractedInfo = extractedInfo;
		}
	}

	/**
	 * Creates a simple matchmaker for OWL-S services.
	 * 
	 */
	public OwlsMatchmaker() {
		kb.setReasoner("Pellet");

		OWLCache cache = kb.getReader().getCache();
		String cacheDirectory = System.getProperty("ontologyCache", ".."
				+ File.separator + "ontology-cache");
		cache.setLocalCacheDirectory(cacheDirectory);
		cache.setForced(true);
	}

	public ExtractedServiceInformation createEmptyServiceInforamtion() {
		return new OwlsExtractedServiceInformation();
	}

	public void newRequirementRegistered(final ServiceRequirement requirement) {
		try {
			URI requiredServiceURI = requirement.getType();
			if (!requirementCache.containsKey(requiredServiceURI)) {
				Service service = kb.readService(requiredServiceURI);
				OwlsExtractedServiceInformation requiredServiceInfo = new OwlsExtractedServiceInformation(
						service);
				requirementCache.put(requiredServiceURI, requiredServiceInfo);
			}
		} catch (FileNotFoundException e) {
			// TODO
			e.printStackTrace();
		}
	}

	public void newServiceRegistered(final ServiceImplementation impl,
			final ExtractedServiceInformation info)
			throws IncompatibleServiceMatcherException {
		if (!(info instanceof OwlsExtractedServiceInformation)) {
			throw new IncompatibleServiceMatcherException(
					"Info is not compatible with OWL-S matcher");
		}

		OwlsExtractedServiceInformation serviceInfo = (OwlsExtractedServiceInformation) info;

		URI serviceDescriptionURI = impl.getServiceDescriptionURI();

		implementationCache.put(serviceDescriptionURI, new ServiceData(impl,
				serviceInfo));
	}

	public ExtractedServiceInformation newServiceRegistered(
			ServiceImplementation impl) throws MatchmakerServiceRegistrationException {
		try {
			URI serviceDescriptionURI = impl.getServiceDescriptionURI();

			if (implementationCache.containsKey(serviceDescriptionURI)) {
				throw new MatchmakerServiceRegistrationException(
						"An implementation for " + serviceDescriptionURI
								+ " is already registered.");
			}

			Service service = kb.readService(serviceDescriptionURI);

			OwlsExtractedServiceInformation serviceInfo = new OwlsExtractedServiceInformation(
					service);

			implementationCache.put(serviceDescriptionURI, new ServiceData(
					impl, serviceInfo));

			assert serviceInfo != null;
			return serviceInfo;
		} catch (FileNotFoundException e) {
			throw new MatchmakerServiceRegistrationException("Could not find file for "
					+ impl.getServiceDescriptionURI(), e);
		} catch (InconsistentOntologyException e) {
			throw new MatchmakerServiceRegistrationException(
					"The ontology of this service was inconsistent: "
							+ impl.getServiceDescriptionURI(), e);
		}
	}

	public void serviceUnregistered(ServiceImplementation impl) {
		URI serviceDescriptionURI = impl.getServiceDescriptionURI();
		implementationCache.remove(serviceDescriptionURI);
	}

	public Set<ServiceImplementation> findServiceMatches(
			final ServiceRequirement requirement) {
		long startTime = System.nanoTime();
		
		URI serviceProfileURI = requirement.getType();

		//This algorithm really needs to be improved so that it doesn't loop
		//over every implementation if this is to scale to large numbers of service
		//implementations.
		
		try {

			OwlsExtractedServiceInformation reqData = findRequestService(serviceProfileURI);

			Set<ServiceImplementation> matchSet = new HashSet<ServiceImplementation>();

			for (ServiceData data : implementationCache.values()) {
				if (serviceDataMatches(reqData, data.extractedInfo)) {
					matchSet.add(data.implementation);
				}
			}

			fireFoundServiceMatches("qos matcher", requirement, System
					.nanoTime()
					- startTime);
			return matchSet;
		} catch (FileNotFoundException e) {
			return Collections.emptySet();
		}
	}

	private OwlsExtractedServiceInformation findRequestService(
			URI serviceProfileURI) throws FileNotFoundException {
		OwlsExtractedServiceInformation data = requirementCache
				.get(serviceProfileURI);
		if (data == null) {
			Service requestService = kb.readService(serviceProfileURI);
			data = new OwlsExtractedServiceInformation(requestService);
			requirementCache.put(serviceProfileURI, data);
		}

		return data;
	}

	private boolean serviceDataMatches(
			final OwlsExtractedServiceInformation requirementData,
			final OwlsExtractedServiceInformation serviceData) {
		if (!serviceData.isSubClassOf(requirementData.getProfileURI())) {
			return false;
		}

		boolean result = subsumes(serviceData.getInputs(), requirementData
				.getInputs())
				&& subsumes(requirementData.getOutputs(), serviceData
						.getOutputs());

		return result;
	}

	private boolean subsumes(final List<ParameterData> pList1,
			final List<ParameterData> pLsit2) {
		if (pList1.size() > pLsit2.size()) {
			return false;
		}

		for (ParameterData candParam : pList1) {
			boolean parameterMatched = false;
			for (ParameterData reqParam : pLsit2) {
				if (reqParam.isSubClassOf(candParam)) {
					parameterMatched = true;
					break;
				}
			}

			if (!parameterMatched) {
				return false;
			}
		}

		return true;
	}
}
