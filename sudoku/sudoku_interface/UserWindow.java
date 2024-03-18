package sudoku.sudoku_interface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class UserWindow extends VBox{
    Button start;
    Button clear;
    SudokuWindow sudoku;
    public UserWindow(SudokuWindow sudoku, double windowWidth){
        super();
        this.setAlignment(Pos.CENTER);
        this.sudoku = sudoku;
        start = new Button("Start");
        clear = new Button("Clear");

        start.setOnAction(event -> {
            sudoku.startSolve();
        });
        
        clear.setOnAction(event -> {
            sudoku.resetAll();
        });

        Insets buttonInset = new Insets(10,10,10,10);

        start.setMaxHeight(windowWidth*0.7);
        clear.setMaxHeight(windowWidth*0.7);
        start.setMaxWidth(windowWidth);
        clear.setMaxWidth(windowWidth);
        start.setStyle("-fx-font-size:30");
        clear.setStyle("-fx-font-size:30");

        setPrefWidth(windowWidth);

        setMargin(start, buttonInset);
        setMargin(clear, buttonInset);

        setVgrow(start, Priority.ALWAYS);
        setVgrow(clear, Priority.ALWAYS);

        getChildren().addAll(start,clear);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
    
}
