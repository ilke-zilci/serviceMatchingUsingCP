package de.tub.ilke;


import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.constraints.SatFactory;
import org.chocosolver.solver.constraints.nary.cnf.LogOp;
import org.chocosolver.solver.constraints.real.Ibex;
import org.chocosolver.solver.constraints.real.RealConstraint;
import org.chocosolver.solver.search.solution.Solution;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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


    @Test
    public void it_should_solve_realConstraint_Externally_with_Ibex(){

        Solver solver = new Solver();
        double PREC = 0.01d; // precision
        RealVar x = VariableFactory.real("x", -1.0d, 1.0d, PREC, solver);
        RealVar y = VariableFactory.real("y", -1.0d, 1.0d, PREC, solver);
        RealConstraint rc = new RealConstraint(
                "my fct",
                "{0}*{1}=1.0",
                Ibex.HC4,
                x, y);
        solver.post(rc);
        Chatterbox.showSolutions(solver);

    }



   /* private void printResults() {
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
    }*/

}
