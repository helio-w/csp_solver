package sudoku.sudoku_interface;

import javafx.scene.control.Button;

public class NoteButton extends Button{
    int value;
    boolean interactable;
    public NoteButton(int number){
        super(String.valueOf(number));
        interactable = true;
        value = number;
    }

    public int getValue(){
        return value;
    }
}
