package de.tub.ilke;


import de.tub.ilke.model.CloudProperty;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.trace.IMessage;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;
import org.chocosolver.solver.variables.VariableFactory;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class SolutionCaptureTest {

    // Arrange Act Assert
    // why did it not work to iterate over the solutions one by one while saving them to objects?

    //create solver with problem x 0..5 y 0..5 x+y<5
    private Solver getSolverWithXY5Problem() {
        Solver solver = new Solver("x+y<5");
        IntVar x = VariableFactory.bounded("X", 0, 5, solver);
        IntVar y = VariableFactory.bounded("Y", 0, 5, solver);
        // 3. Create and post constraints by using constraint factories
        solver.post(IntConstraintFactory.arithm(x, "+", y, "<", 5));
        return solver;
    }

    @Test
    public void it_should_print_solutions_during_resolution_with_chatterbox() {
        Solver solver = getSolverWithXY5Problem();
        Chatterbox.showSolutions(solver);
        solver.findAllSolutions();
    }

    @Test
    public void it_should_find_and_log_solutions_with_chatterbox() {
        Solver solver = getSolverWithXY5Problem();
        solver.findAllSolutions();
        Chatterbox.printSolutions(solver);
    }

    @Test
    public void it_should_print_statistics_after_resolution() {
        Solver solver = getSolverWithXY5Problem();
        solver.findAllSolutions();
        Chatterbox.printStatistics(solver);
    }

    @Test
    public void it_should_find_and_log_solutions_with_monitor() {

        Solver solver = new Solver("my first problem");
        IntVar x = VariableFactory.bounded("X", 0, 5, solver);
        IntVar y = VariableFactory.bounded("Y", 0, 5, solver);
        IntVar[] vars = new IntVar[]{x, y};
        solver.post(IntConstraintFactory.arithm(x, "+", y, "<", 5));
        findAndLogSolutions(solver, vars);
    }

    public void findAndLogSolutions(Solver solver, IntVar[] vars) {
        solver.plugMonitor((IMonitorSolution) () -> {
            logSolutions(vars);
        });
        solver.findAllSolutions();
    }

    private String logSolutions(IntVar[] vars) {
        StringBuilder st = new StringBuilder();
        //Solution #%d
        st.append("call log solutions\n");
        for (int i = 0; i < vars.length; i++) {
            st.append(String.format(" %s : %d \n", vars[i].getName(), vars[i].getValue()));
            // you could also get lower bound upper bound like this: vars[i].getLB(), vars[i].getUB()
        }
        System.out.println(st.toString());
        return st.toString();
    }

    // When i add an object to a list, does it just add a ref and if the internal state of the obj changes it will change or does it copy the object state to the list?
    @Test
    public void it_should_capture_output_into_solution_objects() {
        Solver solver = getSolverWithXY5Problem();
        solver.findSolution();
        while (solver.nextSolution()) {
            Variable[] vars = solver.getVars();
            for (Variable v : vars) {
                CloudProperty cloudProperty = new CloudProperty();
                cloudProperty.setName(v.getName());
                //cloudProperty.setValue(v.);

            }
        }

    }

    @Test
    public void it_should_redirect_solution_stream_to_file() {
        // this might have thread issues.

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream customPrintStream = new PrintStream(baos);
        PrintStream defaultOutStream = System.out;
        System.setOut(customPrintStream);
        // Print some output: goes to your special stream
        Solver solver = getSolverWithXY5Problem();
        Chatterbox.showSolutions(solver);
        solver.findAllSolutions();

        // Put things back
        System.out.flush();
        System.setOut(defaultOutStream);
        // Show what happened
        System.out.println("Here are the captured results as string: \n" + baos.toString());

    }


}
