package uk.ac.ucl.cs.sse.dino.client.bookprice;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Displays a splash screen for use while the DinoBroker is starting up.
 * 
 */
public class SplashScreen extends JDialog {
	/**
	 * Creates a splash screen dialog.
	 */
	SplashScreen() {
		setTitle("Starting Dino broker");

		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		contentPane.add(new JLabel("Starting dino broker..."));
		contentPane
				.add(new JLabel(
						"If you have not run Dino Broker before then you may need to wait"));
		contentPane.add(new JLabel("while the repository is built."));

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		contentPane.add(progressBar);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setLocationRelativeTo(null);

		pack();
		setSize(400, getHeight());

	}

	/**
	 * Displays the splash screen.
	 */
	void display() {
		setVisible(true);
		toFront();
	}

	/**
	 * Closes the splash screen and disposes of the dialog.
	 */
	void close() {
		setVisible(false);
		dispose();
	}

}
