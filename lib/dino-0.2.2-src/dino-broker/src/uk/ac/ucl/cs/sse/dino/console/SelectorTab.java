package uk.ac.ucl.cs.sse.dino.console;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.selector.ServiceSelectionListener;

/**
 * Tab which displays information about service selection.
 * 
 */
class SelectorTab extends JSplitPane implements ServiceSelectionListener {
	private final JTable selectionTable = new JTable();
	private final SelectionTableModel selectionTableModel = new SelectionTableModel();
	private final JTable selectionDetailTable = new JTable();

	/**
	 * Creates a new selector tab.
	 */
	SelectorTab() {
		setOrientation(JSplitPane.VERTICAL_SPLIT);

		selectionTable.setModel(selectionTableModel);
		selectionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add(new JScrollPane(selectionTable), JSplitPane.TOP);
		add(new JScrollPane(selectionDetailTable), JSplitPane.BOTTOM);
	}

	public void implementationEvaluated(final ServiceRequirement requirement,
			final ServiceImplementation impl, final String info) {

	}

	public void serviceSelectionComplete(final ServiceRequirement requirement,
			final List<ServiceImplementation> orderedImplementations,
			final long selectionTime) {
		if (orderedImplementations.size() > 0) {
			selectionTableModel.setImplementation(requirement,
					orderedImplementations.get(0));
			repaint();
		}
	}

	public void serviceSelectionStarted(final ServiceRequirement requirement,
			final String selectionMethod) {
		selectionTableModel.addSelection(requirement, selectionMethod);
		repaint();
	}

}
