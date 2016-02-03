package uk.ac.ucl.cs.sse.dino.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.matchmaker.ExtractedServiceInformation;
import uk.ac.ucl.cs.sse.dino.matchmaker.MatchmakerServiceRegistrationException;
import uk.ac.ucl.cs.sse.dino.matchmaker.ServiceMatcher;
import uk.ac.ucl.cs.sse.dino.selector.AbstractServiceSelector;
import uk.ac.ucl.cs.sse.dino.selector.ServiceSelectionListener;
import uk.ac.ucl.cs.sse.dino.selector.ServiceSelector;
import uk.ac.ucl.cs.sse.dino.util.DinoIO;

/**
 * Stores information about service implementations and matches between
 * requirements and services.
 * 
 */
public final class ServiceInformationRepositoryImpl implements ServiceInformationRepository {
	private final ServiceMatchRepository matchRepository = new ServiceMatchRepository(
			this);
	private final Map<URI, ServiceStats> serviceStats = new ConcurrentHashMap<URI, ServiceStats>();
	private final ServiceMatcher matcher;

	private final File repositoryDirectory;
	private final File dataFile;
	private final File indexFile;
	private final ServiceSelector serviceSelector;

	private List<ServiceImplementationRepositoryListener> listenerList = new LinkedList<ServiceImplementationRepositoryListener>();

	/**
	 * Creates a service implementation repository. The repository directory is
	 * determined by the system property <tt>dinoRepositoryLocation</tt>.
	 * 
	 * @param matcher
	 *            the matcher used to determine which service implementations
	 *            match service requirements.
	 * 
	 * @param serviceSelector
	 *            selector to be used to sort service implementations according
	 *            to how good their QoS is.
	 * 
	 * @throws DinoSetupException
	 *             if there is a problem setting up the repository.
	 */
	public ServiceInformationRepositoryImpl(final ServiceMatcher matcher,
			final AbstractServiceSelector serviceSelector)
			throws DinoSetupException {
		this.serviceSelector = serviceSelector;
		this.matcher = matcher;
		repositoryDirectory = new File(System.getProperty(
				"dinoRepositoryLocation", ".." + File.separator + "repository"));
		if (!repositoryDirectory.exists()) {
			repositoryDirectory.mkdir();
		}

		dataFile = new File(repositoryDirectory, "data.xml");

		try {
			indexFile = new File(repositoryDirectory, "index.xml");

			if (!indexFile.exists()) {
				PrintWriter writer = new PrintWriter(indexFile);
				writer.println("<?xml version=\"1.0\"?>");
				writer.println("<!DOCTYPE serviceIndex [");
				writer.println("  <!ENTITY data SYSTEM \"data.xml\">");
				writer.println("]>");
				writer.println("<serviceIndex>");
				writer.println("&data;");
				writer.println("</serviceIndex>");
				writer.close();
			}

			if (!dataFile.exists()) {
				dataFile.createNewFile();
			}

			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			SAXParser parser = saxFactory.newSAXParser();
			parser.parse(indexFile, new RepositoryIndexParser(matcher,
					repositoryDirectory));

		} catch (IOException e) {
			throw new DinoSetupException("Could not read repository at path: "
					+ repositoryDirectory, e);
		} catch (ParserConfigurationException e) {
			throw new DinoSetupException(
					"Problem setting up SAX parser to read repository information.",
					e);
		} catch (SAXException e) {
			throw new DinoSetupException(
					"Problem parsing repository information.", e);
		}
	}

	public boolean satisfiesAllRequirements(
			final Collection<ServiceRequirement> reqs) {
		if (reqs == null) {
			throw new NullPointerException("Null parameter: reqs");
		}

		for (ServiceRequirement req : reqs) {
			Collection<ServiceImplementation> impls = matchRepository
					.getMatches(req);

			boolean match = false;
			for (ServiceImplementation impl : impls) {
				if (serviceExists(impl)) {
					match = true;
				} else {
					matchRepository.clearMatch(impl);
				}
			}

			if (!match) {
				return false;
			}
		}

		return true;
	}

	private boolean serviceExists(final ServiceImplementation impl) {
		// TODO: Check WSDL document exists
		// TODO: Check end point exists
		// TODO: For Dino services, check service exists and OWL-S document is
		// current.
		return true;
	}

	public void checkForNewMatches(final Collection<ServiceRequirement> reqs) {
		if (reqs == null) {
			throw new NullPointerException("Null parameter: reqs");
		}

		for (ServiceRequirement req : reqs) {
			if (matcher != null) {
				Set<ServiceImplementation> matches;
				synchronized (matcher) {
					matches = matcher.findServiceMatches(req);
				}

				for (ServiceImplementation impl : matches) {
					matchRepository.addMatch(req, impl);
				}
			}
		}
	}

	public void updateAttribute(final URI serviceIdentifier,
			final String attributeName, final double value) {
		ServiceStats stats = getServiceStats(serviceIdentifier);
		stats.updateAttribute(attributeName, value);
	}

	public void resetAttribute(final URI serviceIdentifier,
			final String attributeName) {
		ServiceStats stats = getServiceStats(serviceIdentifier);
		stats.resetAttribute(attributeName);
	}

	public Set<ServiceImplementation> getMatchingImplementations(
			final ServiceRequirement requirement) {
		return matchRepository.getMatches(requirement);
	}

	public List<ServiceImplementation> getMatchingOrderedImplementations(
			final ServiceRequirement requirement) {
		assert requirement != null;

		Set<ServiceImplementation> impls = getMatchingImplementations(requirement);
		return serviceSelector.orderServiceImplementations(requirement, impls);
	}

