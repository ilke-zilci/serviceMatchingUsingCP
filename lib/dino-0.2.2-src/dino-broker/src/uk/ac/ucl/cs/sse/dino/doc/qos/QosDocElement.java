package uk.ac.ucl.cs.sse.dino.doc.qos;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents an element of a QoS(Quality of Service) document. Possible QoS
 * elements include And, Alt and Qos parameter elements.
 * 
 */
abstract class QosDocElement {
	private List<QosDocElement> childElements = new LinkedList<QosDocElement>();

	/**
	 * Adds a child element to this QoS element.
	 * 
	 * @param qosElement
	 *            the child element to add.
	 */
	void addChildElement(QosDocElement qosElement) {
		childElements.add(qosElement);
	}

	/**
	 * Gets the essential QoS requirements which are child elements of this
	 * element. Essential elements are those which are always required to be
	 * satisfied (i.e. those which are not inside ALT element).
	 * 
	 * @return a collection of QoS parameters which are always required to be
	 *         satisfied.
	 */
	abstract Collection<ReqDocQosParameter> getEssentialRequirements();

	/**
	 * Gets the essential QoS capabilities which are child elements of this
	 * element. Essential capabilities are those which are always provided (i.e.
	 * those which are not inside ALT elements).
	 * 
	 * @return a collection of QoS parameters which are always provided.
	 */
	abstract Collection<CapDocQosParameter> getEssentialCapabilities();

	/**
	 * Gets the essential requirements from the children of this element.
	 * @return a collection of QoS requirements.
	 */
	protected Collection<ReqDocQosParameter> getEssentialRequriementsFromChildren() {
		Collection<ReqDocQosParameter> essentialRequirements = new LinkedList<ReqDocQosParameter>();
		for (QosDocElement child : childElements) {
			essentialRequirements.addAll(child.getEssentialRequirements());
		}
		return essentialRequirements;
	}

	/**
	 * Gets the essential capabilities from the children of this element.
	 * @return a collection of QoS capabilities.
	 */
	protected Collection<CapDocQosParameter> getEssentialCapabilitiesFromChildren() {
		Collection<CapDocQosParameter> essentialCapabilties = new LinkedList<CapDocQosParameter>();
		for (QosDocElement child : childElements) {
			essentialCapabilties.addAll(child.getEssentialCapabilities());
		}
		return essentialCapabilties;
	}
}
