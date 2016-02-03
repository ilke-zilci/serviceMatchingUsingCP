package uk.ac.ucl.cs.sse.dino.selector;

import java.util.LinkedList;
import java.util.List;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Abstract implementation of the <tt>ServiceSelector</tt> interface which
 * handles listeners.
 * 
 */
public abstract class AbstractServiceSelector implements ServiceSelector {
	private List<ServiceSelectionListener> listenerList = new LinkedList<ServiceSelectionListener>();

	public void addServiceSelectionListener(final ServiceSelectionListener l) {
		listenerList.add(l);
	}

	/**
	 * Removes a listener from this service selector.
	 * 
	 * @param l
	 *            the listener to remove.
	 */
	public void removeServiceSelectionListener(final ServiceSelectionListener l) {
		listenerList.remove(l);
	}

	/**
	 * Informs all listeners that service selection has started.
	 * 
	 * @param requirement
	 *            the requriement for which a service is being selected.
	 * @param selectionMethod
	 *            the method used for selection.
	 */
	public void fireServiceSelectionStarted(
			final ServiceRequirement requirement, String selectionMethod) {
		for (ServiceSelectionListener l : listenerList) {
			l.serviceSelectionStarted(requirement, selectionMethod);
		}
	}

	/**
	 * Informs all listeners when selection is complete for a particular requriement.
	 * 
	 * @param requirement
	 *            the requriement for which service selection is complete.
	 * @param orderedImplementations
	 *            the ordered list of service implementations.
	 * @param selectionTime
	 *            the time taken to make the selection.
	 */
	public void fireServiceSelectionComplete(
			final ServiceRequirement requirement,
			final List<ServiceImplementation> orderedImplementations,
			final long selectionTime) {
		for (ServiceSelectionListener l : listenerList) {
			l.serviceSelectionComplete(requirement, orderedImplementations,
					selectionTime);
		}
	}

	/**
	 * Informs all listeners that an impleentation has been evaluated.
	 * 
	 * @param requirement
	 *            the requirement for which an implementation is being selected.
	 * @param impl
	 *            the implementation which is being evaluated.
	 * @param info
	 *            infromation about what the result of the evaluation was which
	 *            will depend on the evaluation implementation.
	 */
	public void fireImplementationEvaluated(
			final ServiceRequirement requirement, ServiceImplementation impl,
			String info) {
		for (ServiceSelectionListener l : listenerList) {
			l.implementationEvaluated(requirement, impl, info);
		}
	}

}
