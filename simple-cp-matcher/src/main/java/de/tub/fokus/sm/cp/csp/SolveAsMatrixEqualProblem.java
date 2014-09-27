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

//TODO check input handling with serviceIds inside qvalues, does it start from qvalues[0] or qvalues[1]
//also careful with the buildmodel, if you get the qvalues[1], you get qosdemand[1],although that must be qosdemand[0]
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
		for (int j = 1; j < qosdemand.length; j++) {
			matching.postElement(qvalues[j], indexVar, "=", qosdemand[j - 1]);
		}

	}

	/**This model's optimization objective is to minimize the violations on the element constraints,
	 * (q1values[serviceIndex]!=qosdemand[0]).asBool() if the constraint is satisfied asBool returns 1
	 * if the constraint is not satisfied asBool returns 0,
	 * which is multiplied by the weight and added to violationSum
	 * The solver returns the serviceIndex with the minimum violationSum.  
	 *  Example input and output:
	 *  int[] qosdemand={2,80,96}	
	int[] q1values = { 0, 1, 2 };
	int[] q2values = { 100, 90, 80 };
	int[] q3values = { 92, 96, 95 };
	int[][] qvalues = { q1values, q2values, q3values };
	INFO: Solution #1:
	 serviceIndex[1] Total Constraint Violations[80]
	*/
	// TODO expect weights for each qosdemand

	public void buildModelSoftAsBool(int[] qosdemand, int[][] qvalues) {
		int serviceIndexMax = qvalues[0].length - 1;
		// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
		Var indexVar = matching.variable("serviceIndex", 0, serviceIndexMax);
		int[] q1values = qvalues[1];
		Element e = new Element(q1values, indexVar, "!=", qosdemand[0]);
		// shorthand for Constraint c=matching.add(e);
		// Var violation =c.asBool.multiply(20);
		Var violation1 = matching.add(e).asBool().multiply(20);

		int[] q2values = qvalues[2];
		Element e2 = new Element(q2values, indexVar, "!=", qosdemand[1]);
		Var violation2 = matching.add(e2).asBool().multiply(60);

		int[] q3values = qvalues[3];
		Element e3 = new Element(q3values, indexVar, "!=", qosdemand[2]);
		Var violation3 = matching.add(e3).asBool().multiply(120);

		// just to confirm the statement: if optimization is on another
		// variable, serviceIndex is not costrained and simply the first value
		// in its domain is chosen:
		// int[] elseDomain = { 99, 20, 47 };
		// Var violationElse = matching.variable("violationElse", elseDomain);
		// Var[] weightVars = { violationElse };
		Var[] weightVars = { violation1, violation2, violation3 };

		// Optimization objective
		Var weightedSum = matching.sum(weightVars);
		weightedSum.setName("Total Constraint Violations");
		Solution solution = matching.getSolver().findOptimalSolution(
				Objective.MINIMIZE, weightedSum);
		solution.log();
	}

	public void buildModelSoftDifference(int[] qosdemand, int[][] qvalues)
			throws ConstraintException {
		int serviceIndexMax = qvalues[0].length - 1;
		// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
		Var indexVar = matching.variable("serviceIndex", 1, serviceIndexMax);
		// create the same constraints as buildModelSoftAsBool
		int[] serviceIds = qvalues[0];
		Var serviceIDs = matching.variable("serviceId", serviceIds);

		int[] q1values = qvalues[1];
		Var q1property = matching.variable("property1", q1values);

		int[] q2values = qvalues[2];
		Var q2property = matching.variable("property2", q2values);

		int[] q3values = qvalues[3];
		Var q3property = matching.variable("property3", q3values);

		Element e = new Element(q1values, indexVar, "!=", qosdemand[0]);
		Var violationE1 = matching.add(e).asBool().multiply(20);
		Element e2 = new Element(q2values, indexVar, "!=", qosdemand[1]);
		Var violationE2 = matching.add(e2).asBool().multiply(60);
		Element e3 = new Element(q3values, indexVar, "!=", qosdemand[2]);
		Var violationE3 = matching.add(e3).asBool().multiply(120);

		// not posting the elements resulted in serviceindex independent of the
		// values that properties get it did not have the relation
		// q1values[indexVar], so post them but optimize on violation
		// this one did not work, because once the element constraints are
		// posted
		// if there is no perfect match, the solver throws an exception that
		// there are no solutions to the problem. another option would be to
		// post those constraints
		// with a lower consistency level, however i think this is not
		// implemented yet. i will try that out later and ask feldman

		// i can add here multiply to tell the weights of different properties.
		Var violation1 = q1property.minus(qosdemand[0]).abs();
		violation1.setName("violation1");
		matching.add(violation1);
		Var[] weightVars = { violation1, violationE1, violationE2, violationE3 };
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

	// DECIDED TO USE THIS ONE !!!!!!!!!!!!!!!!
	// TODO post the certainID cetrainProperty conditions in a loop for each
	// property
	public void buildSimpleModelDifference(int[] qosdemand, int[][] qvalues)
			throws ConstraintException {
		int[] serviceIds = qvalues[0];
		Var serviceIDs = matching.variable("serviceId", serviceIds);
		int[] q1values = qvalues[1];
		Var q1property = matching.variable("property1", q1values);
		int[] q2values = qvalues[2];
		Var q2property = matching.variable("property2", q2values);
		int[] q3values = qvalues[3];
		Var q3property = matching.variable("property3", q3values);

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

		// i can add here multiply to tell the weights of different properties.
		Var violation1 = q1property.minus(qosdemand[0]).abs().multiply(20);
		violation1.setName("violation1");
		matching.add(violation1);
		Var[] weightVars = { violation1 };
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

		int[] serviceIds = { 0, 1, 2 };
		int[] qosdemand = { 2, 100, 96 };
		int[] q1values = { 5, 4, 3 };
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
