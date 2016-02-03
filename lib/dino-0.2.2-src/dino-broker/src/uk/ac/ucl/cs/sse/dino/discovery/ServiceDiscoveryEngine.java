package uk.ac.ucl.cs.sse.dino.discovery;

import java.util.Collection;
import java.util.List;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Interface which should be implemented by service discovery engines.
 */
public interface ServiceDiscoveryEngine {

	/**
	 * Attempts to discover services matching the service profile at the
	 * provided URI. The document at the URI should contain only a single
	 * service profile. If multiple profiles are present then only the first one
	 * will be used in determining which service to select. Implementations are
	 * free to ignore the query so client code must perform its own matchmaking.
	 * The query is used only to cut down the number of results which are
	 * returned.
	 * 
	 * @param query
	 *            a collection of ServiceRequirement objects which describe the required
	 *            services.
	 * @return Array of objects which store the details of service
	 *         implementations. These implementations may or may not match the
	 *         query.
	 */
	public List<ServiceImplementation> discoverServices(
			final Collection<ServiceRequirement> query);
}
