package sudoku.sudoku_interface;

import javafx.scene.layout.HBox;

public class MainScene extends HBox {
    public MainScene(){
        super();
        SudokuWindow sudokuWindow = new SudokuWindow(9);
        UserWindow userWindow = new UserWindow(sudokuWindow,160);
        getChildren().addAll(sudokuWindow,userWindow);
        //testSetteur(sudokuWindow);
    }
}