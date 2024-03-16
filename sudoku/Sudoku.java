package sudoku;

import var.IntCsp;

import java.util.ArrayList;

public class Sudoku{
    public ArrayList<ArrayList<IntCsp>> sudokuGrid;

    public Sudoku() {
        this.sudokuGrid = new ArrayList<ArrayList<IntCsp>>();
    }

    /**
     * Obtiens la variable correspondant à la case (x, y)
     * @param x : coordonnée x de la case
     * @param y : coordonnée y de la case
     * @return : la variable correspondant à la case
     */
    public IntCsp getCell(int x, int y) {
        return this.sudokuGrid.get(x).get(y);
    }

    /**
     * Ajoute une valeur fixe à la case (x, y)
     * @param x : coordonnée x de la case
     * @param y : coordonnée y de la case
     * @param v : valeur à fixer
     * @param name : nom de la variable
     */
    public void setCell(int x, int y, int v, String name) {
        try
        {
            IntCsp var = new IntCsp(name, 1, 9);
            var.setUniqueVal(v);
            this.sudokuGrid.get(x).set(y, var);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Met une variable IntCsp à la case (x, y)
     * @param x : coordonnée x de la case
     * @param y : coordonnée y de la case
     * @param v : variable à mettre
     */
    public void setCellVar(int x, int y, IntCsp v) {
        this.sudokuGrid.get(x).set(y, v);
    }

    public static void main(String[] args) {
        
    }
}