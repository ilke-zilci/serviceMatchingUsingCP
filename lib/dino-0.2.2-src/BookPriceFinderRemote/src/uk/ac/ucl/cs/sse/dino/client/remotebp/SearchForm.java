package uk.ac.ucl.cs.sse.dino.client.remotebp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Form used for searching for book prices.
 * 
 */
public class SearchForm extends JPanel {
	private final String[] titles = {
			"The Time Traveler's Wife (Hardcover) US",
			"The Time Traveler's Wife (Paperback) US",
			"The Algebraist (Paperback) US", "Atonement (Paperback) UK",
			"Atonement (Paperback) US" };

	private final String[] ISBNs = { "1596921536", "015602943X", "1597800449",
			"0099429799", "038572179X" };

	private JComboBox titleBox = new JComboBox(titles);

	private JTextField searchField = new JTextField(ISBNs[0], 20);

	private JButton searchButton = new JButton("Search");

	/**
	 * Creates a search form for searching for book prices.
	 * 
	 * @param display
	 *            the display for the search results.
	 */
	public SearchForm(final BookPriceFinder display) {
		initComponents(display);
		addComponents();
	}

	private void initComponents(final BookPriceFinder display) {

		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchButton.doClick();
			}
		});

		titleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = titleBox.getSelectedIndex();
				searchField.setText(ISBNs[index]);
			}
		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bookISBN = searchField.getText();
				display.findBookPrice(bookISBN);
			}
		});
	}

	private void addComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(titleBox);

		JPanel searchRow = new JPanel();
		searchRow.add(new JLabel("ISBN"));
		searchRow.add(searchField);
		searchRow.add(searchButton);

		add(searchRow);
	}

}
