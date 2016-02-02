package uk.ac.ucl.cs.sse.dino.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Table mdoel which stroes which services have been selected for particular
 * requriements.
 * 
 */
class SelectionTableModel extends AbstractTableModel {

	private final static String[] columnNames = { "Selection Method",
			"Requirement", "Selected Implementation" };
	private final List<ListEntry> sessionList = new ArrayList<ListEntry>();
	private final Map<ServiceRequirement, ListEntry> entryMap = new HashMap<ServiceRequirement, ListEntry>();

	private class ListEntry {
		private final String selectionMethod;
		private final ServiceRequirement requirement;
		private ServiceImplementation impl;

		private ListEntry(final String selectionMethod,
				final ServiceRequirement requirement) {
			this.selectionMethod = selectionMethod;
			this.requirement = requirement;
		}
	}

	/**
	 * Adds
	 * 
	 * @param actionID
	 * @param selectionMethod
	 * @param requirement
	 */
	void addSelection(final ServiceRequirement requirement,
			final String selectionMethod) {
		ListEntry entry = new ListEntry(selectionMethod, requirement);
		sessionList.add(entry);
		entryMap.put(requirement, entry);
		fireTableRowsInserted(sessionList.size() - 1, sessionList.size() - 1);
	}

	/**
	 * Sets the service impelmentation which was selected
	 * 
	 * @param requirement the requirement for which the implementation was selected.
	 * @param impl the selected implementation.
	 */
	void setImplementation(final ServiceRequirement requirement,
			final ServiceImplementation impl) {
		ListEntry entry = entryMap.get(requirement);
		if (entry != null) {
			entry.impl = impl;
		}
	}

	/**
	 * Clears an impelmentation from the table corresponding to the provide
	 * requirements.
	 * 
	 * @param requirement
	 *            the requirements for which the implementation should be
	 *            cleared.
	 */
	void clearImplementation(final ServiceRequirement requirement) {
		ListEntry entry = entryMap.get(requirement);
		if (entry != null) {
			entry.impl = null;
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
		return sessionList.size();
	}

	public Object getValueAt(final int row, final int col) {
		ListEntry entry = sessionList.get(row);
		switch (col) {
		case 0:
			return entry.selectionMethod;
		case 1:
			return entry.requirement.getName();
		case 2:
			if (entry.impl == null) {
				return "-";
			} else {
				return entry.impl.getServiceDescriptionURI();
			}
		default:
			return null;
		}
	}

}
