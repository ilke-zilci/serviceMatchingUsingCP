package uk.ac.ucl.cs.sse.dino.client.restaurant.osmdisplay;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a Way (a series of linked line segments) on a map based on the
 * Open Street Map data format. Ways can be linear or if they are closed they
 * can represent areas such as lakes and parks.
 * 
 */
class Way {
	private static class LineSegment {
		private final double startLat, startLon, endLat, endLon;

		private LineSegment(final double startLat, final double startLon,
				final double endLat, final double endLon) {
			this.startLat = startLat;
			this.startLon = startLon;
			this.endLat = endLat;
			this.endLon = endLon;
		}
	}

	/**
	 * The type of way.
	 * 
	 */
	enum WayType {
		/** A trunk road (certain A roads). */
		TRUNK,
		/** A primary (A) road. */
		PRIMARY,
		/** A secondary (B) road. */
		SECONDARY,
		/** An unclassified road through a residential area. */
		RESIDENTIAL,
		/**
		 * An unclassified road through a non-residential area (e.g.
		 * unclassified country roads).
		 */
		UNCLASSIFIED,
		/** A rail line. */
		RAIL,
		/** An area of green space such as a park. */
		GREENSPACE,
		/** Type type of the way is not recognised by this class. */
		UNKNOWN
	}

	private static final Color DARK_GREEN = new Color(0, 150, 0);

	private WayType wayType = WayType.UNKNOWN;

	private final List<LineSegment> lineSegments = new LinkedList<LineSegment>();

	/**
	 * Create a way which has no line segments.
	 * 
	 */
	Way() {
	}

	/**
	 * Set the type of the way.
	 * 
	 * @param wayType
	 *            the type of the way.
	 */
	void setWayType(final WayType wayType) {
		this.wayType = wayType;
	}

	/**
	 * Adds a line segment to this way.
	 * 
	 * @param startLat
	 *            the latitude of the start of the line segment.
	 * @param startLon
	 *            the longitude of the start of the segment.
	 * @param endLat
	 *            the latitude of the end of the line segment.
	 * @param endLon
	 *            the longitude of the end of the line segment.
	 */
	void addSegment(final double startLat, final double startLon,
			final double endLat, final double endLon) {
		LineSegment segment = new LineSegment(startLat, startLon, endLat,
				endLon);
		lineSegments.add(segment);
	}

	/**
	 * Draws this way on the provided graphics context.
	 * 
	 * @param g2d
	 *            the non-null graphics context where the way should be drawn.
	 */
	void draw(final Graphics2D g2d) {
		Graphics2D g = (Graphics2D) g2d.create();

		switch (wayType) {
		case TRUNK:
			g.setColor(DARK_GREEN);
			drawLinearWay(g);
			break;
		case PRIMARY:
			g.setColor(Color.RED);
			drawLinearWay(g);
			break;
		case SECONDARY:
			g.setColor(Color.ORANGE);
			drawLinearWay(g);
			break;
		case RESIDENTIAL:
			g.setColor(Color.BLACK);
			drawLinearWay(g);
			break;
		case UNCLASSIFIED:
			g.setColor(Color.BLACK);
			drawLinearWay(g);
			break;
		case RAIL:
			g.setColor(Color.WHITE);
			drawLinearWay(g);
			g.setColor(Color.BLACK);
			float width = ((BasicStroke) g.getStroke()).getLineWidth();
			g.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 0, new float[] { 0.0002f }, 0));
			drawLinearWay(g);
			break;
		case GREENSPACE:
			g.setColor(Color.GREEN);
			drawArea(g);
			break;
		case UNKNOWN:
			return;
		}
	}

	private void drawLinearWay(final Graphics2D g) {
		for (LineSegment s : lineSegments) {
			g.draw(new Line2D.Double(s.startLon, s.startLat, s.endLon,
			s.endLat));
		}
	}

	private void drawArea(final Graphics2D g) {
		if (lineSegments.size() <= 0) {
			return;
		}
		
		GeneralPath path = new GeneralPath();
		path.moveTo((float)lineSegments.get(0).startLon, (float)lineSegments.get(0).startLat);
		for (LineSegment s : lineSegments) {
			path.lineTo((float)s.endLon, (float)s.endLat);
		}
		g.fill(path);
	}

	/**
	 * Returns true if this way represents an area and false if it does not.
	 * @return true if this way represents an area and false otherwise.
	 */
	boolean isArea() {
		return wayType == WayType.GREENSPACE;
	}
}
