package magicCube.magicCubeInterface;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class MagicCell extends Label{
    private int value = 0;
    public MagicCell(double widthHeight){
        super(" ");
        setAlignment(Pos.CENTER);
        setStyle("-fx-font-size:"+widthHeight/2.5);
        setPrefHeight(widthHeight);
        setPrefWidth(widthHeight);
        setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public void setValue(int newValue){
        value = newValue;
        setText(String.valueOf(newValue));
    }
}
