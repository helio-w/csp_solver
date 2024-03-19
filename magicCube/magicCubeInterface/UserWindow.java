package magicCube.magicCubeInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    int magicCubeValue = 0;
    TextField valueField;
    public UserWindow(double windowWidth){
        super();
        this.setAlignment(Pos.CENTER);

        start = new Button("Start");
        valueField = new TextField("Entrée une valeur");

        /*start.setOnAction(event -> {
            sudoku.startSolve();
        });*/

        start.setMaxHeight(windowWidth*0.7);
        valueField.setMaxHeight(windowWidth*0.7);
        start.setMaxWidth(windowWidth);
        valueField.setMaxWidth(windowWidth);
        start.setStyle("-fx-font-size:15");

        setMargin(start, new Insets(10,10,10,10));
        setVgrow(start, Priority.ALWAYS);
        setMargin(valueField, new Insets(10,10,10,10));
        setVgrow(valueField, Priority.ALWAYS);

        setPrefWidth(windowWidth);

        getChildren().addAll(start,valueField);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
    
}
