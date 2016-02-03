package uk.ac.ucl.cs.sse.dino.discovery.local;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryEngine;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.repository.DinoSetupException;

/**
 * Discovers services from a local directory of service descriptions.
 */
public class LocalDiscoveryEngine implements ServiceDiscoveryEngine {
	private File serviceDirectory;

	/**
	 * Creates a service discovery engine that searches for services in the
	 * specified directory. The directory is searched recursively so that
	 * service descriptions in sub-directories are also found.
	 * 
	 * @param serviceDirectory
	 *            the directory to search for service descriptions.
	 * @throws DinoSetupException if the discovery engine cannot be set up properly.
	 */
	public LocalDiscoveryEngine(final File serviceDirectory)
			throws DinoSetupException {
		try {
			this.serviceDirectory = serviceDirectory.getCanonicalFile();

		} catch (IOException e) {
			throw new DinoSetupException(
					"Error setting up Local Discovery Engine.", e);
		}
	}

	public List<ServiceImplementation> discoverServices(
			final Collection<ServiceRequirement> query) {
		return discoverServices(serviceDirectory);
	}

	private List<ServiceImplementation> discoverServices(final File directory) {
		File[] files = directory.listFiles();

		// If directory was not actually a directory then return.
		if (files == null) {
			return new LinkedList<ServiceImplementation>();
		}

		List<ServiceImplementation> serviceList = new LinkedList<ServiceImplementation>();
		for (File f : files) {
			if (f.isDirectory()) {
				serviceList.addAll(discoverServices(f));
			} else if (f.getName().matches(".*\\.owl\\z")) {
				URI serviceURI = f.toURI();
				try {
					String owlPath = f.getPath();
					File qosFile = new File(owlPath.substring(0, owlPath
							.length() - 4)
							+ ".qos");
					if (qosFile.exists()) {
						URI qosURI = qosFile.toURI();
						serviceList.add(new ServiceImplementation(serviceURI,
								qosURI));
					} else {
						serviceList.add(new ServiceImplementation(serviceURI));
					}
				} catch (DinoDocReadException e) {
					//If the QoS Doc cannot be understood then ignore it.
					serviceList.add(new ServiceImplementation(serviceURI));
				}
			}
		}

		return serviceList;
	}
}
