package constraints;

import var.IntCsp;

public class IntEqCst extends IntConstraint{
	private int cst;
	private IntCsp v;

	public IntEqCst(IntCsp var, int cst) {
		this.cst = cst;
		this.v = var;
	}
	
	@Override
	public void filter() {
		this.v.setUniqueVal(cst);
	}
}
