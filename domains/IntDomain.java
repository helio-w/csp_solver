package domains;

import java.util.BitSet;

public class IntDomain extends Domain{
	private BitSet domain;
	private int borneSup;
	private int borneInf;
	
	/**
	 * Créer un domaine d'entier sur l'intervalle [min, max]
	 * @param min borne inférieure de l'intervalle
	 * @param max borne supérieur de l'intervalle
	 * @throws Exception si la borne min est supérieure à la borne max
	 */
	public IntDomain(int min, int max) throws Exception {
		if (max < min) {
			throw new IllegalArgumentException("Erreur IntDomain : borne inférieur > borne supérieure");
		}
		
		this.borneInf = min;
		this.borneSup = max;
		this.domain = new BitSet(IntDomain.getRangeSize(min, max));
		this.domain.set(0, IntDomain.getRangeSize(min, max));
	}
	
	/**
	 * Créer un domaine à partir d'un autre domaine
	 * @param oldDomain domaine a copier
	 */
    public IntDomain(IntDomain oldDomain) {
        this.borneInf = oldDomain.borneInf;
        this.borneSup = oldDomain.borneSup;
        this.domain = (BitSet) oldDomain.domain.clone();
    }
	
	/**
	 * Vérifie si la valeur est dans le domaine
	 * @param val
	 * @return false si la valeur n'est pas dans la domaine, vraie si elle est dans le domaine
	 */
	public boolean isIn(int val) {
		// On test les cas évidents
		if(val < this.borneInf || val > this.borneSup) {
			return false;
		}else {
			return this.domain.get(this.getValIndex(val)); // Translation
		}
	}
	
	/**
	 * Permet de changer l'état du domaine et de retirer ou ajouter une valeur au domaine
	 * @param val valeur à modifier
	 * @param bl nouvel état
	 */
	public void setValueState(int val, boolean bl) {
		this.domain.set(this.getValIndex(val), bl);
	}
	
	/**
	 * Renvoi le nombre de valeur dans le domaine 
	 * @return l'entier correspondant à la taille du domaine
	 */
	public int getSize() {
		return this.domain.cardinality();
	}
	
	/**
	 * Renvoi 
	 * @return entier correspondant à la borne supérieure de l'intervalle
	 */
	public int getBorneSup() {
		return this.borneSup;
	}
	
	public int getBorneInf() {
		return this.borneInf;
	}
	
	@Override
	public boolean isEmpty() {
		return this.domain.isEmpty();
	}
	
	@Override
	public boolean hasOnlyOneValue() {
		return this.getSize() == 1;
	}
	
	public Integer getFirstValidValue() {
		return this.domain.nextSetBit(0)+this.borneInf;
	}
	
	public void setUniqueVal(int val) {
		this.domain.clear();
		this.domain.set(this.getValIndex(val));
	}
	
	/**
	 * Détermine le nombre de valeurs dans l'intervalle d'entiers [inf, sup]
	 * @param inf borne inférieure de l'intervalle
	 * @param sup borne supérieure de l'intervalle
	 * @return nombre de valeurs dans l'intervalle
	 */
	public static int getRangeSize(int inf, int sup) {
		return (sup-inf)+1;
	}
	
	// Méthode de translation
	private int getValIndex(int val) {
		return val-this.borneInf;
	}
}
