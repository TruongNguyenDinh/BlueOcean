package Game2;

import MainForm.Models.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import java.util.List;
import java.util.Properties;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class FlappyBirdScene {
    private final Stage primaryStage;
    private Scene loginScene;
    private Scene menuScene;
    private Scene gameScene;
    private Scene rankingScene;
    private final int boardWidth;
    private final int boardHeight;
    private FlappyBirdGame game;
    private int highScore = 0;
    private final String propertiesFile = "flappybird.properties";
    private Image backgroundImg;
    private DatabaseConnection db;
    private int currentUserId = -1;
    private String currentFullname; // Thay currentUsername bằng currentFullname

    public FlappyBirdScene(Stage primaryStage, int width, int height) {
        this.primaryStage = primaryStage;
        this.boardWidth = width;
        this.boardHeight = height;
        this.db = new DatabaseConnection();
        
        loadBackgroundImage();
        loadHighScore();
        createLoginScene();
        
    }

    private void loadBackgroundImage() {
        try {
            String resourcePath = "src" + File.separator + "Game2" + File.separator + "resources" + File.separator;
            File bgFile = new File(resourcePath + "flappybirdbg.png");
            backgroundImg = new Image(bgFile.toURI().toString());
            System.out.println("Background image loaded successfully");
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            backgroundImg = createDefaultBackground();
        }
    }

    private Image createDefaultBackground() {
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

    private void createLoginScene() {
        StackPane root = new StackPane();
        BackgroundImage bgImage = new BackgroundImage(
            backgroundImg, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, true, true)
        );
        root.setBackground(new Background(bgImage));

        VBox loginBox = new VBox(20);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setMaxWidth(300);

        Label titleLabel = new Label("ĐĂNG NHẬP");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);");

        TextField usernameField = new TextField();
        usernameField.setText(User.getUsername());
        usernameField.setPromptText("Nhập tài khoản ");
        usernameField.setMaxWidth(200);
        usernameField.setStyle("-fx-font-size: 16; -fx-padding: 10;");

        PasswordField passwordField = new PasswordField();
        passwordField.setText(User.getPassword());
        passwordField.setPromptText("Nhập mật khẩu");
        passwordField.setMaxWidth(200);
        passwordField.setStyle("-fx-font-size: 16; -fx-padding: 10;");

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(Font.font("Arial", 14));

        Button loginButton = createStyledButton("Đăng nhập");
                  
         System.out.println("BTN " + MainFlappyBird.getbtn());
        loginButton.setOnAction(e -> {
            System.out.println("Here");
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            if (username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Vui lòng nhập đầy đủ tên tài khoản và mật khẩu!");
                return;
            }
            DatabaseConnection.UserValidationResult result = db.validateUserWithPassword(username, password);
            if (result.getUserId() != -1) {
                currentUserId = result.getUserId();
                currentFullname = result.getFullname(); // Lưu fullname
                createMenuScene();
                primaryStage.setScene(menuScene);
            } else {
                errorLabel.setText("Tài khoản hoặc mật khẩu không đúng!");
            }
        });
        Platform.runLater(() -> {
            if (MainFlappyBird.getbtn()) {
            loginButton.fire();
            }
        });
        loginBox.getChildren().addAll(titleLabel, usernameField, passwordField, errorLabel, loginButton);
        root.getChildren().add(loginBox);
        loginScene = new Scene(root, boardWidth, boardHeight);
    }

    private void createMenuScene() {
        StackPane root = new StackPane();
        BackgroundImage bgImage = new BackgroundImage(
            backgroundImg, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, true, true)
        );
        root.setBackground(new Background(bgImage));

        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setMaxWidth(300);

        Label titleLabel = new Label("FLAPPY BIRD");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);");

        Label welcomeLabel = new Label("Chào, " + (currentFullname != null ? currentFullname : "Người chơi")); // Hiển thị fullname
        welcomeLabel.setFont(Font.font("Arial", 25));
        welcomeLabel.setTextFill(Color.WHITE);

        Button startButton = createStyledButton("Bắt đầu");
        startButton.setOnAction(e -> startGame());

        Button rankingButton = createStyledButton("Xếp hạng");
        rankingButton.setOnAction(e -> showRankingScene());

        Button exitButton = createStyledButton("Thoát");
        exitButton.setOnAction(e -> primaryStage.close());

        menuBox.getChildren().addAll(titleLabel, welcomeLabel, startButton, rankingButton, exitButton);
        root.getChildren().add(menuBox);
        menuScene = new Scene(root, boardWidth, boardHeight);
    }

    private void showRankingScene() {
        StackPane root = new StackPane();
        BackgroundImage bgImage = new BackgroundImage(
            backgroundImg, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, true, true)
        );
        root.setBackground(new Background(bgImage));

        VBox rankingBox = new VBox(10);
        rankingBox.setAlignment(Pos.CENTER);
        rankingBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 20; -fx-background-radius: 10;");
        rankingBox.setMaxWidth(300);

        Label titleLabel = new Label("BẢNG XẾP HẠNG");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.WHITE);

        List<String> rankings = db.getRankings();
        if (rankings.isEmpty()) {
            Label emptyLabel = new Label("Chưa có xếp hạng!");
            emptyLabel.setFont(Font.font("Arial", 18));
            emptyLabel.setTextFill(Color.WHITE);
            rankingBox.getChildren().add(emptyLabel);
        } else {
            for (String rank : rankings) {
                Label rankLabel = new Label(rank);
                rankLabel.setFont(Font.font("Arial", 18));
                rankLabel.setTextFill(Color.WHITE);
                rankingBox.getChildren().add(rankLabel);
            }
        }

        Button backButton = createStyledButton("Quay lại");
        backButton.setOnAction(e -> primaryStage.setScene(menuScene));

        rankingBox.getChildren().addAll(titleLabel, backButton);
        root.getChildren().add(rankingBox);
        rankingScene = new Scene(root, boardWidth, boardHeight);
        primaryStage.setScene(rankingScene);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        button.setStyle("-fx-background-color: #4CAF50; " +
                      "-fx-text-fill: white; " +
                      "-fx-padding: 10 20; " +
                      "-fx-background-radius: 5;");
        button.setPrefWidth(200);
        
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
            if (currentUserId != -1) {
                db.updateScore(currentUserId, score);
            }
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
        StackPane gameOverRoot = new StackPane();
        gameOverRoot.setPrefSize(boardWidth, boardHeight);
        
        VBox gameOverBox = new VBox(15);
        gameOverBox.setAlignment(Pos.CENTER);
        gameOverBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 20; -fx-background-radius: 10;");
        gameOverBox.setMaxWidth(300);
        gameOverBox.setMaxHeight(250);
        
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        gameOverLabel.setTextFill(Color.WHITE);
        
        Label scoreLabel = new Label("Điểm: " + finalScore);
        scoreLabel.setFont(Font.font("Arial", 20));
        scoreLabel.setTextFill(Color.WHITE);
        
        Label highScoreLabel = new Label("Điểm cao nhất: " + highScore);
        highScoreLabel.setFont(Font.font("Arial", 20));
        highScoreLabel.setTextFill(Color.WHITE);
        
        Button replayButton = createStyledButton("Chơi lại");
        replayButton.setOnAction(e -> startGame());
        
        Button menuButton = createStyledButton("Quay lại Menu");
        menuButton.setOnAction(e -> primaryStage.setScene(menuScene));
        
        gameOverBox.getChildren().addAll(gameOverLabel, scoreLabel, highScoreLabel, replayButton, menuButton);
        gameOverRoot.getChildren().add(gameOverBox);
        
        Pane gamePane = (Pane) gameScene.getRoot();
        gamePane.getChildren().add(gameOverRoot);
    }

    public void show() {
        primaryStage.setTitle("Flappy Bird");
        primaryStage.setResizable(false);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}