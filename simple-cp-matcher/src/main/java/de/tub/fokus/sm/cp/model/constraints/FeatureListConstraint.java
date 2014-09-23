package de.tub.fokus.sm.cp.model.constraints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.constraints.Solution;
import javax.constraints.Var;

import de.tub.fokus.sm.cp.csp.FeatureListMatchingProblem;
import de.tub.fokus.sm.cp.model.ConstraintException;
import de.tub.fokus.sm.cp.model.Evaluator;
import de.tub.fokus.sm.cp.model.ExactEvaluator;
import de.tub.fokus.sm.cp.model.FailEvaluator;
import de.tub.fokus.sm.cp.model.NoSpecEvaluator;
import de.tub.fokus.sm.cp.model.PartialEvaluator;
import de.tub.fokus.sm.cp.model.SuperEvaluator;
import de.tub.fokus.sm.cp.model.specs.FeatureListSpec;
import de.tub.fokus.sm.cp.model.specs.QoSSpec;

public class FeatureListConstraint implements QoSConstraint {
	int[] requested;

	public FeatureListConstraint(int[] codes) throws ConstraintException {
		if (codes.length == 0)
			throw new ConstraintException();
		requested = codes;
	}

	/**
	 * FeatureListConstraint evaluates FeatureListSpec and labels with the
	 * following: super, exact,partial, fail and empty specification
	 * 
	 * provider={0,1,4} requested={0,1} solution ={0,1} SUPER 
	 * provider={0,4}  requested={0,1} solution={0} PARTIAL 
	 * provider={0,1} requested={0,1} solution={0,1} EXACT 
	 * provider={ } requested={0,1} solution={} INCOMPLETE KNOWLEDGE (for now handled same as FAIL)
	 * provider={2,3} requested={0,1} solution={} FAIL
	 */

	@Override
	public void evaluate(QoSSpec qoSSpec) {
		// TODO possible to reuse for other constraint types?
		Map<Integer, Evaluator> evaluators = new HashMap<Integer, Evaluator>();
		evaluators.put(1, new FailEvaluator());
		evaluators.put(2, new ExactEvaluator());
		evaluators.put(3, new SuperEvaluator());
		evaluators.put(4, new PartialEvaluator());
		evaluators.put(5, new NoSpecEvaluator());

		int[] qoSSpecCodes = ((FeatureListSpec) qoSSpec).getCodes();
		// TODO deal with incomplete knowledge
		if (qoSSpecCodes.length == 0) {
			qoSSpec.applyNoSpecRankingRule();
			return;
		}

		FeatureListMatchingProblem matchp = new FeatureListMatchingProblem(
				qoSSpecCodes, requested);
		List<Solution> solutions = matchp.execute();

		int numberOfSolutions = solutions.size();

		// TODO improve design here, too many if statements
		if (numberOfSolutions == 0) {
			qoSSpec.applyFailRankingRule();
			return;
		}

		if (requested.length == numberOfSolutions) {// exact or super
			if (qoSSpecCodes.length == numberOfSolutions) {
				qoSSpec.applyExactRankingRule();
				return;
			}

			if (qoSSpecCodes.length > solutions.size()) {
				qoSSpec.applySuperRankingRule();
				return;
			}
		}

		if (requested.length > numberOfSolutions) {
			qoSSpec.applyPartialRankingRule();
			return;
		}

	}
}
