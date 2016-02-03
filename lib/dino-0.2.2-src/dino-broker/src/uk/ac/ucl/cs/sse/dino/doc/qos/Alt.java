package uk.ac.ucl.cs.sse.dino.doc.qos;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;



/**
 * Represents an alt element of a QoS doc, indicating that one of the enclosed
 * elements must be met.
 */
final class Alt extends QosDocElement {
	Collection<ReqDocQosParameter> getEssentialRequirements() {
		return new LinkedList<ReqDocQosParameter>();
	}
	
	Collection<CapDocQosParameter> getEssentialCapabilities() {
		return Collections.emptyList();
	}
}
