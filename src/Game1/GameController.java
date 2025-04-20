package Game1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;

public class GameController {
    private final Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    private final Text statusText = new Text("TURN: X");
    private final String mode;

    public GameController(Stage stage, String mode) {
        this.mode = mode;

        StackPane root = new StackPane();

        // Background blue
        URL imageUrl = getClass().getResource("/Game1/hinhnen.png");
        if (imageUrl != null) {
            ImageView imageView = new ImageView(new Image(imageUrl.toExternalForm()));
            imageView.setFitWidth(400);
            imageView.setFitHeight(500);
            imageView.setPreserveRatio(false);
            imageView.setEffect(new GaussianBlur(20));
            root.getChildren().add(imageView);
        } else {
            System.out.println("Background image not found");
        }

        // Overlay
        Rectangle overlay = new Rectangle(400, 500, Color.rgb(0, 0, 0, 0.4));
        root.getChildren().add(overlay);

        // Neon font
        Font font;
        try {
            font = Font.loadFont(getClass().getResourceAsStream("/Game1/fonts/Boldonse-Regular.ttf"), 15);
            if (font == null) throw new Exception("Font is null");
        } catch (Exception e) {
            System.out.println("Font error, using default: " + e.getMessage());
            font = Font.font("Roboto", 10);
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button("");
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size: 24px; -fx-background-color: rgba(255,255,255,0.1); -fx-border-color: cyan; -fx-border-radius: 5;");
                button.setTextFill(Color.WHITE);
                button.setFont(font);
                DropShadow glow = new DropShadow(10, Color.CYAN);
                button.setEffect(glow);

                final int r = row, c = col;
                button.setOnAction(e -> handleMove(r, c));
                buttons[row][col] = button;
                buttons[row][col].setFont(Font.font("Roboto",20));
                grid.add(button, col, row);
            }
        }

        // Neon status text
        statusText.setFont(font);
        statusText.setFill(Color.WHITE);
        statusText.setEffect(new DropShadow(10, Color.LIME));

        // Control buttons
        Button retry = createNeonButton("REPLAY", "#00ffff", font);
        Button menu = createNeonButton("BACK TO MENU", "#ff4d4d", font);
        retry.setOnAction(e -> new GameController(stage, mode));
        menu.setOnAction(e -> new MenuScene(stage));

        HBox controls = new HBox(20, retry, menu);
        controls.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20, grid, statusText, controls);
        vbox.setAlignment(Pos.CENTER);

        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    private Button createNeonButton(String text, String glowColor, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 15; -fx-background-radius: 15; -fx-padding: 10 20;");
        DropShadow neonGlow = new DropShadow();
        neonGlow.setColor(Color.web(glowColor));
        neonGlow.setRadius(20);
        neonGlow.setSpread(0.6);
        button.setEffect(neonGlow);

        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-border-color: white; -fx-border-radius: 15;");
            button.setScaleX(1.05);
            button.setScaleY(1.05);
        });

        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 15;");
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });

        return button;
    }

    // Game logic handlers
    private void handleMove(int row, int col) {
        if (!buttons[row][col].getText().isEmpty()) return;
        buttons[row][col].setText(playerXTurn ? "X" : "O");
        buttons[row][col].setTextFill(Color.WHITE);
        DropShadow glow = new DropShadow(20, playerXTurn ? Color.web("#00ffff") : Color.web("#ff66cc"));
        glow.setSpread(0.7);
        buttons[row][col].setEffect(glow);
        if (checkWin()) {
            statusText.setText("PLAYER " + (playerXTurn ? "X" : "O") + " WINS!");
            disableAllButtons();
            return;
        }

        if (isFull()) {
            statusText.setText("DRAW!");
            disableAllButtons();
            return;
        }

        playerXTurn = !playerXTurn;
        statusText.setText("TURN: " + (playerXTurn ? "X" : "O"));

        if (!playerXTurn && !mode.equals("PVP")) {
            int[] move = getBotMove();
            if (move != null) {
                handleMove(move[0], move[1]);
            }
        }
    }

    private int[] getBotMove() {
        return switch (mode) {
            case "EASY" -> getRandomMove();
            case "HARD" -> getBestMove();
            default -> null;
        };
    }

    private int[] getRandomMove() {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!buttons[row][col].getText().isEmpty());
        return new int[]{row, col};
    }

    private int[] getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] move = null;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (buttons[r][c].getText().isEmpty()) {
                    buttons[r][c].setText("O");
                    int score = minimax(0, false);
                    buttons[r][c].setText("");
                    if (score > bestScore) {
                        bestScore = score;
                        move = new int[]{r, c};
                    }
                }
            }
        }
        return move;
    }

    private int minimax(int depth, boolean isMaximizing) {
        if (checkWin()) return isMaximizing ? -1 : 1;
        if (isFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (buttons[r][c].getText().isEmpty()) {
                    buttons[r][c].setText(isMaximizing ? "O" : "X");
                    int score = minimax(depth + 1, !isMaximizing);
                    buttons[r][c].setText("");
                    bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }

    private boolean isFull() {
        for (Button[] row : buttons) {
            for (Button b : row) {
                if (b.getText().isEmpty()) return false;
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (Button[] row : buttons) {
            for (Button b : row) {
                b.setDisable(true);
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty()
                    && buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][1].getText().equals(buttons[i][2].getText())) return true;

            if (!buttons[0][i].getText().isEmpty()
                    && buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[1][i].getText().equals(buttons[2][i].getText())) return true;
        }

        if (!buttons[0][0].getText().isEmpty()
                && buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())) return true;

        return !buttons[0][2].getText().isEmpty()
                && buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText());
    }
} 