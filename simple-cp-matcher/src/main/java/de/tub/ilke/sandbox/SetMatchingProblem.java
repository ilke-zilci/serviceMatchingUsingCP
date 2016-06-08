package de.tub.ilke.sandbox;

import java.util.List;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Solver;
import javax.constraints.VarSet;

import de.tub.ilke.matcher.core.csp.AbstractProblem;

public class SetMatchingProblem extends AbstractProblem {

	Problem matching;
	Solver solver;
	Solution[] solutions;

	public SetMatchingProblem() {
		// PROBLEM DEFINITION
		matching = ProblemFactory.newProblem("SetMatching");
	}

	@Override
	public Solver createSolver() {
		solver = matching.getSolver();
		return solver;
	}

	public void buildModel() {
		// compatible browser names mapping to integers , later it might be hold
		// as List<Browser> browsers Browser{browserId,
		// browserName,recentversion,providedversion}
		final int explorer = 0, firefox = 1, chrome = 2, safari = 3, opera = 4;

		VarSet googleBrowsers = null;
		VarSet salesforceBrowsers = null;
		VarSet demandedBrowsers = null;
		try {
			// google drive business compatible browsers
			googleBrowsers = matching.variableSet("googleBrowsers", new int[] {
					explorer, firefox, chrome });
			googleBrowsers.require(explorer);
			googleBrowsers.require(firefox);
			googleBrowsers.require(chrome);

			// salesforce compatible browsers
			salesforceBrowsers = matching.variableSet("salesforceBrowsers",
					new int[] { explorer, safari, opera });
			salesforceBrowsers.require(explorer);
			salesforceBrowsers.require(safari);
			salesforceBrowsers.require(opera);
			// define basic service query
			demandedBrowsers = matching.variableSet("demandedBrowsers",
					new int[] { chrome, firefox });
			// googleBrowsers(0)[1] googleBrowsers(1)[1] googleBrowsers(2)[1]
			// salesforceBrowsers(0)[1] salesforceBrowsers(3)[1]
			// salesforceBrowsers(4)[0] requiredBrowsers(0)[1]
			// requiredBrowsers(1)[1] requiredBrowsers&googleBrowsers(0)[1]
			// requiredBrowsers&googleBrowsers(1)[1]
			// requiredBrowsers&salesforceBrowsers(0)[1] [1]
			// add VarSets to array
			VarSet[] servicesCapability = { googleBrowsers, salesforceBrowsers };
			// DEFINE SERVICE QUERY AS CONSTRAINTS WITH POST METHOD
			// first query, at least one browser should match, expected result
			// both fit
			for (int i = 0; i < servicesCapability.length; i++) {
				if (demandedBrowsers.intersection(servicesCapability[i]) == null) {
					// remove the salesforce varset from the problem or delete
					// all values in the set
					// what did the following do? TODO unserstand if the
					// variable names are unique identifiers, if they are not
					// what is?
					// salesforceBrowsers =
					// matching.variableSet("salesforceBrowsers",
					// new int[] {});
					salesforceBrowsers.remove(opera);
					salesforceBrowsers.remove(safari);
					salesforceBrowsers.remove(explorer);
					// setEmpty posts a constraint that cardinality of this set
					// variable will be 0
					// however it ends up in propagation error
					servicesCapability[i].setEmpty(true);

					// try posting a constraint which removes it from results
				} else {
					matching.post(
							demandedBrowsers
									.intersection(servicesCapability[i])
									.getCardinality(), ">", 0);
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {

	}

	@Override
	public void configureSearch() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Solution> execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
