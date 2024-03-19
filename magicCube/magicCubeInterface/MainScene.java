package magicCube.magicCubeInterface;

import javafx.scene.layout.HBox;

public class MainScene extends HBox{
    public MainScene(){
        super();
        MagicCubeWindow magicCubeWindow = new MagicCubeWindow(450,9);
        UserWindow userWindow = new UserWindow(150);
        getChildren().addAll(magicCubeWindow,userWindow);
    }
}
