package uk.ac.ucl.cs.sse.dino.doc.qos;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;


/**
 * A QoS parameter from a QoS docuemnt reffered to by a CapDoc.
 */
public final class CapDocQosParameter extends QosParameter {
	/**
	 * Claimed minimum value of this QoS parameter.
	 */
	private double minValue = Double.NaN;

	/**
	 * Claimed maximum value of this QoS parameter.
	 */
	private double maxValue = Double.NaN;

	/**
	 * Claimed average value of this QoS parameter.
	 */
	private double avgVal = Double.NaN;

	/**
	 * Creates an object representing a QoS parameter from a QoS document which
	 * is part of a CapDoc.
	 * 
	 * @param name
	 *            the name of the QoS parameter.
	 */
	public CapDocQosParameter(String name) {
		super(name);	
		setConfidence(1.0d);
	}

	/**
	 * The claimed average value of thsi QoS apraameter.
	 * 
	 * @param avgVal
	 *            the average value.
	 */
	public void setAvgVal(double avgVal) {
		this.avgVal = avgVal;
	}


	/**
	 * Sets the maximum value provided by the service described by this Qos
	 * parameter.
	 * 
	 * @param maxValue
	 *            the maximum value.
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * Sets the minimum value provided by the service described by this Qos
	 * parameter.
	 * 
	 * @param minValue
	 *            the minumum value.
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	@Override
	Collection<ReqDocQosParameter> getEssentialRequirements() {
		return Collections.emptyList();
	}
	
	Collection<CapDocQosParameter> getEssentialCapabilities() {
		Collection<CapDocQosParameter> essentialCapabilities = new LinkedList<CapDocQosParameter>();
		essentialCapabilities.add(this);
		return essentialCapabilities;
	}

	/**
	 * Gets the average value of a numeric quality of service parameter which is
	 * provided.
	 * 
	 * @return the average value of the qualuity of service parameter.
	 */
	public double getAvgVal() {
		return avgVal;
	}

	/**
	 * The maximum value which the service provider claims for this service parameter.
	 * @return the maximum value of this parameter.
	 */
	public double getMaxValue() {
		return maxValue;
	}

	/**
	 * The minimum value which the service provider claims for this service parameter.
	 * @return the minimum value of this parameter.
	 */
	public double getMinValue() {
		return minValue;
	}
}
