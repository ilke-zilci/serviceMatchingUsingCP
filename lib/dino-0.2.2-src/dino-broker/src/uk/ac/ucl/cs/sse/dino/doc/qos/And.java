package uk.ac.ucl.cs.sse.dino.doc.qos;

import java.util.Collection;



/**
 * Represents an and element of a QoS doc, indicating that all of the enclosed
 * elements must be met.
 */
final class And extends QosDocElement {

	@Override
	Collection<ReqDocQosParameter> getEssentialRequirements() {
		return getEssentialRequriementsFromChildren();
	}
	
	Collection<CapDocQosParameter> getEssentialCapabilities() {
		return getEssentialCapabilitiesFromChildren();
	}

}
