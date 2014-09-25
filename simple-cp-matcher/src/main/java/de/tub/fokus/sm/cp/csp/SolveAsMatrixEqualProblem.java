package de.tub.fokus.sm.cp.csp;

import java.util.List;

import javax.constraints.Constraint;
import javax.constraints.Objective;
import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Var;
import javax.constraints.impl.constraint.Element;
import javax.constraints.impl.constraint.Linear;

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
	// use with .solveAll()
	// TODO indexVar defined in each model, remove duplication
	/**
	 * posts constraints for each serviceIndex for exact matching
	 * */
	public void buildModel(int[] qosdemand, int[][] qvalues) {
		int serviceIndexMax = qvalues[0].length - 1;
		// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
		Var indexVar = matching.variable("serviceIndex", 0, serviceIndexMax);
		for (int j = 0; j < qosdemand.length; j++) {
			matching.postElement(qvalues[j], indexVar, "=", qosdemand[j]);
		}

	}

	/** Example input and output:	
	int[] q1values = { 0, 1, 1 };
	int[] q2values = { 100, 90, 10 };
	int[] q3values = { 92, 96, 95 };
	int[][] qvalues = { q1values, q2values, q3values };
	INFO: Solution #1:
	 serviceIndex[1] Total Constraint Violations[80]
	*/
	public void buildModelSoftAsBool(int[] qosdemand, int[][] qvalues) {
		int serviceIndexMax = qvalues[0].length - 1;
		// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
		Var indexVar = matching.variable("serviceIndex", 0, serviceIndexMax);
		int[] q1values = qvalues[0];
		Element e = new Element(q1values, indexVar, "!=", qosdemand[0]);
		// shorthand for Constraint c=matching.add(e);
		// Var violation =c.asBool.multiply(20);
		Var violation1 = matching.add(e).asBool().multiply(20);

		int[] q2values = qvalues[1];
		Element e2 = new Element(q2values, indexVar, "!=", qosdemand[1]);
		Var violation2 = matching.add(e2).asBool().multiply(60);

		int[] q3values = qvalues[2];
		Element e3 = new Element(q3values, indexVar, "!=", qosdemand[2]);
		Var violation3 = matching.add(e3).asBool().multiply(120);

		Var[] weightVars = { violation1, violation2, violation3 };
		// Optimization objective
		Var weightedSum = matching.sum(weightVars);
		weightedSum.setName("Total Constraint Violations");
		Solution solution = matching.getSolver().findOptimalSolution(
				Objective.MINIMIZE, weightedSum);
		solution.log();
	}

	// gives correct values for each property but not the correct serviceIndex
	// TODO implement the relation between serviceIndex and properties
	public void buildModelSoftDifference(int[] qosdemand, int[][] qvalues)
			throws ConstraintException {
		int serviceIndexMax = qvalues[0].length - 1;
		// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
		Var indexVar = matching.variable("serviceIndex", 0, serviceIndexMax);
		// create the same constraints as buildModelSoftAsBool
		int[] serviceIds = qvalues[0];
		Var serviceIDs = matching.variable("serviceId", serviceIds);

		int[] q1values = qvalues[1];
		Var q1property = matching.variable("property1", q1values);

		int[] q2values = qvalues[2];
		Var q2property = matching.variable("property2", q2values);

		int[] q3values = qvalues[3];
		Var q3property = matching.variable("property3", q3values);

		Linear certainId = new Linear(serviceIDs, "=", serviceIds[0]);
		Linear certainIndex = new Linear(indexVar, "=", 0);
		Linear certainProperty1 = new Linear(q1property, "=", q1values[0]);

		Constraint c1 = matching.linear(certainId.asBool(), "=",
				certainIndex.asBool());
		Constraint c12 = matching.linear(certainId.asBool(), "=",
				certainProperty1.asBool());
		Linear certainValue2 = new Linear(serviceIDs, "=", serviceIds[1]);
		Linear certainIndex2 = new Linear(indexVar, "=", 1);
		Constraint c2 = matching.linear(certainValue2.asBool(), "=",
				certainIndex2.asBool());
		Linear certainProperty2 = new Linear(q1property, "=", q1values[1]);
		Linear certainValue3 = new Linear(serviceIDs, "=", serviceIds[2]);
		Linear certainIndex3 = new Linear(indexVar, "=", 2);
		Constraint c3 = matching.linear(certainValue3.asBool(), "=",
				certainIndex3.asBool());
		Linear certainProperty3 = new Linear(q1property, "=", q1values[2]);

		Linear certainId2 = new Linear(serviceIDs, "=", serviceIds[1]);
		Constraint c22 = matching.linear(certainId2.asBool(), "=",
				certainProperty2.asBool());
		Linear certainId3 = new Linear(serviceIDs, "=", serviceIds[2]);
		Constraint c32 = matching.linear(certainId3.asBool(), "=",
				certainProperty3.asBool());
		c1.post();
		c12.post();
		c22.post();
		c32.post();
		c2.post();
		c3.post();
		Element e = new Element(q1values, indexVar, "!=", qosdemand[0]);
		Var violationE1 = matching.add(e).asBool().multiply(20);
		Element e2 = new Element(q2values, indexVar, "!=", qosdemand[1]);
		Var violationE2 = matching.add(e2).asBool().multiply(20);
		Element e3 = new Element(q3values, indexVar, "!=", qosdemand[2]);
		Var violationE3 = matching.add(e3).asBool().multiply(20);

		// not posting the elements resulted in serviceindex independent of the
		// values that properties get it did not have the relation
		// q1values[indexVar], so post them but optimize on violation

		Var violation1 = serviceIDs.minus(qosdemand[0]).abs();
		violation1.setName("violation1");
		matching.add(violation1);
		Var[] weightVars = { violation1, violationE1, violationE2, violationE3 };
		// Optimization objective
		Var weightedSum = matching.sum(weightVars);
		weightedSum.setName("Total Constraint Violations");
		matching.add(weightedSum);
		// Solution solution = matching.getSolver().findOptimalSolution(
		// Objective.MINIMIZE, weightedSum);
		// solution.log();

		/**
		 * Example:
		 * Var i = matching.variable("i", 0, 10); 
		 * Var j = matching.variable("j", 0, 10);
		 * Var k = i.minus(j);
		 */
		this.createSolver();
		this.solveAll();
	}

	public static void main(String[] args) throws ConstraintException {
		// int[] qosdemand = { 3, 120, 98 };
		// int[] q1values = { 3, 3, 0, 3, 3, 3, 2 };
		// int[] q2values = { 120, 100, 110, 120, 120, 120, 120 };
		// int[] q3values = { 98, 95, 95, 98, 98, 98, 98 };
		int[] qosdemand = { 5, 100, 96 };
		int[] serviceIds = { 0, 1, 2 };
		int[] q1values = { 2, 7, 6 };
		int[] q2values = { 100, 100, 100 };
		int[] q3values = { 96, 96, 96 };
		int[][] qvalues = { serviceIds, q1values, q2values, q3values };
		// this happens in QoSRequest evaluateMatrix
		SolveAsMatrixEqualProblem matchp = new SolveAsMatrixEqualProblem(
				qosdemand, qvalues);

		matchp.buildModelSoftDifference(qosdemand, qvalues);

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
