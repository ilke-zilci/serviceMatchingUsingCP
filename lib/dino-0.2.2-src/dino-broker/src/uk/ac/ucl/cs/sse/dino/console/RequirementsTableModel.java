package uk.ac.ucl.cs.sse.dino.console;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;

/**
 * Table model which stores a table of requirements and their associated URIs.
 */
class RequirementsTableModel extends AbstractTableModel {
	private List<ServiceRequirement> reqList = new ArrayList<ServiceRequirement>();
	private static final String[] columnNames = { "Requirement Name",
			"Requirement URI" };

	/**
	 * Adds a new requirement to the table.
	 * @param req the requriement to add.
	 */
	public void addRequirement(final ServiceRequirement req) {
		if (!reqList.contains(req)) {
			reqList.add(req);
			fireTableRowsInserted(reqList.size() - 1, reqList.size() - 1);
		}
	}
	
	/**
	 * Gets the requriement at the given row.
	 * @param row a valid table row.
	 * @return the service requriement at the given row.
	 */
	public ServiceRequirement getRequirement(final int row) {
		return reqList.get(row);
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return reqList.size();
	}

	@Override
	public String getColumnName(final int col) {
		return columnNames[col];
	}

	public Object getValueAt(final int row, final int col) {
		ServiceRequirement req = reqList.get(row);
		switch (col) {
		case 0:
			return req.getName();
		case 1:
			return req.getType();
		default:
			return null;
		}
	}

	/**
	 * Clears the whole table.
	 *
	 */
	public void clearTable() {
		if (!reqList.isEmpty()) {
			int lastRow = reqList.size() - 1;
			reqList.clear();
			fireTableRowsDeleted(0, lastRow);
		}
	}
}
