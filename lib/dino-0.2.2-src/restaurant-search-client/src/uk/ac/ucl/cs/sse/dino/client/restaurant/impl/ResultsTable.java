package uk.ac.ucl.cs.sse.dino.client.restaurant.impl;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import uk.ac.ucl.cs.sse.dino.BrokerCommunicationException;
import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;

/**
 * Table which displays the results of a restaurant search. Information such as
 * restaurant name, cuisine, post code and price is shown.
 * 
 */
public class ResultsTable extends JTable {
	private final DefaultTableModel model = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private final static class PostcodeTableCellRenderer extends
			DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			super.getTableCellRendererComponent(table, value, isSelected,
					hasFocus, row, column);
			setValue("<html><u><font color=blue>" + getText()
					+ "</font></u><html>");
			return this;
		}
	}

	/**
	 * Creates an empty table fo results.
	 * 
	 * @param display
	 *            The <tt>RestaurantSearchDispaly</tt> which this table is
	 *            displayed in.
	 */
	public ResultsTable(final RestaurantSearchDisplay display) {
		setModel(model);
		setDefaultRenderer(String.class, new PostcodeTableCellRenderer());

		model.addColumn("Name");
		model.addColumn("Cusine");
		model.addColumn("Post Code");
		model.addColumn("Min Price");
		model.addColumn("Max Price");

		getColumnModel().getColumn(2).setCellRenderer(
				new PostcodeTableCellRenderer());

		setCellSelectionEnabled(true);

		ListSelectionModel selectionModel = getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (event.getValueIsAdjusting()) {
					return;
				}

				int row = getSelectedRow();
				int col = getSelectedColumn();

				if (row == -1 || col == -1) {
					return;
				}

				Object o = model.getValueAt(row, col);
				if (!(o instanceof String)) {
					return;
				}

				final String postcode = (String) o;
				if ("".equals(postcode)) {
					return;
				}

				Thread t = new Thread() {
					@Override
					public void run() {
						try {
							display.displayMap(postcode);
						} catch (DinoIdException e) {
							display.displayErrorDialog(e);
							e.printStackTrace();
						} catch (UnsupportedServiceException e) {
							display.displayErrorDialog(e);
							e.printStackTrace();
						} catch (ServiceInvocationException e) {
							display.displayErrorDialog(e);
							e.printStackTrace();
						} catch (BrokerCommunicationException e) {
							display.displayErrorDialog(e);
							e.printStackTrace();
						}
					}
				};
				t.start();
			}
		});
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
	 * @param restaurant
	 *            object which stores the information about a restaurant.
	 */
	public void addRow(final RestaurantInfo restaurant) {
		Object[] data = { restaurant.getName(), restaurant.getCusine(),
				restaurant.getPostcode(), restaurant.getMinPrice(),
				restaurant.getMaxPrice() };
		model.addRow(data);
	}
}
