package csp_solver.constraints;

import java.util.ArrayList;

import csp_solver.var.IntCsp;

public class IntAddEqCst extends IntConstraint {
	private ArrayList<IntCsp> var;
	private int sum = 0;
	private int cst;
	
	public IntAddEqCst(ArrayList<IntCsp> v, int c) throws Exception {
		if(!v.isEmpty()) {
			this.var = v;
		}else {
			throw new Exception("Error IntAddEqCst : la liste des variables est vide");
		}
		this.cst = c;
	}
	
	@Override
	public void filter() throws Exception {
		if (this.isAllFixed() && this.cst != this.sumFixed()) {
			var.get(0).invalidateDomain();
		}
	}
	
	private boolean isAllFixed() {
		boolean res = true;
		for(IntCsp v : var) {
			if(!v.isFixed()) {
				res = false;
				break;
			}
		}
		
		return res;
	}
	
	private int sumFixed() {
		sum = 0;
		for(IntCsp v : var) {
			sum += v.getValue();
		}
		return sum;
	}
	


}
