package solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import var.IntCsp;
import constraints.*;

public class Solver {
	private ArrayList<IntCsp> variables;
	private ArrayList<Constraint> constraints;
	
	/**
	 * Initialise un solveur vide de variable et de contraintes
	 */
	public Solver() {
		this.setVariables(new ArrayList<IntCsp>());
		this.setConstraints(new ArrayList<Constraint>());
	}
	
	// Méthodes get/set temporaires
	public ArrayList<IntCsp> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<IntCsp> variables) {
		this.variables = variables;
	}

	public ArrayList<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(ArrayList<Constraint> constraints) {
		this.constraints = constraints;
	}
	
	public void addConstraint(Constraint ct) {
		this.constraints.add(ct);
	}

	public void addVariable(IntCsp v) {
		this.variables.add(v);
	}
	
	private IntCsp getFirstNotFixed(){
		for(IntCsp v : variables){
			if(v.isFixed() == false){
				return v;
			}
		}
		return null;
	}
	
	private boolean checkAllFixed(){
		for(IntCsp v : variables){
			if(v.isFixed() == false){
				return false;
			}
		}
		return true;
	}
	
    public boolean solve() throws Exception {
		boolean backtrack = false;
		IntCsp currentVar;
		Stack<IntCsp> varStack = new Stack<IntCsp>();
		varStack.push(this.getFirstNotFixed());
		
        while(!checkAllFixed())
        {   
        	backtrack = false;
        	
        	System.out.println(this);
        	// "Sauvegarde" des domaines des variables dans la pile de chaque variable
    		for(IntCsp var : variables)
			{
				var.pushDomain();
			}
    		
    		// Forward Checking
        	currentVar = varStack.peek();
			currentVar.fixWithFirstDomVal();
			
			System.out.println("Selection de la variable : "+currentVar);
			
			// Filtrage
            for(Constraint c : constraints)
			{
				c.filter();
			}
            
            // Vérification des domaines des variables et backtracking si un domaine devient vide
			for(IntCsp v2 : variables)
			{
				if(v2.isDomainEmpty())
				{
					System.out.println("Backtracking !");
					backtrack = true;
					for(IntCsp v1 : variables)
					{
						v1.undoDomain();
					}
					currentVar.blacklistCurrentVal();
					break;
				}
			}
			
			if (!backtrack) {
				System.out.println("On ajoute "+this.getFirstNotFixed()+" à la liste");
				varStack.push(this.getFirstNotFixed());
				System.out.println("La tête de liste est maintenant : "+varStack.peek());
			}else {
				// Si on a épuisé le domaine de la currentVar, on dépile et on revient en arrère
				if(currentVar.isDomainEmpty()) {
					varStack.pop();
					// Si la varstack est vide, aucun domaine n'est possible et on a pas de solution
					if(varStack.empty()) {
						return false;
					}
					varStack.firstElement().blacklistCurrentVal();
				}else {
					
				}
			}
			
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            // Gérer l'interruption si nécessaire
	        }
        }
        /* ----- Fin de la boucle ---- */
        
        
        // Retour
        if(this.checkAllFixed()) {
        	return true;
        } else {
        	return false;
        }
    }
    
    // Affichage de l'état du système
    @Override
    public String toString() {
    	String res = "Etat du solveur : \n";
    	for(IntCsp v : variables) {
    		res += v.toString() + "\n";
    	}
    	return res;
    }
    
	public static void main(String[] args) throws Exception {
		// Une démonstration très simple
		// On cherche à produire A=B=C avec C=2
		
		// On initialise un solveur vide
		Solver solver = new Solver();
		
		IntCsp a = new IntCsp("A", 1, 3);
		IntCsp b = new IntCsp("B", 1, 3);
		IntCsp c = new IntCsp("C", 1, 3);
		
		solver.addVariable(a);
		solver.addVariable(b);
		solver.addVariable(c);
		solver.addConstraint(new IntEqCst(a, 3));
		solver.addConstraint(new AllDistincts(new ArrayList<>(Arrays.asList(a, b, c))));
		
		System.out.println(solver);
		
		if(solver.solve()) {
			System.out.println("Le solveur a trouvé une solution !");
		}else {
			System.out.println("Pas de solution ...");
		}
		
		System.out.println(solver);
		
	}
}
