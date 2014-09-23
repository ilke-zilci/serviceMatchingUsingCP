package de.tub.fokus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import de.tub.fokus.sm.cp.model.ConstraintException;
import de.tub.fokus.sm.cp.model.QoSMatchmaker;
import de.tub.fokus.sm.cp.model.QoSRequest;
import de.tub.fokus.sm.cp.model.ServiceDescription;
import de.tub.fokus.sm.cp.model.ServiceRepository;
import de.tub.fokus.sm.cp.model.constraints.AggregatableConstraint;
import de.tub.fokus.sm.cp.model.constraints.DiscreteNumericConstraint;
import de.tub.fokus.sm.cp.model.constraints.FeatureListConstraint;
import de.tub.fokus.sm.cp.model.constraints.QoSConstraint;
import de.tub.fokus.sm.cp.model.specs.DiscreteNumericSpec;
import de.tub.fokus.sm.cp.model.specs.FeatureListSpec;
import de.tub.fokus.sm.cp.model.specs.QoSSpec;

public class NumericFeatureIntegrationTest {
	private ServiceRepository repo;
	private List<ServiceDescription> serviceDescriptions;
	private ServiceDescription sd1;
	private ServiceDescription sd2;
	private ServiceDescription sd3;
	private Map<String, QoSSpec> specification;
	private Map<String, QoSSpec> specification2;
	private Map<String, QoSSpec> specification3;
	Map<String, QoSConstraint> requestConstraints;
	Map<String, AggregatableConstraint> allInOneCSPConstraints;

	@Before
	// will be run before any test in the class
	public void setUp() {

		repo = new ServiceRepository();
		serviceDescriptions = new ArrayList<ServiceDescription>();
		sd1 = new ServiceDescription();
		sd2 = new ServiceDescription();
		sd3 = new ServiceDescription();
		specification = new HashMap<String, QoSSpec>();
		specification2 = new HashMap<String, QoSSpec>();
		specification3 = new HashMap<String, QoSSpec>();
		requestConstraints = new HashMap<String, QoSConstraint>();
		allInOneCSPConstraints = new HashMap<String, AggregatableConstraint>();
	}

	@Test
	public void matchesNumericAndFeature() throws ConstraintException {

		// add three discrete numeric constraints to the allInOneCSPConstraints
		DiscreteNumericConstraint establishmentYear = new DiscreteNumericConstraint();
		establishmentYear.setValue(2010);
		DiscreteNumericConstraint maintenanceWindow = new DiscreteNumericConstraint();
		maintenanceWindow.setValue(20);
		DiscreteNumericConstraint numberOfUsers = new DiscreteNumericConstraint();
		numberOfUsers.setValue(10);
		QoSRequest request = new QoSRequest();
		allInOneCSPConstraints.put("establishmentYear", establishmentYear);
		allInOneCSPConstraints.put("maintenanceWindow", maintenanceWindow);
		allInOneCSPConstraints.put("numberOfUsers", numberOfUsers);
		// TODO from here with @After annotation after each test? too many
		// repetition of this!!!!
		request.setAllInOneCSPConstraints(allInOneCSPConstraints);
		int[] requested = { 0, 1 };
		requestConstraints
				.put("browsers", new FeatureListConstraint(requested));
		request.setRequestConstraints(requestConstraints);

		// add specifications for these to the service descriptions
		// for the first service description
		specification.put("establishmentYear", new DiscreteNumericSpec(2010));
		specification.put("maintenanceWindow", new DiscreteNumericSpec(20));
		specification.put("numberOfUsers", new DiscreteNumericSpec(10));
		int[] provided = { 0, 1, 4 };
		specification.put("browsers", new FeatureListSpec(provided));
		sd1.setSpecification(specification);
		// for the second service description
		specification2.put("establishmentYear", new DiscreteNumericSpec(2012));
		specification2.put("maintenanceWindow", new DiscreteNumericSpec(30));
		// specification2.put("numberOfUsers", new DiscreteNumericSpec(8));
		int[] provided2 = { 0, 4 };
		specification2.put("browsers", new FeatureListSpec(provided2));
		sd2.setSpecification(specification2);
		// for the first service description
		specification3.put("establishmentYear", new DiscreteNumericSpec(2013));
		specification3.put("maintenanceWindow", new DiscreteNumericSpec(15));
		specification3.put("numberOfUsers", new DiscreteNumericSpec(5));
		int[] provided3 = { 0, 1 };
		specification3.put("browsers", new FeatureListSpec(provided3));
		sd3.setSpecification(specification3);
		// --------[add the service descriptions to repository]--------------
		serviceDescriptions.add(sd1);
		serviceDescriptions.add(sd2);
		serviceDescriptions.add(sd3);
		repo.serviceDescriptions = serviceDescriptions;
		QoSMatchmaker matcher = new QoSMatchmaker(repo, request);

		try {
			matcher.solveMatchingProblem();
		} catch (ConstraintException e) {
			e.printStackTrace();
			System.out.println("over constrained problem");
		}

	}
}
