/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

import static BlueOceanScene.AppsLayout.detailappbgPane;
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
/**
 *
 * @author truon
 */
public class bgApps{
    private static Stage mainStage ;
    private static ImageView imageView;
    private static Button openApp;
    private static final Main main = new Main();
    private static final ReminderPanel reminderPanel = new ReminderPanel();
    public static void showDetail(int i,Scene scene){
        mainStage = main.getStage();
        detailappbgPane.getChildren().remove(openApp);
        detailappbgPane.getChildren().remove(imageView);
        openApp = new Button("Open");
        switch (i){
            case 1 -> imageView = new ImageView(new Image("Image/BackGroundApps/TicTacToe.jpg"));
            case 2 -> imageView = new ImageView(new Image("Image/BackGroundApps/Note.jpg"));
            case 3 -> imageView = new ImageView(new Image("Image/BackGroundApps/FlappyBird.jpg"));
            case 4 -> imageView = new ImageView(new Image("Image/BackGroundApps/RocketShooting.jpg"));
        } 
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
        StackPane.setAlignment(openApp, Pos.BOTTOM_LEFT);
    }
    private static void launchingApp1(){
//        mainStage.hide();
        Stage app1Stage = new Stage();
        MenuScene menuScene = new Game1.MenuScene(app1Stage);
        app1Stage.setOnCloseRequest(event->{
//           mainStage.show();
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
        stage.setTitle("Thông tin ngày");
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
