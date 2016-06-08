package de.tub.ilke;

import de.tub.ilke.matcher.core.model.ServiceDescription;
import de.tub.ilke.matcher.core.model.ServiceRepository;
import de.tub.ilke.matcher.core.model.constraints.AggregatableConstraint;
import de.tub.ilke.matcher.core.model.constraints.QoSConstraint;
import de.tub.ilke.matcher.core.model.constraints.impl.DiscreteNumericConstraint;
import de.tub.ilke.matcher.core.model.specs.QoSSpec;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilke on 6/7/16.
 */
public class PriceMatchingTest {
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
    public void matchesUnitPrice(){

        DiscreteNumericConstraint perHourPrice = new DiscreteNumericConstraint();

        perHourPrice.setValue(2);

    }

}
