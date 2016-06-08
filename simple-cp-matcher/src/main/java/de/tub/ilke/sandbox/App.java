package de.tub.ilke.sandbox;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Solver;
import javax.constraints.Var;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//PROBLEM DEFINITION
        Problem p = ProblemFactory.newProblem("ServiceMatching");
        //DEFINE VARIABLES
        Var x= p.variable("x", 1, 10);
        Var y= p.variable("y", 1, 10);
        Var z= p.variable("z", 1, 10);
        // a variable which is calculated by using others
        Var cost = x.multiply(3).multiply(y).minus(z.multiply(4));
        //find solution while cost is minimal
        
        //define and post constraints
        p.post(x, "<", y);
        p.post(x.plus(y), "=", z);
        
        //PROBLEM SOLUTION
        p.log("Find solution");
 //       Solver solver =p.getSolver();
//        Solution solution = solver.findSolution();
//        if (solution !=null)
//        	solution.log();
//        else
//        	p.log("No solution");
//        p.log("Cost" + cost);
        Solver solver = p.getSolver();
		solver.setMaxNumberOfSolutions(8);
		//solver.findOptimalSolution(Objective.MINIMIZE, cost);
		//Solution solution = solver.findOptimalSolution(Objective.MINIMIZE, cost, OptimizationStrategy.NATIVE);
		//solution.log();
		Solution[] solutions = solver.findAllSolutions();
		for(Solution sol : solutions) {
			sol.log();
		}
    }
}
