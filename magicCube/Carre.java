package magicCube;

import var.IntCsp;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import constraints.AllDistincts;
import constraints.IntAddEqCst;
import solver.Solver;

public class Carre {
    public ArrayList<IntCsp> carre;
    public Solver solver;
    private int cte;

    public Carre(int cte) {
        this.carre = new ArrayList<>();
        this.solver = new Solver();
        this.cte = cte;
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++) 
        {
                String name = "var_" + i + "_" + j;
                try
                {
                    IntCsp currVar = new IntCsp(name, 1, cte);
                    this.carre.add(currVar);
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
        return x+(y*3);
    }

    public IntCsp getCell(int x, int y) {
        return this.carre.get(this.getInd(x, y));
    }

    private ArrayList<IntCsp> getLine(int l) {
        ArrayList<IntCsp> line = new ArrayList<>();
        for(int i=0; i<3; i++) {
            line.add(this.getCell(i, l));
        }
        return line;
    }

    private ArrayList<IntCsp> getColumn(int c) {
        ArrayList<IntCsp> column = new ArrayList<>();
        for(int i=0; i<3; i++) {
            column.add(this.getCell(c, i));
        }
        return column;
    }

    private ArrayList<IntCsp> getDiagonal(int d) {
        ArrayList<IntCsp> diagonal = new ArrayList<>();
        if(d==0) {
            diagonal.add(this.getCell(0, 0));
            diagonal.add(this.getCell(1, 1));
            diagonal.add(this.getCell(2, 2));
        } else {
            diagonal.add(this.getCell(0, 2));
            diagonal.add(this.getCell(1, 1));
            diagonal.add(this.getCell(2, 0));
        }
        return diagonal;
    }

    public void createConstraints() {
        for(int i=0; i<3; i++) {
            try
            {
                this.solver.addConstraint(new IntAddEqCst(this.getLine(i), cte));
                this.solver.addConstraint(new IntAddEqCst(this.getColumn(i), cte));
                if(i==0 || i==1)
                {
                    this.solver.addConstraint(new IntAddEqCst(this.getDiagonal(i), cte));
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        this.solver.addConstraint(new AllDistincts(this.carre));
    }

    private void displayCarre() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                System.out.print(this.getCell(i, j).getValue() + " ");
            }
            System.out.println();
        }
    }


    public boolean solve() {
        //Carre carre = new Carre(15);
        long start = System.currentTimeMillis();
        boolean res = false;
        try{
            res = solver.solve();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start) + " ms");
        return res;
    }
}
