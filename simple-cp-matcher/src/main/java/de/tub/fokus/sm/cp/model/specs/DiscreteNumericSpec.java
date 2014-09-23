package de.tub.fokus.sm.cp.model.specs;

public class DiscreteNumericSpec extends TresorQoSSpec {
	int value;

	public int getValue() {

		return value;
	}

	public DiscreteNumericSpec() {
	}

	public DiscreteNumericSpec(int i) {
		value = i;
	}

	@Override
	public String toString() {
		return value + "";
	}
}
