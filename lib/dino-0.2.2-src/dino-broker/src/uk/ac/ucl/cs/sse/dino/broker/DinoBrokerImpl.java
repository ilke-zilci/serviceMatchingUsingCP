package uk.ac.ucl.cs.sse.dino.broker;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.InvocationResponse;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.SelectModeResponse;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.console.DinoConsole;
import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryEngine;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.doc.DocType;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationEngine;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;
import uk.ac.ucl.cs.sse.dino.repository.ServiceInformationRepository;
import uk.ac.ucl.cs.sse.dino.repository.ServiceNotRegisteredException;

/**
 * Implementation of DinoBrokerInterface.
 */
public final class DinoBrokerImpl implements LocalDinoBroker {
	private ServiceInvocationEngine invocationEngine;

	private Collection<ServiceDiscoveryEngine> discoveryEngineList = new ConcurrentLinkedQueue<ServiceDiscoveryEngine>();

	/**
	 * Maps client session IDs to DinoBrokerSession objects which hold the
	 * session information.
	 */
	private Map<String, DinoBrokerSession> sessionMap = new ConcurrentHashMap<String, DinoBrokerSession>();

	private final ServiceInformationRepository implementationRepository;

	private DinoConsole console;

	private List<DinoBrokerListener> listenerList = new LinkedList<DinoBrokerListener>();

	// Session time out is 1 hour by default
	private long sessionTimeOut = 3600000L;

	/**
	 * Creates a new DinoBroker.
	 * 
	 * @param repository
	 *            the Dino Broker should use to store information about service
	 *            implementations.
	 * @param invocationEngine the invocation engine to use to invoke services.
	 */
	public DinoBrokerImpl(final ServiceInformationRepository repository,
			final ServiceInvocationEngine invocationEngine) {
		this.implementationRepository = repository;
		this.invocationEngine = invocationEngine;

		Timer timer = new Timer();
		timer.schedule(new ExpireSessionTask(), 30000, 30000);
	}

	/**
	 * Adds a discovery engine to the list discovery engines which are used by
	 * this broker.
	 * 
	 * @param discoveryEngine
	 *            the discovery engine to add.
	 */
	public void addDiscoveryEngine(ServiceDiscoveryEngine discoveryEngine) {
		discoveryEngineList.add(discoveryEngine);
	}

	/**
	 * Removes a discovery engine from the list of discovery engines which are
	 * used by this broker.
	 * 
	 * @param discoveryEngine
	 *            the discovery engine to remove.
	 */
	void removeDiscoveryEngine(ServiceDiscoveryEngine discoveryEngine) {
		discoveryEngineList.remove(discoveryEngine);
	}

	public String startSession() {
		DinoBrokerSession session = new DinoBrokerSession(this);
		String sessionId = session.getSessionId();
		sessionMap.put(sessionId, session);

		fireSessionStarted(session);

		assert sessionId != null;
		return sessionId;
	}

	public void quitSession(final String sessionId) {
		if (sessionId == null) {
			throw new NullPointerException("Null parameter: sessionId");
		}

		DinoBrokerSession session = sessionMap.remove(sessionId);

		if (session != null) {
			fireSessionQuit(session);
		}
	}

	public void registerReqDoc(final String sessionId, final String reqDocURL)
			throws DinoDocReadException, DinoIdException {
		// Preconditions
		if (sessionId == null) {
			throw new NullPointerException("Null parameter: sessionId");
		}
		if (reqDocURL == null) {
			throw new NullPointerException("Null parameter: reqDocURL");
		}

		DinoBrokerSession session = getDinoBrokerSession(sessionId);
		session.registerReqDoc(implementationRepository, reqDocURL);
	}

	public void registerCapDoc(final String sessionId, final String capDocURL)
			throws DinoIdException, DinoDocReadException {
		if (sessionId == null) {
			throw new NullPointerException("Null parameter: sessionId");
		}
		if (capDocURL == null) {
			throw new NullPointerException("Null parameter: capDocURL");
		}

		DinoBrokerSession session = getDinoBrokerSession(sessionId);
		session.registerCapDoc(capDocURL);
	}

