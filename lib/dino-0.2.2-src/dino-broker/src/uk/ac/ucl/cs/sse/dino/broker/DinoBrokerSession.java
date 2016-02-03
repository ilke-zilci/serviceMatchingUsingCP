package uk.ac.ucl.cs.sse.dino.broker;

import java.io.IOException;
import java.rmi.server.UID;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import uk.ac.ucl.cs.sse.dino.InvocationResponse;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.SelectModeResponse;
import uk.ac.ucl.cs.sse.dino.doc.CapDoc;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocParser;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.doc.DocType;
import uk.ac.ucl.cs.sse.dino.doc.Mode;
import uk.ac.ucl.cs.sse.dino.doc.ReqDoc;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationEngine;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;
import uk.ac.ucl.cs.sse.dino.repository.ServiceInformationRepository;
import uk.ac.ucl.cs.sse.dino.repository.ServiceNotRegisteredException;

/**
 * Stores all the information about a DinoBroker session, such as the registered
 * ReqDoc and service implementations which have been discovered.
 * 
 */
public final class DinoBrokerSession {
	private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
			.newInstance();

	private ReqDoc reqDoc = null;

	private CapDoc capDoc = null;

	private Mode currentMode = null;

	private final String sessionId;

	private final DinoBrokerImpl broker;

	/** Stores the selected services for the current mode. */
	private final Map<String, SelectedServicesList> selectedServices = new ConcurrentHashMap<String, SelectedServicesList>();

	private Long actionCount = 0L;

	private Long lastActionTime = System.currentTimeMillis();

	/**
	 * Creates a new session associated with the provided broker implementation.
	 * 
	 * @param dino
	 *            the associated broker implementation.
	 */
	DinoBrokerSession(final DinoBrokerImpl dino) {
		sessionId = new UID().toString();
		this.broker = dino;
	}

	/**
	 * Registers the ReqDoc for this session.
	 * 
	 * @param repository
	 *            the repository used during registration of this ReqDoc to
	 *            register new requirements (necessary as a performance
	 *            optimisation so required services are parsed only once.).
	 * 
	 * @param reqDocURL
	 *            the URL of the reqDoc.
	 * @throws DinoDocReadException
	 *             if there is a problem reading or understanding the ReqDoc.
	 */
	synchronized void registerReqDoc(final ServiceInformationRepository repository,
			final String reqDocURL) throws DinoDocReadException {
		assert repository != null;
		assert reqDocURL != null;

		updateLastActionTime();

		final ActionIdentifier actionIdentifier;
		synchronized (actionCount) {
			final long actionID = actionCount++;
			actionIdentifier = new ActionIdentifier(sessionId, actionID);
		}
		broker.fireRegisterDocStarted(actionIdentifier, this, reqDocURL,
				DocType.ReqDoc);

		try {
			Document document = documentBuilderFactory.newDocumentBuilder()
					.parse(reqDocURL);
			Element reqDocElement = document.getDocumentElement();
			reqDoc = DinoDocParser.parseReqDoc(reqDocElement);

			Collection<ServiceRequirement> requirements = reqDoc
					.getAllRequirements();
			for (ServiceRequirement req : requirements) {
				repository.newRequirementRegistered(req);
			}

			assert reqDoc != null;
		} catch (SAXException e) {
			throw new DinoDocReadException("Failed to register ReqDoc at "
					+ reqDocURL, e);
		} catch (IOException e) {
			throw new DinoDocReadException("Failed to register ReqDoc at "
					+ reqDocURL, e);
		} catch (ParserConfigurationException e) {
			throw new DinoDocReadException("Failed to register ReqDoc at "
					+ reqDocURL, e);
		} finally {
			broker.fireRegisterDocEnded(actionIdentifier, this);
		}
	}

