package sudoku;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AboutPopUp extends Alert {
    public AboutPopUp(){
        super(AlertType.NONE);

        Text text = new Text(
        "Solveur sudoku réalisé dans le cadre de l'UE gestion de projet\n"+
        "L3 Informatique de L'université de Poitiers\n\n"+
        "Alexandre Guillot : Solveur CSP et Backtracking\n"+
        "Maël Dumont : Algorithme de résolution et Backtracking\n"+
        "Rémy Martin : Interface graphique et liaison au solveur\n"+
        "Ambroise Guillemeteau : Interface graphique\n\n"+
        "22/03/2024\n");
        text.setTextAlignment(TextAlignment.CENTER);
        getDialogPane().setContent(text);

        setTitle("A propos");
        getButtonTypes().add(ButtonType.CLOSE);
        
        this.show();
    }
}
