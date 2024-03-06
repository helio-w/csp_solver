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
		
		for(int i = v1.getDomainBorneInf(); i <=  v1.getDomainBorneSup(); i++) {
			// On parcours v1
			if(!v2.isInDomain(i) ) { // Si la valeur i n'est pas dans v2, alors elle ne doit pas être dans v1
				this.v1.setDomainVal(i, false);
			}
		}
		
		for(int i = v2.getDomainBorneInf(); i <=  v2.getDomainBorneSup(); i++) {
			// On parcours v2
			if(!v1.isInDomain(i)) { // Si la valeur i n'est pas dans v1, alors elle ne doit pas être dans v2
				this.v2.setDomainVal(i, false);
			}
		}
		
	}
}
