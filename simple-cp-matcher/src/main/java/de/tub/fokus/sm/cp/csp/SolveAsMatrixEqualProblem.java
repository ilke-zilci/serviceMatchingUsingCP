package de.tub.fokus.sm.cp.csp;

import java.util.List;

import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Var;

import de.tub.fokus.sm.cp.model.ConstraintException;

public class SolveAsMatrixEqualProblem extends AbstractProblem {
	/**
	 * implementation for the simple numeric matching problem
	 * service descriptions are arrays of integer values for qos parameters
	 *  
	 * @author izi
	 *
	 */

	int[] qosdemand;
	int[][] qvalues;

	public SolveAsMatrixEqualProblem() {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("ServiceMatchingDiscreteNumeric");
	}

	public SolveAsMatrixEqualProblem(int qosdemand[], int[][] qvalues) {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("ServiceMatchingDiscreteNumeric");
		this.qosdemand = qosdemand;
		this.qvalues = qvalues;
	}

	// if solver gets created before posting constraints
	// error : invalid attempt to get indomainvar of Var varname
	public void buildModel(int[] qosdemand, int[][] qvalues) {
		int serviceIndexMax = qvalues.length - 1;
		// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
		Var indexVar = matching.variable("serviceIndex", 0, serviceIndexMax);
		for (int j = 0; j < qosdemand.length; j++) {
			matching.postElement(qvalues[j], indexVar, "=", qosdemand[j]);
		}
	}

	public static void main(String[] args) throws ConstraintException {
		int[] qosdemand = { 3, 120, 98 };
		int[] q1values = { 3, 2, 3, 3, 2, 3, 3 };
		int[] q2values = { 120, 100, 120, 120, 100, 130, 130 };
		int[] q3values = { 98, 95, 98, 98, 95, 97, 97 };
		int[][] qvalues = { q1values, q2values, q3values };
		// this happens in QoSRequest evaluateMatrix
		SolveAsMatrixEqualProblem matchp = new SolveAsMatrixEqualProblem(
				qosdemand, qvalues);

		List<Solution> solutions = matchp.execute();
		int noOfSolutions;
		int[] matchingServiceIndexes = new int[solutions.size()];
		int i = 0;
		for (Solution solution : solutions) {
			noOfSolutions = solution.getNumberOfVars();
			if (noOfSolutions > 0)
				// expecting only one value in each solution with index 0 in
				// intresults
				matchingServiceIndexes[i++] = solution.getValue(0);
		}

		for (int j = 0; j < matchingServiceIndexes.length; j++) {

			System.out.println("matching service index no " + j + " :"
					+ matchingServiceIndexes[j] + "\n");

		}

	}

	@Override
	public void configureSearch() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Solution> execute() throws ConstraintException {
		this.buildModel(qosdemand, qvalues);
		this.createSolver();

		return this.solveAll();
	}

}
