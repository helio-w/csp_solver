package sudoku;

import var.IntCsp;

import java.util.ArrayList;

import constraints.AllDistincts;
import solver.Solver;

public class Sudoku{
    public ArrayList<IntCsp> sudokuGrid;
    public Solver solver;
    private int size;

    /**
     * Constructeur de la grille de Sudoku.<br><br>
     * Les variables CSP sont automatiquement ajoutees.
     * @param size : taille de la grille de sudoku (9, 16, 25 etc)
     */
    public Sudoku(int size) {
    	this.size = size;
        this.sudokuGrid = new ArrayList<>();
        this.solver = new Solver();
        for(int j = 0; j < this.size; j++)
        {
            for(int i = 0; i < this.size; i++)
            {
                String name = "var_" + i + "_" + j;
                try
                {   
                    IntCsp currVar = new IntCsp(name, 1, this.size);
                    this.sudokuGrid.add(currVar);
                    this.solver.addVariable(currVar);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        this.createConstraints();
    }

    private int getInd(int x, int y) {
    	return x+(y*this.size);
    }
    
    /**
     * Obtiens la variable correspondant à la case (x, y)
     * @param x : coordonnée x de la case
     * @param y : coordonnée y de la case
     * @return : la variable correspondant à la case
     */
    public IntCsp getCell(int x, int y) {
        return this.sudokuGrid.get(this.getInd(x, y));
    }

    /**
     * Fixe la variable correspondant à la case (x, y) avec la valeur val
     * @param x : coordonnée x de la case
     * @param y : coordonnée y de la case
     * @param val : valeur à affecter à la variable
     */
    public void setCell(int x, int y, int val) {
        this.sudokuGrid.get(this.getInd(x, y)).setUniqueVal(val);
    }

    
    private ArrayList<IntCsp> getLine(int l){
    	ArrayList<IntCsp> res = new ArrayList<>();
    	for (int i = 0; i < this.size; i++) {
    		res.add(this.getCell(i, l));
    	}
    	return res;
    }
    
    private ArrayList<IntCsp> getColumn(int c){
    	ArrayList<IntCsp> res = new ArrayList<>();
    	for (int i = 0; i < this.size; i++) {
    		res.add(this.getCell(c, i));
    	}
    	return res;
    }
    
    private ArrayList<IntCsp> getZone(int z){
    	ArrayList<IntCsp> res = new ArrayList<>();
    	int xInit = (int) ((z%Math.sqrt(this.size))*Math.sqrt(this.size));
    	int yInit = (int) ((z/Math.sqrt(this.size)));
    	yInit *= Math.sqrt(this.size);
    	
    	System.out.println("Zone " + xInit + " " +yInit);
    	
    	for (int i = xInit; i < xInit + Math.sqrt(this.size); i++) {
    		for(int j = yInit; j < yInit + Math.sqrt(this.size); j++) {
    			res.add(this.getCell(i, j));
    		}
    	}
    	
    	return res;
    }
    
    /**
     * Création des contraintes allDistincts avec leus variables
     */
    public void createConstraints() {
    	for (int i = 0; i < this.size; i++) {
    		solver.addConstraint(new AllDistincts(this.getLine(i)));
    		solver.addConstraint(new AllDistincts(this.getColumn(i)));
     		solver.addConstraint(new AllDistincts(this.getZone(i)));
    	}
    }

    

    /**
     * Affiche le grille de sudoku en mode console
     */
    public void displayGrid() {
        for (int i = 0; i < this.size; i++)
        {
            if (i % Math.sqrt(this.size) == 0 && i != 0)
            {
                System.out.println(this.separator());
            }
            for (int j = 0; j < this.size; j++)
            {
                IntCsp var = this.getCell(j, i);
                if (var.isFixed())
                {
                    int val = var.getValue();
                    System.out.print(val + " ");
                }
                else
                {
                    System.out.print("  ");
                }
                if ((j + 1) % Math.sqrt(this.size) == 0 && j != 8)
                {
                    System.out.print("| "); 
                }
            }
            System.out.println();
        }
    }
    
    private String separator() {
    	String res = "";
    	for(int i = 0; i < Math.sqrt(size); i++) {
    		for(int j = 0; j < Math.sqrt(size)*2; j++) {
        		res += "-";
        	}
    		if (i != 0) {
    			res += "-";
    		}
    		res += "+";

    	}
    	return res;
    }
    
    public boolean solve() {
        long start = System.currentTimeMillis();
        boolean res = false;      
        try
        {
            res = solver.solve();
            if(res)
            {
                System.out.println("Solved !");
            }
            else
            {
                System.out.println("No solution !");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start) + " ms");
        return res;
    }
}