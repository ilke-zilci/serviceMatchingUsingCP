package uk.ac.ucl.cs.sse.dino.remote;

import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import uk.ac.ucl.cs.sse.dino.BrokerCommunicationException;
import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.InvocationResponse;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.SelectModeResponse;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.axis.DinoBrokerServiceLocator;
import uk.ac.ucl.cs.sse.dino.broker.DinoBroker;
import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryException;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;

/**
 * An implementation of the <tt>RemoteDinoBroker</tt> interface which accesses
 * a remote broker which is presented as a web service through Apache Axis.
 */
public class RemoteBrokerClient implements DinoBroker {
	private uk.ac.ucl.cs.sse.dino.axis.DinoBroker broker;
	private URL brokerURL;

	/**
	 * Creates a new connection to a remote DinoBroker.
	 * @param brokerURL the URL of the service where the DinoBroker is presented.
	 * @throws RemoteBrokerException if there is a problem setting up the connection.
	 */
	public RemoteBrokerClient(URL brokerURL) throws RemoteBrokerException {
		try {
			this.brokerURL = brokerURL;

			DinoBrokerServiceLocator locator = new DinoBrokerServiceLocator();
			broker = locator.getDinoBrokerService(brokerURL);
		} catch (ServiceException e) {
			throw new RemoteBrokerException(
					"Problem with broker at: " + brokerURL, e);
		}
	}

	public InvocationResponse invokeService(String sessionId,
			String serviceName, Param[] params) throws DinoIdException,
			UnsupportedServiceException, ServiceInvocationException,
			BrokerCommunicationException {

		uk.ac.ucl.cs.sse.dino.axis.InvocationResponse resp;
		try {
			resp = broker.invokeService(sessionId, serviceName,
					convertParams(params));

			return new InvocationResponse(convertParams(resp
					.getOutputParameters()), resp.getInvokedService());
		} catch (uk.ac.ucl.cs.sse.dino.axis.DinoIdException e) {
			throw new DinoIdException("Problem with broker at: " + brokerURL, e);
		} catch (uk.ac.ucl.cs.sse.dino.axis.UnsupportedServiceException e) {
			throw new UnsupportedServiceException("Problem with broker at: "
					+ brokerURL, e);
		} catch (uk.ac.ucl.cs.sse.dino.axis.ServiceInvocationException e) {
			throw new ServiceInvocationException("Problem with broker at: "
					+ brokerURL, e);
		} catch (RemoteException e) {
			throw new BrokerCommunicationException("Problem with broker at: "
					+ brokerURL, e);
		}

	}

	private uk.ac.ucl.cs.sse.dino.axis.Param[] convertParams(
			final Param[] params) {

		if (params == null) {
			return new uk.ac.ucl.cs.sse.dino.axis.Param[0];
		}

		uk.ac.ucl.cs.sse.dino.axis.Param[] convertedParams = new uk.ac.ucl.cs.sse.dino.axis.Param[params.length];

		for (int i = 0; i < params.length; i++) {
			uk.ac.ucl.cs.sse.dino.axis.Param[] convertedProps = convertParams(params[i]
					.getProperties());
			convertedParams[i] = new uk.ac.ucl.cs.sse.dino.axis.Param(params[i]
					.getName(), convertedProps, params[i].getType(), params[i]
					.getValue());
		}

		return convertedParams;
	}

	private Param[] convertParams(
			final uk.ac.ucl.cs.sse.dino.axis.Param[] params) {
		if (params == null) {
			return new Param[0];
		}

		Param[] convertedParams = new Param[params.length];

		for (int i = 0; i < params.length; i++) {
			convertedParams[i] = new Param(params[i].getName(), params[i]
					.getType(), params[i].getValue());
			convertedParams[i].setProperties(convertParams(params[i]
					.getProperties()));
		}

		return convertedParams;
	}

	public void quitSession(String sessionId) {
		try {
			broker.quitSession(sessionId);
		} catch (RemoteException e) {
			// Do Nothing since the session is being quit anyway.
		}
	}

	public void registerCapDoc(String sessionId, String capDocURL)
			throws DinoIdException, DinoDocReadException,
			BrokerCommunicationException {
		try {
			broker.registerCapDoc(sessionId, capDocURL);
		} catch (uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException e) {
			throw new DinoDocReadException("Problem with broker at: "
					+ brokerURL, e);
		} catch (uk.ac.ucl.cs.sse.dino.axis.DinoIdException e) {
			throw new DinoIdException("Problem with broker at: " + brokerURL, e);
		} catch (RemoteException e) {
			throw new BrokerCommunicationException("Problem with broker at: "
					+ brokerURL, e);
		}
	}

	public void registerReqDoc(String sessionId, String reqDocURL)
			throws DinoIdException, DinoDocReadException,
			BrokerCommunicationException {
		try {
			broker.registerReqDoc(sessionId, reqDocURL);
		} catch (uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException e) {
			throw new DinoDocReadException("Problem with broker at: "
					+ brokerURL, e);
		} catch (uk.ac.ucl.cs.sse.dino.axis.DinoIdException e) {
			throw new DinoIdException("Problem with broker at: " + brokerURL, e);
		} catch (RemoteException e) {
			throw new BrokerCommunicationException("Problem with broker at: "
					+ brokerURL, e);
		}
	}

	public SelectModeResponse selectMode(String sessionId,
			String[] requestedModes) throws DinoIdException,
			ServiceDiscoveryException, BrokerCommunicationException {
		uk.ac.ucl.cs.sse.dino.axis.SelectModeResponse response;
		try {
			response = broker.selectMode(sessionId, requestedModes);
			return new SelectModeResponse(response.getSelectedMode());
		} catch (uk.ac.ucl.cs.sse.dino.axis.DinoIdException e) {
			throw new DinoIdException("Problem with broker at: " + brokerURL, e);
		} catch (RemoteException e) {
			throw new BrokerCommunicationException("Problem with broker at: "
					+ brokerURL, e);
		}
	}

	public String startSession() throws BrokerCommunicationException {
		try {
			return broker.startSession();
		} catch (RemoteException e) {
			throw new BrokerCommunicationException("Problem with broker at: "
					+ brokerURL, e);
		}
	}

	public void updateAttributeForInvocation(String sessionId,
			String serviceURI, String attributeName, double value)
			throws DinoIdException, BrokerCommunicationException {
		try {
			broker.updateAttributeForInvocation(sessionId, serviceURI,
					attributeName, value);
		} catch (uk.ac.ucl.cs.sse.dino.axis.DinoIdException e) {
			throw new DinoIdException("Problem with broker at: " + brokerURL, e);
		} catch (RemoteException e) {
			throw new BrokerCommunicationException("Problem with broker at: "
					+ brokerURL, e);
		}
	}
}
