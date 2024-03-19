package var;

import java.util.Stack;

import domains.IntDomain;


public class IntCsp extends Variable {
	private IntDomain domain; // Public for debug
	
	// La mécanique de backtracking des domaines est intégrée aux variables
	
	Stack<IntDomain> domainStack = new Stack<IntDomain>();
	
	// Constructeurs
	
	
	/**
	 * Construit une variable de type entier utilisée par le solveur csp
	 * @param name Nom de la variable
	 * @param domain Domaine de la variable
	 * @throws Exception Exception quand le domaine choisi est invalide
	 */
	public IntCsp(String name, IntDomain domain) throws Exception {
		super(name);
		this.domain = domain;
	}	
	
	public IntCsp(String name, int borneMin, int borneMax) throws Exception {
		super(name);
		this.domain = new IntDomain(borneMin, borneMax);
	}	
	
	// Méthodes de gestion du domaine
	/**
	 * Getter sur la borne supérieure du domaine d'existence de la variable csp
	 * Cette valeur n'est pas modifée lors de la suppression de valeur dans le domaine
	 * @return Entier correspond à la bonne supérieure du domaine de la variable csp
	 */
	public int getDomainBorneSup() {
		return this.domain.getBorneSup();
	}
	
	/**
	 * Getter sur la borne inférieure du domaine d'existence de la variable csp.
	 * Cette valeur n'est pas modifée lors de la suppression de valeur dans le domaine
	 * @return Entier correspond à la bonne inférieure du domaine de la variable csp
	 */
	public int getDomainBorneInf() {
		return this.domain.getBorneInf();
	}
	
	/**
	 * Fixe le domaine de la variable sur une valeur unique
	 * @param val valeur a associer à la variable
	 */
	public void setUniqueVal(int val) {
		this.domain.setUniqueVal(val);
	}
	
	/**
	 * Vérifie si la valeur val est dans le domaine d'existence de la variable
	 * @param val valeur à vérifier
	 * @return true si val est dans le domaine de la variable. false sinon
	 */
	public boolean isInDomain(int val) {
		return this.domain.isIn(val);
	}
	
	/**
	 * Permet de connaître la taille du domaine
	 * @return la taille du domaine
	 */
	public int getDomainSize() {
		return this.domain.getSize();
	}
	
	/**
	 * Permet d'ajouter ou supprimer une valeur particulière du domaine de la variable
	 * @param val entier à ajuster
	 * @param bl boolean état de l'entier souhaité
	 */
	public void setDomainVal(int val, boolean bl) {
		this.domain.setValueState(val, bl);
	}
	
	/**
	 * Vérifie si le domaine est vide
	 * @return vrai si le domaine est vide. Faux sinon.
	 */
	public boolean isDomainEmpty() {
		return this.domain.isEmpty();
	}
	
	/**
	 * Fixe la valeur de la variable avec la première valeur (valide) de l'intervalle
	 * @return vrai si une valeur a été fixé. faux sinon
	 */
	public boolean fixWithFirstDomVal() {
		if (this.domain.isEmpty()) {
			return false;
		}else {
			int newVal = this.domain.getFirstValidValue();
			this.setUniqueVal(newVal);
			return true;
		}
	}
	
	/**
	 * Verifie si la variable possède une seule valeur possible dans son domaine de définition
	 * @return vrai si la variable ne possède qu'une possibilité. Faux sinon
	 */
	public boolean isFixed() {
		return this.domain.hasOnlyOneValue();
	}
	
	/**
	 * Retourne la première valeur valide du domaine de définition. Exemple : [-2, 5] retourne -2
	 * @return Entier correspondant à la première valeur valide du domaine de définition.
	 */
	public int getValue() {
		return this.domain.getFirstValidValue();
	}
	
	// Gestion de la pile
	
	/**
	 * Ajoute le domaine actuel à la pile de domaines
	 */
	public void pushDomain() {
		this.domainStack.push(new IntDomain(domain));
	}
	
	/**
	 * Remplace le domaine actuel par le premier domaine de la pile. Supprime ce domaine de la pile.
	 */
	public void undoDomain() {
		this.domain = this.domainStack.pop();
	}
	
	/**
	 * Retourne l'état de la pile de domaines
	 * @return vrai si la pile est vide. faux si la pile n'est pas vide
	 */
	public boolean isStackEmpty() {
		return this.domainStack.empty();
	}
	
	public void blacklistCurrentVal() {
		this.setDomainVal(this.getValue(), false);
	}
	
	public void invalidateDomain() {
		this.domain.invalidateDomain();
	}
	
	@Override
	public String toString() {
		if(this.isFixed()) {
			return String.format("%s = %d", this.name, this.getValue());
		}else {
			return String.format("%s pas fixée (valeur temporaire %d)", this.name, this.getValue());
		}
	}
	
}
