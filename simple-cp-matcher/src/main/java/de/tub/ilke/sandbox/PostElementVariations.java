package de.tub.ilke.sandbox;

import javax.constraints.Problem;
import javax.constraints.ProblemFactory;
import javax.constraints.Solution;
import javax.constraints.Var;
import javax.constraints.Solver;

public class PostElementVariations {

	Problem p = ProblemFactory.newProblem("TestXYZ");

	public void define() { // PROBLEM DEFINITION
		// the index variable
		Var v1 = p.variable("M", 0, 5);
		// the array we are investigating
		int[] num = { 1, 2, 3, 4, 5, 6 };
		p.log(p.getVars());
		// int i=3 the other side of the condition
		p.postElement(num, v1, ">=", 3);
		// when M=2 or M=3 , the condition is satisfied
		// num[2]=3 bu >=3
		// num[3]=4 bu da >=3
		// num[4]=5 bu da >=3

	}

	// here usage of p.postElement(int[] array, Var indexVar, oper, Var var)
	// the relation for my case will be then how many solutions are there for
	// M=3 num[3]=4 condition num[3]=X[...],
	// again i have to assess the logs,and tell service which has index i had 3
	// entries in the solution set.

	public void defineIntVar() {
		// with the index var i can state which part of the array i want to
		// investigate now, might help for heap problems to run the solver in
		// smaller steps
		Var v1 = p.variable("M", 0, 5);
		int[] num = { 4, 5, 6, 7, 8, 9 };
		p.log(p.getVars());
		// 1. option domain defined with min max
		// 2. option would be domain defined with specific values in an array
		Var x = p.variable("X", 1, 10);
		p.postElement(num, v1, "=", x);

	}

	public void defineVarInt() {
		Var indexVar = p.variable("M", 0, 1);
		Var a = p.variable("A", 1, 5);
		Var b = p.variable("B", 1, 5);
		// Var c = p.variable("C",1,10);
		// Var d = p.variable("D",1,10);
		// Var[] vars = { a,b,c,d };
		Var[] vars = { a, b };
		p.postElement(vars, indexVar, "<", 2);
	}

	public void defineVarIntSeperately() {
		Var indexVar = p.variable("M", 0, 1);
		Var a = p.variable("A", 1, 5);
		Var b = p.variable("B", 1, 5);
		// Var c = p.variable("C",1,10);
		// Var d = p.variable("D",1,10);
		// Var[] vars = { a,b,c,d };
		Var[] varsa = { a };
		Var[] varsb = { b };
		p.postElement(varsa, indexVar, "<", 3);
		p.postElement(varsb, indexVar, "<", 3);
	}

	public void defineVarVar() {
		Var indexVar = p.variable("M", 0, 0);
		Var a = p.variable("A", 1, 10);
		Var b = p.variable("B", 1, 10);
		// Var c = p.variable("C",1,10);
		// Var d = p.variable("D",1,10);
		// Var[] vars = { a,b,c,d };
		Var[] vars = { a, b };
		Var x = p.variable("X", 1, 3);
		p.postElement(vars, indexVar, "<", x);
	}

	public void defineMatching() {
		int[] q1values = { 3, 2, 3, 3, 2, 3, 3 };
		int[] q2values = { 120, 100, 130, 120, 100, 130, 130 };
		int[] q3values = { 98, 95, 97, 98, 95, 97, 97 };
		int[][] qvalues = { q1values, q2values, q3values };
		// DEFINE SERVICE QUERY AS CONSTRAINTS

		Var indexVar = p.variable("angie", 0, 6);
		p.postElement(q1values, indexVar, ">", 1);
	}

	public void solve() { // PROBLEM RESOLUTION
		p.log("=== Find all solutions:");
		Solver solver = p.getSolver();
		Solution[] solution = solver.findAllSolutions();
		if (solution != null) {
			for (int i = 0; i < solution.length; i++) {
				solution[i].log();
			}
		}
	}

	public static void main(String[] args) {
		PostElementVariations t = new PostElementVariations();
		// t.define();
		t.defineVarIntSeperately();
		// t.defineVarInt();
		// t.defineVarVar();
		// t.defineMatching();
		t.solve();
	}

}
