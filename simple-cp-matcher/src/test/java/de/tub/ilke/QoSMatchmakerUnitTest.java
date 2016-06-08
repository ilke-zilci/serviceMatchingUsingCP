package de.tub.ilke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.tub.ilke.matcher.core.model.ConstraintException;
import de.tub.ilke.matcher.core.model.QoSMatchmaker;
import de.tub.ilke.matcher.core.model.QoSRequest;
import de.tub.ilke.matcher.core.model.ServiceDescription;
import de.tub.ilke.matcher.core.model.ServiceRepository;
import de.tub.ilke.matcher.core.model.constraints.QoSConstraint;
import de.tub.ilke.matcher.core.model.constraints.impl.FeatureListConstraint;
import de.tub.ilke.matcher.core.model.specs.FeatureListSpec;
import de.tub.ilke.matcher.core.model.specs.QoSSpec;

public class QoSMatchmakerUnitTest {
	private ServiceRepository repo;
	private List<ServiceDescription> serviceDescriptions;
	private ServiceDescription sd;
	private Map<String, QoSSpec> specification;
	Map<String, QoSConstraint> requestConstraints;

	@Before
	// will be run before any test in the class
	public void setUp() {

		repo = new ServiceRepository();
		serviceDescriptions = new ArrayList<ServiceDescription>();
		sd = new ServiceDescription();
		specification = new HashMap<String, QoSSpec>();
		requestConstraints = new HashMap<String, QoSConstraint>();

	}

	@Test
	public void testEvaluateSuperMatch() throws ConstraintException {

		// fill in the hashmap
		// TODO it'd be better reading from a file or the discovery
		// component does the reading and converts to QoSSpec

		// -----------------------[initialize service description]----------
		int[] provided = { 0, 1, 4 };
		specification.put("browsers", new FeatureListSpec(provided));
		sd.setSpecification(specification);
		// --------[add the service description to repository]--------------
		serviceDescriptions.add(sd);
		// --------------[initialize request]-------------------
		QoSRequest request = new QoSRequest();
		// the array in the feature list constraint
		int[] requested = { 0, 1 };
		requestConstraints
				.put("browsers", new FeatureListConstraint(requested));

		// TODO from here with @After annotation after each test? too many
		// repetition of this!!!!
		request.setRequestConstraints(requestConstraints);

		// wrap up
		repo.serviceDescriptions = serviceDescriptions;
		QoSMatchmaker matcher = new QoSMatchmaker(repo, request);

		matcher.solveMatchingProblem();
	}

	@Test
	public void testEvaluatePartialMatch() throws ConstraintException {

		// fill in the hashmap
		// TODO it'd be better to read from a file or the discovery
		// component does the reading and converts to QoSSpec

		// -----------------------[initialize service description]----------
		int[] provided = { 0, 4 };
		specification.put("browsers", new FeatureListSpec(provided));
		sd.setSpecification(specification);
		// --------[add the service description to repository]------------------
		serviceDescriptions.add(sd);
		// --------------[initialize request]-------------------
		QoSRequest request = new QoSRequest();
		// the array in the feature list constraint
		int[] requested = { 0, 1 };
		requestConstraints
				.put("browsers", new FeatureListConstraint(requested));
		request.setRequestConstraints(requestConstraints);

		// wrap up
		repo.serviceDescriptions = serviceDescriptions;
		QoSMatchmaker matcher = new QoSMatchmaker(repo, request);

		matcher.solveMatchingProblem();
	}

	@Test
	public void testEvaluateExactMatch() throws ConstraintException {

		// fill in the hashmap
		// TODO it'd be better reading from a file or the discovery
		// component does the reading and converts to QoSSpec

		// -----------------------[initialize service description]----------
		int[] provided = { 0, 1 };
		specification.put("browsers", new FeatureListSpec(provided));
		sd.setSpecification(specification);
		// --------[add the service description to repository]------------------
		serviceDescriptions.add(sd);
		// --------------[initialize request]-------------------
		QoSRequest request = new QoSRequest();
		// the array in the feature list constraint
		int[] requested = { 0, 1 };
		requestConstraints
				.put("browsers", new FeatureListConstraint(requested));
		request.setRequestConstraints(requestConstraints);

		// wrap up
		repo.serviceDescriptions = serviceDescriptions;
		QoSMatchmaker matcher = new QoSMatchmaker(repo, request);

		matcher.solveMatchingProblem();
	}

