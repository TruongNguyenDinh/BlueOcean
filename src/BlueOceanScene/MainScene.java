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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import IconFactoryPkg.IconFact;
import javafx.scene.shape.SVGPath;


/**
 *
 * @author truon
 */
public class MainScene extends Application {
    private static String Id;
    private static boolean news = true,apps = false,setting = false,info = false;
    private IconFact icon = new IconFact();
    public static void setId(String id) {
        Id = id;
    }

    @Override
    public void start(Stage primaryStage) {
        openMainStage(Id);
    }
    private HBox newsPane(){
        HBox news = new HBox();
        return  news;
    }
    public static void openMainStage(String id) {
//        Pane rootPane = new Pane(); // Tạo Pane chính
        Stage mainStage = new Stage();
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #bad9d6");
        Scene scene = new Scene(mainPane, 1000, 700);
        mainStage.setTitle("Chat App - " + Id);
        mainStage.initStyle(StageStyle.UNDECORATED);

        mainStage.setMaximized(true);
        mainStage.setScene(scene);
              
        Rectangle rect1 = new Rectangle();
        Rectangle rect1_1 = new Rectangle();
        
        Rectangle mode  = new Rectangle();
        
        Rectangle rect2 = new Rectangle();
        Rectangle rect2_1 = new Rectangle();
        
        Rectangle rect3 = new Rectangle();
        Rectangle rect3_1 = new Rectangle();
        Rectangle rect3_1_1 = new Rectangle();
        Rectangle rect3_1_2 = new Rectangle();
        
        Button newsbtn = new Button();
        newsbtn.setText("News");
        Button appsbtn = new Button();
        appsbtn.setText("Apps");
        Button infobtn = new Button();
        infobtn.setText("Info");
        Button settingbtn = new Button();
        settingbtn.setText("Setting");
        
        Button logoutbtn = new Button();
        logoutbtn.setText("Log out");
        
        Text close = new Text("X");
        close.setFill(Color.RED);
        rect1.widthProperty().bind(scene.widthProperty().multiply(0.15));
        rect1_1.widthProperty().bind(rect1.widthProperty());
        
        newsbtn.prefWidthProperty().bind(rect1.widthProperty());
        appsbtn.prefWidthProperty().bind(rect1.widthProperty());
        infobtn.prefWidthProperty().bind(rect1.widthProperty());
        settingbtn.prefWidthProperty().bind(rect1.widthProperty());
        logoutbtn.prefWidthProperty().bind(rect1.widthProperty());
        
        mode.widthProperty().bind(scene.widthProperty().multiply(0.9));
        
        rect2.widthProperty().bind(scene.widthProperty().multiply(0.44));
        rect2_1.widthProperty().bind(rect2.widthProperty());
        
        rect3.widthProperty().bind(scene.widthProperty().multiply(0.474));
        rect3_1.widthProperty().bind(rect3.widthProperty());
        rect3_1_1.widthProperty().bind(rect3.widthProperty().multiply(0.8));
        rect3_1_2.widthProperty().bind(rect3.widthProperty().multiply(0.05));

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("y:" +newVal.doubleValue());
            rect1.setHeight(newVal.doubleValue() * 0.1);
            mode.setHeight(newVal.doubleValue() * 0.1);
            
            newsbtn.setPrefHeight(newVal.doubleValue()*0.06);
            settingbtn.setPrefHeight(newVal.doubleValue()*0.06);
            appsbtn.setPrefHeight(newVal.doubleValue()*0.06);
            infobtn.setPrefHeight(newVal.doubleValue()*0.06);
            logoutbtn.setPrefHeight(newVal.doubleValue()*0.06);
            
            rect1_1.setHeight(newVal.doubleValue() * 0.599);
            
            rect2.setHeight(newVal.doubleValue() * 0.4);
            rect2_1.setHeight(newVal.doubleValue() * 0.43);
            
            rect3.setHeight(newVal.doubleValue() * 0.05);
            rect3_1.setHeight(newVal.doubleValue() * 0.8455);
            rect3_1_1.setHeight(newVal.doubleValue() * 0.03);
            rect3_1_2.setHeight(newVal.doubleValue() * 0.03);
        });
        rect1.setFill(Color.web("012a4a")); 
        rect1_1.setFill(Color.web("012a4a")); 
        
        rect2.setFill(Color.CHARTREUSE); 
        rect2_1.setFill(Color.ANTIQUEWHITE); 
        
        rect3.setFill(Color.web("ffffff")); 
        rect3_1.setFill(Color.web("000000")); 
        rect3_1_1.setFill(Color.AQUA); 
        rect3_1_2.setFill(Color.WHITE);
        rect3_1_2.setOnMouseClicked(e ->{
            mainStage.close();
        });
        
        mode.setFill(Color.AQUA); 
        appsbtn.setStyle(""
                + "-fx-background-color : #012a4a;"
                + "-fx-text-fill : white;"
                + "-fx-font-size: 15px;"
                + "-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0; -fx-padding: 0;"
                + "-fx-font-family: 'Time New Roman'" );
        newsbtn.setStyle(""
                + "-fx-background-color : #012a4a;"
                + "-fx-text-fill : white;"
                + "-fx-background-radius: 0;"
                + "-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0; -fx-padding: 0;"
                + "-fx-font-size: 14px;"
                + "-fx-font-family: 'Time New Roman'" );
        settingbtn.setStyle(""
                + "-fx-background-color : #012a4a;"
                + "-fx-text-fill : white;"
                + "-fx-background-radius: 0;"
                + "-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0; -fx-padding: 0;"
                + "-fx-font-size: 15px;"
                + "-fx-font-family: 'Time New Roman'" );
        infobtn.setStyle(""
                + "-fx-background-color : #012a4a;"
                + "-fx-text-fill : white;"
                + "-fx-background-radius: 0;"
                + "-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0; -fx-padding: 0;"
                + "-fx-font-size: 15px;"
                + "-fx-font-family: 'Time New Roman'" );
        logoutbtn.setStyle(""
                + "-fx-background-color : #012a4a;"
                + "-fx-text-fill : white;"
                + "-fx-background-radius: 0;"
                + "-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0; -fx-padding: 0;"
                + "-fx-font-size: 15px;"
                + "-fx-font-family: 'Time New Roman'" );
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
//            rect1.setTranslateY(newHeight.doubleValue() * 0.015);
            newsbtn.setTranslateY(newHeight.doubleValue() * 0.0005);
            logoutbtn.setTranslateY(newHeight.doubleValue() * 0.0025);
