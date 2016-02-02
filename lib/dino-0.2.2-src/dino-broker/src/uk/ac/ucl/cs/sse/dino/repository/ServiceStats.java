package uk.ac.ucl.cs.sse.dino.repository;

import java.util.HashMap;
import java.util.Map;


/**
 * Records statistical information about the invocation of a service such as
 * success in invoking the service.
 * 
 */
final class ServiceStats {
	private Map<String, QosAttribute> qosAttributeMap = new HashMap<String, QosAttribute>();

	/**
	 * Creates a set of ServiceStats.
	 *
	 */
	ServiceStats() {
		qosAttributeMap.put("responseTime", new QosAttribute());
		qosAttributeMap.put("successRate", new QosAttribute());
		qosAttributeMap.put("consecutiveFailures", new QosAttribute());
	}	
	
	/**
	 * Gets the average of all values of an attribute.
	 * @param attributeName the name of the attribute for which to calculate the average.
	 * @return the average value of the attribute.
	 */
	double getAttributeAverage(String attributeName) {
		QosAttribute attribute = qosAttributeMap.get(attributeName);
		if (attribute != null) {
			return attribute.getAverage();
		} else {
			return Double.NaN;
		}
	}
	
	/**
	 * Gets the sum of all values of an attribute.
	 * @param attributeName the name of the attribute for which to calculate the sum.
	 * @return the sum of the attribute.
	 */
	double getAttributeSum(String attributeName) {
		QosAttribute attribute = qosAttributeMap.get(attributeName);
		if (attribute != null) {
			return attribute.getSum();
		} else {
			return Double.NaN;
		}
	}
	
	/**
	 * Adds an attribute value for the named attribute.
	 * @param attributeName the name of the attribute.
	 * @param value the value of the attribute.
	 */
	void updateAttribute(String attributeName, double value) {
		QosAttribute attribute = qosAttributeMap.get(attributeName);
		if (attribute != null) {
			attribute.updateAttribute(value);
		}
	}
	
	/**
	 * Resets an attribute, removing all the previously stored values.
	 * @param attributeName the name of the attribute to reset.
	 */
	void resetAttribute(String attributeName) {
		QosAttribute attribute = qosAttributeMap.get(attributeName);
		if (attribute != null) {
			attribute.reset();
		}
	}

}
