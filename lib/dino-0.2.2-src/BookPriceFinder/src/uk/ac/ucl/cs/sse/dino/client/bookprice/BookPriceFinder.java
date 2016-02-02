package uk.ac.ucl.cs.sse.dino.client.bookprice;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.SelectModeResponse;
import uk.ac.ucl.cs.sse.dino.StandAloneBroker;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.broker.LocalDinoBroker;
import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryException;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;
import uk.ac.ucl.cs.sse.dino.repository.DinoSetupException;

/**
 * GUI application which given an ISBN, searches for the price of the
 * corresponding book and displays the result in a table. External services are
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
	private LocalDinoBroker broker;

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
		initBroker();
		splash.close();
		initGUI();
	}

	private void initGUI() {
		searchForm = new SearchForm(this);

		JScrollPane resultsScroll = new JScrollPane(resultsTable);

		searchFrame.getContentPane().add(searchForm, BorderLayout.NORTH);
		searchFrame.getContentPane().add(resultsScroll, BorderLayout.CENTER);

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

	private void initBroker() {
		try {
			// Create a DinoBroker instance. The StandAloneBroker class
			// impelments the DinoBroekr interface
			// and sets up all the classes which the Dino Broker depends on.
			broker = new StandAloneBroker(true);

			// Start a Dino Broekr session, getting a session ID.
			sessionId = broker.startSession();

			// Register a Dino Reqdoc to the session. Each session can have one
			// ReqDoc which
			// identifies what services are required
			broker
					.registerReqDoc(
							sessionId,
							"reqdoc.xml");

			// Select a mode. In the registered ReqDoc, the onl;y mdoe available
			// is called "default",
			// so this one is selected.
			String[] requestedModes = { "default" };
			SelectModeResponse resp = broker.selectMode(sessionId,
					requestedModes);

			// Check that the mode was selected. If the mdoe could not be
			// selected then
			// the selected mdoe is set to null.
			if (resp.getSelectedMode() == null) {
				displayErrorDialog("Could not find a book price finder service.");
			}
		} catch (DinoSetupException e) {
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
			Param bookISBNParam = new Param(bibtexURL + "hasISBN", "string",
					bookISBN);

			// Create BookInfo parameter which is the input parameter to the
			// service.
			Param bookInfo = new Param("BookInfo", "class", bibtexURL + "Book");

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
			Param amountProp = price.getProperty(conceptsURL + "amount");

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
