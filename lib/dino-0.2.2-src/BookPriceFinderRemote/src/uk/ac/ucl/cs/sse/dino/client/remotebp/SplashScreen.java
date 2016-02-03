package uk.ac.ucl.cs.sse.dino.client.remotebp;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Displays a splash screen for use while connecting to the DinoBroker.
 *
 */
public class SplashScreen extends JDialog {
	/**
	 * Creates a splash screen dialog.
	 */
	SplashScreen() {
		setTitle("Connecting to Dino Broker");
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		contentPane.add(new JLabel("Connecting to Dino Broker..."));
		
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
		setSize(200, getHeight());

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
