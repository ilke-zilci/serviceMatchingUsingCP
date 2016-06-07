package de.tub.ilke.matcher.core.model.evaluator;

import de.tub.ilke.matcher.core.model.specs.QoSSpec;
import de.tub.ilke.matcher.core.model.specs.QoSSpec.MatchingDegree;

public class NoSpecEvaluator implements Evaluator {

	@Override
	public void evaluate(QoSSpec qoSSpec) {
		qoSSpec.setRanking(0);
		// TODO differentiate from the known but failing services?
		qoSSpec.setMatchingDegree(MatchingDegree.FAIL);
	}

}
