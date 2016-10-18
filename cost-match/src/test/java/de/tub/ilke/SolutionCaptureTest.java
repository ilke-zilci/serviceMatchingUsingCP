package de.tub.ilke;


import de.tub.ilke.model.CloudService;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;
import org.chocosolver.solver.variables.VariableFactory;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.*;



/**
 *
 */
public class SolutionCaptureTest {

    // Arrange Act Assert

    // ARRANGE: create a solver with problem x 0..5 y 0..5 x+y<5
    private Solver getSolverWithXY5Problem() {
        Solver solver = new Solver("x+y<5");
        IntVar x = VariableFactory.bounded("X", 0, 5, solver);
        IntVar y = VariableFactory.bounded("Y", 0, 5, solver);
        // 3. Create and post constraints by using constraint factories
        solver.post(IntConstraintFactory.arithm(x, "+", y, "<", 5));
        return solver;
    }

    // TODO extract these to methods which ACT
    // TODO add assertions
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

    @Test
    public void it_should_find_and_capture_one_solution() throws Exception {
        Solver solver = getSolverWithXY5Problem();
        CloudService cloudService = new CloudService();
        solver.findSolution();
        Variable[] vars = solver.getVars();
        for (Variable v : vars) {
            IntVar intVar = (IntVar)v;
            cloudService.name = v.getName();
            cloudService.capacity = intVar.getValue();
            }

        assertThat(cloudService.name).isEqualTo("Y");
    }

    @Test
    public void it_should_capture_output_into_solution_objects() {

        Solver solver = getSolverWithXY5Problem();
        CloudService cloudService = new CloudService();
        //  Solver.findSolution() must be called once before calling Solver.nextSolution()
        solver.findSolution();

        while (solver.nextSolution()) {
            Variable[] vars = solver.getVars();

            for (Variable v : vars) {
                IntVar intVar = (IntVar)v;
                cloudService.name = v.getName();
                cloudService.capacity = intVar.getValue();

                }
        }
        // TODO add assertions
        assertThat(cloudService.name).isEqualTo("Y");
    }

    @Test
    @Ignore
    // this is just to remind that this method has thread issues, if all tests are run at the same time, allResults fail to capture the right sysout. if the test method is run alone it will pass.
    public void it_should_redirect_solution_stream_to_file() {

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
        String allResults = baos.toString();
        // Show what happened
        System.out.println("\n #####################################################\n Here are the captured results as string: \n" + allResults +
        " \n ######################################################### \n " +
                "end of captured sysout \n"
        );
        assertThat(allResults).contains("Solution #2");

    }


}
