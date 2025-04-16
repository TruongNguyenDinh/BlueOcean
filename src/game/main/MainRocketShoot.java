package game.main;

import game.component.PanelGame;
import game.obj.sound.Sound;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainRocketShoot extends Application {

    private Scene menuScene;
    private Stage primaryStage;

    private Button createStyledButton(String text, Sound sound) {
        Button button = new Button(text);
        button.setStyle(
            "-fx-background-color: linear-gradient(#ff5400,rgb(0, 149, 255));"
            + "-fx-background-radius: 30;"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 20px;"
            + "-fx-font-weight: bold;"
            + "-fx-padding: 10 20 10 20;"
            + "-fx-cursor: hand;"
            + "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 0);"
        );

        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: linear-gradient(#be1d00,rgb(0, 149, 255));"
            + "-fx-background-radius: 30;"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 20px;"
            + "-fx-font-weight: bold;"
            + "-fx-padding: 10 20 10 20;"
            + "-fx-cursor: hand;"
            + "-fx-effect: dropshadow(gaussian, yellow, 15, 0.7, 0, 0);"
        ));
        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: linear-gradient(#ff5400,rgb(0, 149, 255));"
            + "-fx-background-radius: 30;"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 20px;"
            + "-fx-font-weight: bold;"
            + "-fx-padding: 10 20 10 20;"
            + "-fx-cursor: hand;"
            + "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 0);"
        ));

        // Mặc định thêm âm thanh click cho mọi nút
        button.setOnAction(e -> sound.soundClick());

        button.setPrefWidth(250);
        return button;
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showMenu();
    }

    private void showMenu() {
        Sound sound = new Sound(); // Đối tượng âm thanh

        BackgroundImage backgroundImage = new BackgroundImage(
            new Image(getClass().getResource("/game/image/back.png").toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Label title = new Label("🚀 Rocket Shooting");
        title.setStyle("-fx-font-size: 64px; -fx-text-fill: linear-gradient(#ff5400, #be1d00); -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 15, 0.7, 0, 0);");

        // Tạo các nút có âm thanh
        Button playBtn = createStyledButton("▶ Play", sound);
        Button scoresBtn = createStyledButton("🏆 Ranking", sound);
        Button helpBtn = createStyledButton("❓ Help", sound);
        Button creditBtn = createStyledButton("👨‍💻 Credit", sound);
        Button exitBtn = createStyledButton("❌ Exit", sound);

        // Gán hành động cụ thể cho từng nút
        playBtn.setOnAction(e -> {
            sound.soundClick();
            showGame();
        });

        scoresBtn.setOnAction(e -> {
            sound.soundClick();
            showAlert("🏆 Ranking", "🚀 Top Players:\n\n1. Player1 - 5000 points\n2. Player2 - 4500 points\n3. Player3 - 4000 points\n\nFeature coming soon!");
        });

        helpBtn.setOnAction(e -> {
            sound.soundClick();
            showAlert("❓ Help", "🎮 How to Play:\n\n- Use 'A' and 'D' to move left and right.\n- Use 'W' to speed up.\n- Use 'J' to shoot bullets.\n- Use 'K' to launch rockets.\n\nSurvive as long as you can!");
        });

        creditBtn.setOnAction(e -> {
            sound.soundClick();
            showAlert("👨‍💻 Credit", "✨ Developed by:\n\n- Nhatt\nSpecial thanks to all contributors!");
        });

        exitBtn.setOnAction(e -> {
            sound.soundClick();
            primaryStage.close();
        });

        menuLayout.getChildren().addAll(title, playBtn, scoresBtn, helpBtn, creditBtn, exitBtn);

        StackPane root = new StackPane();
        root.setBackground(new Background(backgroundImage));
        root.getChildren().add(menuLayout);

        menuScene = new Scene(root, 1366, 768);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Rocket Shooting");
        primaryStage.show();
    }

    private void showGame() {
        Sound sound = new Sound(); // Tạo đối tượng Sound
        sound.playBackgroundMusic(); // Phát nhạc nền
    
        PanelGame panelGame = new PanelGame(1366, 768);
    
        StackPane root = new StackPane(panelGame);
        Scene gameScene = new Scene(root, 1366, 768);
    
        // Lắng nghe khi Game Over
        panelGame.setOnGameOver(score -> {
            System.out.println("Game Over! Score: " + score);
            sound.stopBackgroundMusic(); // Dừng nhạc nền khi Game Over
            showMenu();
        });
    
        panelGame.setOnBackToMenu(back -> {
            sound.stopBackgroundMusic(); // Dừng nhạc nền khi quay lại menu
            showMenu();
        });
    
        // Focus vào canvas khi scene load xong
        gameScene.setOnMouseClicked(e -> panelGame.requestFocus());
    
        primaryStage.setScene(gameScene);
        panelGame.requestFocus(); // Rất quan trọng để nhận key
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.getDialogPane().setStyle(
            "-fx-font-size: 16px; " +
            "-fx-font-family: 'Arial'; " +
            "-fx-background-color: linear-gradient(#ff5400, #be1d00); " +
            "-fx-text-fill: white;"
        );

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/game/image/startup.png").toExternalForm()));

        alert.showAndWait();
    }
}
