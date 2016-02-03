package uk.ac.ucl.cs.sse.dino.doc.qos;

/**
 * A QoS parameter from a QoS document.
 */
public abstract class QosParameter extends QosDocElement {
	/**
	 * Name of QoS parameter
	 */
	private String name;

	/**
	 * Operation to which this QoS parameter applies.
	 */
	private String operation;

	/**
	 * Unit of measurement used in the values in this parameter.
	 */
	private String unit;

	/**
	 * Values for enum parameter. In reqDoc this is the requested values in
	 * order of preference, in CapDoc it all the values offered by this service
	 * in no particular order.
	 */
	private String[] enumValues;

	/**
	 * Confidence in the values in this QoS parameter as a percentage or minimum
	 * required confidence.
	 */
	private double confidence;

	/**
	 * Creates a QoS parameter with the given name. No other properties are set.
	 * 
	 * @param name
	 *            the name of the QoS parameter.
	 */
	public QosParameter(String name) {
		this.name = name;
	}

	/**
	 * Sets the enumeration of values associated with this service.
	 * 
	 * @param enumValues
	 */
	public void setEnumValues(String[] enumValues) {
		this.enumValues = enumValues;
	}

	/**
	 * Returns an enumeration of values relating to a service. In the description of a
	 * provided service these represent all the values provided by the service.
	 * In the case of a required service, these are an ordered list of desirable
	 * values from most preferred to least preferred.
	 * 
	 * @return the enumerated values.
	 */
	public String[] getEnumValues() {
		return enumValues;
	}

	/**
	 * Returns the name of the quality of service parameter represented by this element.
	 * 
	 * @return the name of the quality of service parameter.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the name of the operation that this QoS parameter is associated with.
	 * @return the name of the operation.
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Gets the units of the values used in this QoS parameter.
	 * @return a string representing the units.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Sets the confidencethat the service provider has in this QoS parameter as
	 * a percentage.
	 * 
	 * @param confidence
	 *            a percentage confidence level which.
	 */
	public void setConfidence(double confidence) {
			this.confidence = confidence;
	}

	/**
	 * Gets the confidence that the service provider has in the attributes for
	 * this quality of service parameter as a percentage. For example, if
	 * confidence is 99% then the provider thinks that in no more than 1% of
	 * cases will one of the attributes of this quality of service parameter be
	 * inaccurate.
	 * 
	 * @return the confidence value as a percentage.
	 */
	public double getConfidence() {
		return confidence;
	}
}
