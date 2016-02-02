package uk.ac.ucl.cs.sse.dino.broker;

import uk.ac.ucl.cs.sse.dino.BrokerCommunicationException;
import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.InvocationResponse;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.SelectModeResponse;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryException;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;

/**
 * Interface containing methods used by client applications to interact with
 * remote Dino Brokers.
 */
public interface DinoBroker {
	/**
	 * Starts a new Dino session and gets the session ID.
	 * 
	 * @return an ID for this session.
	 * @throws BrokerCommunicationException
	 *             if there is a problem communicating with the remote broker.
	 */
	public String startSession() throws BrokerCommunicationException;

	/**
	 * Ends the specified session. All information of associated with the
	 * session will be discarded and all subsequent calls to the
	 * <tt>DinoBroker</tt> interface will result in the
	 * <tt>DinoIdException</tt> being thrown. If the session does not exist
	 * then no action is taken and the session returns normally.
	 * 
	 * @param sessionId
	 *            the id of the session to quit.
	 * @throws BrokerCommunicationException
	 *             if there is a problem communicating with the remote broker.
	 */
	public void quitSession(final String sessionId);

	/**
	 * Registers a new Dino ReqDoc with the Dino Broker. This document describes
	 * the services which are required by the registering entity.
	 * 
	 * @param sessionId
	 *            the id of the session for which a ReqDoc should be registered.
	 * @param reqDocURL
	 *            the URL of the ReqDoc to be registered.
	 * @throws DinoIdException
	 *             if the specified session ID is invalid.
	 * @throws DinoDocReadException
	 *             if there was a problem reading or understanding the provided
	 *             ReqDoc XML fragment.
	 * @throws BrokerCommunicationException
	 *             if there is a problem communicating with the remote broker.
	 */
	public void registerReqDoc(final String sessionId, final String reqDocURL)
			throws DinoIdException, DinoDocReadException,
			BrokerCommunicationException;

	/**
	 * Registers a new Dino CapDoc with the Dino Broker. This document describes
	 * the service which are provided by the registering entity.
	 * 
	 * @param sessionId
	 *            the id of the session for which a CapDoc should be registered.
	 * @param capDocURL
	 *            the URL of the CapDoc to be registered.
	 * @throws DinoIdException if no registration for the provided client ID could be found.
	 * @throws DinoDocReadException
	 * @throws BrokerCommunicationException
	 *             if there is a problem communicating with the remote broker.
	 */
	public void registerCapDoc(final String sessionId, final String capDocURL)
			throws DinoIdException, DinoDocReadException,
			BrokerCommunicationException;

	/**
	 * Requests services which satisfy one of the provided execution modes. The
	 * requested execution modes are provided in order of priority. When this
	 * method is called, the Dino Broker should refer to the ReqDoc associated
	 * with the client to discover the service requirements for the requested
	 * modes. The Dino Broker should then discover services which satisfy the
	 * service requirements of one of the requested modes, preferably the first,
	 * and provide the client with details of the selected mode.
	 * 
	 * @param sessionId
	 *            identifies the client and the client's ReqDoc to the Dino
	 *            Broker.
	 * @param requestedModes
	 *            priority ordering of desired client modes.
	 * @return information about the selected mode.
	 * @throws DinoIdException
	 *             if no registration for the provided client ID could be found.
	 * @throws ServiceDiscoveryException
	 *             if services could not be found to satisfy any of the
	 *             requested modes.
	 * @throws BrokerCommunicationException
	 *             if there is a problem communicating with the remote broker.
	 */
	public SelectModeResponse selectMode(final String sessionId,
			final String[] requestedModes) throws DinoIdException,
			ServiceDiscoveryException, BrokerCommunicationException;

	/**
	 * Invokes a service via the Dino Broker. The client invokes the service
	 * using the terms defined in the OWL-S requests refered to in the ReqDoc.
	 * The DinoBroker is responsible for translating these service invocations
	 * into a form which the target service can understand.
	 * 
	 * @param sessionId
	 *            identifies the client and the client's ReqDoc to the Dino
	 *            Broker.
	 * @param serviceName
	 *            the name of the service, from the client's ReqDoc, which
	 *            should be invoked.
	 * @param params
	 *            the input parameters to the service, which should match those
	 *            in the OWL-S service specified in the request for this
	 *            service.
	 * @return the response from the service.
	 * @throws DinoIdException
	 *             if no registration for the provided client ID could be found.
	 * @throws UnsupportedServiceException
	 *             if the requested service is not supported by the current
	 *             mode.
	 * @throws ServiceInvocationException
	 *             if there is a failure invoking the service.
	 * @throws BrokerCommunicationException
	 *             if there is a problem communicating with the remote broker.
	 */
	public InvocationResponse invokeService(final String sessionId,
			final String serviceName, final Param[] params)
			throws DinoIdException, UnsupportedServiceException,
			ServiceInvocationException, BrokerCommunicationException;

	/**
	 * Updates the value of a QoS attribute related to the most recent service
	 * invocation of the named service. Note that if multiple services are being
	 * invoked from different threads thaen the attribute update may not match
	 * the correct service implementation. There are several possible solutions
	 * to this problem. Either, use synchronisation, uses a different session
	 * for each thread or live with some inaccuracies in the attribute values.
	 * 
	 * @param sessionId
	 *            session which this message forms a part of.
	 * @param serviceURI
	 *            the URI of the service for which attribute should be updated.
	 * @param attributeName
	 *            name of the attirbute to be updated.
	 * @param value
	 *            value of the attribute for last invocation.
	 * @throws DinoIdException
	 *             if no registration for the provided client ID could be found.
	 * @throws BrokerCommunicationException
	 *             if there is a problem communicating with the remote broker.
	 */
	public void updateAttributeForInvocation(final String sessionId,
			final String serviceURI, final String attributeName,
			final double value) throws DinoIdException,
			BrokerCommunicationException;
}
