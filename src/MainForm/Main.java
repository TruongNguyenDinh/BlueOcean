/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm;

import MainForm.Views.LogInView;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author truong
 */
public class Main extends Application {
    @Override
    public void start(Stage splashStage) {
        // Tạo và hiển thị cửa sổ splash
        ImageView logoView = new ImageView(new Image("Image/BlueOcean2.png"));
        logoView.setFitWidth(300);
        logoView.setFitHeight(300);
        logoView.setPreserveRatio(true);

        StackPane splashPane = new StackPane(logoView);
        Scene splashScene = new Scene(splashPane, 400, 400); // Thêm kích thước
        splashScene.setFill(null); // Làm trong suốt nền
        // Đặt cửa sổ trong suốt, không viền
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.setScene(splashScene);
        splashStage.show();

        // Chờ 3 giây trước khi mở cửa sổ đăng nhập
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            splashStage.close(); // Đóng màn hình splash
            javafx.application.Platform.runLater(() -> {
                        splashStage.close();
                        new LogInView().start(new Stage()); // Mở cửa sổ đăng nhập
                    });

        });
        delay.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
