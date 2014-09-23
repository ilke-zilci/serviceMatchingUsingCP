package de.tub.fokus.sm.cp.model;

import de.tub.fokus.sm.cp.model.specs.QoSSpec;
import de.tub.fokus.sm.cp.model.specs.QoSSpec.MatchingDegree;

public class NoSpecEvaluator implements Evaluator {

	@Override
	public void evaluate(QoSSpec qoSSpec) {
		qoSSpec.setRanking(0);
		// TODO differentiate from the known but failing services?
		qoSSpec.setMatchingDegree(MatchingDegree.FAIL);
	}

}
