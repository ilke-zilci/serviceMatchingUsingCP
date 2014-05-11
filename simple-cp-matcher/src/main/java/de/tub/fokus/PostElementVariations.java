package de.tub.fokus;

	import javax.constraints.Problem;
	import javax.constraints.ProblemFactory;
	import javax.constraints.Solution;
	import javax.constraints.Var;
	import javax.constraints.Solver;

	public class PostElementVariations {
		
		Problem p = ProblemFactory.newProblem("TestXYZ");

		public void define() { // PROBLEM DEFINITION
			Var v1 = p.variable("M",0,6);
			int[] num={1,2,3,4,5,6};
			p.log(p.getVars());
			p.postElement(num, v1, ">=",3);
			//when M=2 or M=3 , the condition is satisfied
			//num[2]=3 bu >=3
			//num[3]=4 bu da >=3
			//num[4]=5 bu da >=3

		}	
		
		//try p.postElement(int[] array, Var indexVar, oper, Var var)	
		public void defineIntVar(){
			Var v1 = p.variable("M",0,6);
			int[] num={1,2,3,4,5,6};
			p.log(p.getVars());
		    //1. option domain defined with min max
			Var x = p.variable("X",1,10);
			p.postElement(num, v1, ">", x);
			//2. option would be domain defined with specific values in an array
		}
		
		public void defineVarInt(){
			Var indexVar = p.variable("M",0,2);
			Var a = p.variable("A",1,10);
			Var b = p.variable("B",1,10);
			Var c = p.variable("C",1,10);
			Var d = p.variable("D",1,10);
			Var[] vars = { a,b,c,d };
			p.postElement(vars, indexVar, "<", 3);
		}
		
		public void defineVarVar(){
			Var indexVar = p.variable("M",0,0);
			Var a = p.variable("A",1,10);
			Var b = p.variable("B",1,10);
			Var c = p.variable("C",1,10);
			Var d = p.variable("D",1,10);
			Var[] vars = { a,b,c,d };
			Var x = p.variable("X",1,10);
			p.postElement(vars, indexVar, "<", x);
		}
		
		
			
		public void solve() {	// PROBLEM RESOLUTION
			p.log("=== Find all solutions:");
			Solver solver = p.getSolver(); 
			Solution[] solution = solver.findAllSolutions(); 
			if(solution!=null){
			for(int i=0;i<solution.length;i++){
				solution[i].log();
			     } 
			}
		}
		
		public static void main(String[] args) {
			PostElementVariations t = new PostElementVariations();
			//t.define();
			//t.defineVarInt();
			t.defineVarVar();
			t.solve();
		}
	
}
