package de.tub.fokus.sm.cp.model.evaluator;

import de.tub.fokus.sm.cp.model.specs.QoSSpec;
import de.tub.fokus.sm.cp.model.specs.QoSSpec.MatchingDegree;

public class ExactEvaluator implements Evaluator {

	@Override
	public void evaluate(QoSSpec qoSSpec) {
		qoSSpec.setMatchingDegree(MatchingDegree.EXACT);
		qoSSpec.setRanking(2);
	}

}
