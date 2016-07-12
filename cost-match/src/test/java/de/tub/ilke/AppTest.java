package de.tub.ilke;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.constraints.real.Ibex;
import org.chocosolver.solver.constraints.real.RealConstraint;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.RealVar;
import org.chocosolver.solver.variables.VariableFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    // find images with a simple unit price list
    public void it_should_find_image_unit_prices(){


    }


    //learning tests
    //solve 1.2>x>0.3 use a real variable as money will be bigdecimal or similar
    @org.junit.Test
    public void it_should_find_number_x(){
        // 1. Create a Solver
        Solver solver = new Solver("my first problem");
        // 2. Create variables through the variable factory
        IntVar x = VariableFactory.bounded("X", 0, 5, solver);
        IntVar y = VariableFactory.bounded("Y", 0, 5, solver);
        // 3. Create and post constraints by using constraint factories
        solver.post(IntConstraintFactory.arithm(x, "+", y, "<", 5));
        // 4. Define the search strategy
        solver.set(IntStrategyFactory.lexico_LB(x, y));
        // 5. Launch the resolution process
        solver.findAllSolutions();

        //6. Print search statistics
        Chatterbox.printSolutions(solver);
        Chatterbox.printStatistics(solver);

    }

    @org.junit.Test
    public void it_should_find_onepoint_decimal_x(){
        // 1. Create a Solver
        Solver solver = new Solver("my first problem");
        // must use BigDecimal for currency or for precise values in general

        // 2. Create variables through the variable factory
        RealVar realVar1 = VariableFactory.real("public-cloud-1", 0.2d, 1.0e8d, 0.001d, solver);
        IntVar intVar = VariableFactory.bounded("i", 0, 4, solver);
        RealVar basedOnIntVar = VariableFactory.real(intVar, 0.01d);

        //  RealConstraint k1 = new RealConstraint("{0} > {1}",x,y);
        // RealConstraint k2 = new RealConstraint("{0} < {1}",x,y);
        // 3. Create and post constraints by using constraint factories


        IntVar y = VariableFactory.bounded("Y", 0, 5, solver);




        //solver.post(RealConstraint);
        // 4. Define the search strategy
        // solver.set(IntStrategyFactory.lexico_LB(realVar1, y));
        // 5. Launch the resolution process
        solver.findAllSolutions();

        //6. Print search statistics
        Chatterbox.printSolutions(solver);
        Chatterbox.printStatistics(solver);

    }

    public void it_should_solve_realConstraint_Externally_with_Ibex(){


    }

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

        //so that sum of capacity is >= numberOfRequests
        // how to model it so that the solver knows small adds little cost, while medium adds more cost?
        IntVar capacity = VariableFactory.fixed("numberOfRequests",numberOfRequests,solver);
        //IntConstraintFactory.

        //and so that sum of pricePerHour is minimum
    }



}
