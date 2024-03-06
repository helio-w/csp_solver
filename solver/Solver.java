package solver;

import java.util.ArrayList;

import var.IntCsp;
import constraints.*;

public class Solver {
	private ArrayList<IntCsp> variables;
	private ArrayList<Constraint> constraints;
	
	/**
	 * Initialise un solveur vide de variable et de contraintes
	 */
	public Solver() {
		this.setVariables(new ArrayList<IntCsp>());
		this.setConstraints(new ArrayList<Constraint>());
	}
	
	// Méthodes get/set temporaires
	public ArrayList<IntCsp> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<IntCsp> variables) {
		this.variables = variables;
	}

	public ArrayList<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(ArrayList<Constraint> constraints) {
		this.constraints = constraints;
	}
	
	public void addConstraint(Constraint ct) {
		this.constraints.add(ct);
	}

	public void addVariable(IntCsp v) {
		this.variables.add(v);
	}
	
	private IntCsp getFirstNotFixed(){
		for(IntCsp v : variables){
			if(v.isFixed == false){
				return v;
			}
		}
		return null;
	}

	private boolean checkAllFixed(){
		for(IntCsp v : variables){
			if(v.isFixed == false){
				return false;
			}
		}
		return true;
	}
	
    public void solve() {
		boolean isEnd = false;
		IntCsp currentVar;

        while(!isEnd)
        {   
			currentVar = this.getFirstNotFixed();
			currentVar.fixWithFirstDomVal();
			System.out.println("La variable séléctionnée est : "+currentVar);
			System.out.println(this);

			for(IntCsp var : variables)
			{
				var.pushDomain();
			}

            for(Constraint c : constraints)
			{
				try
				{
					c.filter();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for(IntCsp v2 : variables)
			{
				if(v2.isDomainEmpty())
				{
					System.out.println("Le domaine de v2 est vide");
					currentVar.setDomainVal(currentVar.value, false);
					currentVar.isFixed = false;
					for(IntCsp v1 : variables)
					{
						v1.undoDomain();
					}
					break;
				}
			}

			if(checkAllFixed())
			{
				isEnd = true;
			}
        }
    }
    
    @Override
    public String toString() {
    	String res = "";
    	for(IntCsp v : variables) {
    		res += v.toString() + "\n";
    	}
    	return res;
    }
    
	public static void main(String[] args) throws Exception {
		// Une démonstration très simple
		// On cherche à produire A=B=C avec C=2
		
		// On initialise in solveur vide
		Solver solver = new Solver();
		
		IntCsp a = new IntCsp("A", -2, 5);
		IntCsp b = new IntCsp("B", 3, 5);
		// IntCsp c = new IntCsp("C", -5, 3);
		
		solver.addVariable(a);
		solver.addVariable(b);
		// solver.addVariable(c);
		
		solver.addConstraint(new IntEq(a, b));
		// solver.addConstraint(new IntEq(b, c));
		
		System.out.println(solver);
		
		solver.solve();
		
	}
}
