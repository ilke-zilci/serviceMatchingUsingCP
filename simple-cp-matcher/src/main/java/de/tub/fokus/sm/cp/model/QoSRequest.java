package de.tub.fokus.sm.cp.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.constraints.Solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.Ints;

import de.tub.fokus.sm.cp.csp.SolveAsMatrixEqualProblem;
import de.tub.fokus.sm.cp.model.constraints.AggregatableConstraint;
import de.tub.fokus.sm.cp.model.constraints.DiscreteNumericConstraint;
import de.tub.fokus.sm.cp.model.constraints.QoSConstraint;
import de.tub.fokus.sm.cp.model.specs.DiscreteNumericSpec;
import de.tub.fokus.sm.cp.model.specs.QoSSpec;

public class QoSRequest {
	/**
	 * The logger of the class.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * for these constraints: one csp per constraint is needed
	 */
	private Map<String, QoSConstraint> ownCSPConstraints;

	// TODO think: the information which specifications go into which list must
	// be here ,
	// instead of giving the request the ready list or not.
	private Map<String, AggregatableConstraint> allInOneCSPConstraints;

	public Map<String, AggregatableConstraint> getAllInOneCSPConstraints() {
		return allInOneCSPConstraints;
	}

	public void setAllInOneCSPConstraints(
			Map<String, AggregatableConstraint> allInOneCSPConstraints) {
		this.allInOneCSPConstraints = allInOneCSPConstraints;
	}

	public void setRequestConstraints(
			Map<String, QoSConstraint> requestConstraints) {
		this.ownCSPConstraints = requestConstraints;
	}

	public void evaluate(List<ServiceDescription> serviceDescriptions) {

		// evaluate QosSpecifications which need one CSP for each of them
		if (ownCSPConstraints.isEmpty())
			return;
		for (Map.Entry<String, QoSConstraint> entry : ownCSPConstraints
				.entrySet()) {
			String constraintName = entry.getKey();
			QoSConstraint constraint = entry.getValue();
			for (ServiceDescription sd : serviceDescriptions) {
				Map<String, QoSSpec> serviceSpecification = sd
						.getSpecification();
				if (serviceSpecification.containsKey(constraintName)) {
					constraint.evaluate(serviceSpecification
							.get(constraintName));
				}
			}
		}

	}

	/**
	 *	 evaluates QoSSpecifications which can be aggregated in one matrix
		 assumes that if the request contains a constraint name in the
		 allInOneCSPconstraints,
	     that is one which can be solved with a matrix 
	    CP solver expects input as: 
	  	int[] qosdemand = { 3, 120, 98 };
		int[] q1values = { 3, 2, 3, 3, 2, 3, 3 };
		int[] q2values = { 120, 100, 120, 120, 100, 130, 130 };
		int[] q3values = { 98, 95, 98, 98, 95, 97, 97 };
		and returns:
		serviceindex 0 and service index 2 as the matching
	 * @throws ConstraintException 
		
	 */
	// TODO clean up here, maybe intervalconstraint also can be in this
	// list and casting not safe
	public void evaluateMatrix(List<ServiceDescription> serviceDescriptions)
			throws ConstraintException {
		// for each Qi add service specification's value to the list, to form
		// q1values, q2values ...
		for (Map.Entry<String, AggregatableConstraint> entry : allInOneCSPConstraints
				.entrySet()) {
			String constraintName = entry.getKey();
			AggregatableConstraint constraint = entry.getValue();
			for (ServiceDescription sd : serviceDescriptions) {
				Map<String, QoSSpec> serviceSpecification = sd
						.getSpecification();
				if (serviceSpecification.containsKey(constraintName)) {
					constraint.addToList(serviceSpecification
							.get(constraintName));
				} else {
					// if the service specification does not contain a value for
					// that constraint, use -1 to mark
					constraint.addToList(new DiscreteNumericSpec(-1));
				}
			}
		}

		// add the q1values,q2values .. to the matrix qvalues
		List<Integer> qosDemandList = new ArrayList<Integer>();

		int[][] qvaluesMatrix = new int[allInOneCSPConstraints.size()][serviceDescriptions
				.size()];
		Iterator<AggregatableConstraint> constraintIterator = allInOneCSPConstraints
				.values().iterator();
		for (int i = 0; i < allInOneCSPConstraints.size(); i++) {
			AggregatableConstraint a = constraintIterator.next();
			qvaluesMatrix[i] = Ints.toArray(a.getSpecifications());
		}

		// qosdemand is the values in each constraint has the requested value
		// for q1, q2, q3 and so on
		for (Map.Entry<String, AggregatableConstraint> entry : allInOneCSPConstraints
				.entrySet()) {
			DiscreteNumericConstraint constraint = (DiscreteNumericConstraint) entry
					.getValue();
			qosDemandList.add(constraint.getValue());
		}

		int[] qosDemand = Ints.toArray(qosDemandList);

		SolveAsMatrixEqualProblem matrixProblem = new SolveAsMatrixEqualProblem(
				qosDemand, qvaluesMatrix);
		List<Solution> solutions = matrixProblem.execute();
		// each solution is a serviceindex. so the service index is the index to
		// increment ranking from list of service descriptions

		int noOfSolutions;
		int[] matchingServiceIndexes = new int[solutions.size()];
		int i = 0;
		for (Solution solution : solutions) {
			noOfSolutions = solution.getNumberOfVars();
			if (noOfSolutions > 0)
				// expecting only one value in each solution with index 0 in
				// intresults
				matchingServiceIndexes[i++] = solution.getValue(0);
		}

		// evaluate exact for each discretenumericspec of this service.
		for (int j = 0; j < matchingServiceIndexes.length; j++) {
			LOGGER.debug("matching service index no " + j + " :"
					+ matchingServiceIndexes[j] + "\n");
			Map<String, QoSSpec> matchingServiceSpecification = serviceDescriptions
					.get(matchingServiceIndexes[j]).getSpecification();
			for (String constraintName : allInOneCSPConstraints.keySet()) {
				if (matchingServiceSpecification.containsKey(constraintName)) {
					matchingServiceSpecification.get(constraintName)
							.applyExactRankingRule();
				}
			}
		}

	}
}
