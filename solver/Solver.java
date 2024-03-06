package solver;

import java.util.ArrayList;
import java.util.Stack;

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

    //Attention pour l'instant c'est la même instance à chaque fois
    public void solve() {
        StackBackTrack stack = new StackBackTrack();
        stack.push(variables);
        Boolean isend = false;
        int compteur = 0;

        while(!isend)
        {   
            //forward checking
            Variable v = variables.get(compteur);
            //v.domain.setFirstValidValue();
            /*
             * Faut faire le parcours sur la liste des contraintes et vérifier chaque contraintes une par une
             * Si ça marche on retourne à la contrainte précedentes en changeant la valeur de la variable
             * Sinon on passe à la contrainte suivante
             */
            
        }


    }
}
