package de.tub.fokus.sm.cp.model.constraints.impl;

import java.util.HashMap;
import java.util.Map;

import de.tub.fokus.sm.cp.model.constraints.AggregatableConstraint;

public abstract class TresorQoSConstraint implements AggregatableConstraint {
	public String priority;
	public String hardSoftConstraint;
}
