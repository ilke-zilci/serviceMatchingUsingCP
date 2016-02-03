package uk.ac.ucl.cs.sse.dino.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerSession;
import uk.ac.ucl.cs.sse.dino.doc.Mode;

/**
 * Table model for a table of sessions which displays information about the
 * session's mode and current action.
 * 
 */
class SessionTableModel extends AbstractTableModel {
	private final static String[] columnNames = { "Session ID", "Mode",
			"Action" };
	private final List<ListEntry> sessionList = new ArrayList<ListEntry>();
	private final Map<String, ListEntry> entryMap = new HashMap<String, ListEntry>();

	private class ListEntry {
		private final DinoBrokerSession session;
		private SessionAction action;

		private ListEntry(final DinoBrokerSession session) {
			this.session = session;
		}
	}

	/**
	 * Adds a new session to this table model.
	 * @param session the session to add.
	 */
	void addSession(final DinoBrokerSession session) {
		ListEntry entry = new ListEntry(session);
		sessionList.add(entry);
		entryMap.put(session.getSessionId(), entry);
		fireTableRowsInserted(sessionList.size() - 1, sessionList.size() - 1);
	}

	/**
	 * Gets the session at the given row in the table model.
	 * @param row the row of the session to get.
	 * @return the session at the given row.
	 */
	DinoBrokerSession getSession(int row) {
		return sessionList.get(row).session;
	}

	/**
	 * Removes the given session from this table model.
	 * @param session the session to remove.
	 */
	void removeSession(final DinoBrokerSession session) {
		for (int row = 0; row < sessionList.size(); row++) {
			ListEntry entry = sessionList.get(row);
			if (entry.session == session) {
				entryMap.remove(session.getSessionId());
				sessionList.remove(row);
				fireTableRowsDeleted(row, row);
			}
		}
	}

	/**
	 * Sets the current action for the given session.
	 * @param session the session for which the current action should be set.
	 * @param action the action to set.
	 */
	void setAction(final DinoBrokerSession session,
			final SessionAction action) {
		ListEntry entry = entryMap.get(session.getSessionId());
		if (entry != null) {
			entry.action = action;
		}
	}

	/**
	 * Clears the current action for the given session.
	 * @param session non-null session object.
	 */
	void clearAction(final DinoBrokerSession session) {
		ListEntry entry = entryMap.get(session.getSessionId());
		if (entry != null) {
			entry.action = null;
		}
	}

	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(final int col) {
		return columnNames[col];
	}

	public int getRowCount() {
		return sessionList.size();
	}

	public Object getValueAt(final int row, final int col) {
		ListEntry entry = sessionList.get(row);
		switch (col) {
		case 0:
			return entry.session.getSessionId();
		case 1:
			Mode mode = entry.session.getCurrentMode();
			if (mode != null) {
				return mode.getName();
			} else {
				return "-";
			}
		case 2:
			if (entry.action == null) {
				return "-";
			} else {
				return entry.action.getActionType();
			}
		default:
			return null;
		}
	}

}
