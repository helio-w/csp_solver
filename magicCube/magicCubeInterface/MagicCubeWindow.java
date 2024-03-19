package magicCube.magicCubeInterface;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MagicCubeWindow extends GridPane{
    public MagicCubeWindow(double windowWidth ,int gridSize){
        super();
        int gridSquareSize = (int) java.lang.Math.sqrt(gridSize); 
        for(int i = 0; i < gridSize; i++)
        {
            Label actuCell = new MagicCell(60);

            this.add(actuCell,i/gridSquareSize,i%gridSquareSize);
        }
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
    
}
