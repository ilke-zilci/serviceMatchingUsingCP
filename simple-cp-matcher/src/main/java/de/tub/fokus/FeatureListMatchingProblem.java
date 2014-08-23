package de.tub.fokus;

import java.util.ArrayList;
import java.util.List;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.SearchStrategy;
import javax.constraints.Solution;
import javax.constraints.SolutionIterator;
import javax.constraints.Solver;
import javax.constraints.Var;

public class FeatureListMatchingProblem {
	Problem matching;
	Solver solver;
	Solution[] solutions;

	public FeatureListMatchingProblem() {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("ServiceMatching");
	}
	
	
	//here usage of p.postElement(int[] array, Var indexVar, oper, Var var) based on PostElementVariations.defineIntVar
	//the relation for my case will be then how many solutions are there for M=3 num[3]=4 condition num[3]=X[...], 
	//again i have to assess the logs,and tell service which has index i had 3 entries in the solution set.
	//X is the list of browsers in the query
	//@Override
	public Var[] buildModel() {
		//with the index var i can state which part of the array i want to investigate now, might help for heap problems to run the solver in smaller steps
		final int explorer = 0, firefox = 1, chrome = 2, safari = 3, opera = 4;
		Var indexVar = matching.variable("G",0,5);
		//int[] googleBrowsers={explorer, firefox, chrome};
		int[] googleBrowsers={0,1,2};
		int[] requiredBrowsers={0,2,3};
		matching.log(matching.getVars());
	    //1. option domain defined with min max
		//2. option would be domain defined with specific values in an array
		Var requiredBrowsersVar= matching.variable("Q",requiredBrowsers);
		matching.postElement(googleBrowsers, indexVar, "=", requiredBrowsersVar);
		Var[] vars = {indexVar,requiredBrowsersVar};
		return vars;
	}	
	//TODO 5 oktober %60 fertge version abgeben konkrete termin vereinbaren fur evaluation
	//@Override
	public Solver createSolver() {
		solver = matching.getSolver();
		return solver;
	}

	//@Override
	public void solveAll() {

		matching.log("=== Find all solutions:");
		
		solutions = solver.findAllSolutions();
		if (solutions != null) {
			for (int i = 0; i < solutions.length; i++) {
				solutions[i].log();
			}
		}
	}
	
	public void solveIterative(Var[] vars){
		solver.logStats();
		// is this mandatory, what is the purpose of this?
		SearchStrategy strategy = solver.getSearchStrategy();
        strategy.setVars(vars);
        
		SolutionIterator iter = solver.solutionIterator();
		List<Solution> solutions = new ArrayList<Solution>();

		while (iter.hasNext()) {
			Solution solution = iter.next();
			solutions.add(solution);
			
			for (int i = 0; i < vars.length; i++) {
				System.out.println("variable name: " + vars[i].getName());
				System.out.println(vars[i].toString() + "\n\n");
			}
		}
		
		//in the end copy the contents of the list to an array, why?
		Solution[] array = new Solution[solutions.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = solutions.get(i);
		}
		
	}
	
	//@Override
	public void configureSearch() {
		// TODO Auto-generated method stub

	}

	//@Override
	public void prettyOut() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		FeatureListMatchingProblem matchp = new FeatureListMatchingProblem();
		Var[] vars =matchp.buildModel();
		matchp.createSolver();
		matchp.configureSearch();
		matchp.solveIterative(vars);
		//matchp.solveAll();
}
}
