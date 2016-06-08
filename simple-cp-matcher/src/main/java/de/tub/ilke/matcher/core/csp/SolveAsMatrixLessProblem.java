package de.tub.ilke.matcher.core.csp;

import java.util.List;

import javax.constraints.Constraint;
import javax.constraints.Objective;
import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Var;
import javax.constraints.impl.constraint.Linear;

import de.tub.ilke.matcher.core.model.ConstraintException;

public class SolveAsMatrixLessProblem extends AbstractProblem {
	/**
	 * implementation for matching properties with negative tendency
	 * example: maximum response time 
	 * the service descriptions and the request specify an upper limit
	 * service descriptions which specify an upper limit less than or equal to the request, match.
	 * if the service description has a higher value for that limit, no match. 
	 * @author izi
	 *
	 */

	int[] qosdemand;
	int[][] qvalues;

	public SolveAsMatrixLessProblem() {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("ServiceMatchingDiscreteNumeric");
	}

	public SolveAsMatrixLessProblem(int qosdemand[], int[][] qvalues) {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("ServiceMatchingDiscreteNumeric");
		this.qosdemand = qosdemand;
		this.qvalues = qvalues;
	}

	/**
	 * posts constraints for each serviceIndex for exact matching
	 * 	int[] serviceIds = { 0, 1, 2, 3 };
		int[] qosdemand = { 100 };
		int[] q1values = { 80, 90, 110, 120 };
		INFO: Solution #1:
	 	serviceIndex[0]
		Sep 27, 2014 4:46:04 PM javax.constraints.impl.Problem log
		INFO: Solution #2:
	 	serviceIndex[1]
	 * */
	public void buildModel(int[] qosdemand, int[][] qvalues) {
		int serviceIndexMax = qvalues[0].length - 1;
		// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
		Var indexVar = matching.variable("serviceIndex", 0, serviceIndexMax);
		int[] q1values = qvalues[1];
		int qosdemand1 = qosdemand[0];

		matching.postElement(q1values, indexVar, "<=", qosdemand1);
		// for (int j = 1; j < qosdemand.length; j++) {
		// matching.postElement(qvalues[j], indexVar, "<=", qosdemand[j]);
		// }

	}

	public void buildSimpleModelDifference(int[] qosdemand, int[][] qvalues)
			throws ConstraintException {
		int[] serviceIds = qvalues[0];
		Var serviceIDs = matching.variable("serviceId", serviceIds);
		int[] q1values = qvalues[1];
		Var q1property = matching.variable("property1", q1values);
		// int[] q2values = qvalues[2];
		// Var q2property = matching.variable("property2", q2values);
		// int[] q3values = qvalues[3];
		// Var q3property = matching.variable("property3", q3values);

		// if serviceId==0 then q1property must have the value q1values[0]
		Linear certainId = new Linear(serviceIDs, "=", serviceIds[0]);
		Linear certainProperty1 = new Linear(q1property, "=", q1values[0]);
		Constraint c12 = matching.linear(certainId.asBool(), "=",
				certainProperty1.asBool());
		// if serviceId==1 then q1property must have the value q1values[1]
		Linear certainId2 = new Linear(serviceIDs, "=", serviceIds[1]);
		Linear certainProperty2 = new Linear(q1property, "=", q1values[1]);
		Constraint c22 = matching.linear(certainId2.asBool(), "=",
				certainProperty2.asBool());
		// if serviceId==2 then q1property must have the value q1values[2]
		Linear certainId3 = new Linear(serviceIDs, "=", serviceIds[2]);
		Linear certainProperty3 = new Linear(q1property, "=", q1values[2]);
		Constraint c32 = matching.linear(certainId3.asBool(), "=",
				certainProperty3.asBool());

		c12.post();
		c22.post();
		c32.post();

		// this time it is not the absolute difference, if the result is
		// negative,
		// property 80 demand 100 violation -20, a negative vioaltion means the
		// service is better than requested
		Var violation1 = q1property.minus(qosdemand[0]).multiply(1);
		violation1.setName("violation1");
		matching.add(violation1);
		Var[] weightVars = { violation1 };
		// Optimization objective
		Var weightedSum = matching.sum(weightVars);
		weightedSum.setName("Total Constraint Violations");
		matching.add(weightedSum);
		Solution solution = matching.getSolver().findOptimalSolution(
				Objective.MINIMIZE, weightedSum);
		solution.log();

		/**
		 * Example:
		 * Var i = matching.variable("i", 0, 10); 
		 * Var j = matching.variable("j", 0, 10);
		 * Var k = i.minus(j);
		 */
		// this.createSolver();
		// this.solveAll();
	}

	public static void main(String[] args) throws ConstraintException {

		int[] serviceIds = { 0, 1, 2 };
		int[] qosdemand = { 100 };
		int[] q1values = { 90, 80, 110 };
		// int[] q2values = { 100, 100, 100 };
		// int[] q3values = { 96, 96, 96 };
		int[][] qvalues = { serviceIds, q1values };
		// this happens in QoSRequest evaluateMatrix
		SolveAsMatrixLessProblem matchp = new SolveAsMatrixLessProblem(
				qosdemand, qvalues);

		matchp.buildSimpleModelDifference(qosdemand, qvalues);

		// List<Solution> solutions = matchp.execute();
		// int noOfSolutions;
		// int[] matchingServiceIndexes = new int[solutions.size()];
		// int i = 0;
		// for (Solution solution : solutions) {
		// noOfSolutions = solution.getNumberOfVars();
		// if (noOfSolutions > 0)
		// // expecting only one value in each solution with index 0 in
		// // intresults
		// matchingServiceIndexes[i++] = solution.getValue(0);
		// }
		//
		// for (int j = 0; j < matchingServiceIndexes.length; j++) {
		//
		// System.out.println("matching service index no " + j + " :"
		// + matchingServiceIndexes[j] + "\n");
		//
		// }

	}

	@Override
	public void configureSearch() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Solution> execute() throws ConstraintException {
		this.buildModel(qosdemand, qvalues);
		// this method just gets the solver of the problem, it is created
		// if not created before
		this.createSolver();
		return this.solveAll();
	}
}
