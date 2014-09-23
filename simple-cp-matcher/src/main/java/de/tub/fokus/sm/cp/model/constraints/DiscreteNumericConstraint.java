package de.tub.fokus.sm.cp.model.constraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.tub.fokus.sm.cp.model.specs.DiscreteNumericSpec;
import de.tub.fokus.sm.cp.model.specs.QoSSpec;

public class DiscreteNumericConstraint extends TresorQoSConstraint {

	/**
	 * the list of values that the service specifications has for this particular qos demand
	 */
	List<Integer> specifications = new ArrayList<Integer>();

	public List<Integer> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<Integer> specifications) {
		this.specifications = specifications;
	}

	private int value;

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public void addToList(QoSSpec qoSSpec) {
		DiscreteNumericSpec numspec = (DiscreteNumericSpec) qoSSpec;
		specifications.add(numspec.getValue());
	}
}
