package uk.ac.ucl.cs.sse.dino;

import java.io.File;

import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerImpl;
import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerListener;
import uk.ac.ucl.cs.sse.dino.broker.LocalDinoBroker;
import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryException;
import uk.ac.ucl.cs.sse.dino.discovery.local.LocalDiscoveryEngine;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.invocation.InvocationMonitorManager;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;
import uk.ac.ucl.cs.sse.dino.invocation.owls.OWLS_InvocationEngine;
import uk.ac.ucl.cs.sse.dino.matchmaker.AbstractServiceMatcher;
import uk.ac.ucl.cs.sse.dino.matchmaker.MatcherListener;
import uk.ac.ucl.cs.sse.dino.matchmaker.owls.OwlsMatchmaker;
import uk.ac.ucl.cs.sse.dino.matchmaker.owls.OwlsQosMatchmaker;
import uk.ac.ucl.cs.sse.dino.monitor.qos.DinoMonitor;
import uk.ac.ucl.cs.sse.dino.repository.DinoSetupException;
import uk.ac.ucl.cs.sse.dino.repository.ServiceInformationRepositoryImpl;
import uk.ac.ucl.cs.sse.dino.selector.AbstractServiceSelector;
import uk.ac.ucl.cs.sse.dino.selector.ServiceSelectionListener;
import uk.ac.ucl.cs.sse.dino.selector.appval.AppValServiceSelector;

/**
 * Broker implementation which doesn't require OSGi to run.
 * 
 */
public class StandAloneBroker implements LocalDinoBroker {
	private final DinoBrokerImpl broker;
	private final AbstractServiceMatcher simpleMatcher, qosMatcher;
	private final ServiceInformationRepositoryImpl repository;
	private final AbstractServiceSelector selector;

	/**
	 * Creates a stand alone broker.
	 * 
	 * @param displayConsole
	 *            if true then the Dino Broker console will be displayed
	 *            otherwise it will not be.
	 * 
	 * @throws DinoSetupException
	 *             if there is a problem setting up the stand alone broker.
	 */
	public StandAloneBroker(final boolean displayConsole)
			throws DinoSetupException {
		simpleMatcher = new OwlsMatchmaker();
		qosMatcher = new OwlsQosMatchmaker(simpleMatcher);

		selector = new AppValServiceSelector();
		repository = new ServiceInformationRepositoryImpl(
				qosMatcher, selector);
		
		InvocationMonitorManager monitorManager = new InvocationMonitorManager();
		DinoMonitor monitor = new DinoMonitor(repository);
		monitorManager.addMonitor(monitor);
		OWLS_InvocationEngine invocationEngine = new OWLS_InvocationEngine(
				monitorManager);

		broker = new DinoBrokerImpl(repository, invocationEngine);


		LocalDiscoveryEngine discoveryEngine = new LocalDiscoveryEngine(
				new File(".." + File.separator + "owls-services"));
		broker.addDiscoveryEngine(discoveryEngine);

		if (displayConsole) {
			broker.showConsole();
		}
	}

	/**
	 * Creates a stand alone broker with no display console.
	 * 
	 * @throws DinoSetupException
	 *             if there is a problem setting up the stand alone broker.
	 */
	public StandAloneBroker() throws DinoSetupException {
		this(false);
	}

	public InvocationResponse invokeService(String sessionId,
			String serviceName, Param[] params) throws DinoIdException,
			UnsupportedServiceException, ServiceInvocationException {
		return broker.invokeService(sessionId, serviceName, params);
	}

	public void quitSession(String sessionId) {
		broker.quitSession(sessionId);
	}

	public void registerCapDoc(String sessionId, String capDocURL)
			throws DinoIdException, DinoDocReadException {
		broker.registerCapDoc(sessionId, capDocURL);
	}

	public void registerReqDoc(String sessionId, String reqDocURL)
			throws DinoIdException, DinoDocReadException {
		broker.registerReqDoc(sessionId, reqDocURL);
	}

	public SelectModeResponse selectMode(String sessionId,
			String[] requestedModes) throws DinoIdException,
			ServiceDiscoveryException {
		return broker.selectMode(sessionId, requestedModes);
	}

	public String startSession() {
		return broker.startSession();
	}

	public void updateAttributeForInvocation(String sessionId,
			String serviceURI, String attributeName, double value)
			throws DinoIdException {
		broker.updateAttributeForInvocation(sessionId, serviceURI,
				attributeName, value);
	}

	/**
	 * Adds a service selection listener to this broker.
	 * 
	 * @param l
	 *            the non-null listener to add.
	 */
	public void addServiceSelectionListener(final ServiceSelectionListener l) {
		selector.addServiceSelectionListener(l);
	}

	/**
	 * Adds a broker listener to this broker.
	 * 
	 * @param l
	 *            the non-null listener to add.
	 */
	public void addBrokerListener(final DinoBrokerListener l) {
		broker.addListener(l);
	}

	/**
	 * Adds a matcher listener to thsi broker.
	 * 
	 * @param l
	 *            the non-null listener to add.
	 */
	public void addMatchmakerListener(final MatcherListener l) {
		simpleMatcher.addMatcherListener(l);
		qosMatcher.addMatcherListener(l);
	}
}