	@Test
	public void testEvaluateFail() throws ConstraintException {

		// fill in the hashmap
		// TODO it'd be better reading from a file or the discovery
		// component does the reading and converts to QoSSpec

		// -----------------------[initialize service description]----------
		int[] provided = { 2, 3 };
		specification.put("browsers", new FeatureListSpec(provided));
		sd.setSpecification(specification);
		// --------[add the service description to repository]------------------
		serviceDescriptions.add(sd);
		// --------------[initialize request]-------------------
		QoSRequest request = new QoSRequest();
		// the array in the feature list constraint
		int[] requested = { 0, 1 };
		requestConstraints
				.put("browsers", new FeatureListConstraint(requested));
		request.setRequestConstraints(requestConstraints);

		// wrap up
		repo.serviceDescriptions = serviceDescriptions;
		QoSMatchmaker matcher = new QoSMatchmaker(repo, request);

		matcher.solveMatchingProblem();
	}

	@Test(expected = ConstraintException.class)
	public void testEvaluateEmptyConstraint() throws ConstraintException {
		// TODO if the request is empty, choco solver throws
		// indexoutofboundsexception
		// to avoid this, dont create a featurelistconstraint if the requested
		// array is empty

		// -----------------------[initialize service description]----------
		int[] provided = { 0, 1 };
		specification.put("browsers", new FeatureListSpec(provided));
		sd.setSpecification(specification);
		// --------[add the service description to repository]------------------
		serviceDescriptions.add(sd);
		// --------------[initialize request]-------------------
		QoSRequest request = new QoSRequest();
		// the array in the feature list constraint
		int[] requested = {};

		requestConstraints
				.put("browsers", new FeatureListConstraint(requested));
		request.setRequestConstraints(requestConstraints);

		// wrap up
		repo.serviceDescriptions = serviceDescriptions;
		QoSMatchmaker matcher = new QoSMatchmaker(repo, request);

		matcher.solveMatchingProblem();
	}

	@Test
	public void testEvaluateEmptySpec() throws ConstraintException {
		// -----------------------[initialize service description]----------
		int[] provided = {};
		specification.put("browsers", new FeatureListSpec(provided));
		sd.setSpecification(specification);
		// --------[add the service description to repository]------------------
		serviceDescriptions.add(sd);
		// --------------[initialize request]-------------------
		QoSRequest request = new QoSRequest();
		// the array in the feature list constraint
		int[] requested = { 0, 1 };

		requestConstraints
				.put("browsers", new FeatureListConstraint(requested));
		request.setRequestConstraints(requestConstraints);
		request.setRequestConstraints(requestConstraints);

		// wrap up
		repo.serviceDescriptions = serviceDescriptions;
		QoSMatchmaker matcher = new QoSMatchmaker(repo, request);

		matcher.solveMatchingProblem();
	}

	@Test
	@Ignore
	public void testEvaluateSameConstraintTwice() throws ConstraintException {
		// TODO if there is the same constraint twice, the solver just iterates
		// over them an calculates although there must be some mechanism to
		// stop this from the beginning
		// fill in the hashmap
		// TODO it'd be better reading from a file or the discovery
		// component does the reading and converts to QoSSpec

		// -----------------------[initialize service description]----------
		int[] provided = { 0, 1 };
		specification.put("browsers", new FeatureListSpec(provided));
		sd.setSpecification(specification);
		// --------[add the service description to repository]------------------
		serviceDescriptions.add(sd);
		// --------------[initialize request]-------------------
		QoSRequest request = new QoSRequest();
		// the array in the feature list constraint
		int[] requested = { 0, 1 };
		int[] requested2 = { 0 };
		requestConstraints
				.put("browsers", new FeatureListConstraint(requested));
		requestConstraints.put("browsers",
				new FeatureListConstraint(requested2));

		request.setRequestConstraints(requestConstraints);

		// wrap up
		repo.serviceDescriptions = serviceDescriptions;
		QoSMatchmaker matcher = new QoSMatchmaker(repo, request);

		matcher.solveMatchingProblem();
	}
}
