package uk.ac.ucl.cs.sse.dino.selector;

import java.util.Collection;
import java.util.List;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Interface implemented by classes which are responsible for selecting a
 * service from among several functionally equivalent services using QoS
 * attribute.
 */
public interface ServiceSelector {
	/**
	 * Selects the service which best satisfies the requirement from among the
	 * provided service implementations.
	 * 
	 * @param requirement
	 *            the requirement to be satisfied.
	 * @param impls
	 *            the list of implementations from which the best candidate
	 *            should be chosen.
	 * @param actionID the ID of the action in which this request is made. 
	 * @return the best service implementation or null if no good implementation
	 *         could be found.
	 */
	public List<ServiceImplementation> orderServiceImplementations(
			final ServiceRequirement requirement,
			final Collection<ServiceImplementation> impls);
	
	/**
	 * Adds a listener to this service selector.
	 * 
	 * @param l
	 *            the listener to add.
	 */
	public void addServiceSelectionListener(ServiceSelectionListener l);
}
