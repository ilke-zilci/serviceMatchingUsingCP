package uk.ac.ucl.cs.sse.dino.repository;

import java.io.PrintWriter;
import java.net.URI;

import org.xml.sax.Attributes;

import uk.ac.ucl.cs.sse.dino.matchmaker.ExtractedServiceInformation;


/**
 * Stores general information about a service which is common to all description
 * formats. This includes the URI of the original service description and the
 * names of files in the local repository.
 * 
 */
class GeneralServiceInformation implements ExtractedServiceInformation {
	private URI serviceDescriptionURI;

	private String fileName;

	private String qosFileName;

	private final ExtractedServiceInformation specificServiceInformation;

	/**
	 * Creates a GeneralServiceInformation object which uses the specified
	 * object to store format specific information.
	 * 
	 * @param specificServiceInformation
	 *            object to use for format specific information.
	 */
	public GeneralServiceInformation(
			final ExtractedServiceInformation specificServiceInformation) {
		assert specificServiceInformation != null;
		
		this.specificServiceInformation = specificServiceInformation;
	}

	/**
	 * Creates a GeneralServiceInformation object which uses the specified
	 * object to store format specific information. Sets the values of the
	 * service description URI and the location of the local cached service
	 * description.
	 * 
	 * @param specificServiceInformation
	 *            object to use for format specific information.
	 * @param serviceDescriptionURI
	 *            URI at which the service description is located.
	 * @param fileName
	 *            file where service description document is cached.
	 */
	public GeneralServiceInformation(
			final ExtractedServiceInformation specificServiceInformation,
			final URI serviceDescriptionURI, final String fileName) {
		this(specificServiceInformation);
		this.serviceDescriptionURI = serviceDescriptionURI;
		this.fileName = fileName;
	}

	/**
	 * Creates a GeneralServiceInformation object which uses the specified
	 * object to store format specific information. Sets the values of the
	 * service description URI and the location of the local cached service
	 * description and cached QoS file.
	 * 
	 * @param specificServiceInformation
	 *            object to use for format specific information.
	 * @param serviceDescriptionURI
	 *            URI at which the service description is located.
	 * @param fileName
	 *            file where service description document is cached.
	 * @param qosFileName
	 *            file where QoS document is cached.
	 */
	public GeneralServiceInformation(
			final ExtractedServiceInformation specificServiceInformation,
			final URI serviceDescriptionURI, final String fileName,
			final String qosFileName) {
		this(specificServiceInformation, serviceDescriptionURI, fileName);
		this.qosFileName = qosFileName;
	}

	public void endChildElement(final String name) {
		specificServiceInformation.endChildElement(name);
	}

	public void endParentElement() {
		specificServiceInformation.endParentElement();
	}

	public void startChildElementInfo(final String name,
			final Attributes attributes) {
		specificServiceInformation.startChildElementInfo(name, attributes);
	}

	public void startParentElement(final Attributes attributes) {
		fileName = attributes.getValue("fileName");
		qosFileName = attributes.getValue("qosFileName");
		serviceDescriptionURI = URI.create(attributes.getValue("uri"));
		specificServiceInformation.startParentElement(attributes);

	}

	public void writeInformation(final PrintWriter writer) {
		writer.println("<serviceInformation fileName=\"" + fileName + "\"");
		if (qosFileName != null) {
			writer.println("                    qosFileName=\"" + qosFileName
					+ "\"");
		}
		writer.println("                    uri=\"" + serviceDescriptionURI
				+ "\"");

		specificServiceInformation.writeInformation(writer);

		writer.println("</serviceInformation>");
	}

	/**
	 * The file name of the service description file. This string does not
	 * contain the path to the repository, only the file name within the
	 * repository.
	 * 
	 * @return the service description file name.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * The file name of the QoS file. This string does not contain the path to
	 * the repository, only the file name within the repository.
	 * 
	 * @return the QoS file name.
	 */
	public String getQosFileName() {
		return qosFileName;
	}

	/**
	 * Gets the URI of the service description.
	 * 
	 * @return the URI of the service description.
	 */
	public URI getServiceDescriptionURI() {
		return serviceDescriptionURI;
	}

}
