package csp_solver.constraints;

import java.util.ArrayList;

import csp_solver.var.IntCsp;

public class AllDistincts extends Constraint{
	
	private ArrayList<IntCsp> varArr;
	private ArrayList<IntNotEq> notEq = new ArrayList<IntNotEq>();
	
	public AllDistincts(ArrayList<IntCsp> a) {
		this.varArr = a;
		this.processCte();
	}
	
	@Override
	public void filter() {
		for(IntNotEq cte : notEq) {
			try {
				cte.filter();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void processCte() {
		for(IntCsp var : varArr) {
			for(IntCsp v2 : varArr) {
				if(!v2.equals(var)) {
					notEq.add(new IntNotEq(var, v2));
				}
			}
		}
	}
	
}
