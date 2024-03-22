package csp_solver.constraints;

import csp_solver.var.IntCsp;

public class IntNotEq extends IntConstraint {
	private IntCsp v1;
	private IntCsp v2;
	
	public IntNotEq(IntCsp v1, IntCsp v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public void filter() throws Exception {
		if (v1.isFixed()) {
			this.notEq(v1, v2);
		}else if (v2.isFixed()) {
			this.notEq(v2, v1);
		}
	}

	private void notEq(IntCsp vFixed, IntCsp v) {
		for (int i = v.getDomainBorneInf(); i <= v.getDomainBorneSup(); i++) {
			if (vFixed.isInDomain(i)) {
				v.setDomainVal(i, false);
			}
		}
	}
}
