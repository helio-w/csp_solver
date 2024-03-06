package var;

import domains.IntDomain;


public class IntCsp extends Variable {
	private final IntDomain domain;
	public int value;					
	public boolean isFixed = false;		// True si la valeur est fixée, faux sinon
	
	// Constructeurs
	
	public IntCsp(String name, IntDomain domain) throws Exception {
		super(name);
		this.domain = domain;
		this.value = this.domain.getBorneInf(); 	// On initialise la valeur par défaut de la variable à la borne inférieure de son domaine de définition
	}	
	
	
	public IntCsp(IntCsp oldVar) {
		super(oldVar.name);
		this.domain = oldVar.domain;
		this.isFixed = oldVar.isFixed;
	}
	
	// Méthodes de gestion du domaine
	
	public int getDomainBorneSup() {
		return this.domain.getBorneSup();
	}
	
	public int getDomainBorneInf() {
		return this.domain.getBorneInf();
	}
	
	public void setUniqueVal(int val) {
		this.domain.setUniqueVal(val);
	}
	
	public boolean isInDomain(int val) {
		return this.domain.isIn(val);
	}
	
	public int getDomainSize() {
		return this.domain.getSize();
	}
	
	public void setDomainVal(int val, boolean bl) {
		this.domain.setValueState(val, bl);
	}
	
	public boolean isDomainEmpty() {
		return this.domain.isEmpty();
	}
	
	public boolean fixWithFirstDomVal() {
		if (this.domain.isEmpty()) {
			return false;
		}else {
			this.value = this.domain.getFirstValidValue();
			this.domain.setUniqueVal(this.value);
			this.isFixed = true;
			return true;
		}
	}
}
