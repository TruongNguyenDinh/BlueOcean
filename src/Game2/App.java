package Game2;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        int boardWidth = 360;
        int boardHeight = 640;
        
        // Khởi tạo FlappyBirdScene thay vì FlappyBirdGame trực tiếp
        FlappyBirdScene flappyBirdScene = new FlappyBirdScene(primaryStage, boardWidth, boardHeight);
        flappyBirdScene.show();
    }

    public static void main(String[] args) {
        launch(args);
    } 
}