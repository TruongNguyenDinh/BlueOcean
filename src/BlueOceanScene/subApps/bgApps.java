/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.subApps;

import BlueOceanScene.Main;
import BlueOceanScene.Utils.ReminderPanel;
import static BlueOceanScene.Layouts.AppsLayout.detailappbgPane;
import Font.FontManagement;
import Game1.MenuScene;
import MainForm.Models.User;
import game.main.MainRocketShoot;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Game2.MainFlappyBird;
import LanguagePackage.LanguageManager;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
/**
 *
 * @author truon
 */
public class bgApps{
    private static Node namm = null;
    private static Node detaill = null;
    private static Node btn = null;
    private static Node img = null;
    private static ImageView imageView;
    private static Button openApp;
    private static final Main main = new Main();
    private static final ReminderPanel reminderPanel = new ReminderPanel();
    public static void showDetail(int i,Scene scene){
        Text name = new Text();
        Text detail = new Text();
        namm = detailappbgPane.lookup("#name");
        detaill = detailappbgPane.lookup("#detail");
        btn = detailappbgPane.lookup("#btn");
        img = detailappbgPane.lookup("#img");
        if (namm!=null){
            detailappbgPane.getChildren().remove(namm);
        }
        if (detaill!=null){
            detailappbgPane.getChildren().remove(detaill);
        }
        if (btn!=null){
            detailappbgPane.getChildren().remove(btn);
        }
        if (img!=null){
            detailappbgPane.getChildren().remove(img);
        }
        openApp = new Button("Open");
        
        switch (i){
            case 1 -> {
                imageView = new ImageView(new Image("Image/BackGroundApps/TicTacToe.jpg"));
                name.setText(LanguageManager.get("BO.bgApps.tictactoe.name"));
                detail.setText(LanguageManager.get("BO.bgApps.tictactoe.detail"));
                
            }
            case 2 -> {
                imageView = new ImageView(new Image("Image/BackGroundApps/Note.jpg"));
                name.setText(LanguageManager.get("BO.bgApps.noteapp.name"));
                detail.setText(LanguageManager.get("BO.bgApps.noteapp.detail"));
            }
            case 3 ->{
                imageView = new ImageView(new Image("Image/BackGroundApps/FlappyBird.jpg"));
                name.setText(LanguageManager.get("BO.bgApps.rocketshooting.name"));
                detail.setText(LanguageManager.get("BO.bgApps.rocketshooting.detail"));
            }
            case 4 -> {
                imageView = new ImageView(new Image("Image/BackGroundApps/RocketShooting.jpg"));
                name.setText(LanguageManager.get("BO.bgApps.flappybird.name"));
                detail.setText(LanguageManager.get("BO.bgApps.flappybird.detail"));
            }
        } 
        name.setFont(FontManagement.Pacifico(60));
        name.setTranslateY(-300);
        name.setTranslateX(-100);
        detail.setFont(FontManagement.Roboto(20));
        detail.setWrappingWidth(800);
        detail.setTranslateY(-200);
        detail.setTranslateX(-100);
        
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(Color.CYAN); // màu viền sáng
        glow.setRadius(20);        // độ lan sáng
        glow.setSpread(0.8);       // mức độ phủ sáng

        name.setEffect(glow);
        detail.setEffect(glow);
        
        imageView.setTranslateX(scene.getWidth() * 0.005);
        openApp.setTranslateX(scene.getWidth() * 0.005);
        imageView.fitHeightProperty().bind(scene.heightProperty().multiply(0.84));
        imageView.fitWidthProperty().bind(scene.widthProperty().multiply(0.8));
        Platform.runLater(()->{
                scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
//                imageView.setTranslateY( newHeight.doubleValue()*0.008);
                });
                scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
                imageView.setTranslateX(newWidth.doubleValue()*0.005);
                openApp.setTranslateX(newWidth.doubleValue()*0.005);
                });
            
        });
         
        openApp.getStyleClass().add("openAppBtn");
        openApp.prefWidthProperty().bind(detailappbgPane.widthProperty().multiply(0.8));
        openApp.setOnMouseClicked(e->{
            switch(i) {
                case 1 ->{launchingApp1();break;}
                case 2 ->{launchingApp2();break;}
                case 3 ->{launchingApp3();break;}
                case 4 ->{launchingApp4();break;}        
            }
         });
         
        detailappbgPane.getChildren().add(imageView);
        detailappbgPane.getChildren().add(openApp);
        detailappbgPane.getChildren().add(name);
        detailappbgPane.getChildren().add(detail);
        StackPane.setAlignment(openApp, Pos.BOTTOM_LEFT);
        detail.setId("detail");
        name.setId("name");
        openApp.setId("btn");
        imageView.setId("img");
    }
    private static void launchingApp1(){
        Stage app1Stage = new Stage();
        MenuScene menuScene = new Game1.MenuScene(app1Stage);
        app1Stage.setOnCloseRequest(event->{
        });
        app1Stage.show(); // Nếu chưa được show bên trong MenuScene
    }
    private static void launchingApp2(){
        Stage stage = new Stage();
        BorderPane reminderPaneWithDate = reminderPanel.getReminderPane(null,User.getId()); 
        Scene newScene = new Scene(reminderPaneWithDate, 850, 300);
        stage.setScene(newScene);
        stage.setResizable(false);
        stage.setOnCloseRequest(close->{
            reminderPanel.stop();
        });
        stage.setTitle(LanguageManager.get("BO.CalendarView.info"));
        stage.show();
    }
    private static void launchingApp4() {
        Platform.runLater(() -> {
            Stage flappyStage = new Stage();
            try {
                MainFlappyBird flappyApp = new MainFlappyBird();
                MainFlappyBird.setbtn(true);
                flappyApp.start(flappyStage);  // Tạo stage riêng và khởi động game
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
}

    private static void launchingApp3() {
        Platform.runLater(() -> {
            // Tạo một cửa sổ mới (Stage) để chạy game
            Stage gameStage = new Stage();
            try {
                MainRocketShoot gameApp = new MainRocketShoot();
                gameApp.start(gameStage);  // Start game trong Stage mới
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
