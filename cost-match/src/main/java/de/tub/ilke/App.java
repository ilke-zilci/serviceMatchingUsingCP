package de.tub.ilke;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Solver solver = new Solver("my first problem");
        // 2. Create variables through the variable factory
        IntVar x = VariableFactory.bounded("X", 0, 5, solver);
        IntVar y = VariableFactory.bounded("Y", 0, 5, solver);
        IntVar[] vars = new IntVar[]{x, y};

        // 3. Create and post constraints by using constraint factories
        solver.post(IntConstraintFactory.arithm(x, "+", y, "<", 5));
        solver.plugMonitor((IMonitorSolution) () -> {
            StringBuilder st = new StringBuilder();
            st.append("\t");
            for (int i = 0; i < vars.length; i++) {
                st.append(String.format("%s : [%d, %d]\n\t", vars[i].getName(), vars[i].getLB(), vars[i].getUB()));
            }
            System.out.println("SimpleInteger");
            System.out.println(st.toString());
        });
        solver.findAllSolutions();

    }
}
