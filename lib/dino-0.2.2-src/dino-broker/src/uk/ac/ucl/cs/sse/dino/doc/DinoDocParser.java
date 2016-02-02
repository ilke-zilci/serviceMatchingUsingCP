package uk.ac.ucl.cs.sse.dino.doc;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.ac.ucl.cs.sse.dino.doc.qos.QosDoc;
import uk.ac.ucl.cs.sse.dino.doc.qos.QosDocParser;

/**
 * Parses Dino ReqDocs and CapDocs expressed in XML and creates a data structure
 * representing the ReqDoc or CapDoc.
 */
public class DinoDocParser {
	// private static final String reqDocNS =
	// "http://www.cs.ucl.ac.uk/staff/A.Dingwall-Smith/Dino-ReqDoc";

	/**
	 * Creates a ReqDoc parser.
	 */
	private DinoDocParser() {
	}

	/**
	 * Parses the ReqDoc with the provided root element.
	 * @param reqDocElement the root element of the ReqDoc.
	 * @return an object representing the parsed document.
	 * @throws DinoDocReadException if there is a problem parsing the provided ReqDoc.
	 */
	public static ReqDoc parseReqDoc(final Element reqDocElement) throws DinoDocReadException {
		ReqDoc reqDoc = (ReqDoc) parse(reqDocElement, DocType.ReqDoc);
		return reqDoc;
	}

	/**
	 * Parses the CapDoc with the provided root element.
	 * @param capDocElement the root element of the CapDoc.
	 * @return an object representing the parsed document.
	 * @throws DinoDocReadException if there is a problem parsing the provided CapDoc.
	 */
	public static CapDoc parseCapDoc(final Element capDocElement) throws DinoDocReadException {
		CapDoc capDoc = (CapDoc) parse(capDocElement, DocType.CapDoc);
		return capDoc;
	}

	private static DinoDoc parse(final Element reqDocElement, final DocType docType)
			throws DinoDocReadException {
		String reqDocName = reqDocElement.getAttribute("name");

		DinoDoc dinoDoc;
		if (docType == DocType.ReqDoc) {
			dinoDoc = new ReqDoc(reqDocName);
		} else {
			dinoDoc = new CapDoc(reqDocName);
		}

		// Parse normal &lt;mode&gt; elements.
		// NodeList modeElementList = reqDocElement.getElementsByTagNameNS(
		// reqDocNS, "mode");
		NodeList modeElementList = reqDocElement.getElementsByTagName("mode");
		for (int i = 0; i < modeElementList.getLength(); i++) {
			Element modeElement = (Element) modeElementList.item(i);
			parseMode(dinoDoc, modeElement, docType);
		}

		// Parse the &lt;common&gt; element. There should only be one of
		// these
		// but this code will actually
		// parse multiple &lt;common&gt; elements if they exist.
		NodeList commonElementList = reqDocElement.getElementsByTagName("common");
		for (int i = 0; i < commonElementList.getLength(); i++) {
			Element commonElement = (Element) commonElementList.item(i);
			parseCommon(dinoDoc, commonElement, docType);
		}

		return dinoDoc;
	}

	private static void parseMode(final DinoDoc dinoDoc,
			final Element modeElement, final DocType docType) throws DinoDocReadException {
		// Mode names are separated by commas, so split these out. Any excess
		// white space will
		// be removed later.
		String[] modeNames = modeElement.getAttribute("name").split(",");

		for (String modeName : modeNames) {
			dinoDoc.addMode(modeName);
		}

		// NodeList serviceElementList = modeElement.getElementsByTagNameNS(
		// reqDocNS, "service");
		NodeList serviceElementList = modeElement.getElementsByTagName("service");
		for (int i = 0; i < serviceElementList.getLength(); i++) {
			Element serviceElement = (Element) serviceElementList.item(i);

			if (docType == DocType.ReqDoc) {
				ServiceRequirement service = parseServiceRequirement(serviceElement);
				for (String modeName : modeNames) {
					// Add the service to every mode in this mode element.
					// If the mode does not exist then it will be created.
					((ReqDoc) dinoDoc).addServiceRequirement(modeName.trim(), service);
				}
			} else {
				ServiceImplementation impl = parseServiceImplementation(serviceElement);
				for (String modeName : modeNames) {
					// Add the service to every mode in this mode element.
					// If the mode does not exist then it will be created.
					((CapDoc) dinoDoc).addServiceImplementation(modeName.trim(), impl);
				}
			}
		}
	}

