package uk.ac.ucl.cs.sse.dino.monitor.qos;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.monitor.ServiceInvocationMonitor;
import uk.ac.ucl.cs.sse.dino.repository.ServiceInformationRepository;

/**
 * Monitor implementation which stores monitoring information obtained in a
 * ServiceInformationRepository.
 * 
 */
public class DinoMonitor implements ServiceInvocationMonitor {
	private Map<String, Long> invocationTimeMap = new HashMap<String, Long>();

	private ServiceInformationRepository informationRepository = null;

	/**
	 * Creates the monitor.
	 * @param informationRepository the repository in which to store the obtained information.
	 */
	public DinoMonitor(ServiceInformationRepository informationRepository) {
		this.informationRepository = informationRepository;
	}

	public void invokingService(String invocationId, ServiceImplementation impl) {
		invocationTimeMap.put(invocationId, System.currentTimeMillis());
	}

	public void serviceFailed(String invocationId, ServiceImplementation impl) {
		URI implId = impl.getServiceDescriptionURI();
		informationRepository.updateAttribute(implId, "consecutiveFailures", 1);
		informationRepository.updateAttribute(implId, "successRate", 0);

		invocationTimeMap.remove(invocationId);

	}

	public void serviceCompleted(final String invocationId, final ServiceImplementation impl,
			final Param[] results) {
		long invocationTime = invocationTimeMap.remove(invocationId);
		long responseTime = System.currentTimeMillis() - invocationTime;

		URI implId = impl.getServiceDescriptionURI();
		informationRepository.resetAttribute(implId, "consecutiveFailures");
		informationRepository.updateAttribute(implId, "successRate", 1);
		informationRepository.updateAttribute(implId, "avgResponseTime", responseTime);
	}
}
