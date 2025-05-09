package Game2;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainFlappyBird extends Application { 
    private static boolean btn;
    

    @Override
    public void start(Stage primaryStage) {
        int boardWidth = 360;
        int boardHeight = 640;
        
        FlappyBirdScene flappyBirdScene = new FlappyBirdScene(primaryStage, boardWidth, boardHeight);
        
        flappyBirdScene.show();
    }
    public static void setbtn(boolean btn) {
        MainFlappyBird.btn = btn;    
    }
    public static boolean getbtn(){
        return btn;
    }
    public static void main(String[] args) {
        launch(args);
    } 
}