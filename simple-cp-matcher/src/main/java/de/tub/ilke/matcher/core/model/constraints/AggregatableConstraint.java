package de.tub.ilke.matcher.core.model.constraints;

import java.util.List;

import de.tub.ilke.matcher.core.model.specs.QoSSpec;

public interface AggregatableConstraint {

	public void addToList(QoSSpec qoSSpec);

	public List<Integer> getSpecifications();
}
