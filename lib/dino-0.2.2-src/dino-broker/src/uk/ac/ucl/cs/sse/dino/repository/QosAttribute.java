package uk.ac.ucl.cs.sse.dino.repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a QoS attribute. This class stores a collection of values which
 * represent the value of the attribute for individual invocations.
 * 
 */
final class QosAttribute {
	private final List<Double> values = new LinkedList<Double>();

	/**
	 * Creates a new QoS attribute with no values.
	 * 
	 */
	QosAttribute() {
	}

	/**
	 * Adds a value to collection of values for the attribute.
	 * 
	 * @param value the value to add.
	 */
	void updateAttribute(double value) {
		values.add(value);
	}

	/**
	 * Resets this attribute, clearing all the stored values.
	 *
	 */
	void reset() {
		values.clear();
	}

	/**
	 * Gets the average of the values stored for this attribute.
	 * @return the average of the attribute values.
	 */
	double getAverage() {
		return getSum() / values.size();
	}

	/**
	 * Gets the sum of the values for this attribute.
	 * @return the sum of the attribute values.
	 */
	double getSum() {
		double sum = 0;
		for (double v : values) {
			sum += v;
		}
		return sum;
	}

	/**
	 * Gets the minimum value of the values stored for this attribute.
	 * @return the minimum value.
	 */
	double getMin() {
		return Collections.min(values);

	}

	/**
	 * Gets the maximum value of the value stored for this attribute.
	 * @return the maximum value.
	 */
	double getMax() {
		return Collections.max(values);
	}
}
