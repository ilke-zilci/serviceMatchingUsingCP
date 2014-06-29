package de.tub.fokus;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Solver;
import javax.constraints.Var;

import de.tub.fokus.sandbox.PostElementVariations;

/**
 * implementation for the simple numeric matching problem
 * service descriptions are arrays of integer values for qos parameters
 * assumption is that always the higher values are better 
 * @author ilke
 *
 */
public class SimpleNumericMatchingProblem extends AbstractProblem {
	Problem matching;
	Solver solver;
	Solution[] solutions;

	public SimpleNumericMatchingProblem() {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("ServiceMatching");
	}

	// DEFINE SERVICE DESCRIPTIONS AS ARRAYS OF FIXED VALUES
	// EACH SERVICE HAS ITS VALUES FOR AVGEXTIME, ATHR and AVGAVAIL
	int[] service1 = { 3, 120, 98 };
	int[] service2 = { 2, 100, 95 };
	int[] service3 = { 3, 130, 97 };
	int[] service4 = { 3, 120, 98 };
	int[] service5 = { 2, 100, 95 };
	int[] service6 = { 3, 130, 97 };
	int[] service7 = { 3, 130, 97 };
	// assume all have to be smaller
	int[] qosdemand = { 0, 110, 96 };

	int[][] _services = { service1, service2, service3, service4, service5,
			service6, service7 }, services = _services;

	// if solver gets created before posting constraints
	// error : invalid attempt to get indomainvar of Var varname
	@Override
	public void buildModel() {
		
		// TODO serviceX.QOSTERM1 for each service, add to q1values
		int[] q1values = { 3, 2, 3, 3, 2, 3, 3 };
		int[] q2values = { 120, 100, 130, 120, 100, 130, 130 };
		int[] q3values = { 98, 95, 97, 98, 95, 97, 97 };
		int[][] qvalues = { q1values, q2values, q3values };
		
		// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
		Var indexVar = matching.variable("serviceIndex", 0, 6);
		 for (int j=0; j<qosdemand.length; j++) {
		 matching.postElement(qvalues[j], indexVar, ">", qosdemand[j]);
		 }
	}
	@Override
	public Solver createSolver() {
		solver = matching.getSolver();
		return solver;
	}

	@Override
	public void solve() {

		matching.log("=== Find all solutions:");
		
		solutions = solver.findAllSolutions();
		if (solutions != null) {
			for (int i = 0; i < solutions.length; i++) {
				solutions[i].log();
			}
		}
	}
	
	@Override
	public void configureSearch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void prettyOut() {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		SimpleNumericMatchingProblem matchp = new SimpleNumericMatchingProblem();
		matchp.execute();
		
	}

}
