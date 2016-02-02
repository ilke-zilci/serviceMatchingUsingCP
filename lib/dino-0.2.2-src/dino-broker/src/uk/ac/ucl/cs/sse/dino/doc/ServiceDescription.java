package uk.ac.ucl.cs.sse.dino.doc;

import java.net.URI;

/**
 * Represents the detailed functional description of a service.
 */
public abstract class ServiceDescription {
	private URI serviceURI;
	
	/**
	 * Creates a service description associated with the given service description URI.
	 * @param serviceURI
	 */
	public ServiceDescription(URI serviceURI) {
		this.serviceURI = serviceURI;
	}
	
	/**
	 * Gets the URI of the service description.
	 * @return a URI of a functional service description.
	 */
	public URI getServiceURI() {
		return serviceURI;
	}

}
