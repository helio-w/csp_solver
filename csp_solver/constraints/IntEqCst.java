package csp_solver.constraints;

import csp_solver.var.IntCsp;

public class IntEqCst extends IntConstraint{
	private int cst;
	private IntCsp v;

	public IntEqCst(IntCsp var, int cst) {
		this.cst = cst;
		this.v = var;
	}
	
	@Override
	public void filter() {
		for(int i = v.getDomainBorneInf(); i <= v.getDomainBorneSup(); i++) {
			if(i != cst) {
				v.setDomainVal(i, false);
			}
		}
	}
}
