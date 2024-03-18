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

    //fonction de stress test pour les setCellValue, plus d'utilité a part pour des démonstration
    private void testSetteur(SudokuWindow sudokuWindow){
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudokuWindow.setCellValue(x, y, y+1);
            }
        }
    }
}