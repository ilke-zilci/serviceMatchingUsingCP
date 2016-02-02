package uk.ac.ucl.cs.sse.dino.client.remotebp;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import uk.ac.ucl.cs.sse.dino.BrokerCommunicationException;
import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.SelectModeResponse;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.broker.DinoBroker;
import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryException;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;
import uk.ac.ucl.cs.sse.dino.remote.RemoteBrokerClient;
import uk.ac.ucl.cs.sse.dino.remote.RemoteBrokerException;

/**
 * GUI application which given an ISBN, searches for the price of the
 * corresponding book and dispalys the result in a table. Extreanal services are
 * accessed using Dino Broker.
 * 
 */
public class BookPriceFinder {
	// The main window of the application.
	private JFrame searchFrame = new JFrame("Book Price Search");

	// The portion of the GUI which allows a search query to be entered and
	// a search initiated.
	private SearchForm searchForm;

	// The portion of the GUI which displays the search results.
	private ResultsTable resultsTable = new ResultsTable();

	// The Dino Broker instance to use to access services.
	private DinoBroker broker;

	// This applications Dino session ID.
	private String sessionId;

	// The URL of the ontology based on Bibtex.
	private final String bibtexURL = "http://purl.org/net/nknouf/ns/bibtex#";

	// The URL of an ontology of general concepts used by Mindswap OWL-S
	// examples.
	private final String conceptsURL = "http://www.mindswap.org/2004/owl-s/concepts.owl#";

	/**
	 * Creates the Book Price Finder application.
	 * 
	 */
	public BookPriceFinder() {
		SplashScreen splash = new SplashScreen();
		splash.display();

		try {
			Properties props = new Properties();
			props.load(new FileInputStream("bookPriceFinder.properties"));
			String host = props.getProperty("broker-host");
			String portString = props.getProperty("broker-port", "8080");
			int port = Integer.parseInt(portString);
			String file = props.getProperty("broker-file",
					"/axis/services/DinoBrokerService");
			URL brokerURL = new URL("http", host, port, file);
			initBroker(brokerURL);

		} catch (MalformedURLException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (IOException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		}

		splash.close();
		initGUI();
	}

	private void initGUI() {
		searchForm = new SearchForm(this);

		JScrollPane resultsScroll = new JScrollPane(resultsTable);

		searchFrame.getContentPane().add(searchForm, BorderLayout.NORTH);
		searchFrame.getContentPane().add(resultsScroll, BorderLayout.CENTER);

		searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quitBrokerSession();
				System.exit(0);
			}
		});

		searchFrame.setSize(300, 300);
		searchFrame.pack();
		searchFrame.setVisible(true);
		searchFrame.toFront();
	}

	private void initBroker(URL brokerURL) {
		try {
			broker = new RemoteBrokerClient(brokerURL);

			// Start a Dino Broekr session, getting a session ID.
			sessionId = broker.startSession();

			// Register a Dino Reqdoc to the session. Each session can have one
			// ReqDoc which
			// identifies what services are required
			broker
					.registerReqDoc(
							sessionId,
							"http://www.cs.ucl.ac.uk/research/dino/reqdoc/bookPriceFinder.xml");

			// Select a mode. In the registered ReqDoc, the only mode available
			// is called "default",
			// so this one is selected.
			String[] requestedModes = { "default" };
			SelectModeResponse resp = broker.selectMode(sessionId,
					requestedModes);

			// Check that the mode was selected. If the mode could not be
			// selected then
			// the selected mdoe is set to null.
			if (resp.getSelectedMode() == null) {
				displayErrorDialog("Could not find a book price finder service.");
			}
		} catch (RemoteBrokerException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (BrokerCommunicationException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (DinoIdException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (DinoDocReadException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (ServiceDiscoveryException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		}
	}

	private void quitBrokerSession() {
		if (broker != null) {
			broker.quitSession(sessionId);
		}
	}

	/**
	 * Run the Book Price Finder application.
	 * 
	 * @param args
	 *            command line arguments which are ignored.
	 */
	public static void main(String[] args) {
		new BookPriceFinder();
	}

	/**
	 * Searches for the price of the book with the given ISBN and adds the
	 * result to the table of results.
	 * 
	 * @param bookISBN
	 *            an International Standard Book Number.
	 */
	void findBookPrice(String bookISBN) {
		try {
			// Set a parameter to represent the ISBN of the book.
			Param bookISBNParam = new Param(bibtexURL + "hasISBN",
					"string", bookISBN);

			// Create BookInfo parameter which is the input parameter to the
			// service.
			Param bookInfo = new Param("BookInfo", "class", bibtexURL
					+ "Book");

			// Add the ISBN as a property of the BookInfo parameter
			Param[] bookProps = { bookISBNParam };
			bookInfo.setProperties(bookProps);

			// Set the BookInfo parameter as the single input parameter to the
			// service.
			Param[] bookFinderIn = { bookInfo };

			// Invoke the service to find the price of the book. The service
			// which satisfies the service requriement named "priceFinder" in
			// the ReqDoc.
			// The returned values from the service are stored in "out".
			Param[] out = broker.invokeService(sessionId, "priceFinder",
					bookFinderIn).getOutputParameters();

			// Get the price parameter, which is a property of the returned
			// parameter.
			Param price = out[0];
			Param[] properties = price.getProperties();
			Param amountProp = null;
			for (Param p : properties) {
				if (p.getName().equals(conceptsURL + "amount")) {
					amountProp = p;
				}
			}

			try {
				// Parse the value of the price property as a double value and
				// construct a BookInfo object
				// which is added to the results table.
				double amount = Double.parseDouble(amountProp.getValue());
				BookInfo info = new BookInfo(bookISBN, amount);
				resultsTable.addRow(info);
			} catch (NumberFormatException e) {
				// If the value fo the price property was not a valid double
				// value then construct
				// a BookInfo object without a price and add it to the results.
				BookInfo info = new BookInfo(bookISBN);
				resultsTable.addRow(info);
			}
		} catch (DinoIdException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (UnsupportedServiceException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (ServiceInvocationException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (BrokerCommunicationException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		}
	}

	/**
	 * Displays an error dialog detailing the provided exception.
	 * 
	 * @param e
	 *            the exception for which details should be displayed.
	 */
	private void displayErrorDialog(Exception e) {
		JOptionPane.showMessageDialog(searchFrame, e,
				"Restaurant Search Error", JOptionPane.ERROR_MESSAGE);
	}

	private void displayErrorDialog(String message) {
		JOptionPane.showMessageDialog(searchFrame, message,
				"Book Price Finder Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Clears the results table.
	 * 
	 */
	void clearTable() {
		resultsTable.clearTable();
	}
}
