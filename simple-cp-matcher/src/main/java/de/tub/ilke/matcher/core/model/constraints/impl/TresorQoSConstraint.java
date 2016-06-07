package de.tub.ilke.matcher.core.model.constraints.impl;

import de.tub.ilke.matcher.core.model.constraints.AggregatableConstraint;

public abstract class TresorQoSConstraint implements AggregatableConstraint {
	public String priority;
	public boolean hardSoftConstraint;
	public int weight;
}
