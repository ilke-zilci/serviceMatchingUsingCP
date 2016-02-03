package uk.ac.ucl.cs.sse.dino.doc.qos;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.ac.ucl.cs.sse.dino.doc.DinoDocReadException;
import uk.ac.ucl.cs.sse.dino.doc.DocType;

/**
 * Parses Dino quality of service specifications.
 * 
 */
public class QosDocParser {
	/*
	 * Never called.
	 */
	private QosDocParser() {

	}

	/**
	 * Parses the QosDoc with the provided root element.
	 * 
	 * @param reqDocElement
	 *            the root element of the QosDoc.
	 * @param type
	 *            specifies whether this QosDoc belongs to a CapDoc or a ReqDoc.
	 * @return an object representing the parses QosDoc.
	 * @throws DinoDocReadException
	 *             if there si a problem parsing the document.
	 */
	public static QosDoc parse(final Element reqDocElement, DocType type)
			throws DinoDocReadException {
		QosDoc qosDoc = new QosDoc();

		NodeList childList = reqDocElement.getChildNodes();
		for (int i = 0; i < childList.getLength(); i++) {
			Node childNode = childList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) childNode;
				QosDocElement topElement = parseElement(childElement, type);
				if (topElement != null) {
					qosDoc.setTopElement(topElement);
					break;
				}
			}
		}

		return qosDoc;
	}

	private static QosDocElement parseElement(Element element, DocType type)
			throws DinoDocReadException {
		String tagName = element.getTagName();
		if (tagName.equals("and")) {
			And andElement = new And();
			parseChildElements(element, andElement, type);
			return andElement;
		} else if (tagName.equals("alt")) {
			Alt altElement = new Alt();
			parseChildElements(element, altElement, type);
			return altElement;
		} else if (tagName.equals("qos")) {
			if (type == DocType.ReqDoc) {
				return parseReqDocQosElement(element);
			} else if (type == DocType.CapDoc) {
				return parseCapDocQosElement(element);
			}
		}
		return null;
	}

	private static ReqDocQosParameter parseReqDocQosElement(
			final Element element) throws DinoDocReadException {
		String name = element.getAttribute("name");
		ReqDocQosParameter qosParameter = new ReqDocQosParameter(name);

		parseCommonAttributes(element, qosParameter);

		double lpVal = parseDoubleParameter(element, "lpVal");
		qosParameter.setLpValue(lpVal);

		double mpVal = parseDoubleParameter(element, "mpVal");
		qosParameter.setMpValue(mpVal);

		double priority = parseDoubleParameter(element, "priority");
		qosParameter.setPriority(priority);

		return qosParameter;
	}

	private static CapDocQosParameter parseCapDocQosElement(
			final Element element) throws DinoDocReadException {
		String name = element.getAttribute("name");
		CapDocQosParameter qosParameter = new CapDocQosParameter(name);

		parseCommonAttributes(element, qosParameter);

		double maxVal = parseDoubleParameter(element, "maxVal");
		qosParameter.setMaxValue(maxVal);

		double minVal = parseDoubleParameter(element, "minVal");
		qosParameter.setMinValue(minVal);

		double avgVal = parseDoubleParameter(element, "avgVal");
		qosParameter.setAvgVal(avgVal);

		return qosParameter;
	}

	private static void parseCommonAttributes(final Element element,
			final QosParameter parameter) throws DinoDocReadException {
		String enumString = element.getAttribute("enum");

		if (!enumString.equals("")) {
			String[] enumArray = enumString.split(",");
			for (int i = 0; i < enumArray.length; i++) {
				enumArray[i] = enumArray[i].trim();
			}
			parameter.setEnumValues(enumArray);
		}

		double confidence = parseDoubleParameter(element, "confidence");
		if (!Double.isNaN(confidence)) {
			parameter.setConfidence(confidence);
		}
	}

	private static double parseDoubleParameter(final Element element,
			final String attributeName) throws DinoDocReadException {
		try {
			String lpValueString = element.getAttribute(attributeName);
			if (lpValueString.equals("")) {
				return Double.NaN;
			} else {
				return Double.parseDouble(lpValueString);
			}
		} catch (NumberFormatException e) {
			throw new DinoDocReadException("The attribute " + attributeName
					+ " should be a double.", e);
		}

	}

	private static void parseChildElements(Element element,
			QosDocElement aggregateElement, DocType type)
			throws DinoDocReadException {
		NodeList childList = element.getChildNodes();
		for (int i = 0; i < childList.getLength(); i++) {
			Node childNode = childList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) childNode;
				QosDocElement qosElement = parseElement(childElement, type);
				if (qosElement != null) {
					aggregateElement.addChildElement(qosElement);
				}
			}
		}
	}

	/**
	 * Parses a QosDoc referenced by a Reqdoc.
	 * 
	 * @param qosDocElement
	 *            the root element of the QosDoc.
	 * @return an object representing the parses QosDoc.
	 * @throws DinoDocReadException
	 *             if there si a problem parsing the document.
	 */
	public static QosDoc parseQosRequirements(Element qosDocElement)
			throws DinoDocReadException {
		return parse(qosDocElement, DocType.ReqDoc);
	}

	/**
	 * Parses a CapDoc referenced by a Reqdoc.
	 * 
	 * @param qosDocElement
	 *            the root element of the QosDoc.
	 * @return an object representing the parses QosDoc.
	 * @throws DinoDocReadException
	 *             if there si a problem parsing the document.
	 */
	public static QosDoc parseQosCapabilities(Element qosDocElement)
			throws DinoDocReadException {
		return parse(qosDocElement, DocType.CapDoc);
	}
}
