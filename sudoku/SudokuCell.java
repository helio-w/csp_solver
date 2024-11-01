package sudoku;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SudokuCell extends GridPane{
    private int value = 0;
    private Button valueButton = new Button();
    private int fontSize = 30;
    public SudokuCell(int size){
        super();
        for (int index = 0; index < size; index++)
        {
            NoteButton button = new NoteButton(index+1);
            add(button,index%3,index/3);
            button.setOnAction(event -> {
                setValue(button.getValue());
                oneButtonView();
            });
        }
        add(valueButton,0,0,3,3);
        valueButton.setMaxWidth(Double.MAX_VALUE);
        valueButton.setMaxHeight(Double.MAX_VALUE);
        changeStyleCell("#a09c9b");

        valueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                valueButtonAction();
            }
        });
    }

    public void setValue(int newValue){
        value = newValue;
        if (newValue != 0)
        {
            valueButton.setText(String.valueOf(newValue));
        }
        else
        {
            valueButton.setText(" ");
        }
        
    }

    public int getValue(){
        return value;
    }

    public void oneButtonView(){
        valueButton.setVisible(true);
    }

    public void multipleButtonView(){
        valueButton.setVisible(false);
    }

    public void valueButtonAction(){
        if(value == 0)
        {
            multipleButtonView();
        }
        else
        {
            setValue(0);
        }
    }

    public void changeStyleCell(String newColor){
        valueButton.setStyle("-fx-border-color: "+newColor+"; -fx-font-size:"+fontSize);
    }
}
