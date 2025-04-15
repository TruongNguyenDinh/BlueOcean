package Game1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class GameController {
    private final Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    private final Text statusText = new Text("Lượt: X");
    private final String mode;

    public GameController(Stage stage, String mode) {
        this.mode = mode;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button("");
                button.setPrefSize(100, 100);
                final int r = row, c = col;
                button.setOnAction(e -> handleMove(r, c));
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        HBox controls = new HBox(20);
        controls.setAlignment(Pos.CENTER);
        Button retry = new Button("CHƠI LẠI");
        Button menu = new Button("QUAY LẠI");
        retry.setOnAction(e -> new GameController(stage, mode));
        menu.setOnAction(e -> new MenuScene(stage));
        controls.getChildren().addAll(retry, menu);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(grid, statusText, controls);

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    // Sửa phương thức handleMove() để loại bỏ ranking
    private void handleMove(int row, int col) {
        if (!buttons[row][col].getText().isEmpty()) return;

        buttons[row][col].setText(playerXTurn ? "X" : "O");
        
        if (checkWin()) {
            statusText.setText("Người chơi " + (playerXTurn ? "X" : "O") + " thắng!");
            disableAllButtons();
            return;
        }

        if (isFull()) {
            statusText.setText("Hòa!");
            disableAllButtons();
            return;
        }

        playerXTurn = !playerXTurn;
        statusText.setText("Lượt: " + (playerXTurn ? "X" : "O"));

        if (!playerXTurn && !mode.equals("PVP")) {
            int[] move = getBotMove();
            if (move != null) {
                handleMove(move[0], move[1]);
            }
        }
    }

    private int[] getBotMove() {
        switch (mode) {
            case "EASY" -> {
                return getRandomMove();
            }
            case "HARD" -> {
                return getBestMove();
            }
        }
        return null;
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