	private static void parseCommon(final DinoDoc dinoDoc,
			final Element commonElement, final DocType docType) throws DinoDocReadException {
		// NodeList serviceElementList = commonElement.getElementsByTagNameNS(
		// reqDocNS, "service");
		NodeList serviceElementList = commonElement.getElementsByTagName("service");
		for (int i = 0; i < serviceElementList.getLength(); i++) {
			Element serviceElement = (Element) serviceElementList.item(i);
			if (docType == DocType.ReqDoc) {
				ServiceRequirement service = parseServiceRequirement(serviceElement);
				((ReqDoc) dinoDoc).addServiceRequirementToAllModes(service);
			} else {
				ServiceImplementation impl = parseServiceImplementation(serviceElement);
				((CapDoc) dinoDoc).addServiceImplementationToAllModes(impl);
			}
		}
	}

	private static ServiceImplementation parseServiceImplementation(final Element serviceElement)
			throws DinoDocReadException {
		// String serviceName = serviceElement.getAttribute("name");
		String typeString = serviceElement.getAttribute("functional-ref");
		if (typeString.equals("")) {
			// For backwards compatibility with previous versions of CapDoc
			// functional-ref may be named type instead.
			typeString = serviceElement.getAttribute("type");
		}
		String qosString = serviceElement.getAttribute("qos-ref");

		try {
			ServiceImplementation impl = null;
			URI typeURI = new URI(typeString);
			if (qosString.equals("")) {
				// There is no qos-ref attribute so see if the is a QosDoc child
				// element instead.
				QosDoc qosDoc = parseQosDoc(serviceElement, DocType.ReqDoc);
				if (qosDoc != null) {
					impl = new ServiceImplementation(typeURI, qosDoc);
				}
			}

			if (impl == null) {
				impl = new ServiceImplementation(typeURI, new URI(qosString));
			}

			return impl;
		} catch (URISyntaxException e) {
			throw new DinoDocReadException(typeString + " is not a valid URL.");
		}
	}

	private static ServiceRequirement parseServiceRequirement(final Element serviceElement)
			throws DinoDocReadException {
		String serviceName = serviceElement.getAttribute("name");
		String typeString = serviceElement.getAttribute("functional-ref");
		if (typeString.equals("")) {
			// For backwards compatibility with previous versions of ReqDoc
			// functional-ref may be named type instead.
			typeString = serviceElement.getAttribute("type");
		}
		String qosString = serviceElement.getAttribute("qos-ref");

		try {
			ServiceRequirement requirement = null;
			URI typeURI = new URI(typeString);
			if (qosString.equals("")) {
				// There is no qos-ref attribute so see if the is a QosDoc child
				// element instead.
				QosDoc qosDoc = parseQosDoc(serviceElement, DocType.ReqDoc);
				if (qosDoc != null) {
					requirement = new ServiceRequirement(serviceName, typeURI, qosDoc);
				}
			}

			if (requirement == null) {
				requirement = new ServiceRequirement(serviceName, typeURI, new URI(qosString));
			}

			return requirement;
		} catch (URISyntaxException e) {
			throw new DinoDocReadException(typeString + " is not a valid URL.");
		}
	}

	private static QosDoc parseQosDoc(final Element parentElement, final DocType docType)
			throws DinoDocReadException {
		NodeList children = parentElement.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node childNode = children.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element child = (Element) childNode;
				if (child.getTagName().equals("QosDoc")) {
					QosDoc qosDoc = QosDocParser.parse(child, docType);
					return qosDoc;
				}
			}
		}

		return null;
	}
}
