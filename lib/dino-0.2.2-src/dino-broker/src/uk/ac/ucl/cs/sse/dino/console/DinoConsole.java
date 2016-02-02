package uk.ac.ucl.cs.sse.dino.console;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import uk.ac.ucl.cs.sse.dino.broker.DinoBrokerImpl;
import uk.ac.ucl.cs.sse.dino.repository.ServiceInformationRepository;

/**
 * Console which shows information about a Dino Broker and its associated
 * repository and service selector.
 */
public final class DinoConsole extends JFrame {
	private final MatchTab matchTab;
	private final SessionTab sessionTab;
	private final SelectorTab selectorTab;

	private final ServiceInformationRepository repository;
	private final DinoBrokerImpl broker;

	/**
	 * Creates a Dino Broker console which provides information on the given
	 * service repository, broker and service selector.
	 * 
	 * @param repository
	 *            the repository to show information on.
	 * @param broker
	 *            the broker to show information on.
	 * @param selector
	 *            the service selector to show information on.
	 */
	public DinoConsole(final ServiceInformationRepository repository,
			final DinoBrokerImpl broker) {
		super("Dino Broker Console");

		this.repository = repository;
		this.broker = broker;

		matchTab = new MatchTab(broker, repository);
		sessionTab = new SessionTab();
		selectorTab = new SelectorTab();

		repository.addListener(matchTab);
		broker.addListener(sessionTab);
		repository.addServiceSelectionListener(selectorTab);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add(sessionTab, "Sessions");
		tabbedPane.add(matchTab, "Match Repository");
		tabbedPane.add(selectorTab, "ServiceSelection");

		setContentPane(tabbedPane);

		pack();
		setSize(800, 600);
		setVisible(true);
		sessionTab.setDividerLocation(0.5);
		selectorTab.setDividerLocation(0.5);
	}

	@Override
	public void dispose() {
		repository.removeListener(matchTab);
		broker.removeListener(sessionTab);
	}

}
