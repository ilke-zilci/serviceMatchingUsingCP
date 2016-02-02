package uk.ac.ucl.cs.sse.dino.selector;

import java.util.List;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Listener which is informed of the current state of service selection.
 * 
 */
public interface ServiceSelectionListener {
	/**
	 * Called when service selection starts for a particular requriement.
	 * 
	 * @param requirement
	 *            the requriement for which a service is being selected.
	 * @param selectionMethod
	 *            the method used for selection.
	 */
	public void serviceSelectionStarted(ServiceRequirement requirement,
			String selectionMethod);

	/**
	 * Called when selection is complete for a particular requriement.
	 * 
	 * @param requirement
	 *            the requriement for which service selection is complete.
	 * @param orderedImplementations
	 *            the list of service implementations which has been ordered by
	 *            the service selector.
	 * @param selectionTime
	 *            the time taken to make the selection.
	 */
	public void serviceSelectionComplete(ServiceRequirement requirement,
			List<ServiceImplementation> orderedImplementations,
			long selectionTime);

	/**
	 * Called during selection when an implementation is being evaluated.
	 * 
	 * @param requirement
	 *            the requirement for which an implementation is being selected.
	 * @param impl
	 *            the implementation which is being evaluated.
	 * @param info
	 *            infromation about what the result of the evaluation was which
	 *            will depend on the evaluation implementation.
	 */
	public void implementationEvaluated(ServiceRequirement requirement,
			ServiceImplementation impl, String info);

}
