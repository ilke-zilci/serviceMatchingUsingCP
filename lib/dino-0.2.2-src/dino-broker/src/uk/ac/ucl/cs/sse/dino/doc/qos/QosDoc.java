package uk.ac.ucl.cs.sse.dino.doc.qos;

import java.util.Collection;
import java.util.Collections;

/**
 * Represents a QoS document. These documents are referenced by ReqDocs, in
 * which case they specify required QoS attributes of the requested service, or
 * by CapDocs, in which case they represent the provided QoS attributes of the
 * provided service. Note that optional elements, represented by ALT elements,
 * are not yet implemented.
 * 
 */
public class QosDoc {
	private QosDocElement topElement;

	/**
	 * Builds an empty quality of service document.
	 * 
	 */
	public QosDoc() {

	}

	/**
	 * Sets the top element of the QosDoc.
	 * 
	 * @param qosElement
	 *            the top element of the QoS document.
	 */
	void setTopElement(QosDocElement qosElement) {
		topElement = qosElement;
	}

	/**
	 * Gets the quality of service requirements which are always necessary. That
	 * is, all requirements which are not a child of an alt element.
	 * 
	 * @return a collection of ReqDoc Qos parameters which are always required.
	 */
	public Collection<ReqDocQosParameter> getEssentialRequirements() {
		if (topElement != null) {
			return topElement.getEssentialRequirements();
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Gets the quality of service capabilities which are always provided. That
	 * is, all capabilities which are not a child of an alt element.
	 * 
	 * @return a collection of CapDoc Qos parameters which are always provided.
	 */
	public Collection<CapDocQosParameter> getEssentialCapabilities() {
		if (topElement != null) {
			return topElement.getEssentialCapabilities();
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * Gets the named provided parameter from this QoSDoc.
	 * @param parameterName the name of the apraemter to retrieve.
	 * @return the named paraemter or null if it does not exist.
	 */
	public CapDocQosParameter getEssentialCapability(String parameterName) {
		Collection<CapDocQosParameter> essentialCapabilities = getEssentialCapabilities();
		for (CapDocQosParameter p : essentialCapabilities) {
			if (parameterName.equals(p.getName())) {
				return p;
			}
		}
		
		return null;
	}

	/*
	 * protected Collection<Collection<ReqDocQosParameterImpl>>
	 * getOptionalRequirements() { if (topElement != null) { return
	 * topElement.getOptionalRequirements(false); } else { return null; } }
	 */

}
