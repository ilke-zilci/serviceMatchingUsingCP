package uk.ac.ucl.cs.sse.dino.doc;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import uk.ac.ucl.cs.sse.dino.doc.qos.CapDocQosParameter;
import uk.ac.ucl.cs.sse.dino.doc.qos.QosDoc;
import uk.ac.ucl.cs.sse.dino.doc.qos.QosDocParser;

/**
 * This class represents the implementation of a service requirement. An
 * instance of this class is created when a service is discovered. This class is
 * responsible for invoking the service implementation by translating service
 * invocations using OWL-S into invocations of the service implementation. This
 * is done using the mindswap OWL-S API.
 */
public class ServiceImplementation {
	private static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
			.newInstance();

	private final URI serviceURI;

	private URI qosURI = null;

	private File localServiceDescription;

	private ServiceDescription serviceDescription;

	private final QosDoc qosDoc;

	/**
	 * Create a service implementation with the given service description URI.
	 * 
	 * @param serviceDescriptionURI
	 *            URI which provides a functional description of the service.
	 */
	public ServiceImplementation(final URI serviceDescriptionURI) {
		if (serviceDescriptionURI == null) {
			throw new NullPointerException(
					"Null parameter: serviceDescriptionURI");
		}

		this.serviceURI = serviceDescriptionURI;
		this.qosDoc = new QosDoc();
	}

	/**
	 * Creates a service implementation with the given service description URI
	 * which is stored in the specified file.
	 * 
	 * @param serviceDescriptionURI
	 *            URI which provides a functional description of the service.
	 * @param localServiceDescription
	 *            location of the cached functional description.
	 */
	public ServiceImplementation(final URI serviceDescriptionURI,
			final File localServiceDescription) {
		this(serviceDescriptionURI);

		if (localServiceDescription == null) {
			throw new NullPointerException(
					"Null parameter: localServiceDescription");
		}

		this.localServiceDescription = localServiceDescription;
	}

	/**
	 * Creates a service implementation which is described by an OWL-S document
	 * at the provided URL.
	 * 
	 * @param serviceDescriptionURI
	 *            the non-null location of the OWL-S description of the service.
	 * @param qosDoc
	 *            the quality of service document associated with this service
	 *            implemetnation.
	 */
	public ServiceImplementation(final URI serviceDescriptionURI,
			final QosDoc qosDoc) {
		// Preconditions
		if (serviceDescriptionURI == null) {
			throw new NullPointerException(
					"Null parameter: serviceDescriptionURI");
		}
		if (qosDoc == null) {
			throw new NullPointerException("Null parameter: qosDoc");
		}

		this.serviceURI = serviceDescriptionURI;
		this.qosDoc = qosDoc;
	}

	/**
	 * Creates a service implementation which is described by an OWL-S document
	 * at the provided URL and the QosDoc at the provided URI. The service
	 * description is cached at the given file.
	 * 
	 * @param serviceDescriptionURI
	 *            URI of the functional service description.
	 * @param qosRef
	 *            the URI of the QosDoc.
	 * @param localServiceDescription
	 *            location of the cached functional description.
	 * @throws DinoDocReadException
	 *             if there is a problem understanding either of the referenced
	 *             documents.
	 */
	public ServiceImplementation(final URI serviceDescriptionURI,
			final URI qosRef, final File localServiceDescription)
			throws DinoDocReadException {
		this(serviceDescriptionURI, qosRef);
		this.localServiceDescription = localServiceDescription;

	}

	/**
	 * Creates a service implementation which is described by an OWL-S document
	 * at the provided URL and the QosDoc at the provided URI.
	 * 
	 * @param serviceDescriptionURI
	 *            URI of the functional service description.
	 * @param qosRef
	 *            the URI of the QosDoc.
	 * @throws DinoDocReadException
	 *             if there is a problem understanding the QoS Doc.
	 */
	public ServiceImplementation(final URI serviceDescriptionURI,
			final URI qosRef) throws DinoDocReadException {
		// Preconditions
		if (serviceDescriptionURI == null) {
			throw new NullPointerException(
					"Null parameter: serviceDescriptionURI");
		}
		if (qosRef == null) {
			throw new NullPointerException("Null parameter: qosRef");
		}

		this.qosURI = qosRef;

		this.serviceURI = serviceDescriptionURI;

		if (qosRef.toString().equals("")) {
			// create an empty Qos doc.
			qosDoc = new QosDoc();
		} else {
			Document document;
			try {
				document = documentBuilderFactory.newDocumentBuilder().parse(
						qosRef.toString());
				Element qosDocElement = document.getDocumentElement();
				qosDoc = QosDocParser.parseQosCapabilities(qosDocElement);
			} catch (SAXException e) {
				throw new DinoDocReadException(
						"Failed to read QoSDoc for service "
								+ serviceDescriptionURI, e);
			} catch (IOException e) {
				throw new DinoDocReadException(
						"Failed to read QoSDoc for service "
								+ serviceDescriptionURI, e);
			} catch (ParserConfigurationException e) {
				throw new DinoDocReadException(
						"Failed to read QoSDoc for service "
								+ serviceDescriptionURI, e);
			}
		}
	}

	/**
	 * Gets the URI which provides the functional description of this service.
	 * 
	 * @return the URI of a service description.
	 */
	public URI getServiceDescriptionURI() {
		return serviceURI;
	}

	/**
	 * Sets the location of the cached functional description for this
	 * impelmentation.
	 * 
	 * @param localServiceDescription
	 *            the location of the cached functional description.
	 */
	public void setLocalServiceDescription(final File localServiceDescription) {
		this.localServiceDescription = localServiceDescription;
	}

	/**
	 * Gets the location of the cached functional description for this
	 * implementation.
	 * 
	 * @return the location of the cached functional description or null if
	 *         there is no local functional description.
	 */
	public File getlocalServiceDescription() {
		return localServiceDescription;
	}

	/**
	 * Gets the detailed service description of this service.
	 * 
	 * @return the detailed description of this service.
	 */
	public ServiceDescription getServiceDescription() {
		return serviceDescription;
	}

	/**
	 * Sets the detailed service description of this service.
	 * 
	 * @param serviceDescription
	 *            the detailed description of this service.
	 */
	public void setServiceDescription(ServiceDescription serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	/**
	 * Gets the essential QoS parameters of this service.
	 * 
	 * @return a colelction of Qos parameters.
	 */
	public Collection<CapDocQosParameter> getEssentialQosCapabilities() {
		return qosDoc.getEssentialCapabilities();
	}

	/**
	 * Gets the URI of the QoS document.
	 * @return the URI of the quality of service document.
	 */
	public URI getQosURI() {
		return qosURI;
	}
	
	/**
	 * Gets the QoS document associated with this service provider.
	 * @return the QoS associated with this provider or null if no QoS docuemnt is associated.
	 */
	public QosDoc getQosDoc() {
		return qosDoc;
	}
}
