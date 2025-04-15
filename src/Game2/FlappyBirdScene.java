/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class FlappyBirdScene {
    private final Stage primaryStage;
    private Scene menuScene;
    private Scene gameScene;
    private final int boardWidth;
    private final int boardHeight;
    private FlappyBirdGame game;
    private int highScore = 0;
    private final String propertiesFile = "flappybird.properties";
    private Image backgroundImg;

    public FlappyBirdScene(Stage primaryStage, int width, int height) {
        this.primaryStage = primaryStage;
        this.boardWidth = width;
        this.boardHeight = height;
        
        // Tải ảnh nền
        loadBackgroundImage();
        
        // Tạo menu và tải điểm cao nhất
        loadHighScore();
        createMenuScene();
    }

    private void loadBackgroundImage() {
        try {
            String resourcePath = "src" + File.separator + "Game2" + File.separator + "resources" + File.separator;
            File bgFile = new File(resourcePath + "flappybirdbg.png");
            backgroundImg = new Image(bgFile.toURI().toString());
            System.out.println("Background image loaded successfully");
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            // Tạo ảnh nền mặc định nếu không tải được
            backgroundImg = createDefaultBackground();
        }
    }

    private Image createDefaultBackground() {
        // Tạo một nền xanh dương đơn giản 
        Canvas canvas = new Canvas(boardWidth, boardHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(0, 0, boardWidth, boardHeight);
        return canvas.snapshot(null, null);
    }

    private void loadHighScore() {
        Properties props = new Properties();
        try {
            File file = new File(propertiesFile);
            if (file.exists()) {
                try (FileInputStream in = new FileInputStream(file)) {
                    props.load(in);
                }
                String highScoreStr = props.getProperty("highScore", "0");
                highScore = Integer.parseInt(highScoreStr);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading high score: " + e.getMessage());
            highScore = 0;
        }
    }

    private void saveHighScore() {
        Properties props = new Properties();
        props.setProperty("highScore", String.valueOf(highScore));
        try {
            FileOutputStream out = new FileOutputStream(propertiesFile);
            props.store(out, "Flappy Bird High Score");
            out.close();
        } catch (IOException e) {
            System.err.println("Error saving high score: " + e.getMessage());
        }
    }

    private void createMenuScene() {
        // Tạo layout chính
        StackPane root = new StackPane();
        
        // Thêm ảnh nền
        BackgroundImage bgImage = new BackgroundImage(
            backgroundImg, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, true, true)
        );
        root.setBackground(new Background(bgImage));
        
        // Tạo layout cho các thành phần menu
        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setMaxWidth(300);
        
        // Thêm tiêu đề
        Label titleLabel = new Label("FLAPPY BIRD");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);");
             
        // Tạo nút bắt đầu
        Button startButton = createStyledButton("Bắt đầu ");
        startButton.setOnAction(e -> startGame());
        // Tạo nút reset điểm cao nhất
        Button resetHighScoreButton = createStyledButton("Cài lại điểm số ");
        resetHighScoreButton.setOnAction(e -> {
        highScore = 0;
        saveHighScore();
        });
        // Tạo nút thoát
        Button exitButton = createStyledButton("Thoát");
        exitButton.setOnAction(e -> primaryStage.close());
        
        // Thêm tất cả vào layout menu
        menuBox.getChildren().addAll(titleLabel, startButton,resetHighScoreButton, exitButton);
        
        // Thêm menu vào root
        root.getChildren().add(menuBox);
        
        // Tạo scene và thiết lập cho stage
        menuScene = new Scene(root, boardWidth, boardHeight);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        button.setStyle("-fx-background-color: #4CAF50; " +
                      "-fx-text-fill: white; " +
                      "-fx-padding: 10 20; " +
                      "-fx-background-radius: 5;");
        button.setPrefWidth(200);
        
        // Hover effect
        button.setOnMouseEntered(e -> 
            button.setStyle("-fx-background-color: #45a049; " +
                         "-fx-text-fill: white; " +
                         "-fx-padding: 10 20; " +
                         "-fx-background-radius: 5;"));
        
        button.setOnMouseExited(e -> 
            button.setStyle("-fx-background-color: #4CAF50; " +
                         "-fx-text-fill: white; " +
                         "-fx-padding: 10 20; " +
                         "-fx-background-radius: 5;"));
        
        return button;
    }

    private void startGame() {
        game = new FlappyBirdGame(boardWidth, boardHeight);
        game.setOnGameOver(score -> {
            if (score > highScore) {
                highScore = score;
                saveHighScore();
            }
            // Thêm nút để quay lại menu
            showGameOverMenu(score);
        });
        
        Pane gameRoot = new Pane();
        gameRoot.getChildren().add(game);
        
        gameScene = new Scene(gameRoot, boardWidth, boardHeight);
        gameScene.setOnKeyPressed(game::handleKeyPress);
        
        primaryStage.setScene(gameScene);
        game.requestFocus();
        game.startGame();
    }

    private void showGameOverMenu(int finalScore) {
        // Tạo overlay cho menu game over
        StackPane gameOverRoot = new StackPane();
        gameOverRoot.setPrefSize(boardWidth, boardHeight);
        
        // Tạo panel cho menu game over
        VBox gameOverBox = new VBox(15);
        gameOverBox.setAlignment(Pos.CENTER);
        gameOverBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 20; -fx-background-radius: 10;");
        gameOverBox.setMaxWidth(300);
        gameOverBox.setMaxHeight(250);
        
        // Thêm tiêu đề
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        gameOverLabel.setTextFill(Color.WHITE);
        
        // Thêm điểm số
        Label scoreLabel = new Label("Điểm: " + finalScore);
        scoreLabel.setFont(Font.font("Arial", 20));
        scoreLabel.setTextFill(Color.WHITE);
        
        // Thêm điểm cao nhất
        Label highScoreLabel = new Label("Điểm cao nhất: " + highScore);
        highScoreLabel.setFont(Font.font("Arial", 20));
        highScoreLabel.setTextFill(Color.WHITE);
        
        // Tạo nút chơi lại
        Button replayButton = createStyledButton("Chơi lại");
        replayButton.setOnAction(e -> startGame());
        
        // Tạo nút về menu chính
        Button menuButton = createStyledButton("Quay lại Menu");
        menuButton.setOnAction(e -> primaryStage.setScene(menuScene));
        
        // Thêm tất cả vào panel game over
        gameOverBox.getChildren().addAll(gameOverLabel, scoreLabel, highScoreLabel, replayButton, menuButton);
        
        // Thêm overlay vào game
        gameOverRoot.getChildren().add(gameOverBox);
        
        // Thêm overlay vào scene game hiện tại
        Pane gamePane = (Pane) gameScene.getRoot();
        gamePane.getChildren().add(gameOverRoot);
    }

    public void show() {
        primaryStage.setTitle("Flappy Bird");
        primaryStage.setResizable(false);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
}