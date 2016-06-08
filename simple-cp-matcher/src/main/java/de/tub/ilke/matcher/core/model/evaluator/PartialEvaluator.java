package de.tub.ilke.matcher.core.model.evaluator;

import de.tub.ilke.matcher.core.model.specs.QoSSpec;
import de.tub.ilke.matcher.core.model.specs.QoSSpec.MatchingDegree;

public class PartialEvaluator implements Evaluator {

	@Override
	public void evaluate(QoSSpec qoSSpec) {
		qoSSpec.setMatchingDegree(MatchingDegree.PARTIAL);
		qoSSpec.setRanking(1);
	}

}
