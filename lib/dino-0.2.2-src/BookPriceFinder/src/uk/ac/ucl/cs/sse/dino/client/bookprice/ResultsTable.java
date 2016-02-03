package uk.ac.ucl.cs.sse.dino.client.bookprice;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Table which displays the results of a book price search.
 * 
 */
public class ResultsTable extends JTable {
	private final DefaultTableModel model = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	/**
	 * Creates an empty table fo results.
	 * 
	 */
	public ResultsTable() {
		setModel(model);

		model.addColumn("ISBN");
		model.addColumn("Price");
	}

	/**
	 * Clears old results from the table.
	 * 
	 */
	public void clearTable() {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		repaint();
	}

	/**
	 * Adds a row of results to the table.
	 * 
	 * @param info
	 *            object which stores the information about a book.
	 */
	public void addRow(final BookInfo info) {
		double price = info.getPrice();
		if (Double.isNaN(price)) {
			Object[] data = { info.getTitle(), "Unknown" };
			model.addRow(data);
		} else {
			Object[] data = { info.getTitle(), "$" + info.getPrice() };
			model.addRow(data);
		}

	}
}
