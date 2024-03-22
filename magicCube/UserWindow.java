package magicCube;

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
    Button aPropos;
    int magicCubeValue = 0;
    TextField valueField;
    boolean hasBeenClicked = false;
    public UserWindow(double windowWidth, MagicCubeWindow magicCubeWindow){
        super();
        this.setAlignment(Pos.CENTER);

        start = new Button("Calculer");
        valueField = new TextField("Entrer une valeur");
        aPropos = new Button("A propos");

        start.setOnAction(e -> {
            magicCubeWindow.startSolve(Integer.parseInt(valueField.getText()));
        });

        valueField.setOnMouseClicked(e -> {
            if (!hasBeenClicked) {
                hasBeenClicked = true;
                valueField.setText("");
            }
        });

        aPropos.setOnAction(e -> {
            new AboutPopUp();
        });

        start.setMaxHeight(windowWidth*0.7);
        valueField.setMaxHeight(windowWidth*0.7);
        aPropos.setMaxHeight(windowWidth*0.5);

        start.setMaxWidth(windowWidth);  
        valueField.setMaxWidth(windowWidth);
        aPropos.setMaxWidth(windowWidth*0.5);

        start.setStyle("-fx-font-size:15");
        aPropos.setStyle("-fx-font-size:12");

        setMargin(start, new Insets(10,10,10,10));
        setVgrow(start, Priority.ALWAYS);
        setMargin(aPropos, new Insets(10,10,10,10));
        setVgrow(aPropos, Priority.ALWAYS);
        setMargin(valueField, new Insets(10,10,10,10));
        setVgrow(valueField, Priority.ALWAYS);

        setPrefWidth(windowWidth);

        getChildren().addAll(start,valueField,aPropos);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
}
