package uk.ac.ucl.cs.sse.dino.experiment;

import java.io.File;

import javax.swing.UIManager;

import uk.ac.ucl.cs.sse.dino.StandAloneBroker;

/**
 * Tests the stand alone broker using the restaurant search example.
 * 
 */
public class ExperimentRunner {
	/**
	 * The main method.
	 * 
	 * @param args
	 *            command line arguments. Ignored.
	 * @throws Exception
	 *             if any problem occurs which is not handled.
	 */
	public static void main(String[] args) throws Exception {
		DocumentGenerator.generate(100);
		runBroker(100);
		
		for (int i = 400; i <= 8000; i+=400) {
			DocumentGenerator.generate(i);
			runBroker(i);
		}
		System.exit(0);
	}
	
	private static void runBroker(int number) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		StandAloneBroker broker = new StandAloneBroker(false);

		DataLogger logger = new DataLogger(new File("results.txt"), number);
		broker.addServiceSelectionListener(logger);
		broker.addBrokerListener(logger);
		broker.addMatchmakerListener(logger);

		// registerCapDoc(broker);

		String sessionId = broker.startSession();

		broker
				.registerReqDoc(sessionId,
						"http://www.cs.ucl.ac.uk/staff/a.dingwall-smith/reqdoc/restaurant-reqdoc.xml");

		String[] gpsRequest = { "gps-location" };
		String[] userInputRequest = { "user-input-location" };

		for (int i = 0; i < 10; i++) {
			Thread.sleep((long)(Math.random() * 2000));
			broker.selectMode(sessionId, gpsRequest);
			Thread.sleep((long)(Math.random() * 2000));
			broker.selectMode(sessionId, userInputRequest);
		}
		
		broker.quitSession(sessionId);
	}

}