	/**
	 * Registers the CapDoc for this session.
	 * 
	 * @param capDocURL
	 *            the URL of the CapDoc.
	 * @throws DinoDocReadException
	 *             if there is a problem reading or understanding the CapDoc.
	 */
	synchronized void registerCapDoc(final String capDocURL) throws DinoDocReadException {
		assert capDocURL != null;

		updateLastActionTime();

		final ActionIdentifier actionIdentifier;
		synchronized (actionCount) {
			final long actionID = actionCount++;
			actionIdentifier = new ActionIdentifier(sessionId, actionID);
		}

		broker.fireRegisterDocStarted(actionIdentifier, this, capDocURL,
				DocType.CapDoc);

		try {
			Document document = documentBuilderFactory.newDocumentBuilder()
					.parse(capDocURL);
			Element capDocElement = document.getDocumentElement();
			capDoc = DinoDocParser.parseCapDoc(capDocElement);

			assert capDoc != null;
		} catch (SAXException e) {
			throw new DinoDocReadException("Failed to register ReqDoc at "
					+ capDocURL, e);
		} catch (IOException e) {
			throw new DinoDocReadException("Failed to register ReqDoc at "
					+ capDocURL, e);
		} catch (ParserConfigurationException e) {
			throw new DinoDocReadException("Failed to register ReqDoc at "
					+ capDocURL, e);
		} finally {
			broker.fireRegisterDocEnded(actionIdentifier, this);
		}
	}

	/**
	 * Gets the session ID associated with this session.
	 * 
	 * @return the session ID.
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Tries to select a mode which can be satisfied from the list of requested
	 * mode. The first mode which can be satisfied is selected.
	 * 
	 * @param repository
	 *            the repository of service implementations which can be used to
	 *            satisfy the mode and where provided services should be
	 *            registered.
	 * 
	 * @param requestedModes
	 *            the list of requested modes in order of priority.
	 * @return a response to the select mode request, identifying the selected
	 *         mode and any other associated information.
	 */
	synchronized SelectModeResponse selectMode(
			final ServiceInformationRepository repository,
			final String[] requestedModes) {
		// Preconditions
		if (requestedModes == null) {
			throw new NullPointerException("Null argument: requestedModes");
		}
		if (requestedModes.length < 1) {
			throw new IllegalArgumentException(
					"requestedModes: this array should contain at least one element.");
		}

		updateLastActionTime();

		final ActionIdentifier actionIdentifier;
		synchronized (actionCount) {
			final long actionID = actionCount++;
			actionIdentifier = new ActionIdentifier(sessionId, actionID);
		}
		broker.fireSelectModeStarted(actionIdentifier, this, requestedModes);

		SelectModeResponse resp = new SelectModeResponse();

		Mode selectedMode = findRequiredServices(repository, requestedModes,
				actionIdentifier);
		if (selectedMode != null) {
			resp.setSelectedMode(selectedMode.getName());
			registerProvidedServices(repository, selectedMode.getName());
		}

		currentMode = selectedMode;
		broker.fireSelectModeEnded(actionIdentifier, this, resp
				.getSelectedMode());

		// Postconditions
		assert resp != null;
		return resp;
	}

	private Mode findRequiredServices(
			final ServiceInformationRepository repository,
			final String[] requestedModes,
			final ActionIdentifier actionIdentifier) {

		// If there is no ReqDoc then there are no service requirements and the
		// first mode
		// can be safely selected.
		if (reqDoc == null) {
			if (capDoc == null) {
				return null;
			} else {
				return capDoc.getMode(requestedModes[0]);
			}
		}

		// Iterate over the requested modes, starting with the most preferred
		// one
		// (the first one in the list), looking for one for which we can satisfy
		// the service requirements.
		Mode mode = null;
		Collection<ServiceRequirement> requirements = null;
		for (String requestedMode : requestedModes) {
			// Gets the Mode object matching the requested mode name. If the
			// named mode does not exist then we simply go on to the next one.
			mode = reqDoc.getMode(requestedMode);
			if (mode == null) {
				continue;
			}
			// Gets the service requirements of the selected mode.
			requirements = mode.getServiceRequirements();

			// Try to discover services which match the requirements and store
			// them in the repository.
			repository.checkForNewMatches(requirements);

			if (repository.satisfiesAllRequirements(requirements)) {
				break;
			}

			broker.discoverServiceImplementations(requirements);
			if (repository.satisfiesAllRequirements(requirements)) {
				break;
			}
		}

		if (mode != null && requirements != null) {
			selectBestServiceImplementations(repository, requirements,
					actionIdentifier);
		}

		return mode;
	}

