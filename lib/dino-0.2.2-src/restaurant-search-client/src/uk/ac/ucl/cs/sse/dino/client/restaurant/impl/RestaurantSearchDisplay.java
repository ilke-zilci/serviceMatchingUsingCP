package uk.ac.ucl.cs.sse.dino.client.restaurant.impl;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import uk.ac.ucl.cs.sse.dino.BrokerCommunicationException;
import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.InvocationResponse;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.SelectModeResponse;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.broker.DinoBroker;
import uk.ac.ucl.cs.sse.dino.client.restaurant.osmdisplay.MapDisplayException;
import uk.ac.ucl.cs.sse.dino.client.restaurant.osmdisplay.OSMDisplay;
import uk.ac.ucl.cs.sse.dino.discovery.ServiceDiscoveryException;
import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;

/**
 * Example application which performs a location sensitive search for
 * restaurants using a Dino Broker.
 * 
 */
public class RestaurantSearchDisplay {
	private JFrame searchFrame = new JFrame("Restaurant Search");

	private SearchForm searchForm;

	private ResultsTable resultsTable;

	private final DinoBroker broker;
	private final String sessionId;

	/**
	 * Creates the restaurant search application.
	 * 
	 * @param broker
	 *            the broker to use to access services.
	 * @param sessionId
	 *            the session ID to use with the Dino Broker.
	 */
	public RestaurantSearchDisplay(final DinoBroker broker,
			final String sessionId) {
		this.broker = broker;
		this.sessionId = sessionId;

		try {
			broker
					.registerReqDoc(sessionId,
							"http://www.cs.ucl.ac.uk/research/dino/reqdoc/restaurant-reqdoc.xml");

			String[] requestedModes = { "gps-location", "user-input-location" };
			String mode = null;
			SelectModeResponse resp = broker.selectMode(sessionId,
					requestedModes);

			mode = resp.getSelectedMode();

			boolean userInputLocation = (requestedModes[1].equals(mode));

			searchForm = new SearchForm(broker, sessionId, this,
					userInputLocation);
			resultsTable = new ResultsTable(this);

			JScrollPane resultsScroll = new JScrollPane(resultsTable);

			searchFrame.getContentPane().add(searchForm, BorderLayout.NORTH);
			searchFrame.getContentPane()
					.add(resultsScroll, BorderLayout.CENTER);

			searchFrame.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowClosing(WindowEvent arg0) {
					broker.quitSession(sessionId);
					System.exit(0);
				}

			});

			searchFrame.setSize(300, 300);
			searchFrame.pack();
			searchFrame.setVisible(true);
			searchFrame.toFront();
		} catch (DinoIdException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (DinoDocReadException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (ServiceDiscoveryException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		} catch (BrokerCommunicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays an error dialog detailing the provided exception.
	 * 
	 * @param e
	 *            the exception for which details should be displayed.
	 */
	void displayErrorDialog(Exception e) {
		JOptionPane.showMessageDialog(searchFrame, e,
				"Restaurant Search Error", JOptionPane.ERROR_MESSAGE);
	}
	
	void displayErrorDialog(String msg) {
		JOptionPane.showMessageDialog(searchFrame, msg,
				"Restaurant Search Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Adds the provided information to the results table.
	 * 
	 * @param restaurant
	 *            the information to add.
	 */
	void addRow(RestaurantInfo restaurant) {
		resultsTable.addRow(restaurant);

	}

	/**
	 * Clears the results table.
	 * 
	 */
	void clearTable() {
		resultsTable.clearTable();
	}

	/**
	 * Pops up a window which displays a map around the specified postcode with
	 * a marker at the position of that postcode.
	 * 
	 * @param postcode
	 *            non-null string which is a valid UK postcode.
	 * @throws DinoIdException
	 *             if the session ID is not valid.
	 * @throws UnsupportedServiceException
	 *             if one of the services used is not supported in the current
	 *             mode.
	 * @throws ServiceInvocationException
	 *             if there is a problem invoking one of the service used.
	 * @throws BrokerCommunicationException
	 *             if there is a problem communicating with a remote broker.
	 */
	void displayMap(String postcode) throws DinoIdException,
			UnsupportedServiceException, ServiceInvocationException,
			BrokerCommunicationException {
		JFrame f = new JFrame("Map of " + postcode);
		f.getContentPane().add(new JLabel("Loading map..."));
		f.pack();
		f.setSize(400, 400);
		f.setVisible(true);

		Param postcodeParam = new Param("Postcode", "string", postcode);
		Param[] params = { postcodeParam };
		InvocationResponse resp = broker.invokeService(sessionId, "postcode-locator",
				params);
				
		if (resp == null) {
			displayErrorDialog("Unable to locate postcode.");
			f.dispose();
			return;
		}
		Param[] out = resp.getOutputParameters();

		Param[] props = out[0].getProperties();
		double lat = Double.parseDouble(props[0].getValue());
		double lon = Double.parseDouble(props[1].getValue());

		out = broker.invokeService(sessionId, "street-map", out)
				.getOutputParameters();

		String xmlDoc = out[0].getValue();

		Reader reader = new StringReader(xmlDoc);

		OSMDisplay display = new OSMDisplay();

		try {
			display.displayMap(reader);
			display.setDisplayBounds(lat - 0.02, lon - 0.02, lat + 0.02,
					lon + 0.02);
			display.addMarker(lat, lon);
			f.setContentPane(display);
			f.invalidate();
			f.validate();
		} catch (MapDisplayException e) {
			displayErrorDialog(e);
			e.printStackTrace();
		}
	}
}
