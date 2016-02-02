package uk.ac.ucl.cs.sse.dino.invocation;

import java.rmi.server.UID;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;

/**
 * Abstract class which adds invocation monitoring to service invocation
 * engines.
 */
public abstract class AbstractServiceInvocationEngine implements
		ServiceInvocationEngine {
	private final InvocationMonitorManager monitorManager;

	/**
	 * Creates an invocation engine which uses the specified
	 * 
	 * @param monitorManager
	 *            the monitor manager to send invocation events to.
	 */
	public AbstractServiceInvocationEngine(
			final InvocationMonitorManager monitorManager) {
		this.monitorManager = monitorManager;
	}

	public Param[] invokeService(final ServiceImplementation impl,
			final Param[] params) throws ServiceInvocationException {
		final String invocationId = new UID().toString();
		try {
			monitorManager.invokingService(invocationId, impl);
			Param[] out = tryInvokeService(impl, params);
			monitorManager.serviceCompleted(invocationId, impl, out);
			return out;
		} catch (ServiceExecutionException e) {
			monitorManager.serviceFailed(invocationId, impl);
			return null;
		}

	}

	/**
	 * Tries to invoke the given service implementation with the provided
	 * parameters.
	 * 
	 * @param impl
	 *            the service implementation to invoke.
	 * @param params
	 *            the parameters to use in invoking the service.
	 * @return the output parameters from the service.
	 * @throws ServiceInvocationException
	 *             if the service could not be invoked.
	 * @throws ServiceExecutionException
	 *             if the service was invoked successfully but the invoked
	 *             service generated an error.
	 */
	public abstract Param[] tryInvokeService(ServiceImplementation impl,
			Param[] params) throws ServiceInvocationException,
			ServiceExecutionException;

}
