package de.tub.fokus.sm.cp.model.specs;

import java.util.HashMap;
import java.util.Map;

import de.tub.fokus.sm.cp.model.evaluator.Evaluator;
import de.tub.fokus.sm.cp.model.evaluator.ExactEvaluator;
import de.tub.fokus.sm.cp.model.evaluator.FailEvaluator;
import de.tub.fokus.sm.cp.model.evaluator.NoSpecEvaluator;
import de.tub.fokus.sm.cp.model.evaluator.PartialEvaluator;
import de.tub.fokus.sm.cp.model.evaluator.SuperEvaluator;

public abstract class TresorQoSSpec implements QoSSpec {
	protected int ranking;
	protected MatchingDegree matchingDegree;
	Map<Integer, Evaluator> evaluators;

	public TresorQoSSpec() {
		evaluators = new HashMap<Integer, Evaluator>();
		evaluators.put(1, new FailEvaluator());
		evaluators.put(2, new ExactEvaluator());
		evaluators.put(3, new SuperEvaluator());
		evaluators.put(4, new PartialEvaluator());
		evaluators.put(5, new NoSpecEvaluator());
	}

	// TODO this is not easy to read , improve!!
	public void applyFailRankingRule() {
		evaluators.get(1).evaluate(this);
		// new FailEvaluator().evaluate(this);
	}

	public void applyExactRankingRule() {
		evaluators.get(2).evaluate(this);
	}

	public void applySuperRankingRule() {
		evaluators.get(3).evaluate(this);
	}

	public void applyPartialRankingRule() {
		evaluators.get(4).evaluate(this);
	}

	public void applyNoSpecRankingRule() {
		evaluators.get(5).evaluate(this);
	}

	@Override
	public void setRanking(int i) {
		ranking = i;
	}

	@Override
	public int getRanking() {
		return ranking;
	}

	// TODO why is this here public, and not somehow set inside the evaluator
	@Override
	public void setMatchingDegree(MatchingDegree m) {
		matchingDegree = m;
	}

}
