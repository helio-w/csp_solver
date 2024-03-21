package sudoku.sudoku_interface;

import javafx.scene.layout.HBox;

public class MainScene extends HBox {
    public MainScene(){
        super();
        SudokuWindow sudokuWindow = new SudokuWindow(9);
        UserWindow userWindow = new UserWindow(sudokuWindow,160);
        getChildren().addAll(sudokuWindow,userWindow);
        presentation(sudokuWindow);
    }

    public void presentation(SudokuWindow sudokuWindow){
        sudokuWindow.setCellValue(4, 0, 5);
        sudokuWindow.setCellValue(6, 0, 9);
        sudokuWindow.setCellValue(8, 0, 2);

        sudokuWindow.setCellValue(1, 1, 6);
        sudokuWindow.setCellValue(7, 1, 4);

        sudokuWindow.setCellValue(3, 2, 7);
        sudokuWindow.setCellValue(5, 2, 4);
        sudokuWindow.setCellValue(6, 2, 5);
        sudokuWindow.setCellValue(8, 2, 8);

        sudokuWindow.setCellValue(2, 3, 2);
        sudokuWindow.setCellValue(5, 3, 3);
        sudokuWindow.setCellValue(6, 3, 8);

        sudokuWindow.setCellValue(0, 4, 3);
        sudokuWindow.setCellValue(4, 4, 8);
        sudokuWindow.setCellValue(8, 4, 6);

        sudokuWindow.setCellValue(2, 5, 7);
        sudokuWindow.setCellValue(3, 5, 4);
        sudokuWindow.setCellValue(6, 5, 1);

        sudokuWindow.setCellValue(0, 6, 8);
        sudokuWindow.setCellValue(2, 6, 4);
        sudokuWindow.setCellValue(3, 6, 3);
        sudokuWindow.setCellValue(5, 6, 9);

        sudokuWindow.setCellValue(1, 7, 3);
        sudokuWindow.setCellValue(7, 7, 8);
        
        sudokuWindow.setCellValue(0, 8, 2);
        sudokuWindow.setCellValue(2, 8, 5);
        sudokuWindow.setCellValue(4, 8, 6);
    }
}