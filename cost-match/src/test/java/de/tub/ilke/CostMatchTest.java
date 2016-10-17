package de.tub.ilke;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by izi on 10/17/16.
 */
public class CostMatchTest {

    public void it_should_get_list_of_VM_types(){
        Solver solver = new Solver("VM effort distribution problem");
        //vmachine: type, capacity, price
        List<VMachine> vmachines= Arrays.asList(new VMachine("m1.small", 300, 1),
                new VMachine("m1.medium", 500, 2),
                new VMachine("m1.big", 800, 3)
        );

        int numberOfRequests = 3000;
        //results
        IntVar numberOfSmallVMs =  VariableFactory.bounded("number_Of_smalls", 0, 100, solver);
        IntVar numberOfMediumVMs =  VariableFactory.bounded("number_Of_mediums", 0, 100, solver);
        IntVar numberOfLargeVMs =  VariableFactory.bounded("number_Of_larges", 0, 100, solver);

        int capacityOfSmallVM = 10;
        int capacityOfMediumVM = 15;
        int capacityOfLargeVM = 20;


        IntVar[] numberofvms = {numberOfSmallVMs,numberOfMediumVMs,numberOfLargeVMs};
        IntVar totalCapacity = VariableFactory.bounded("number_Of_requests", 0, numberOfRequests, solver);

        Constraint capacityMet = IntConstraintFactory.arithm(totalCapacity, "=", numberOfRequests);
        //solver.post(IntConstraintFactory.arithm(totalCapacity, "+", "<", 5));
        solver.post(capacityMet);
        //so that sum of capacity is >= numberOfRequests
        //how to model it so that the solver knows small adds little cost, while medium adds more cost?
        IntVar capacity = VariableFactory.fixed("numberOfRequests",numberOfRequests,solver);
        //IntConstraintFactory.

        //and so that sum of pricePerHour is minimum
    }

    // find images with a simple unit price list
    public void it_should_find_image_unit_prices(){


    }
}
