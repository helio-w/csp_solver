package magicCube.magicCubeInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
    @Override
    public void start(Stage primaryStage){

        MainScene mainScene = new MainScene();
        
        Scene containMainScene = new Scene(mainScene);

        primaryStage.setTitle("Solveur carré magique");
        primaryStage.setScene(containMainScene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}