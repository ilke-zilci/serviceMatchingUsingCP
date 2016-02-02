package uk.ac.ucl.cs.sse.dino.client.restaurant.impl;

import javax.swing.UIManager;

import uk.ac.ucl.cs.sse.dino.StandAloneBroker;

/**
 * Runs the restaurant search example using a local, stand alone broker.
 *
 */
public class RestaurantSearchExample {

	/**
	 * Runs this example.
	 * @param args ignored.
	 * @throws Exception if there is a problem running this example.
	 */
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

//		RemoteBrokerClient broker = new RemoteBrokerClient(
//				new URL(
//						"http://bozenka.cs.ucl.ac.uk:8080/axis/services/DinoBrokerService"));
		
//		RemoteBrokerClient broker = new RemoteBrokerClient(
//				new URL(
//						"http://localhost:8080/axis/services/DinoBrokerService"));
		
		StandAloneBroker broker = new StandAloneBroker(true);

		String sessionId = broker.startSession();
		new RestaurantSearchDisplay(broker, sessionId);
	}

}
