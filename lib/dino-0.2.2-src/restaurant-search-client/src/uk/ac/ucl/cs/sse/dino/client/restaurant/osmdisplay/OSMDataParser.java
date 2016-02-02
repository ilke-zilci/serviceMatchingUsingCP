package uk.ac.ucl.cs.sse.dino.client.restaurant.osmdisplay;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX Handler for parsing Open Street Map XML data.
 * 
 */
class OSMDataParser extends DefaultHandler {
	private static final class Node {
		private final double lat;
		private final double lon;

		private Node(final double lat, final double lon) {
			this.lat = lat;
			this.lon = lon;
		}
	}

	private static final class Segment {
		private final Node start;
		private final Node end;

		private Segment(final Node start, final Node end) {
			this.start = start;
			this.end = end;
		}
	}

	private final Map<String, Node> nodeIdMap = new HashMap<String, Node>();
	private final Map<String, Segment> segmentIdMap = new HashMap<String, Segment>();
	private final OSMMap map = new OSMMap();

	private Way currentWay;

	/**
	 * Parses the specified Open Street Map XML map data file.
	 * 
	 * @param mapFile
	 *            the non-null file which exists on the file system and stores
	 *            XML data in the Open Street Map map data format.
	 * @return an object which represents the same map as the map file.
	 * @throws SAXException
	 *             if the SAX parser is unable to parse the XML file.
	 * @throws IOException
	 *             if there is an IO problem reading the file.
	 * @throws ParserConfigurationException
	 *             if the SAX parser cannot be configured properly.
	 */
	OSMMap parse(File mapFile) throws SAXException, IOException,
			ParserConfigurationException {
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		SAXParser parser = saxFactory.newSAXParser();
		parser.parse(mapFile, this);

		return map;
	}

	/**
	 * Parses the specified Open Street Map XML data from the provided reader.
	 * 
	 * @param mapReader
	 *            non-null reader which provides XML data in the Open Street Map
	 *            map data format.
	 * @return an object which represents the same map as the data from the reader.
	 * @throws SAXException
	 *             if the SAX parser is unable to parse the XML file.
	 * @throws IOException
	 *             if there is an IO problem reading the file.
	 * @throws ParserConfigurationException
	 *             if the SAX parser cannot be configured properly.
	 */
	OSMMap parse(final Reader mapReader) throws ParserConfigurationException,
			SAXException, IOException {
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		SAXParser parser = saxFactory.newSAXParser();
		parser.parse(new InputSource(mapReader), this);

		mapReader.close();

		return map;
	}

	@Override
	public void startElement(final String uri, final String localName,
			final String qName, final Attributes attributes)
			throws SAXException {
		if ("node".equals(qName)) {
			parseNode(attributes);
		} else if ("segment".equals(qName)) {
			parseSegment(attributes);
		} else if ("way".equals(qName)) {
			parseWay(attributes);
		} else if ("seg".equals(qName)) {
			parseSeg(attributes);
		} else if ("tag".equals(qName)) {
			parseTag(attributes);
		}
	}

	private void parseNode(final Attributes attributes) throws SAXException {
		String id = attributes.getValue("id");
		if (id == null) {
			throw new SAXException("Missing 'id' attribute on node element");
		}

		String latString = attributes.getValue("lat");
		if (latString == null) {
			throw new SAXException(
					"Missing 'id' attribute on node element with id: " + id);
		}

		String lonString = attributes.getValue("lon");
		if (lonString == null) {
			throw new SAXException(
					"Missing 'lon' attribute on node element with id: " + id);
		}

		try {
			double lat = Double.parseDouble(latString);
			double lon = Double.parseDouble(lonString);
			Node n = new Node(lat, lon);
			nodeIdMap.put(id, n);
		} catch (NumberFormatException e) {
			throw new SAXException(
					"lat and lon attributes of node element (id: " + id
							+ ") must both be numbers");
		}
	}

	private void parseSegment(final Attributes attributes) throws SAXException {
		String id = attributes.getValue("id");
		if (id == null) {
			throw new SAXException("Missing 'id' attribute on segment element");
		}

		String fromId = attributes.getValue("from");
		if (fromId == null) {
			throw new SAXException(
					"Missing 'from' attribute on segment element with id: "
							+ id);
		}

		String toId = attributes.getValue("to");
		if (toId == null) {
			throw new SAXException(
					"Missing 'to' attribute on segment element with id: " + id);
		}

		Node start = nodeIdMap.get(fromId);
		if (start == null) {
			throw new SAXException("'from' attribute on segment element (id: "
					+ id + ") must reference a node id.");
		}

		Node end = nodeIdMap.get(toId);
		if (end == null) {
			throw new SAXException("'to' attribute on segment element (id: "
					+ id + ") must reference a node id.");
		}

		Segment s = new Segment(start, end);
		segmentIdMap.put(id, s);
	}

	private void parseWay(final Attributes attributes) {
		currentWay = new Way();
		map.addWay(currentWay);
	}

	private void parseSeg(final Attributes attributes) throws SAXException {
		String id = attributes.getValue("id");
		if (id == null) {
			throw new SAXException("Missing 'id' attribute on seg element");
		}

		Segment segment = segmentIdMap.get(id);
		if (segment == null) {
			throw new SAXException("'id' attribute on seg element (id: " + id
					+ ") must reference a segment id.");
		}

		currentWay.addSegment(segment.start.lat, segment.start.lon,
				segment.end.lat, segment.end.lon);
	}

	private void parseTag(final Attributes attributes) throws SAXException {
		if (currentWay != null) {
			String key = attributes.getValue("k");
			String value = attributes.getValue("v");

			if ("highway".equals(key)) {
				if ("trunk".equals(value)) {
					currentWay.setWayType(Way.WayType.TRUNK);
				} else if ("primary".equals(value)) {
					currentWay.setWayType(Way.WayType.PRIMARY);
				} else if ("secondary".equals(value)) {
					currentWay.setWayType(Way.WayType.SECONDARY);
				} else if ("residential".equals(value)) {
					currentWay.setWayType(Way.WayType.RESIDENTIAL);
				} else if ("unclassified".equals(value)) {
					currentWay.setWayType(Way.WayType.UNCLASSIFIED);
				} else {
					currentWay.setWayType(Way.WayType.UNKNOWN);
				}
			} else if ("railway".equals(key)) {
				if ("rail".equals(value)) {
					currentWay.setWayType(Way.WayType.RAIL);
				}
			} else if ("leisure".equals(key)) {
				if ("common".equals(value)) {
					currentWay.setWayType(Way.WayType.GREENSPACE);
				}
			}
		}
	}

	@Override
	public void endElement(final String uri, final String localName,
			final String qName) throws SAXException {
		if ("way".equals(qName)) {
			currentWay = null;
		}
	}
}
