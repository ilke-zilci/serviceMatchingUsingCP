package de.tub.fokus.sm.cp.csp;

import java.util.ArrayList;
import java.util.List;

import javax.constraints.Problem;
import javax.constraints.SearchStrategy;
import javax.constraints.Solution;
import javax.constraints.SolutionIterator;
import javax.constraints.Solver;
import javax.constraints.Var;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tub.fokus.sm.cp.model.ConstraintException;

public abstract class AbstractProblem {
	Problem matching;
	Solver solver;
	Solution[] solutions;

	/**
	 * The logger of the class.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	public Solver createSolver() {
		solver = matching.getSolver();
		return solver;
	}

	/* Set searching strategy, configure solver */
	public abstract void configureSearch();

	// you can configure strategy in the above method like this:
	// strategy.setVarSelectorType(VarSelectorType.MIN_DOMAIN_MIN_VALUE);
	// strategy.setValueSelector(new ValueSelectorMax());
	/* find only one solution or all solutions or an optimal one */

	public List<Solution> solveAll() throws ConstraintException {

		List<Solution> solutionsList = new ArrayList<Solution>();
		matching.log("=== Find all solutions:");

		solutions = solver.findAllSolutions();
		if (solutions.length == 0)
			throw new ConstraintException();
		if (solutions.length > 0) {
			for (int i = 0; i < solutions.length; i++) {
				solutions[i].log();
				solutionsList.add(solutions[i]);
			}
		}

		return solutionsList;
	}

	// TODO how to handle if there are no solutions?
	public List<Solution> solveIterative(Var[] vars) {
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
				LOGGER.debug("variable name: " + vars[i].getName());
				LOGGER.debug(vars[i].toString() + "\n");
			}
		}

		// in the end i had copied the contents of the list to an array, i
		// think if not copied they will change if other variables are added to
		// the
		// problem, this was interesting for the case if i want to solve
		// everything in one problem, then i decided that that wont work.
		// // TODO find out why you copied these to an array
		// Solution[] array = new Solution[solutions.size()];
		// for (int i = 0; i < array.length; i++) {
		// array[i] = solutions.get(i);
		// }
		//
		return solutions;
	}

	// TODO decide if you need to use javax.constraints.impl.Problem instead of
	// javax.constraints.Problem if using choco2 in the background

	public abstract List<Solution> execute() throws ConstraintException;

}
