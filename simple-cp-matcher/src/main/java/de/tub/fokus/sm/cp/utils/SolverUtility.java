package de.tub.fokus.sm.cp.utils;

import java.util.ArrayList;

import javax.constraints.SearchStrategy;
import javax.constraints.Solution;
import javax.constraints.SolutionIterator;
import javax.constraints.Solver;
import javax.constraints.VarSet;

public class SolverUtility {
	public void findAndLogNumberOfSolutions(Solver solver, int numberOfResults) {
		int maxNumberOfSolutions = numberOfResults;
		solver.setMaxNumberOfSolutions(maxNumberOfSolutions);
		Solution[] solutionsA = solver.findAllSolutions();

		if (solutionsA != null) {
			for (int j = 0; j < solutionsA.length; j++) {
				solutionsA[j].log();
			}
		}
	}
	
	public void findandLogAllSolutions(VarSet[] setVars, Solver solver){
		solver.logStats();
		// solver.setTimeLimit(milliseconds); // mills
		SearchStrategy strategy = solver.getSearchStrategy();
		strategy.setVars(setVars);

		SolutionIterator iter = solver.solutionIterator();
		ArrayList<Solution> solutions = new ArrayList<Solution>();

		while (iter.hasNext()) {
			Solution solution = iter.next();
			solutions.add(solution);
			System.out.println("the array of VarSets: \n");
			for (int i = 0; i < setVars.length; i++) {
				System.out.println("set name: " + setVars[i].getName());
				System.out.println("set cardinality"
						+ setVars[i].getCardinality());
				System.out.println(setVars[i].toString() + "\n\n");
			}
		}
		
		Solution[] array = new Solution[solutions.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = solutions.get(i);
		}
		
		
	}
}
