package de.tub.fokus.sm.cp.model.constraints;

import java.util.List;
import java.util.Map;

import de.tub.fokus.sm.cp.model.specs.QoSSpec;

public interface AggregatableConstraint {

	public void addToList(QoSSpec qoSSpec);

	public List<Integer> getSpecifications();
}
