package uk.ac.ucl.cs.sse.dino.client.restaurant.impl;

import java.net.URL;

import javax.swing.UIManager;

import uk.ac.ucl.cs.sse.dino.remote.RemoteBrokerClient;

/**
 * Runs the restaurant search example using a remote broker.
 *
 */
public class RemoteRestaurantSearchExample {
	/**
	 * Runs this example.
	 * @param args ignored.
	 * @throws Exception if there is a problem running this example.
	 */
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		RemoteBrokerClient broker = new RemoteBrokerClient(
				new URL(
						"http://bozenka.cs.ucl.ac.uk:8080/axis/services/DinoBrokerService"));
		

		String sessionId = broker.startSession();
		new RestaurantSearchDisplay(broker, sessionId);
	}
}
