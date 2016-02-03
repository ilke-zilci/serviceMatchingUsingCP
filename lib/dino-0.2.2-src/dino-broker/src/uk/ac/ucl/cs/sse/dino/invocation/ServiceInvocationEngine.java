package uk.ac.ucl.cs.sse.dino.invocation;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;

/**
 * An interface for invoking a service in an implementation independent manner.
 * 
 */
public interface ServiceInvocationEngine {
	/**
	 * Invokes a service in an implementation independent manner. Param objects
	 * are used as parameters
	 * 
	 * @param impl
	 *            The information held about the service to be implemented.
	 * @param params
	 *            the input parameters to the service.
	 * @return the output parameters from the service.
	 * @throws ServiceInvocationException
	 *             if there is an error in invoking the service.
	 */
	public Param[] invokeService(final ServiceImplementation impl, final Param[] params)
			throws ServiceInvocationException;
}
