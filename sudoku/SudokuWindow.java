package sudoku;

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
        SudokuCell actuCell = cellsArray.get(Arrays.asList(x, y));
        actuCell.setValue(value);
        actuCell.changeStyleCell("#a09c9b");
    }
    
    public void resetAll(){
        for (HashMap.Entry cell : cellsArray.entrySet()) {
            SudokuCell actuCell = ((SudokuCell)cell.getValue());
            actuCell.setValue(0);
            actuCell.changeStyleCell("#a09c9b");
        }
    }

    public void startSolve(){
        SudokuSolver sudokuSolve = new SudokuSolver(gridSize);
        for (HashMap.Entry cell : cellsArray.entrySet()) {
            SudokuCell actualCell = ((SudokuCell)cell.getValue());
            actualCell.oneButtonView();
            if (actualCell.getValue() != 0)
            {
                List<Integer> coordonnee = (List<Integer>) cell.getKey();
                sudokuSolve.setCell(coordonnee.get(0), coordonnee.get(1), actualCell.getValue());
            }
        }

        if(sudokuSolve.solve())
        {
            for (HashMap.Entry cell : cellsArray.entrySet()) {
                List<Integer> coordonnee = (List<Integer>) cell.getKey();
                int cooX = coordonnee.get(0);
                int cooY = coordonnee.get(1);
                setCellValue(cooX, cooY, sudokuSolve.getCell(cooX, cooY).getValue());
            }
        }
        else
        {
            for (HashMap.Entry cell : cellsArray.entrySet()) {
                if (((SudokuCell)cell.getValue()).getValue() != 0)
                {
                    List<Integer> coordonnee = (List<Integer>) cell.getKey();
                    checkCell(coordonnee.get(0), coordonnee.get(1));
                }
            }
        }
    }

    private void checkCell(int cooX, int cooY){
        int actuCellValue = cellsArray.get(Arrays.asList(cooX,cooY)).getValue();
        for (int i = 0; i < gridSize; i++) {
            if(cellsArray.get(Arrays.asList(cooX,i)).getValue() == actuCellValue && i != cooY)
            {
                cellsArray.get(Arrays.asList(cooX,i)).changeStyleCell("#ff2d00");
            }
            if(cellsArray.get(Arrays.asList(i,cooY)).getValue() == actuCellValue && i != cooX)
            {
                cellsArray.get(Arrays.asList(i,cooY)).changeStyleCell("#ff2d00");
            }
        }
    }
}
