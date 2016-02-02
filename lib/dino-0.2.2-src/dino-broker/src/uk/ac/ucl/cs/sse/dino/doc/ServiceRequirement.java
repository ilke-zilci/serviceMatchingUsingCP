package uk.ac.ucl.cs.sse.dino.doc;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import uk.ac.ucl.cs.sse.dino.doc.qos.QosDoc;
import uk.ac.ucl.cs.sse.dino.doc.qos.QosDocParser;
import uk.ac.ucl.cs.sse.dino.doc.qos.ReqDocQosParameter;

/**
 * A representation of a required service from a Dino ReqDoc.
 */
public final class ServiceRequirement {
	private static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
			.newInstance();

	// Name of the requirement
	private final String name;

	// Reference to functional description of requirement.
	private final URI type;

	private final QosDoc qosDoc;

	/**
	 * Creates a service requirement with the specified name and type reference.
	 * 
	 * @param name
	 *            the name of the service requirement as specified in the
	 *            ReqDoc.
	 * @param type
	 *            a reference to a document which describes the type of service
	 *            which is required. This will most likely be an OWL-S document.
	 * @param qosRef
	 *            URI of the document which specifies QoS requirements.
	 * @throws DinoDocReadException
	 *             if there is a problem with the QoS doc referred to by qosRef.
	 */
	public ServiceRequirement(final String name, final URI type, final URI qosRef)
			throws DinoDocReadException {
		// Preconditions
		assert name != null;
		assert type != null;
		assert qosRef != null;

		this.name = name;
		this.type = type;

		if (qosRef.toString().equals("")) {
			// create an empty Qos doc.
			qosDoc = new QosDoc();
		} else {
			Document document;
			try {
				document = documentBuilderFactory.newDocumentBuilder().parse(qosRef.toString());
				Element qosDocElement = document.getDocumentElement();
				qosDoc = QosDocParser.parseQosRequirements(qosDocElement);
			} catch (SAXException e) {
				throw new DinoDocReadException("Failed to read QoSDoc for service " + name, e);
			} catch (IOException e) {
				throw new DinoDocReadException("Failed to read QoSDoc for service " + name, e);
			} catch (ParserConfigurationException e) {
				throw new DinoDocReadException("Failed to read QoSDoc for service " + name, e);
			}
		}
	}

	/**
	 * Creates a service requirement with the specified name and type reference.
	 * 
	 * @param name
	 *            the name of the service requirement as specified in the
	 *            ReqDoc.
	 * @param type
	 *            a reference to a document which describes the type of service
	 *            which is required. This will most likely be an OWL-S document.
	 * @param qosDoc
	 *            the qosDoc belonging to this service requirement.
	 */
	public ServiceRequirement(final String name, final URI type, final QosDoc qosDoc) {
		// Preconditions
		assert name != null;
		assert type != null;
		assert qosDoc != null;

		this.name = name;
		this.type = type;
		this.qosDoc = qosDoc;
	}

	/**
	 * Returns the name of this service requirement, which should uniquely
	 * identify the service within a mode.
	 * 
	 * @return the non-null name of the service requirement.
	 */
	public String getName() {
		// Postcondition
		assert name != null;

		return name;
	}

	/**
	 * Gets a reference to the type of service required, in to form of a URI
	 * which describes the type in OWL-S.
	 * 
	 * @return a non-null reference to the document describing the type.
	 */
	public URI getType() {
		return type;
	}

	/*
	 * public URI getQosRef() { return qosRef; }
	 */

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof ServiceRequirement)) {
			return false;
		}

		ServiceRequirement r = (ServiceRequirement) o;
		if (!r.name.equals(name)) {
			return false;
		}

		if (!r.qosDoc.equals(qosDoc)) {
			return false;
		}

		if (!r.type.equals(type)) {
			return false;
		}

		return true;

	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + name.hashCode();
		result = 37 * result + qosDoc.hashCode();
		result = 37 * result + type.hashCode();
		return result;
	}

	/**
	 * Gets the essential oS requirmeents of this requirement.
	 * 
	 * @return a collection of QoS parameters which are required to be met.
	 */
	public Collection<ReqDocQosParameter> getEssentialQosRequirements() {
		return qosDoc.getEssentialRequirements();
	}
}
