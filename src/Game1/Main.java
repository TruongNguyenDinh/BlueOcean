package Game1;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MenuScene menuScene = new MenuScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
