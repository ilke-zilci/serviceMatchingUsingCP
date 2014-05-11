package de.tub.fokus;

import javax.constraints.Problem;
import javax.constraints.Solution;
import javax.constraints.Solver;

public abstract class AbstractProblem {
	    
	    /**/
	    public abstract Solver createSolver();
	    /*define variables and constraints*/
		public abstract void buildModel();
		/*Set searching strategy, configure solver*/
		public abstract void configureSearch(); 
		/*find only one solution or all solutions or an optimal one*/
		public abstract void solve();
		/*format the output*/
		public abstract void prettyOut();
		
		protected Solver solver;
		protected Problem problem;
		protected Solution[] solutions;
		//TODO decide if you need to use javax.constraints.impl.Problem instead of javax.constraints.Problem if using choco2 in the background
	    public final void execute() {
	            this.createSolver();
	            this.buildModel();
	            this.configureSearch();
	            this.solve();
	           
	    }
		
	}