	public SelectModeResponse selectMode(final String sessionId,
			final String[] requestedModes) throws DinoIdException {
		// Preconditions
		if (sessionId == null) {
			throw new NullPointerException("Null parameter: sessionId");
		}
		if (requestedModes == null) {
			throw new NullPointerException("Null parameter: requestedModes");
		}
		if (requestedModes.length < 1) {
			throw new IllegalArgumentException(
					"requestedModes: this array should contain at least one element.");
		}

		DinoBrokerSession session = getDinoBrokerSession(sessionId);
		SelectModeResponse resp = session.selectMode(implementationRepository,
				requestedModes);

		assert resp != null;
		return resp;

	}

	public InvocationResponse invokeService(final String sessionId,
			final String serviceName, final Param[] params)
			throws DinoIdException, UnsupportedServiceException,
			ServiceInvocationException {
		// Preconditions
		if (sessionId == null) {
			throw new NullPointerException("null client ID.");
		}

		if (serviceName == null) {
			throw new NullPointerException("null service name.");
		}

		if (params == null) {
			throw new NullPointerException("null parameter array.");
		}

		DinoBrokerSession session = getDinoBrokerSession(sessionId);
		InvocationResponse out = session.invokeService(invocationEngine,
				serviceName, params);

		// Postconditions
		assert out != null;

		return out;

	}

	/**
	 * Discovers services which match the provided service requirements and
	 * stores them in the service repository. This is done using every available
	 * discovery engine.
	 * 
	 * @param requirements
	 *            the service requirements for which to discover matching
	 *            implementations.
	 */
	public void discoverServiceImplementations(
			final Collection<ServiceRequirement> requirements) {
		for (ServiceDiscoveryEngine de : discoveryEngineList) {
			List<ServiceImplementation> impls = de
					.discoverServices(requirements);
			for (ServiceImplementation impl : impls) {
				try {
					implementationRepository.addServiceImplementation(impl);
				} catch (ServiceNotRegisteredException e) {
					e.printStackTrace();
				}
			}

		}
		implementationRepository.checkForNewMatches(requirements);
	}

	private DinoBrokerSession getDinoBrokerSession(final String sessionId)
			throws DinoIdException {
		assert sessionId != null;

		DinoBrokerSession session = sessionMap.get(sessionId);
		if (session == null) {
			throw new DinoIdException("The client ID " + sessionId
					+ " is not registered with this broker.");
		}
		return session;
	}

	public void updateAttributeForInvocation(String sessionId,
			String serviceURI, String attributeName, double value)
			throws DinoIdException {
		URI uri = URI.create(serviceURI);
		implementationRepository.updateAttribute(uri, attributeName, value);
	}

	/**
	 * Shows the Dino Broker console if it is not already displayed.
	 * 
	 */
	public void showConsole() {
		if (console == null) {
			console = new DinoConsole(implementationRepository, this);
		}
	}

	/**
	 * Hides the Dino Broker console if it is displayed.
	 * 
	 */
	public void hideConsole() {
		if (console != null) {
			console.dispose();
			console = null;
		}
	}

	/**
	 * Registers a <tt>DinoBrokerListener</tt> to this broker.
	 * 
	 * @param l
	 *            the <tt>DinoBrokerListener</tt> to add to this broker.
	 */
	public void addListener(DinoBrokerListener l) {
		listenerList.add(l);
	}

