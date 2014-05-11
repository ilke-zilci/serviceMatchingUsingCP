package de.tub.fokus;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Solver;
import javax.constraints.Var;

public class SimpleMatchingProblem extends AbstractProblem {
    
	int[][] services;
	
	@Override
	public Solver createSolver() {

		return problem.getSolver();
	}

	@Override
	public void buildModel() {

		//PROBLEM DEFINITION
        Problem p = ProblemFactory.newProblem("ServiceMatching");
        //DEFINE SERVICE DESCRIPTIONS AS ARRAY OF FIXED VALUES
        //one thing is defining variables with domains and another is what value the service description has for that variable
        //EACH SERVICE HAS ITS VALUES FOR AVGEXTIME, ATHR and AVGAVAIL
        int[] service1= {3,120,98};
        int[] service2= {2,100,95};
        int[] service3= {3,130,97};
        int[] service4= {3,120,98};
        int[] service5= {2,100,95};
        int[] service6= {3,130,97};
        int[] service7= {3,130,97};
        //assume all have to be smaller
        int[] qosdemand= {2,110,96}; 
       
        
        int[][] _services = {service1, service2, service3, service4,service5, service6,service7}; 
        services = _services;
        
        //serviceX.QOSTERM1 for each service, add to q1values
        int[] q1values={};
        int[] q2values={};
        int[] q3values={};
        int[][] qvalues={q1values,q2values,q3values};
        //DEFINE SERVICE QUERY AS CONSTRAINTS
        
        Var indexVar= p.variable("index", 0,2);
       
        for (int j : qosdemand) {
        	p.postElement(qvalues[j], indexVar, "<", qosdemand[j]);	
		} 
               
        //see bins.java
        //quite similar problem, where some variables are constrained to have specific values
        
	}

	
	@Override
	public void configureSearch() {
//	//later configure here one of the following
//	strategy.setVarSelectorType(VarSelectorType.MIN_DOMAIN_MIN_VALUE);
//	strategy.setValueSelector(new ValueSelectorMax());
	}

	@Override
	public void solve() {
		solutions = solver.findAllSolutions();
		for(Solution sol : solutions) {
			sol.log();
		}
	}

	@Override
	public void prettyOut() {
		// TODO Auto-generated method stub
	   
	}

}
