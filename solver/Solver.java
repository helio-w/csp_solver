package solver;

import java.util.ArrayList;


import var.Variable;
import constraints.*;

public class Solver {
	private ArrayList<Variable> variables;
	private ArrayList<Constraint> constraints;
	
	/**
	 * Initialise un solveur vide de variable et de contraintes
	 */
	public Solver() {
		this.setVariables(new ArrayList<Variable>());
		this.setConstraints(new ArrayList<Constraint>());
	}
	
	public Solver(ArrayList<Variable> variables, ArrayList<Constraint> constraints) {
		this.setVariables(variables);
		this.setConstraints(constraints);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	// Méthodes get/set temporaires
	public ArrayList<Variable> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<Variable> variables) {
		this.variables = variables;
	}

	public ArrayList<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(ArrayList<Constraint> constraints) {
		this.constraints = constraints;
	}
}