	private void selectBestServiceImplementations(
			final ServiceInformationRepository repository,
			final Collection<ServiceRequirement> requirements,
			final ActionIdentifier actionIdentifier) {
		selectedServices.clear();
		for (ServiceRequirement req : requirements) {
			selectedServices.put(req.getName(), new SelectedServicesList(
					repository, req));
		}
	}

	private void registerProvidedServices(
			final ServiceInformationRepository repository,
			final String selectedMode) {
		if (capDoc != null) {
			// Unregister old services
			// The mode object from the CapDoc is not the same as that from the
			// ReqDoc, which is stored in currentMode.
			if (currentMode != null) {
				Mode oldMode = capDoc.getMode(currentMode.getName());
				if (oldMode != null) {
					Collection<ServiceImplementation> providedServices = oldMode
							.getServiceImpelmentations();
					for (ServiceImplementation impl : providedServices) {
						repository.removeServiceImplementation(impl);
					}
				}
			}

			// Register new provided services
			Mode mode = capDoc.getMode(selectedMode);
			if (mode != null) {
				Collection<ServiceImplementation> providedServices = mode
						.getServiceImpelmentations();
				for (ServiceImplementation impl : providedServices) {
					try {
						repository.addServiceImplementation(impl);
					} catch (ServiceNotRegisteredException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Invokes the named service with the provided parameters.
	 * 
	 * @param invocationEngine
	 *            engine used to invoke service (e.g. an OWL-S invocation
	 *            engine).
	 * @param serviceName
	 *            the name of the service to invoke, as detailed in the
	 *            registered ReqDoc.
	 * @param params
	 *            the input parameters with which the service should be invoked.
	 * @return the output parameters from the invoked service.
	 * @throws ServiceInvocationException
	 *             if there was a problem invoking the service.
	 */
	InvocationResponse invokeService(
			final ServiceInvocationEngine invocationEngine,
			final String serviceName, final Param[] params)
			throws ServiceInvocationException {
		// Preconditions
		assert serviceName != null;
		assert params != null;

		updateLastActionTime();

		final ActionIdentifier actionIdentifier;
		synchronized (actionCount) {
			final long actionID = actionCount++;
			actionIdentifier = new ActionIdentifier(sessionId, actionID);

		}
		broker.fireInvokeServiceStarted(actionIdentifier, this, serviceName,
				params);

		try {
			if (currentMode == null) {
				throw new ServiceInvocationException("No mode selected");
			}
			ServiceRequirement requirement = currentMode
					.getServiceRequirement(serviceName);

			InvocationResponse resp = null;

			SelectedServicesList availableServices = selectedServices
					.get(requirement.getName());
			resp = availableServices
					.invokeBestService(invocationEngine, params);

			if (resp != null) {
				broker.fireInvokeServiceEnded(actionIdentifier, this, resp
						.getInvokedService(), resp.getOutputParameters());
			}

			return resp;
		} catch (ServiceInvocationException e) {
			broker.fireInvokeServiceEnded(actionIdentifier, this, e
					.getMessage());
			throw e;
		}
	}

	/**
	 * Gets the current mode of this session.
	 * 
	 * @return the currently selected mode.
	 */
	public Mode getCurrentMode() {
		return currentMode;
	}

	private void updateLastActionTime() {
		assert lastActionTime != null;
		synchronized (lastActionTime) {
			lastActionTime = System.currentTimeMillis();
		}
	}

	/**
	 * Calculates the time since the last action was performed in this session.
	 * This is used to see if the session has expired due to inactivity.
	 * 
	 * @param currentTime
	 *            the current time to use in the calculation.
	 * @return the duration since the last action occurred, relative to the
	 *         current time.
	 */
	long calculateTimeSinceLastAction(long currentTime) {
		assert lastActionTime != null;
		synchronized (lastActionTime) {
			return currentTime - lastActionTime;
		}
	}
}
