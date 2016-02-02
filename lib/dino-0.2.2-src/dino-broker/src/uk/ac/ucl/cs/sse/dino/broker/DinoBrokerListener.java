package uk.ac.ucl.cs.sse.dino.broker;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.doc.DocType;

/**
 * Listener interface which receives events from a Dino Broker.
 * 
 */
public interface DinoBrokerListener {
	/**
	 * Called when a session is started.
	 * 
	 * @param session
	 *            the session which was started.
	 */
	public void sessionStarted(DinoBrokerSession session);

	/**
	 * Called when a session has been quit.
	 * 
	 * @param session
	 *            the session which was quit.
	 */
	public void sessionQuit(DinoBrokerSession session);

	/**
	 * Called when a register document (either a ReqDoc or a CapDoc) operation
	 * has started.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param time
	 *            the time at which this operation was invoked.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param docURL
	 *            the URL of the registered document.
	 * @param docType
	 *            the type of the document (ReqDoc or CapDoc).
	 */
	public void registerDocStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String docURL, DocType docType);

	/**
	 * Called when a register document (either a ReqDoc or a CapDoc) operation
	 * has ended.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param time
	 *            time at which this operation completed.
	 * @param session
	 *            the session in which this operation occurred.
	 */
	public void registerDocEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session);

	/**
	 * Called when a select mode operation has started.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param time
	 *            the time at which this operation was invoked.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param requestedModes
	 *            the modes which were requested, in order of priority.
	 */
	public void selectModeStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String[] requestedModes);

	/**
	 * Called when that a select mode operation has ended.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param time
	 *            the time at which this operation completed.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param selectedMode
	 *            the mdoe which was selected.
	 */
	public void selectModeEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String selectedMode);

	/**
	 * Called when an invoke service operation has started.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param time
	 *            the time at which this operation was invoked.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param serviceName
	 *            the name of the service which was invoked.
	 * @param params
	 *            the invocation parameter.
	 */
	public void invokeServiceStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String serviceName, Param[] params);

	/**
	 * Called when an invoke service operation has ended normally (without an
	 * error).
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param time
	 *            the time at which this operation completed.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param invokedService
	 *            the identifier (e.g. URL) of the service which was actually
	 *            selected and invoked.
	 * @param outputParameters
	 *            the output parameters from the invocation.
	 */
	public void invokeServiceEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String invokedService,
			Param[] outputParameters);

	/**
	 * Called when an invoke service operation has ended with an error.
	 * 
	 * @param actionID
	 *            the ID assigned to this action.
	 * @param time
	 *            the time at which this operation completed.
	 * @param session
	 *            the session in which this operation occurred.
	 * @param errorMsg
	 *            the error message returned by the invocation.
	 */
	public void invokeServiceEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String errorMsg);

}
