package de.tub.fokus.sm.cp.model.constraints;

import java.util.HashMap;
import java.util.Map;

import de.tub.fokus.sm.cp.model.specs.QoSSpec;

public interface QoSConstraint {

	public void evaluate(QoSSpec qoSSpec);
}
