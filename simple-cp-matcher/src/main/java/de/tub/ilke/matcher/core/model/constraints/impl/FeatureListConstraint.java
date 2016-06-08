package de.tub.ilke.matcher.core.model.constraints.impl;

import java.util.List;

import javax.constraints.Solution;

import de.tub.ilke.matcher.core.csp.FeatureListMatchingProblem;
import de.tub.ilke.matcher.core.model.ConstraintException;
import de.tub.ilke.matcher.core.model.constraints.QoSConstraint;
import de.tub.ilke.matcher.core.model.specs.FeatureListSpec;
import de.tub.ilke.matcher.core.model.specs.QoSSpec;

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
