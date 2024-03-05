package domains;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class IntDomain extends Domain{
	private HashMap<Integer, Boolean> boolTab;
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
		
		this.boolTab = new HashMap<Integer, Boolean>();
		this.borneInf = min;
		this.borneSup = max;
		
		// Remplissage du tableau de booléens
		for(int i = this.borneInf; i <= this.borneSup; i++) {
			this.boolTab.put(i, true);	
		}
		
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
			return this.boolTab.get(val);
		}
	}
	
	/**
	 * Permet de changer l'état du domaine et de retirer ou ajouter une valeur au domaine
	 * @param val valeur à modifier
	 * @param bl nouvel état
	 */
	public void setValueState(int val, boolean bl) {
		this.boolTab.put(val, bl);
	}
	
	/**
	 * Renvoi la taille du domaine 
	 * @return l'entier correspondant à la taille du domaine
	 */
	public int getSize() {
		return (int) this.boolTab.values().stream().filter(Boolean::valueOf).count();
	}
	
	public int getBorneSup() {
		return this.borneSup;
	}
	
	public int getBorneInf() {
		return this.borneInf;
	}
	
	public boolean isEmpty() {
		return this.getSize() == 0;
	}
	
	public boolean hasOnlyOneValue() {
		return this.getSize() == 1;
	}
	
	public Integer getFirstValidValue() {
		Optional<Integer> key = this.boolTab.entrySet()
				.stream()
				.filter(entry -> entry.getValue())
				.map(Map.Entry::getKey)
				.findFirst();
		return key.orElseThrow(() -> new NoSuchElementException("Aucune clé associée à la valeur true n'a été trouvée"));
	}
	
	public HashMap<Integer, Boolean> getValues(){
		return this.boolTab;
	}
}
