package de.tub.fokus.sm.cp.csp;

import java.util.ArrayList;
import java.util.List;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.SearchStrategy;
import javax.constraints.Solution;
import javax.constraints.SolutionIterator;
import javax.constraints.Solver;
import javax.constraints.Var;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureListMatchingProblem extends AbstractProblem {
	int[] provided;
	int[] required;

	public FeatureListMatchingProblem() {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("FeatureListMatching");
	}

	public FeatureListMatchingProblem(int[] provided, int[] required) {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("FeatureListMatching");
		this.provided = provided;
		this.required = required;
	}

	// here usage of p.postElement(int[] array, Var indexVar, oper, Var var)
	// based on PostElementVariations.defineIntVar
	// the relation for my case will be then how many solutions are there for
	// M=3 num[3]=4 condition num[3]=X[...],
	// again i have to assess the logs,and tell service which has index i had 3
	// entries in the solution set.
	// X is the list of browsers in the query
	// @Override
	public Var[] buildModel() {
		// with the index var i can state which part of the array i want to
		// investigate now, might help for heap problems to run the solver in
		// smaller steps
		final int explorer = 0, firefox = 1, chrome = 2, safari = 3, opera = 4;
		Var indexVar = matching.variable("providedIndex", 0, 5);
		// int[] googleBrowsers={explorer, firefox, chrome};

		matching.log(matching.getVars());
		// 1. option domain defined with min max
		// 2. option would be domain defined with specific values in an array
		Var requiredBrowsersVar = matching.variable("query", required);
		matching.postElement(provided, indexVar, "=", requiredBrowsersVar);
		Var[] vars = { indexVar, requiredBrowsersVar };
		return vars;
	}

	public static void main(String[] args) {

		int[] googleBrowsers = { 0, 1, 2 };
		int[] requiredBrowsers = { 0, 2, 3 };
		FeatureListMatchingProblem matchp = new FeatureListMatchingProblem(
				googleBrowsers, requiredBrowsers);

		List<Solution> solutions = matchp.execute();

	}

	@Override
	public void configureSearch() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Solution> execute() {
		Var[] vars = this.buildModel();
		this.createSolver();
		this.configureSearch();
		return this.solveIterative(vars);
	}

}