	/**
	 * Removes a <tt>DinoBrokerListener</tt> from this broker.
	 * 
	 * @param l
	 *            the <tt>DinoBrokerListener</tt> to remove from this broker.
	 */
	public void removeListener(DinoBrokerListener l) {
		listenerList.remove(l);
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that a session has been quit.
	 * 
	 * @param session
	 *            the session which was quit.
	 */
	void fireSessionQuit(DinoBrokerSession session) {
		for (DinoBrokerListener l : listenerList) {
			l.sessionQuit(session);
		}
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that a session has started.
	 * 
	 * @param session
	 *            the session which was started.
	 */
	void fireSessionStarted(DinoBrokerSession session) {
		for (DinoBrokerListener l : listenerList) {
			l.sessionStarted(session);
		}
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that a register document (either a ReqDoc or a CapDoc) operation has
	 * started.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param docURL
	 *            the URL of the registered document.
	 * @param docType
	 *            the type of the docuemnt (ReqDoc or CapDoc).
	 */
	void fireRegisterDocStarted(ActionIdentifier actionID,
			DinoBrokerSession session, String docURL, DocType docType) {
		for (DinoBrokerListener l : listenerList) {
			long time = System.nanoTime();
			l.registerDocStarted(actionID, time, session, docURL, docType);
		}
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that a register document (either a ReqDoc or a CapDoc) operation has
	 * ended.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param session
	 *            the session in which this operation occurred.
	 */
	void fireRegisterDocEnded(ActionIdentifier actionID,
			DinoBrokerSession session) {
		for (DinoBrokerListener l : listenerList) {
			long time = System.nanoTime();
			l.registerDocEnded(actionID, time, session);
		}
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that a select mode operation has started.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param requestedModes
	 *            the modes which were requested, in order of priority.
	 */
	void fireSelectModeStarted(ActionIdentifier actionID,
			DinoBrokerSession session, String[] requestedModes) {
		long time = System.nanoTime();
		for (DinoBrokerListener l : listenerList) {
			l.selectModeStarted(actionID, time, session, requestedModes);
		}
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that a select mode operation has ended.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param selectedMode
	 *            the mdoe which was selected.
	 */
	void fireSelectModeEnded(ActionIdentifier actionID,
			DinoBrokerSession session, String selectedMode) {
		for (DinoBrokerListener l : listenerList) {
			long time = System.nanoTime();
			l.selectModeEnded(actionID, time, session, selectedMode);
		}
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that an invoke service operation has started.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param serviceName
	 *            the name of the service which was invoked.
	 * @param params
	 *            the invocation parameter.
	 */
	void fireInvokeServiceStarted(ActionIdentifier actionID,
			DinoBrokerSession session, String serviceName, Param[] params) {
		long time = System.nanoTime();
		for (DinoBrokerListener l : listenerList) {
			l
					.invokeServiceStarted(actionID, time, session, serviceName,
							params);
		}
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that an invoke service operation has ended normally (without an error).
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param invokedService
	 *            the identifier (e.g. URL) of the service which was actually
	 *            selected and invoked.
	 * @param outputParameters
	 *            the output parameters from the invocation.
	 */
	void fireInvokeServiceEnded(ActionIdentifier actionID,
			DinoBrokerSession session, String invokedService,
			Param[] outputParameters) {
		long time = System.nanoTime();
		for (DinoBrokerListener l : listenerList) {
			l.invokeServiceEnded(actionID, time, session, invokedService,
					outputParameters);
		}
	}

	/**
	 * Informs all <tt>DinoBrokerListener</tt>s registered with this broker
	 * that an invoke service operation has ended with an error.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param errorMsg
	 *            the error message returned by the invocation.
	 */
	public void fireInvokeServiceEnded(ActionIdentifier actionID,
			DinoBrokerSession session, String errorMsg) {
		long time = System.nanoTime();
		for (DinoBrokerListener l : listenerList) {
			l.invokeServiceEnded(actionID, time, session, errorMsg);
		}
	}

	private class ExpireSessionTask extends TimerTask {
		@Override
		public void run() {
			long currentTime = System.currentTimeMillis();
			for (Iterator<DinoBrokerSession> i = sessionMap.values().iterator(); i
					.hasNext();) {
				DinoBrokerSession session = i.next();

				if (session.calculateTimeSinceLastAction(currentTime) > sessionTimeOut) {
					i.remove();
					fireSessionQuit(session);
				}
			}
		}
	}
}
