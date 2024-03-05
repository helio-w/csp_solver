package constraints;

import var.IntCsp;

import java.util.HashMap;

import domains.IntDomain;

public class IntEq extends Constraint{
	private IntCsp v1;
	private IntCsp v2;
	
	public IntEq(IntCsp v1, IntCsp v2) {
		this.v1 = v1;
		this.v2 = v1;
	}
	
	@Override
	public void filter() {
		// Le filtrage est bidirectionnel ici : le domaine de v1 doit être égal au domaine de v2 et inversement
		// Si la clé est dans v1 et dans v2, alors on regarde si v1 et v2 sont a true dans la même clé -> Si ce n'est pas le cas, on passe les 2 à false.
		// Si la clé est dans v1 mais pas dans v2, alors on passe la valeur correspondante dans v1 à false. Cette valeur n'est pas dans l'intervalle de v2.
		
		IntDomain v1Domain = (IntDomain)v1.domain;
		IntDomain v2Domain = (IntDomain)v2.domain;
		
		v1Domain.getValues().entrySet().stream()
		.filter(entry -> )
		
		
	}
}