	private ServiceStats getServiceStats(final URI serviceIdentifier) {
		ServiceStats stats = serviceStats.get(serviceIdentifier);
		if (stats == null) {
			stats = new ServiceStats();
			serviceStats.put(serviceIdentifier, stats);
		}
		return stats;
	}

	public double getAttributeAverage(final URI serviceIdentifier,
			final String attributeName) {
		ServiceStats stats = serviceStats.get(serviceIdentifier);
		if (stats == null) {
			return Double.NaN;
		}
		return stats.getAttributeAverage(attributeName);
	}

	public double getAttributeSum(final URI serviceIdentifier,
			final String attributeName) {
		ServiceStats stats = serviceStats.get(serviceIdentifier);
		if (stats == null) {
			return Double.NaN;
		}
		return stats.getAttributeSum(attributeName);
	}

	public void addServiceImplementation(final ServiceImplementation impl)
			throws ServiceNotRegisteredException {
		if (impl == null) {
			throw new NullPointerException("Null parameter: impl");
		}

		try {
			URI serviceURI = impl.getServiceDescriptionURI();
			File serviceFile = generateUniqueFileName(serviceURI, "owl");
			File qosFile = null;

			ExtractedServiceInformation info;
			ExtractedServiceInformation specificInfo = matcher
					.newServiceRegistered(impl);
			if (impl.getQosURI() == null) {
				info = new GeneralServiceInformation(specificInfo, serviceURI,
						serviceFile.getName());
			} else {
				qosFile = generateUniqueFileName(impl.getQosURI(), "qos");
				info = new GeneralServiceInformation(specificInfo, serviceURI,
						serviceFile.getName(), qosFile.getName());
			}

			appendServiceInformationToRepositoryIndex(info);

			impl.setLocalServiceDescription(serviceFile);

			// Copy files
			DinoIO.copy(serviceURI, serviceFile);
			if (qosFile != null) {
				DinoIO.copy(impl.getQosURI(), qosFile);
			}
		} catch (IOException e) {
			throw new ServiceNotRegisteredException(
					"IO problem adding service implementation "
							+ impl.getServiceDescriptionURI()
							+ " to repository.", e);
		} catch (MatchmakerServiceRegistrationException e) {
			throw new ServiceNotRegisteredException(
					"Problem registering service implementation "
							+ impl.getServiceDescriptionURI()
							+ " with matchmaker.", e);
		}
	}

	private File generateUniqueFileName(final URI resourceURI,
			final String fileExtension) throws IOException {
		assert resourceURI != null;
		assert fileExtension != null;

		File repositoryFile;

		String path = resourceURI.getPath();
		if (path == null || path.equals("")) {
			path.equals(resourceURI.getHost());
		}

		String[] splitArray = resourceURI.getPath().split("[/\\\\]");
		if (splitArray.length > 0) {
			String fileName = splitArray[splitArray.length - 1];
			if (!fileName.endsWith("." + fileExtension)) {
				fileName += fileExtension;
			}

			repositoryFile = new File(repositoryDirectory, fileName);

			int count = 1;
			while (repositoryFile.exists()) {
				String newFileName = fileName.substring(0,
						fileName.length() - 4)
						+ count + ".owl";
				repositoryFile = new File(repositoryDirectory, newFileName);
				count++;
			}
		} else {
			throw new IOException();
		}

		assert repositoryFile != null;
		return repositoryFile;
	}

	private void appendServiceInformationToRepositoryIndex(
			final ExtractedServiceInformation info) throws IOException {
		assert info != null;

		PrintWriter writer;
		writer = new PrintWriter(new FileWriter(dataFile, true));

		info.writeInformation(writer);
		writer.println();
		writer.close();
	}

	public void removeServiceImplementation(final ServiceImplementation impl) {
		if (impl == null) {
			throw new NullPointerException("Null parameter: impl");
		}

		matchRepository.clearMatch(impl);
		matcher.serviceUnregistered(impl);
	}
	
	public void newRequirementRegistered(final ServiceRequirement requirement) {
		matcher.newRequirementRegistered(requirement);
	}

	public void addListener(ServiceImplementationRepositoryListener l) {
		listenerList.add(l);
	}

	public void removeListener(ServiceImplementationRepositoryListener l) {
		listenerList.remove(l);
	}

	public Set<ServiceRequirement> getMatchedRequriements() {
		return matchRepository.getRequriements();
	}

	/**
	 * Informs all listeners that a match has been added to the repository.
	 * 
	 * @param requirement
	 *            the requirement which was matched.
	 * @param implementation
	 *            the implementation which matches the repository.
	 */
	void fireAddedMatch(ServiceRequirement requirement,
			ServiceImplementation implementation) {
		for (ServiceImplementationRepositoryListener l : listenerList) {
			l.addedMatch(requirement, implementation);
		}
	}

	/**
	 * Informs all listeners that an implementation has been removed from all
	 * matches in the repository.
	 * 
	 * @param implementation
	 *            the implementation which was removed.
	 */
	void fireRemovedImplementation(ServiceImplementation implementation) {
		for (ServiceImplementationRepositoryListener l : listenerList) {
			l.removedImplementation(implementation);
		}
	}

	public void addServiceSelectionListener(final ServiceSelectionListener l) {
		if (serviceSelector != null) {
			serviceSelector.addServiceSelectionListener(l);
		}
	}
}
