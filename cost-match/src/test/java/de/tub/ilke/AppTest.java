package de.tub.ilke;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;

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
    public void it_should_find_onepoint_decimal_x(){
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
}
