package uk.ac.ucl.cs.sse.dino.client.restaurant.osmdisplay;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Panel which displays a map and any associated markers.
 * 
 */
public class OSMDisplay extends JPanel {
	private static class Marker {
		private final double lat, lon;

		private Marker(final double lat, final double lon) {
			this.lat = lat;
			this.lon = lon;
		}
	}

	private OSMMap map;
	private double lat1, lat2, lon1, lon2;
	private List<Marker> markers = new LinkedList<Marker>();
	private Image markerImage;

	/**
	 * Creates a blank map display.
	 * 
	 */
	public OSMDisplay() {
		setPreferredSize(new Dimension(600, 500));
		try {
			markerImage = ImageIO.read(new File("pin.png"));
		} catch (IOException e) {
			// The image didn't load so map pointers probably won't display
			// but since some partial functionality will remain we just ignore
			// this exception.
		}
	}

	/**
	 * Displays the map stored in the specified file.
	 * 
	 * @param mapFile
	 *            non-null file which exists on the file system and stores XML
	 *            data in Open Street Map map data format.
	 * @throws MapDisplayException if there is a problem displaying the map.
	 */
	public synchronized void displayMap(final File mapFile) throws MapDisplayException {
		OSMDataParser parser = new OSMDataParser();
		try {
			map = parser.parse(mapFile);
		} catch (SAXException e) {
			throw new MapDisplayException("Could not display map.", e);
		} catch (IOException e) {
			throw new MapDisplayException("Could not display map.", e);
		} catch (ParserConfigurationException e) {
			throw new MapDisplayException("Could not display map.", e);
		}
	}

	/**
	 * Displays the map provided by the provided reader.
	 * 
	 * @param mapReader
	 *            non-null reader which provides XML
	 *            data in Open Street Map map data format.
	 * @throws MapDisplayException if there is a problem displaying the map.
	 */
	public synchronized void displayMap(final Reader mapReader) throws MapDisplayException {
		OSMDataParser parser = new OSMDataParser();
		try {
			map = parser.parse(mapReader);
		} catch (ParserConfigurationException e) {
			throw new MapDisplayException("Could not display map.", e);
		} catch (SAXException e) {
			throw new MapDisplayException("Could not display map.", e);
		} catch (IOException e) {
			throw new MapDisplayException("Could not display map.", e);
		}
	}

	/**
	 * Sets the area of the map which should be displayed.
	 * @param lat1 latitude of bottom left corner of display area.
	 * @param lon1 longitude of bottom left corner of display area.
	 * @param lat2 latitude of top right corner of display area.
	 * @param lon2 longitude of top right corner of display area.
	 */
	public synchronized void setDisplayBounds(final double lat1, final double lon1,
			final double lat2, final double lon2) {
		this.lat1 = lat1;
		this.lat2 = lat2;
		this.lon1 = lon1;
		this.lon2 = lon2;
	}

	@Override
	public synchronized void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		// AffineTransform imgTransform = new AffineTransform();
		// g2d.drawImage(markerImage, imgTransform, null);

		final AffineTransform transform = new AffineTransform();
		transform.translate(0, getHeight());
		transform
				.scale(getWidth() / (lon2 - lon1), -getWidth() / (lat2 - lat1));
		transform.translate(-lon1, -lat1);
		g2d.setTransform(transform);
		g2d.setStroke(new BasicStroke(0.0002f));
		map.draw(g2d);

		for (Marker m : markers) {
			AffineTransform imgTransform = new AffineTransform();
			imgTransform.setToTranslation(m.lon, m.lat);
			imgTransform.scale(0.00003, -0.00003);
			g2d.drawImage(markerImage, imgTransform, null);
		}
	}

	/**
	 * Adds a marker to this map display at the specified position.
	 * @param lat latitude of the marker.
	 * @param lon longitude of the marker.
	 */
	public synchronized void addMarker(double lat, double lon) {
		markers.add(new Marker(lat, lon));
	}

	/**
	 * Test main method for this class.
	 * 
	 * @param args
	 *            ignored.
	 * @throws Exception
	 *             if there are any problems.
	 */
	public static void main(String[] args) throws Exception {

		OSMDisplay mapDisplay = new OSMDisplay();
		JFrame frame = new JFrame();
		frame.setContentPane(mapDisplay);

		// mapDisplay.setDisplayBounds(51.3, -0.18, 51.5, 0.0);
		// mapDisplay.setDisplayBounds(40.0d, -300.0d, 44.0d, -30.0d);

		mapDisplay.setDisplayBounds(51.41, -0.15, 51.45, -0.11);

		// OSMMap map = new OSMMap();
		// Way w1 = new Way();
		// w1.addSegment(51.4, -0.16, 51.43, -0.10);
		// w1.addSegment(40.0d, -200.0d, 42.0d, -50.0d);
		// map.addWay(w1);

		mapDisplay.displayMap(new File("map.xml"));

		mapDisplay.addMarker(51.42, -0.13);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);
	}

}
