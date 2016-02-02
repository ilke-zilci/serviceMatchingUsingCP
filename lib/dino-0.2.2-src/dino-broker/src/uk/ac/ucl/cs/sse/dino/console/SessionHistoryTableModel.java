package uk.ac.ucl.cs.sse.dino.console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

/**
 * Table model which stores the history of actions performed in a session.
 * 
 */
final class SessionHistoryTableModel extends AbstractTableModel {
	private final static String[] columnNames = { "ID", "Action",
			"Execution Time", "Input", "Output" };
	private final Map<String, List<SessionAction>> sessionHistory = new HashMap<String, List<SessionAction>>();
	private List<SessionAction> actionList = Collections.emptyList();

	/**
	 * Sets the session which should be selected. This is the session which is
	 * displayed and is affected by various other methods.
	 * 
	 * @param sessionID
	 *            the ID of the session.
	 */
	void selectSession(String sessionID) {
		List<SessionAction> l = sessionHistory.get(sessionID);
		if (l != null) {
			actionList = l;
		} else {
			actionList = Collections.emptyList();
		}
		fireTableDataChanged();
	}

	/**
	 * Clears all actions from the currently selected session histroy.
	 * 
	 */
	void clearSession() {
		actionList = Collections.emptyList();
	}

	/**
	 * Adds a new session to this table model.
	 * @param sessionID the ID of the session to add.
	 */
	void addSession(String sessionID) {
		sessionHistory.put(sessionID, new ArrayList<SessionAction>());
	}

	/**
	 * Removes a session from this table mdoel.
	 * @param sessionID the ID of the session to remove.
	 */
	void removeSession(String sessionID) {
		sessionHistory.remove(sessionID);
	}

	/**
	 * Adds an action to the given session history.
	 * @param sessionID the session to add the action to.
	 * @param action the action to add to the session histroy.
	 */
	void addAction(String sessionID, SessionAction action) {
		List<SessionAction> l = sessionHistory.get(sessionID);
		if (l != null) {
			l.add(action);
			if (l == actionList) {
				fireTableRowsInserted(l.size() - 1, l.size() - 1);
			}
		}
	}

	/**
	 * Informs the table mdoel that an action is complete and adds details about the action to the history.
	 * @param sessionID the session which the completed action is part of.
	 * @param actionID the ID of the completed action.
	 * @param outputString string representing the output from the action.
	 * @param completionTime the time taken to complete the action.
	 */
	void actionComplete(String sessionID, long actionID, String outputString,
			long completionTime) {
		List<SessionAction> l = sessionHistory.get(sessionID);
		if (l != null) {
			SessionAction action = null;
			int row = 0;
			for (row = 0; row < l.size(); row++) {
				SessionAction a = l.get(row);
				if (a.getID() == actionID) {
					action = a;
					break;
				}
			}

			if (action != null) {
				action.executionComplete(outputString, completionTime);
				fireTableRowsUpdated(row, row);
			}
		}
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(final int col) {
		return columnNames[col];
	}

	public int getRowCount() {
		return actionList.size();
	}

	public Object getValueAt(final int row, final int col) {
		SessionAction action = actionList.get(row);
		switch (col) {
		case 0:
			return action.getID();
		case 1:
			return action.getActionType();
		case 2:
			long executionTime = action.getExecutionTime();
			if (executionTime >= 0) {
				return executionTime;
			} else {
				return "";
			}
		case 3:
			return action.getInputString();
		case 4:
			String outputString = action.getOutputString();
			if (outputString == null) {
				return "";
			} else {
				return outputString;
			}
		default:
			return null;
		}
	}

}
