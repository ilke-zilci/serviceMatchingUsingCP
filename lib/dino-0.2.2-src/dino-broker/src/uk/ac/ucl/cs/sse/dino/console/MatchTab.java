package uk.ac.ucl.cs.sse.dino.console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerImpl;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.doc.ServiceRequirement;
import uk.ac.ucl.cs.sse.dino.repository.ServiceImplementationRepositoryListener;
import uk.ac.ucl.cs.sse.dino.repository.ServiceInformationRepository;

/**
 * Tab which shows information about which requriements have been matched with
 * which services.
 * 
 */
final class MatchTab extends JPanel implements
		ServiceImplementationRepositoryListener {
	private final JSplitPane splitPane = new JSplitPane();
	private final JButton discoverButton = new JButton("Discover Services");

	private final ServiceInformationRepository repository;
	private final JTable requirementsTable = new JTable();
	private final JTable implementationTable = new JTable();

	private final RequirementsTableModel reqModel = new RequirementsTableModel();
	private final UneditableTableModel implModel = new UneditableTableModel();

	/**
	 * Creates a new match tab for the given broker and service repository whcih
	 * displays information on matches and provides a user interface for
	 * requesting the broker to discover new services.
	 * 
	 * @param broker
	 *            the broker to be used to discover new services.
	 * @param repository
	 *            the repository to display matches for.
	 */
	MatchTab(final DinoBrokerImpl broker,
			final ServiceInformationRepository repository) {
		this.repository = repository;

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		requirementsTable.setModel(reqModel);
		requirementsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		requirementsTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting()) {
							int row = requirementsTable.getSelectedRow();
							if (row == -1) {
								clearRequirements();
							} else {
								requirementSelected(row);
							}
							repaint();
						}
					}
				});

		implementationTable.setModel(implModel);
		implModel.addColumn("Implementation URI");
		implModel.addColumn("Repository File");
		implModel.addColumn("QoS URI");

		JScrollPane reqScroll = new JScrollPane(requirementsTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		splitPane.add(reqScroll, JSplitPane.TOP);
		splitPane.add(new JScrollPane(implementationTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), JSplitPane.BOTTOM);

		discoverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int row = requirementsTable.getSelectedRow();
				if (row != -1) {
					ServiceRequirement req = reqModel.getRequirement(row);
					if (req != null) {
						broker.discoverServiceImplementations(Collections
								.singletonList(req));
					}
				}
				repaint();
			}
		});

		setLayout(new BorderLayout());
		add(splitPane, BorderLayout.CENTER);
		add(discoverButton, BorderLayout.SOUTH);

	}

	public void addedMatch(final ServiceRequirement requirement,
			final ServiceImplementation implementation) {
		reqModel.addRequirement(requirement);
		repaint();
	}

	public void removedImplementation(final ServiceImplementation implementation) {
		matchesModified();
	}

	private void matchesModified() {
		reqModel.clearTable();
		implModel.clearTable();

		Set<ServiceRequirement> requirements = repository
				.getMatchedRequriements();
		for (ServiceRequirement req : requirements) {
			reqModel.addRequirement(req);
		}

		repaint();
	}

	private void requirementSelected(final int row) {
		implModel.clearTable();

		ServiceRequirement requirement = reqModel.getRequirement(row);
		Set<ServiceImplementation> impls = repository
				.getMatchingImplementations(requirement);
		for (ServiceImplementation impl : impls) {
			Object[] data = { impl.getServiceDescriptionURI(),
					impl.getlocalServiceDescription().getName(),
					impl.getQosURI() };
			implModel.addRow(data);
		}
	}

	private void clearRequirements() {
		implModel.clearTable();
	}
}
