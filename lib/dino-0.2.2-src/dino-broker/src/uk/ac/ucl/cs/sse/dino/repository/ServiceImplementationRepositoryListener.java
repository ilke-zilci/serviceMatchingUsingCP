package uk.ac.ucl.cs.sse.dino.repository;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Listener interface which receives events from a service implementation repository.
 *
 */
public interface ServiceImplementationRepositoryListener {
	/**
	 * Called when a match is added to the repository.
	 * @param requirement the requriement which was matched.
	 * @param implementation the implmentation which matches the repository.
	 */
	public void addedMatch(ServiceRequirement requirement, ServiceImplementation implementation);
	
	/**
	 * Called when an implementation is removed from all matches in the repository.
	 * @param implementation the implementation which was removed.
	 */
	public void removedImplementation(ServiceImplementation implementation);

}
