package de.tub.fokus.sm.cp.csp;

import javax.constraints.Problem;
import javax.constraints.Solution;
import javax.constraints.Solver;
import javax.constraints.Var;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//i think i am still mixing this up with interval matching because why would i need a csp to tell if two values are equal.
// maybe for this one it makes sense to use the matrix and as input get all the discrete numeric constraints in the request
//and their counterparts in the service descriptions and give these as input, so that they are handled in one CSP see SolveAsMatrix.
// and then loop on the solutions and tell for which 
public class DiscreteValueMatchingProblem {
	Problem matching;
	Solver solver;
	Solution[] solutions;
	/**
	 * The logger of the class.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	public Var[] buildModel(int provided, int required) {

		return null;
	}

}
