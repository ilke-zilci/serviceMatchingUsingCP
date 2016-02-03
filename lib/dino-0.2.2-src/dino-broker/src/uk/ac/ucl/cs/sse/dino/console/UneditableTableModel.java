package uk.ac.ucl.cs.sse.dino.console;

import javax.swing.table.DefaultTableModel;

/**
 * Modifies the <tt>DefaultTableModel</tt> to make the table cells uneditable
 * and to make it possible to clear the table in a single operation.
 * 
 */
class UneditableTableModel extends DefaultTableModel {
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * Clears all rows from the table.
	 * 
	 */
	public void clearTable() {
		// Clear the rows, starting from the last, as indexes get changed
		// whenever a row is removed.
		for (int i = getRowCount() - 1; i >= 0; i--) {
			removeRow(i);
		}
	}
}
