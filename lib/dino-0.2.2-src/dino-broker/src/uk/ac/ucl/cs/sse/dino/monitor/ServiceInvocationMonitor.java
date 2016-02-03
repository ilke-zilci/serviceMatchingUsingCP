package uk.ac.ucl.cs.sse.dino.monitor;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;

/**
 * Interface to be implemented by a class which monitors the invocation of
 * services in Dino. Call back methods are used to inform the monitor of state
 * changes within a particular session of the Dino broker.
 * 
 */
public interface ServiceInvocationMonitor {
	/**
	 * Called immediately before Dino invokes a service.
	 * 
	 * @param invocationId identifies this invocation.
	 * @param impl the service which is being implemented.
	 */
	public void invokingService(String invocationId, ServiceImplementation impl);

	/**
	 * Called if a service invocation fails to complete successfully.
	 * 
	 * @param invocationId identifies the invocation which failed.
	 * @param impl the service which failed.
	 */
	public void serviceFailed(String invocationId, ServiceImplementation impl);

	/**
	 * Called immediately after a service is successfully invoked.
	 * 
	 * @param invocationId identifies the invocation which completed.
	 * @param impl the service which successfully completed.
	 * @param outputParameters the output parameters of the service invocation.
	 */
	public void serviceCompleted(String invocationId, ServiceImplementation impl,
			Param[] outputParameters);
	
	/*public void updateAttribute(ServiceImplementation impl,
			String attributeName, double value);*/
}
