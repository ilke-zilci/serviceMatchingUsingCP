//===============================================
// J A V A  C O M M U N I T Y  P R O C E S S
// 
// J S R  3 3 1
// 
// TestXYZ Compatibility Kit
// 
//================================================
package de.tub.fokus.sandbox;

import java.util.ArrayList;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.SearchStrategy;
import javax.constraints.Solution;
import javax.constraints.SolutionIterator;
import javax.constraints.Solver;
import javax.constraints.VarSet;

public class FlightCrewsSimplified {

	static final String[] names = { "Tom", "David", "Jeremy", "Ron", "Joe",
			"Bill", "Fred", "Bob", "Mario", "Ed", "Carol", "Janet", "Tracy",
			"Marilyn", "Carolyn", "Cathy", "Inez", "Jean", "Heather", "Juliet" };

	static final int Tom = 0, David = 1, Jeremy = 2, Ron = 3, Joe = 4,
			Bill = 5, Fred = 6, Bob = 7, Mario = 8, Ed = 9, Carol = 10,
			Janet = 11, Tracy = 12, Marilyn = 13, Carolyn = 14, Cathy = 15,
			Inez = 16, Jean = 17, Heather = 18, Juliet = 19;

	static final int iNbMembers = 0, iStewards = 1, iHostesses = 2,
			iFrench = 3, iSpanish = 4, iGerman = 5;

	static int[] Staff = { Tom, David, Jeremy, Ron, Joe, Bill, Fred, Bob,
			Mario, Ed, Carol, Janet, Tracy, Marilyn, Carolyn, Cathy, Inez,
			Jean, Heather, Juliet };

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

	public static void main(String[] args) {
		try {
			Problem csp = ProblemFactory.newProblem("FlightCrew");
			VarSet Stewards = csp.variableSet("Stewards", new int[] { Tom,
					David, Jeremy, Fred, Ed });
			// VarSet Hostesses = csp.variableSet("Hostesses", new int[] {
			// Carol,
			// Janet, Tracy, Marilyn, Carolyn, Cathy, Inez, Jean, Heather,
			// Juliet });

			VarSet French = csp
					.variableSet("French", new int[] { David, Fred });
			// VarSet German = csp.variableSet("German", new int[] { Tom,
			// Jeremy,
			// Mario, Cathy, Juliet });
			// VarSet Spanish = csp.variableSet("Spanish", new int[] { Bill,
			// Fred,
			// Joe, Mario, Marilyn, Inez, Heather });

			// Stewards.require(Tom);
			// Stewards.require(David);

			// see what came out of this idea
			// VarSet intersection = csp.variableSet("intersection", new
			// int[]{Bill}); //("intersection", Stewards.intersection(French));

			// csp.post(Stewards.getCardinality(),">",3);
			//csp.post(Stewards.intersection(French).getCardinality(), "<", 1);
			// Stewards.intersection(French).setEmpty(true);
			// Hostesses.intersection(German).setEmpty(true);
			// csp.post(Hostesses.getCardinality(),">",2);
			// Spanish.intersection(German).setEmpty(true);
			 csp.post(French.getCardinality(),"<",2);
			// csp.post(German.getCardinality(),">",2);

			VarSet[] setVars = new VarSet[] { Stewards, French };
			for (int i = 0; i < setVars.length; i++) {
				System.out.println(setVars[i].toString());
			}

			Solver solver = csp.getSolver();
			solver.logStats();
			// solver.setTimeLimit(milliseconds); // mills
			SearchStrategy strategy = solver.getSearchStrategy();
			strategy.setVars(setVars);

			SolutionIterator iter = solver.solutionIterator();
			ArrayList<Solution> solutions = new ArrayList<Solution>();

			while (iter.hasNext()) {
				Solution solution = iter.next();
				solutions.add(solution);
				solution.log();
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

		} catch (Exception e) {
			System.err
					.println("Problem is over-constrained: errors during constraints posting");
			e.printStackTrace();
		}

	}

}
