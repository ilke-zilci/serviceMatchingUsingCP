package uk.ac.ucl.cs.sse.dino.matchmaker;

import java.io.PrintWriter;

import org.xml.sax.Attributes;

/**
 * Interface for classes which store extracted service information. This
 * information is used in matchmaking to improve performance by not reading raw
 * service descriptions. This interface is written so that this information can
 * be read and written using this interface independently of the service
 * description format. This allows the extracted information to be serialised.
 * This is necessary because with a large number of service descriptions, start
 * up can take an extremely long time if every service description needs to be
 * parsed every time the broker is started (400 services can take several
 * hours). This interface is intended to be called while parsing the information
 * using a SAX parser.
 * 
 */
public interface ExtractedServiceInformation {
	/**
	 * Start element of the serialised extracted service information has been
	 * encountered by the SAX parser.
	 * 
	 * @param attributes
	 *            the attributes of the parent service description element.
	 */
	public void startParentElement(Attributes attributes);

	/**
	 * The end element of the serialised extracted service information has been
	 * encountered by the SAX parser.
	 * 
	 */
	public void endParentElement();

	/**
	 * A child element of the extracted service information element has been encountered.
	 * @param name the name of the child element.
	 * @param attributes the SAX attributes of the child element.
	 */
	public void startChildElementInfo(String name, Attributes attributes);

	/**
	 * A child element of the extracted service information element has ended.
	 * @param name the name of the child element.
	 */
	public void endChildElement(String name);

	/**
	 * Writes the extracted service information as XML to the provided writer.
	 * @param writer the writer to write the XML data to.
	 */
	public void writeInformation(PrintWriter writer);
}
