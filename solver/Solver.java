package solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import var.IntCsp;
import constraints.*;

public class Solver {
	private Stack<IntCsp> varStack = new Stack<>();
	private Stack<IntCsp> varValidated = new Stack<>();
	private ArrayList<Constraint> constraints;
	private int undo = 0;
	private int backup = 0;
	
	/**
	 * Initialise un solveur vide de variable et de contraintes
	 */
	public Solver() {
		this.setVariables(new ArrayList<IntCsp>());
		this.setConstraints(new ArrayList<Constraint>());
	}
	
	// Méthodes get/set temporaires
	public Stack<IntCsp> getVariables() {
		return varStack;
	}

	public void setVariables(ArrayList<IntCsp> arrVar) {
		for(IntCsp var : arrVar) {
			this.varStack.push(var);
		}
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
		this.varStack.push(v);
	}
	
	
	private void undoAllDomain() {
		for(IntCsp v1 : varStack)
		{
			v1.undoDomain();
		}
		for(IntCsp v1 : varValidated)
		{
			v1.undoDomain();
		}
		this.undo++;
	}
	
	private void backupAllDomain() {
		for(IntCsp var : varStack)
		{
			var.pushDomain();
		}
		for(IntCsp var : varValidated)
		{
			var.pushDomain();
		}
		this.backup++;
	}
	
    public boolean solve() throws Exception {
		boolean backtrack = false;
		boolean theEnd = false;
    	varValidated.push(varStack.pop());
    	varValidated.peek().pushDomain();
		
		/* ----- Boucle de résolution ----- */
        while(!theEnd)
        {   
        	System.out.println(String.format("%d backup et %d undo", this.backup, this.undo));
        	System.out.println(varValidated);
        	backtrack = false;
          	// "Sauvegarde" des domaines des variables dans la pile de chaque variable
    		this.backupAllDomain();    		

    		// Forward checking
        	varValidated.peek().fixWithFirstDomVal();
        	System.out.println("Selection de la variable : "+varValidated.peek());

			// Filtrage
            for(Constraint c : constraints)
			{
				c.filter();
			}
            
            // Vérification des domaines des variables et backtracking si un domaine devient vide
			for(IntCsp v2 : varStack)
			{
				if(v2.isDomainEmpty())
				{
					System.out.println("La domaine de la variable (" + v2 + ") est vide !");
					backtrack = true;
					break;
				}
			}
			
			for(IntCsp v2 : varValidated)
			{
				if(v2.isDomainEmpty())
				{
					System.out.println("La domaine de la variable (" + v2 + ") est vide !");
					backtrack = true;
					break;
				}
			}
			
			// Si on ne fait pas de backtracking, alors on peut faire du FC une autre variable
			// Sinon, on s'est trompé et on doit faire marche arrière et annuler le filtrage
			if(backtrack) {
				System.out.println("Backtracking !");
				// On annule le dernier filtrage
				this.undoAllDomain();
				// On invalide la valeur actuelle de currentVar -> elle ne mène à aucune solution
				varValidated.peek().blacklistCurrentVal();

								
				// Si le domaine de la variable courante est vide, alors on fait encore marche arrière
				// Sinon, on ne fait rien et on laisse l'algo filter avec une valeur
				while(!varValidated.isEmpty() && varValidated.peek().isDomainEmpty()) {
					System.out.println("Retour en arrière ! La variable n'a pas de solution : " + varValidated.peek());
					this.varStack.push(varValidated.pop());
					if(varValidated.isEmpty()) {
						System.out.println("Tous les domaines sont vides !");
						return false;
					}
					this.undoAllDomain();
					varValidated.peek().blacklistCurrentVal();
					
				}
			}else if(varStack.isEmpty()) {
				theEnd = true;
			}
			else {
				varValidated.push(varStack.pop());
			}
			
			

        }
        /* ----- Fin de la boucle ---- */
    
        return true;
    }
    
    // Affichage de l'état du système
    @Override
    public String toString() {
    	String res = "Etat du solveur : \n";
    	for(IntCsp v : varStack) {
    		res += v.toString() + "\n";
    	}
    	res += "\nVariables validées = \n";
    	for(IntCsp v : varValidated) {
    		res += v.toString() + "\n";
    	}
    	return res;
    }
    
	public static void main(String[] args) throws Exception {
		// Une démonstration très simple
		// On cherche à produire A=B=C avec C=2
		
		// On initialise un solveur vide
		Solver solver = new Solver();
	
		
		// Création des variables avec les domaines appropriés
		IntCsp A = new IntCsp("A", 1, 4);
		IntCsp B = new IntCsp("B", 1, 4);
		IntCsp C = new IntCsp("C", 1, 4);
		IntCsp D = new IntCsp("D", -6, 4);
		
		// Ajout des variables au solveur
		solver.addVariable(A);
		solver.addVariable(B);
		solver.addVariable(C);
		solver.addVariable(D);

		// Ajout des contraintes pour fixer les valeurs des variables
		solver.addConstraint(new IntAddEqCst(new ArrayList<>(Arrays.asList(A, B, C, D)), 5));
				
		System.out.println(solver);
		
		if(solver.solve()) {
			System.out.println("Le solveur a trouvé une solution !");
		}else {
			System.out.println("Pas de solution ...");
		}
		
		System.out.println(solver);
		
	}
}