//            rect1_1.setTranslateY(newHeight.doubleValue() * 0.002);
            mode.setTranslateY(newHeight.doubleValue() * 0.003);
//            news.setTranslateY(newHeight.doubleValue() * 0.015);
            rect2.setTranslateY(newHeight.doubleValue() * 0.015);
            rect2_1.setTranslateY(newHeight.doubleValue() * 0.03);
            close.setTranslateY(newHeight.doubleValue() * 0.016);
//            rect3.setTranslateY(newHeight.doubleValue() * 0.015);
            rect3_1.setTranslateY(newHeight.doubleValue() * 0.015);
            rect3_1_1.setTranslateY(newHeight.doubleValue() * 0.001);
            rect3_1_2.setTranslateY(newHeight.doubleValue() * 0.001);
        });
        Text text = new Text("Blue Ocean");
        text.setFont(Font.font(20));
        text.setFill(Color.GRAY);
        
         // Cập nhật vị trí chữ để nó nằm giữa hình chữ nhật
        rect1.widthProperty().addListener((obs, oldVal, newVal) -> 
            text.setX(newVal.doubleValue() / 2 - text.getLayoutBounds().getWidth() / 2)
        );
        rect1.heightProperty().addListener((obs, oldVal, newVal) -> 
            text.setY(newVal.doubleValue() / 2 + text.getLayoutBounds().getHeight() / 4)
        );
        Pane pane = new Pane(rect1,text);
        scene.widthProperty().addListener((obs, oldHeight, newHeight) -> {
//            rect1.setTranslateX(newHeight.doubleValue() * 0.0098);
//            rect1_1.setTranslateX(newHeight.doubleValue() * 0.0098);
//            newsbtn.setTranslateX(newHeight.doubleValue() * 0.0098);
//            appsbtn.setTranslateX(newHeight.doubleValue() * 0.0098);
//            infobtn.setTranslateX(newHeight.doubleValue() * 0.0098);
            mode.setTranslateX(newHeight.doubleValue() * 0.003);
            rect2.setTranslateX(newHeight.doubleValue() * 0.003);
            rect2_1.setTranslateX(newHeight.doubleValue() * 0.003);
            rect3.setTranslateX(newHeight.doubleValue() * 0.0055);
            rect3_1.setTranslateX(newHeight.doubleValue() * 0.0055);
            rect3_1_1.setTranslateX(newHeight.doubleValue() * 0.447);
            rect3_1_2.setTranslateX(newHeight.doubleValue() * 0.445);
            close.setTranslateX(newHeight.doubleValue() * 0.835);
        }); 
        Pane closePane = new Pane(close,rect3_1_2);
        HBox title_bar = new HBox(rect3_1_1,closePane);
        closePane.setStyle("-fx-border-color: red;"); // Để dễ nhìn
        VBox group_1 = new VBox(rect2,rect2_1);
        VBox group_2 = new VBox(title_bar,rect3_1);
        HBox container = new HBox(group_1,group_2);
        VBox taskbar = new VBox(pane,newsbtn,appsbtn,infobtn,settingbtn,logoutbtn,rect1_1);
        
        VBox content_layout = new VBox(title_bar,mode,container);
        HBox layout = new HBox(taskbar,content_layout);
        mainPane.setLeft(layout);
        mainStage.show();
    }  
    

    public static void main(String[] args) {
        launch(args);
    }
}
