package uk.ac.ucl.cs.sse.dino.selector;

import java.net.URI;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.doc.qos.CapDocQosParameter;
import uk.ac.ucl.cs.sse.dino.repository.ServiceInformationRepository;

/**
 * Service selector implementation which selects services based on response time
 * and number of consecutive failures.
 * 
 */
public class ServiceSelectorImpl extends AbstractServiceSelector {
	private ServiceInformationRepository informationRepository = null;

	/**
	 * Creates a service selector which uses the specified repository to access
	 * monitoring information about services.
	 * 
	 * @param informationRepository
	 *            the information repository from which to get monitoring
	 *            information.
	 */
	public ServiceSelectorImpl(
			final ServiceInformationRepository informationRepository) {
		this.informationRepository = informationRepository;
	}

	public List<ServiceImplementation> orderServiceImplementations(
			final ServiceRequirement requriedQos,
			final Collection<ServiceImplementation> impls) {
		if (impls == null) {
			throw new NullPointerException("Null parameter: impls");
		}

		SortedMap<Double, ServiceImplementation> sortedImpls = new TreeMap<Double, ServiceImplementation>(
				new Comparator<Double>() {

					public int compare(Double d1, Double d2) {
						return -Double.compare(d1, d2);
					}

				});

		for (ServiceImplementation impl : impls) {
			URI uri = impl.getServiceDescriptionURI();
			double monitoredValue = informationRepository.getAttributeAverage(
					uri, "responseTime");

			double qos = Double.POSITIVE_INFINITY;
			if (Double.isNaN(monitoredValue)) {
				Collection<CapDocQosParameter> essentialCapabilities = impl
						.getEssentialQosCapabilities();
				for (CapDocQosParameter param : essentialCapabilities) {
					if (param.getName().equals("responseTime")) {
						qos = param.getMaxValue();
						break;
					}
				}
			} else {
				qos = monitoredValue;
			}

			qos = 1 / qos;

			sortedImpls.put(qos, impl);
		}

		return new LinkedList<ServiceImplementation>(sortedImpls.values());
	}
}
