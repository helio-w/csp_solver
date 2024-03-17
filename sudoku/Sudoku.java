package sudoku;

import var.IntCsp;

import java.util.ArrayList;
import java.util.List;

import constraints.AllDistincts;
import solver.Solver;

public class Sudoku{
    public ArrayList<ArrayList<IntCsp>> sudokuGrid;
    public Solver solver;

    /**
     * Constructeur de la grille de Sudoku.<br><br>
     * Les variables CSP sont automatiquement ajoutees.
     */
    public Sudoku() {
        this.sudokuGrid = new ArrayList<ArrayList<IntCsp>>();
        this.solver = new Solver();
        for(int i = 0; i < 9; i++)
        {
            this.sudokuGrid.add(new ArrayList<IntCsp>());
            for(int j = 0; j < 9; j++)
            {
                String name = "var_" + i + "_" + j;
                try
                {   
                    IntCsp currVar = new IntCsp(name, 1, 9);
                    this.sudokuGrid.get(i).add(currVar);
                    this.solver.addVariable(currVar);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
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
        return this.sudokuGrid.get(y).get(x);
    }

    /**
     * Fixe la variable correspondant à la case (x, y) avec la valeur val
     * @param x : coordonnée x de la case
     * @param y : coordonnée y de la case
     * @param val : valeur à affecter à la variable
     */
    public void setCell(int x, int y, int val) {
        this.sudokuGrid.get(x).get(y).setUniqueVal(val);
    }

    /**
     * Création des contraintes allDistincts avec leus variables
     */
    public void createConstraints() {
        List<AllDistincts> allDistincts_row = new ArrayList<AllDistincts>();
        List<AllDistincts> allDistincts_col = new ArrayList<AllDistincts>();
        List<AllDistincts> allDistincts_block = new ArrayList<AllDistincts>();

        for(int i=0; i<9; i++)
        {
            allDistincts_col.add(new AllDistincts());
            allDistincts_row.add(new AllDistincts());
            allDistincts_block.add(new AllDistincts());
        }

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                IntCsp var = this.getCell(i, j);
                // TODO : à peut-être modifer après avoir codé allDistincts
                allDistincts_row.get(i).add(var);
                allDistincts_col.get(j).add(var);
                allDistincts_block.get((i / 3) * 3 + j / 3).add(var);
            }
        }

        for(int i=0; i<9; i++)
        {
            this.solver.addConstraint(allDistincts_row.get(i));
            this.solver.addConstraint(allDistincts_col.get(i));
            this.solver.addConstraint(allDistincts_block.get(i));
        }
    }

    /**
     * Affiche le grille de sudoku en mode console
     */
    public void displayGrid() {
        for (int i = 0; i < 9; i++)
        {
            if (i % 3 == 0 && i != 0)
            {
                System.out.println("------+-------+-------");
            }
            for (int j = 0; j < 9; j++)
            {
                IntCsp var = this.getCell(i, j);
                if (var.isFixed())
                {
                    int val = var.getValue();
                    System.out.print(val + " ");
                }
                else
                {
                    System.out.print("  ");
                }
                if ((j + 1) % 3 == 0 && j != 8)
                {
                    System.out.print("| "); 
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();

        sudoku.setCell(0, 0, 1);
        sudoku.setCell(1, 1, 2);
        sudoku.setCell(2, 2, 3);
        sudoku.setCell(2, 2, 4);
        sudoku.setCell(4, 0, 5);

        sudoku.displayGrid();

        try
        {
            boolean res = sudoku.solver.solve();
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
    }
}