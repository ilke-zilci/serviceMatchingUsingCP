package uk.ac.ucl.cs.sse.dino.doc.qos;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;


/**
 * A QoS parameter from a QoS document referred to by a ReqDoc.
 */
public final class ReqDocQosParameter extends QosParameter  {
	/**
	 * The least preferred value of this parameter.
	 */
	private double lpValue = Double.NaN;

	/**
	 * The most preferred value of this parameter.
	 */
	private double mpValue = Double.NaN;

	/**
	 * Priority of this QoS parameter relative to other parameters.
	 */
	private double priority = 1.0d;

	/**
	 * Creates an object representing a QoS parameter from a QoS document which
	 * is part of a ReqDoc.
	 * 
	 * @param name
	 *            the name of the QoS parameter.
	 */
	public ReqDocQosParameter(final String name) {
		super(name);
		setConfidence(0.0d);
	}

	/**
	 * Sets the least preferred value of this required parameter.
	 * 
	 * @param lpValue
	 *            the least preffered value fo the parameter.
	 */
	public void setLpValue(double lpValue) {
		this.lpValue = lpValue;
	}

	/**
	 * Sets the most preferred value of this required parameter.
	 * 
	 * @param mpValue
	 *            the most preffered value of the parameter.
	 */
	public void setMpValue(double mpValue) {
		this.mpValue = mpValue;
	}

	/**
	 * Sets the priority of this required parameter.
	 * 
	 * @param priority
	 *            the priority of the Qos requirment.
	 */
	public void setPriority(double priority) {
		this.priority = priority;
	}

	Collection<ReqDocQosParameter> getEssentialRequirements() {
		Collection<ReqDocQosParameter> essentialRequirements = new LinkedList<ReqDocQosParameter>();
		essentialRequirements.add(this);
		return essentialRequirements;
	}
	
	@Override
	Collection<CapDocQosParameter> getEssentialCapabilities() {
		return Collections.emptyList();
	}

	/**
	 * Gets the least preferred value required of the service. Depending on the
	 * quality of service parameter this may be the highest or lowest value
	 * which is acceptable.
	 * 
	 * @return the least preferred value.
	 */
	public double getLpValue() {
		return lpValue;
	}

	/**
	 * Gets the most preferred value of the service. This primarily determines
	 * whether low or high values are preferred, by determining whether it is
	 * less or more than the least preferred value. It may also indicate that
	 * values better than this value don't provide any improvement to the
	 * service consumer.
	 * 
	 * @return the most preferred value.
	 */
	public double getMpValue() {
		return mpValue;
	}

	/**
	 * Determines how high a priority this parameter is. The higher this value,
	 * the more important it is to improve this quality of service parameter.
	 * The least preferred value is still the worst value which is acceptable and
	 * all least preferred values must be satisfied before priority becomes a
	 * factor.
	 * 
	 * @return the priority of this quality of service parameter.
	 */
	public double getPriority() {
		return priority;
	}
}
