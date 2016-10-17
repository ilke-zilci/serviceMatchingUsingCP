package de.tub.ilke;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.constraints.SatFactory;
import org.chocosolver.solver.constraints.nary.cnf.LogOp;
import org.chocosolver.solver.search.solution.Solution;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by izi on 10/17/16.
 */
public class ExampleProblemTest {
    //learning tests
    //solve 1.2>x>0.3 use a real variable as money will be bigdecimal or similar
    @org.junit.Test
    public void it_should_solve_x_plus_y_smaller_5(){
        // 1. Create a Solver
        Solver solver = new Solver("my first problem");
        // 2. Create variables through the variable factory
        IntVar x = VariableFactory.bounded("X", 0, 5, solver);
        IntVar y = VariableFactory.bounded("Y", 0, 5, solver);
        // 3. Create and post constraints by using constraint factories
        solver.post(IntConstraintFactory.arithm(x, "+", y, "<", 5));

        // 5. Launch the resolution process
        solver.findSolution();

        //6. Print search statistics
        Chatterbox.showSolutions(solver);
    }

    @Test
    public void it_should_solve_x_plus_y_equals_z(){
        Solver solver  =   new   Solver();
        IntVar             v1             = VariableFactory.bounded("number_Of_smalls", 0, 100, solver);
        IntVar             v2             = VariableFactory.bounded("number_Of_smalls", 0, 100, solver);
        //IntegerExpressionVariable e1
        // SAT is Boolean satisfiability problem


        BoolVar C1 = VF.bool("C1", solver);
        BoolVar C2 = VF.bool("C2", solver);
        BoolVar R = VF.bool("R", solver);
        BoolVar AR = VF.bool("AR", solver);
        SatFactory.addClauses(
                LogOp.ifThenElse(LogOp.nand(C1, C2), R, AR),
                solver);
        solver.findAllSolutions();
        SatFactory.addClauses(
                LogOp.ifThenElse(LogOp.nand(C1, C2), R, AR),
                solver);
    }


    @Test
    public void it_should_solve_scalar_multiplication(){

        Solver solver = new Solver();
        IntVar[] CS = VF.enumeratedArray("CS", 4, 1, 4, solver);
        int[] coeffs = new int[]{1, 2, 3, 4};
        IntVar R = VF.bounded("R", 0, 20, solver);
        solver.post(ICF.scalar(CS, coeffs, R));

        //how to iterate over solutions
        List<Solution> solutions = new ArrayList<Solution>();
        solver.findSolution();
        Variable[] vars = solver.getVars();

        Chatterbox.printSolutions(solver);
        //Chatterbox.printStatistics(solver);
    }

    // () -> System.out.println("Zero parameter lambda");

}
