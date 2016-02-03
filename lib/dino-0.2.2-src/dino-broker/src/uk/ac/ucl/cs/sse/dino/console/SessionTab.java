package uk.ac.ucl.cs.sse.dino.console;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.broker.ActionIdentifier;
import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerListener;
import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerSession;
import uk.ac.ucl.cs.sse.dino.console.SessionAction.SessionActionType;
import uk.ac.ucl.cs.sse.dino.doc.DocType;

/**
 * Tab which displays information about a session.
 * 
 */
class SessionTab extends JSplitPane implements DinoBrokerListener {
	private final JTable sessionTable = new JTable();
	private final SessionTableModel sessionModel = new SessionTableModel();
	private final JTable sessionHistoryTable = new JTable();
	private final SessionHistoryTableModel sessionHistoryModel = new SessionHistoryTableModel();

	/**
	 * Creates a new session tab.
	 */
	SessionTab() {
		setOrientation(JSplitPane.VERTICAL_SPLIT);

		sessionTable.setModel(sessionModel);
		sessionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sessionTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting()) {
							int row = sessionTable.getSelectedRow();
							if (row == -1) {
								sessionHistoryModel.clearSession();
							} else {
								DinoBrokerSession session = sessionModel
										.getSession(row);
								sessionHistoryModel.selectSession(session
										.getSessionId());
							}
							repaint();
						}
					}
				});

		sessionHistoryTable.setModel(sessionHistoryModel);

		add(new JScrollPane(sessionTable), JSplitPane.TOP);
		add(new JScrollPane(sessionHistoryTable), JSplitPane.BOTTOM);

	}

	public void sessionQuit(DinoBrokerSession session) {
		sessionModel.removeSession(session);
		sessionHistoryModel.removeSession(session.getSessionId());
		repaint();
	}

	public void sessionStarted(DinoBrokerSession session) {
		sessionModel.addSession(session);
		sessionHistoryModel.addSession(session.getSessionId());
		repaint();
	}

	private void actionStarted(DinoBrokerSession session, SessionAction action) {
		sessionModel.setAction(session, action);
		sessionHistoryModel.addAction(session.getSessionId(), action);
		repaint();
	}

	private void actionEnded(DinoBrokerSession session,
			ActionIdentifier actionID, String outputString, long completionTime) {
		sessionModel.clearAction(session);
		sessionHistoryModel.actionComplete(session.getSessionId(), actionID
				.getActionID(), outputString, completionTime);
		repaint();
	}

	public void invokeServiceEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String invokedService,
			Param[] outputParameters) {
		String outputString = invokedService + ": "
				+ getParamString(outputParameters);
		actionEnded(session, actionID, outputString, time);
	}

	public void invokeServiceStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String serviceName, Param[] params) {
		String actionDetail = serviceName + getParamString(params);
		SessionAction action = new SessionAction(actionID,
				SessionActionType.InvokeService, actionDetail, time);
		actionStarted(session, action);
	}

	private String getParamString(Param[] params) {
		String s = "[";
		for (Param p : params) {
			s += p.getValue();
			Param[] properties = p.getProperties();
			if (properties != null) {
				s += getParamString(properties);
			}
			s += ", ";
		}
		s += "]";
		return s;
	}

	public void registerDocEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session) {
		actionEnded(session, actionID, "", time);
	}

	public void registerDocStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String docURL, DocType docType) {
		SessionAction action;
		if (docType == DocType.ReqDoc) {
			action = new SessionAction(actionID,
					SessionActionType.RegisterReqDoc, docURL, time);
		} else {
			action = new SessionAction(actionID,
					SessionActionType.RegisterCapDoc, docURL, time);
		}
		actionStarted(session, action);
	}

	public void selectModeEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String selectedMode) {
		actionEnded(session, actionID, selectedMode, time);
	}

	public void selectModeStarted(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String[] requestedModes) {
		String actionString = "Selecting mode: [";
		for (String mode : requestedModes) {
			actionString += mode + ",";
		}
		actionString += "]";
		SessionAction action = new SessionAction(actionID,
				SessionActionType.SelectMode, actionString, time);
		actionStarted(session, action);
	}

	public void invokeServiceEnded(ActionIdentifier actionID, long time,
			DinoBrokerSession session, String errorMsg) {
		actionEnded(session, actionID, errorMsg, time);
	}
}
