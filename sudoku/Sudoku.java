package sudoku;

import var.IntCsp;

import java.util.ArrayList;

public class Sudoku{
    public ArrayList<ArrayList<IntCsp>> sudokuGrid;

    public Sudoku() {
        this.sudokuGrid = new ArrayList<ArrayList<IntCsp>>();
        for(int i = 0; i < 9; i++) {
            this.sudokuGrid.add(new ArrayList<IntCsp>());
            for(int j = 0; j < 9; j++) {
                this.sudokuGrid.get(i).add(null);
            }
        }
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

    public void displayGrid() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+-------");
            }
            for (int j = 0; j < 9; j++) {
                IntCsp var = this.getCell(i, j);
                if (var != null) {
                    int val = var.getValue();
                    System.out.print(val + " ");
                } else {
                    System.out.print("  ");
                }
                if ((j + 1) % 3 == 0 && j != 8) {
                    System.out.print("| "); 
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.setCell(0, 0, 1, "a");
        sudoku.setCell(1, 1, 2, "b");
        sudoku.setCell(2, 2, 3, "c");
        sudoku.setCell(0, 2, 4, "d");
        sudoku.setCell(0, 4, 5, "e");
        sudoku.displayGrid();
    }
}