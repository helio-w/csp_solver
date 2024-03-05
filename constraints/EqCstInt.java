package constraints;

import var.CspInt;
import domains.IntDomain;

public class EqCstInt extends Constraint{
	private int cst;
	private CspInt v;

	public EqCstInt(CspInt var, int cst) {
		this.cst = cst;
		this.v = var;
	}
	
	@Override
	public void filter() {
		v.getDomain().getValues().entrySet().stream()
		.filter(entry -> entry.getKey() != this.cst)
		.forEach(entry -> ((IntDomain)v.domain).setValueState(entry.getKey(), false));
		
		// Pas sur de la suite mais testons quand même
		try {
			this.v.setVal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
