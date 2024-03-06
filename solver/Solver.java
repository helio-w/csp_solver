package solver;

import java.util.ArrayList;
import java.util.Stack;

import var.IntCsp;
import var.Variable;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
        StackBackTrack stack = new StackBackTrack();
        stack.push(variables);
		boolean isEnd = false;
		IntCsp firstVar;

        while(!isEnd)
        {   
			firstVar = this.getFirstNotFixed();
			firstVar.fixWithFirstDomVal();

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
					firstVar.setDomainVal(firstVar.value, false);
					firstVar.isFixed = false;
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
}
