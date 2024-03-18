package sudoku.sudoku_interface;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sudoku.Sudoku;

public class SudokuWindow extends GridPane{
    private HashMap<List<Integer>, SudokuCell> cellsArray = new HashMap<List<Integer>, SudokuCell>();
    private int gridSize;
    private int gridSizeCube;

    public SudokuWindow(int gridSize){
        super();
        this.gridSize = gridSize;
        gridSizeCube = (int) java.lang.Math.sqrt(gridSize) ;
        setVgap(10);
        setHgap(10);

        for (int i = 0; i < gridSize; i++)
        {
            GridPane zone = new GridPane();
            for (int j = 0; j < gridSize; j++)
            {
                SudokuCell actuCell = new SudokuCell(gridSize);
                zone.add(actuCell, j/gridSizeCube, j%gridSizeCube);
                int cooX = (i/gridSizeCube*gridSizeCube+j/gridSizeCube);
                int cooY = (i%gridSizeCube*gridSizeCube+j%gridSizeCube);
                cellsArray.put(Arrays.asList(cooX, cooY), actuCell);
            }
            this.add(zone,i/gridSizeCube,i%gridSizeCube);
        }
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setStyle("-fx-background-color: #A4A4A4;");
    }

    public void setCellValue(int x, int y, int value){
        //cellsArray.get(new int[] {x,y}).setValue(value);
        cellsArray.get(Arrays.asList(x, y)).setValue(value);
    }
    
    public void resetAll(){
        for (HashMap.Entry cell : cellsArray.entrySet()) {
            ((SudokuCell)cell.getValue()).setValue(0);
        }
    }

    public void startSolve(){
        Sudoku sudokuSolve = new Sudoku(gridSize);
        for (HashMap.Entry cell : cellsArray.entrySet()) {
            int actualCell = ((SudokuCell)cell.getValue()).getValue();
            if (actualCell != 0)
            {
                List<Integer> coordonnee = (List<Integer>) cell.getKey();
                sudokuSolve.setCell(coordonnee.get(0), coordonnee.get(1), actualCell);
            }
        }
        sudokuSolve.displayGrid();
        if(sudokuSolve.solve())
        {
            sudokuSolve.displayGrid();
            for (HashMap.Entry cell : cellsArray.entrySet()) {
                List<Integer> coordonnee = (List<Integer>) cell.getKey();
                int cooX = coordonnee.get(0);
                int cooY = coordonnee.get(1);
                setCellValue(cooX, cooY, sudokuSolve.getCell(cooX, cooY).getValue());
            }
        }
        //reste a remplir les case de l'ihm avec celle du solve si il est juste mais je prefere attendre Alexandre
    }
}
