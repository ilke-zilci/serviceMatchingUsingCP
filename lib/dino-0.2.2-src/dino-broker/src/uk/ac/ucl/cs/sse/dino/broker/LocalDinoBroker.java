package uk.ac.ucl.cs.sse.dino.broker;

import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.InvocationResponse;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.SelectModeResponse;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryException;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;

/**
 * Interface used for accessing a local dino Broker. This interface removes the
 * <tt>BrokerCommunicationException</tt> exceptions from the interface
 * methods. This interface extends the RemoteDinoBroker class on the principle that
 * a remote broker is the more general case and a local broker is a special case.
 * 
 */
public interface LocalDinoBroker extends DinoBroker {
	public String startSession();

	public void registerReqDoc(final String sessionId, final String reqDocURL)
			throws DinoIdException, DinoDocReadException;

	public void registerCapDoc(final String sessionId, final String capDocURL)
			throws DinoIdException, DinoDocReadException;

	public SelectModeResponse selectMode(final String sessionId,
			final String[] requestedModes) throws DinoIdException,
			ServiceDiscoveryException;

	public InvocationResponse invokeService(final String sessionId,
			final String serviceName, final Param[] params)
			throws DinoIdException, UnsupportedServiceException,
			ServiceInvocationException;

	public void updateAttributeForInvocation(final String sessionId,
			final String serviceURI, final String attributeName,
			final double value) throws DinoIdException;
}
