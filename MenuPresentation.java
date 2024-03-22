import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MenuPresentation extends Application{
    Scene containMainScene;

    @Override
    public void start(Stage primaryStage){

        VBox mainScene = new VBox();
        mainScene.setPadding(new Insets(10));
        mainScene.setAlignment(Pos.CENTER);
        mainScene.setPrefHeight(150);
        mainScene.setPrefWidth(200);

        mainScene.setSpacing(20);

        Button sudokuButton = new Button("Sudoku");
        Button magicCubeButton = new Button("Carré magique");

        sudokuButton.setPadding(new Insets(10));
        magicCubeButton.setPadding(new Insets(10));

        mainScene.getChildren().addAll(sudokuButton,magicCubeButton);

        containMainScene = new Scene(mainScene);

        sudokuButton.setOnAction(event -> {
            setStage(primaryStage, "Solveur sudoku",new sudoku.sudoku_interface.MainScene());
        });

        magicCubeButton.setOnAction(event -> {
            setStage(primaryStage, "Solveur carré magique", new magicCube.magicCubeInterface.MainScene());
        });

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
        if (KeyCode.ESCAPE == event.getCode()) {
            setStage(primaryStage, "Menu",mainScene);
        }});

        primaryStage.setTitle("Menu");
        primaryStage.setScene(containMainScene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }

    public void setStage(Stage primaryStage , String title, Parent newRoot){
        primaryStage.setTitle(title);
        containMainScene.setRoot(newRoot);
        primaryStage.sizeToScene();
    }
}
