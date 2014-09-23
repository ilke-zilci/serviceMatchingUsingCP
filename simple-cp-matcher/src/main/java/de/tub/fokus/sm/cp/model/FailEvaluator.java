package de.tub.fokus.sm.cp.model;

import de.tub.fokus.sm.cp.model.specs.QoSSpec;
import de.tub.fokus.sm.cp.model.specs.QoSSpec.MatchingDegree;

public class FailEvaluator implements Evaluator {

	@Override
	public void evaluate(QoSSpec qoSSpec) {
		qoSSpec.setMatchingDegree(MatchingDegree.FAIL);
		qoSSpec.setRanking(0);

	}
}
