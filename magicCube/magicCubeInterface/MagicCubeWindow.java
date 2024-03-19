package magicCube.magicCubeInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import magicCube.Carre;

public class MagicCubeWindow extends GridPane{

    private HashMap<List<Integer>, MagicCell> magicCellsArray = new HashMap<List<Integer>, MagicCell>();

    public MagicCubeWindow(double windowWidth ,int gridSize){
        super();
        int gridSquareSize = (int) java.lang.Math.sqrt(gridSize); 
        for(int i = 0; i < gridSize; i++)
        {
            MagicCell actuCell = new MagicCell(60);

            int cooX = i/gridSquareSize;
            int cooY = i%gridSquareSize;
            this.add(actuCell,cooX,cooY);
            
            magicCellsArray.put(Arrays.asList(cooX, cooY), actuCell);
        }
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
    
    public void setValue(int cooX, int cooY, int value){
        MagicCell actuCell = magicCellsArray.get(Arrays.asList(cooX, cooY));
        actuCell.setValue(value);
    }

    public void startSolve(int magicCubeValue){
        Carre solveurCarre = new Carre(magicCubeValue);
        if(solveurCarre.solve())
        {
            for (HashMap.Entry cell : magicCellsArray.entrySet()) {
                List<Integer> coordonnee = (List<Integer>) cell.getKey();
                int cooX = coordonnee.get(0);
                int cooY = coordonnee.get(1);
                setValue(cooX, cooY, solveurCarre.getCell(cooX, cooY).getValue());
            }
        }
        else
        {
            Alert dialog = new Alert(AlertType.NONE,"Aucune solution pour la valeur n'a été trouvé", ButtonType.CLOSE);
            dialog.show();
        }
    }
}
