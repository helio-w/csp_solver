package constraints;

import var.IntCsp;


public class IntEq extends Constraint{
	private IntCsp v1;
	private IntCsp v2;
	
	public IntEq(IntCsp v1, IntCsp v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public void filter() {
		// Le filtrage est bidirectionnel ici : l'intersection du domaine de v1 et v2 doit être la même. Tout le reste doit être nul
		
		for(int i = v1.getDomainBorneInf(); i <  v1.getDomainSize(); i++) {
			if(!v2.isInDomain(i) ) {
				v1.setDomainVal(i, false);
			}
		}
		
		for(int i = v2.getDomainBorneInf(); i <  v2.getDomainSize(); i++) {
			if(!v1.isInDomain(i)) {
				v2.setDomainVal(i, false);
			}
		}
		
	}
}
