package sudoku;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AboutPopUp extends Alert {
    public AboutPopUp(){
        super(AlertType.NONE);

        Text text = new Text(
        "Solveur sudoku réalisé dans le cadre de l'UE gestion de projet\n"+
        "Université de Poitiers\n"+
        "L3 Informatique\n\n"+
        "Alexandre Guillot\n"+
        "Maël Dumont\n"+
        "Rémy Martin\n"+
        "Ambroise Guillemeteau\n\n"+
        "2024");
        text.setTextAlignment(TextAlignment.CENTER);
        VBox vBox = new VBox(text);
        vBox.setPadding(new Insets(10));

        getDialogPane().setContent(vBox);

        setTitle("About");
        getButtonTypes().add(ButtonType.CLOSE);
        
        this.show();
    }
}
