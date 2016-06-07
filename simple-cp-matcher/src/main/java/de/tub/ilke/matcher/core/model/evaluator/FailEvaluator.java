package de.tub.ilke.matcher.core.model.evaluator;

import de.tub.ilke.matcher.core.model.specs.QoSSpec;
import de.tub.ilke.matcher.core.model.specs.QoSSpec.MatchingDegree;

public class FailEvaluator implements Evaluator {

	@Override
	public void evaluate(QoSSpec qoSSpec) {
		qoSSpec.setMatchingDegree(MatchingDegree.FAIL);
		qoSSpec.setRanking(0);

	}
}
