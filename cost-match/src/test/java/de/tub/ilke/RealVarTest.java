package de.tub.ilke;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.real.Ibex;
import org.chocosolver.solver.constraints.real.RealConstraint;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.RealVar;
import org.chocosolver.solver.variables.VariableFactory;
import org.junit.Test;

import static org.chocosolver.solver.constraints.real.Ibex.COMPO;

/**
 * Created by izi on 10/17/16.
 */
public class RealVarTest {
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

    @Test
    public void it_should_load_Ibex(){
        Ibex ibex = new Ibex();
        ibex.add_contractor(2,"{0}={1}",COMPO);


    }

    @Test
    public void it_should_solve_realConstraint_Externally_with_Ibex(){
        RealVar[] vars;
        RealVar x, y, z;
        Solver solver = new Solver();
        double precision = 1.0e-6;
        x = VariableFactory.real("x", -1.0e8, 1.0e8, precision, solver);
        y = VariableFactory.real("y", -1.0e8, 1.0e8, precision, solver);
        z = VariableFactory.real("z", -1.0e8, 1.0e8, precision, solver);

        vars = new RealVar[]{x, y, z};
        solver.post(new RealConstraint("RealConstraint",
                "{1}^2 * (1 + {2}^2) + {2} * ({2} - 24 * {1}) = -13;",
                Ibex.HC4_NEWTON, vars)
        );

        solver.plugMonitor((IMonitorSolution) () -> {
            StringBuilder st = new StringBuilder();
            st.append("\t");
            for (int i = 0; i < vars.length; i++) {
                st.append(String.format("%s : [%f, %f]\n\t", vars[i].getName(), vars[i].getLB(), vars[i].getUB()));
            }
            System.out.println("CycloHexan");
            System.out.println(st.toString());
        });
        solver.findAllSolutions();
        solver.getIbex().release();
        // Chatterbox.showSolutions(solver);

    }


}
