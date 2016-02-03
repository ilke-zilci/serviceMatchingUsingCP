package uk.ac.ucl.cs.sse.dino.invocation;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.monitor.ServiceInvocationMonitor;

/**
 * Class which manages a number of invocation managers. Keeps a list of monitors
 * and forwards messages to all monitors in the list.
 * 
 */
public class InvocationMonitorManager {
	private Collection<ServiceInvocationMonitor> monitorList = new ConcurrentLinkedQueue<ServiceInvocationMonitor>();

	/**
	 * Creates an invocation monitor manager.
	 */
	public InvocationMonitorManager() {
	}

	/**
	 * Add a monitor to this manager.
	 * 
	 * @param monitor
	 *            the monitor to add.
	 */
	public void addMonitor(final ServiceInvocationMonitor monitor) {
		monitorList.add(monitor);
	}

	/**
	 * Remove a monitor from this manager.
	 * 
	 * @param monitor
	 *            the monitor to remove.
	 */
	public void removeMonitor(final ServiceInvocationMonitor monitor) {
		monitorList.remove(monitor);
	}

	/**
	 * Inform the monitor manager that a service is being invoked.
	 * 
	 * @param invocationId
	 *            the ID of the invocation.
	 * @param impl
	 *            the service which was invoked.
	 */
	public void invokingService(String invocationId, ServiceImplementation impl) {
		for (ServiceInvocationMonitor mon : monitorList) {
			mon.invokingService(invocationId, impl);
		}
	}

	/**
	 * Inform the monitor manager that a service has failed.
	 * 
	 * @param invocationId
	 *            the ID of the invocation.
	 * @param impl
	 *            the service which was invoked.
	 */
	public void serviceFailed(String invocationId, ServiceImplementation impl) {
		for (ServiceInvocationMonitor mon : monitorList) {
			mon.serviceFailed(invocationId, impl);
		}

	}

	/**
	 * Inform the monitor manager that a service has completed.
	 * 
	 * @param invocationId
	 *            the ID of the invocation.
	 * @param impl
	 *            the service which was invoked.
	 * @param results the output parameters from the invocation.
	 */
	public void serviceCompleted(String invocationId, ServiceImplementation impl, Param[] results) {
		for (ServiceInvocationMonitor mon : monitorList) {
			mon.serviceCompleted(invocationId, impl, results);
		}

	}
}
