package constraints;

import var.IntCsp;
import domains.IntDomain;

public class IntEqCst extends IntConstraint{
	private int cst;
	private IntCsp v;

	public IntEqCst(IntCsp var, int cst) {
		this.cst = cst;
		this.v = var;
	}
	
	@Override
	public void filter() {
		IntDomain vDomain = (IntDomain)v.domain;
		
		vDomain.getValues().entrySet().stream()
		.filter(entry -> entry.getKey() != this.cst)
		.forEach(entry -> ((IntDomain)v.domain).setValueState(entry.getKey(), false));
	}
}
