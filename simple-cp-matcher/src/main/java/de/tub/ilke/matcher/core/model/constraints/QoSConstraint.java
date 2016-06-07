package de.tub.ilke.matcher.core.model.constraints;

import de.tub.ilke.matcher.core.model.specs.QoSSpec;

public interface QoSConstraint {

	public void evaluate(QoSSpec qoSSpec);
}
