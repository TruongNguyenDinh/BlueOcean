/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author truon
 */
public class MainScene extends Application {
    private static String Id;
    private static double width,height;
    
    public static void setId(String id) {
        Id = id;
    }

    @Override
    public void start(Stage primaryStage) {
        openMainStage(Id);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void openMainStage(String id) {
        Stage mainStage = new Stage();
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #013a6a");
        Scene scene = new Scene(mainPane, 1000, 700);
        mainStage.setTitle("Chat App - " + Id);
        mainStage.setMaximized(true);
        mainStage.setScene(scene);
        
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();
        Rectangle rect3 = new Rectangle();
//        rect1.widthProperty().bind(scene.widthProperty().multiply(0.05));
//        rect1.heightProperty().bind(scene.heightProperty().multiply(0.97));
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            rect1.setWidth(newVal.doubleValue() * 0.05);
            rect2.setWidth(newVal.doubleValue() * 0.47);
            rect3.setWidth(newVal.doubleValue() * 0.44);
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            rect1.setHeight(newVal.doubleValue() * 0.97);
            rect2.setHeight(newVal.doubleValue() * 0.97);
            rect3.setHeight(newVal.doubleValue() * 0.97);
        });
        rect1.setFill(Color.web("012a4a")); 
        rect2.setFill(Color.web("012a4a")); 
        rect3.setFill(Color.web("012a4a")); 
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            rect1.setTranslateY(newHeight.doubleValue() * 0.015);
            rect2.setTranslateY(newHeight.doubleValue() * 0.015);
            rect3.setTranslateY(newHeight.doubleValue() * 0.015);
        });
        scene.widthProperty().addListener((obs, oldHeight, newHeight) -> {
            rect1.setTranslateX(newHeight.doubleValue() * 0.0098);
            rect2.setTranslateX(newHeight.doubleValue() * 0.018);
            rect3.setTranslateX(newHeight.doubleValue() * 0.028);
        });  
        HBox taskbar = new HBox(rect1,rect2,rect3);
        mainPane.setLeft(taskbar);
        mainStage.show();
    }
    
}
