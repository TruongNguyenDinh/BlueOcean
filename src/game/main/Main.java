package game.main;

import game.component.PanelGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private Scene menuScene;
    private Stage primaryStage;

    private Button createStyledButton(String text) {
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

        // Hiệu ứng hover
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

        button.setPrefWidth(250); // Đặt chiều rộng cố định cho nút
        return button;
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showMenu();
    }
    
    private void showMenu() {
        // Tạo hình nền
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image(getClass().getResource("/game/image/back.png").toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        // Tạo bố cục menu
        VBox menuLayout = new VBox(20); // Tăng khoảng cách giữa các nút
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        // Tiêu đề
        Label title = new Label("🚀 Rocket Shooting");
        title.setStyle("-fx-font-size: 64px; -fx-text-fill: linear-gradient(#ff5400, #be1d00); -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 15, 0.7, 0, 0);");

        // Các nút
        Button playBtn = createStyledButton("▶ Play");
        Button scoresBtn = createStyledButton("🏆 Ranking");
        Button helpBtn = createStyledButton("❓ Help");
        Button creditBtn = createStyledButton("👨‍💻 Credit");
        Button exitBtn = createStyledButton("❌ Exit");

        // Đặt sự kiện cho các nút
        playBtn.setOnAction(e -> showGame());
        scoresBtn.setOnAction(e -> showAlert("🏆 Ranking", "🚀 Top Players:\n\n1. Player1 - 5000 points\n2. Player2 - 4500 points\n3. Player3 - 4000 points\n\nFeature coming soon!"));
        helpBtn.setOnAction(e -> showAlert("❓ Help", "🎮 How to Play:\n\n- Use 'A' and 'D' to move left and right.\n- Use 'W' to speed up.\n- Use 'J' to shoot bullets.\n- Use 'K' to launch rockets.\n\nSurvive as long as you can!"));
        creditBtn.setOnAction(e -> showAlert("👨‍💻 Credit", "✨ Developed by:\n\n- Nhatt\nSpecial thanks to all contributors!"));
        exitBtn.setOnAction(e -> primaryStage.close());

        // Thêm các thành phần vào bố cục
        menuLayout.getChildren().addAll(title, playBtn, scoresBtn, helpBtn, creditBtn, exitBtn);

        // Tạo StackPane để chứa hình nền và menu
        StackPane root = new StackPane();
        root.setBackground(new Background(backgroundImage));
        root.getChildren().add(menuLayout);

        // Tạo Scene và hiển thị
        menuScene = new Scene(root, 1366, 768);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Rocket Shooting");
        primaryStage.show();
    }

    private void showGame() {
        PanelGame panelGame = new PanelGame(1366, 768);

        StackPane root = new StackPane(panelGame);
        Scene gameScene = new Scene(root, 1366, 768);

        // Lắng nghe khi Game Over
        panelGame.setOnGameOver(score -> {
            System.out.println("Game Over! Score: " + score);
            // Ở đây bạn có thể lưu vào DB nếu cần
        });
        panelGame.setOnBackToMenu(back -> {
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

        // Tùy chỉnh giao diện hộp thoại
        alert.getDialogPane().setStyle(
            "-fx-font-size: 16px; " +
            "-fx-font-family: 'Arial'; " +
            "-fx-background-color: linear-gradient(#ff5400, #be1d00); " +
            "-fx-text-fill: white;"
        );

        // Thêm icon cho hộp thoại
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/game/image/startup.png").toExternalForm()));

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}