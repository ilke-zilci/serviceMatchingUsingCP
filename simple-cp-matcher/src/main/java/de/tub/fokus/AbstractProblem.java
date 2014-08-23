package de.tub.fokus;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Solver;

public abstract class AbstractProblem {

	/**/
	public abstract Solver createSolver();

	/* define variables and constraints */
	public abstract void buildModel();

	/* Set searching strategy, configure solver */
	public abstract void configureSearch();

	// //you can configure strategy in the above method like this:
	// strategy.setVarSelectorType(VarSelectorType.MIN_DOMAIN_MIN_VALUE);
	// strategy.setValueSelector(new ValueSelectorMax());
	/* find only one solution or all solutions or an optimal one */
	public abstract void solveAll();

	/* format the output */
	public abstract void prettyOut();



	// TODO decide if you need to use javax.constraints.impl.Problem instead of
	// javax.constraints.Problem if using choco2 in the background
	public final void execute() {
		this.buildModel();
		this.createSolver();
		this.configureSearch();
		this.solveAll();
		}

}
