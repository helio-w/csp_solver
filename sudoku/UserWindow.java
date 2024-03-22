package sudoku;

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
    Button aPropos;
    SudokuWindow sudoku;
    public UserWindow(SudokuWindow sudoku, double windowWidth){
        super();
        this.setAlignment(Pos.CENTER);
        this.sudoku = sudoku;
        start = new Button("Start");
        clear = new Button("Clear");
        aPropos = new Button("About");

        start.setOnAction(event -> {
            sudoku.startSolve();
        });
        
        clear.setOnAction(event -> {
            sudoku.resetAll();
        });

        aPropos.setOnAction(event -> {
            new AboutPopUp();
        });

        Insets buttonInset = new Insets(10,10,10,10);

        start.setMaxHeight(windowWidth*0.7);
        clear.setMaxHeight(windowWidth*0.7);
        aPropos.setMaxHeight(windowWidth*0.5);
        start.setMaxWidth(windowWidth);
        clear.setMaxWidth(windowWidth);
        aPropos.setMaxWidth(windowWidth*0.8);
        start.setStyle("-fx-font-size:30");
        clear.setStyle("-fx-font-size:30");
        aPropos.setStyle("-fx-font-size:20");

        setPrefWidth(windowWidth);

        setMargin(start, buttonInset);
        setMargin(clear, buttonInset);
        setMargin(aPropos, buttonInset);

        setVgrow(start, Priority.ALWAYS);
        setVgrow(clear, Priority.ALWAYS);
        setVgrow(aPropos, Priority.ALWAYS);

        getChildren().addAll(start,clear,aPropos);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
}
