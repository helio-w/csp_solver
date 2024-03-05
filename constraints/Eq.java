package constraints;

import var.Variable;

import java.util.HashMap;

public class Eq extends Constraint{
	private Variable v1;
	private Variable v2;
	
	public Eq(Variable v1, Variable v2) {
		this.v1 = v1;
		this.v2 = v1;
	}
	
	@Override
	public void filter() {
		// Le filtrage est bidirectionnel ici : le domaine de v1 doit être égal au domaine de v2 et inversement
		// Si la clé est dans v1 et dans v2, alors on regarde si v1 et v2 sont a true dans la même clé -> Si ce n'est pas le cas, on passe les 2 à false.
		// Si la clé est dans v1 mais pas dans v2, alors on passe la valeur correspondante dans v1 à false. Cette valeur n'est pas dans l'intervalle de v2.
		
		HashMap<?, Boolean> v1Domain = v1.;
		HashMap<?, Boolean> v2Domain = v2.domain.getValues();
		
		v1.domain.getValues()
		
	}
}
