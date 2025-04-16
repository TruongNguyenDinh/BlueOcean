package game.component;

import game.obj.Bullet;
import game.obj.Effect;
import game.obj.Player;
import game.obj.Rocket;
import game.obj.sound.Sound;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.util.*;
import java.util.function.Consumer;
import javafx.scene.media.MediaPlayer;

import javax.print.attribute.standard.Media;

public class PanelGame extends Canvas {

    private GraphicsContext gc;
    private final int width;
    private final int height;
    private final Key key = new Key();
    private int shotTime = 0;

    private final Sound sound = new Sound();
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Rocket> rockets = new ArrayList<>();
    private final List<Effect> boomEffects = new ArrayList<>();
    private final Random rand = new Random();

    private AnimationTimer timer;
    private Timer rocketTimer;
    private boolean gameOverShown = false;
    private Consumer<Boolean> onBackToMenu;
    private Player player;
    private int score = 0;
    private Consumer<Integer> onGameOver;

    public PanelGame(int width, int height) {
        super(width, height);
        this.width = width;
        this.height = height;
        this.gc = getGraphicsContext2D();

        initKeyboard();
        initGame();
    }
    
    public void setOnGameOver(Consumer<Integer> callback) {
        this.onGameOver = callback;
    }

    public void setOnBackToMenu(Consumer<Boolean> callback) {
        this.onBackToMenu = callback;
    }

    private void initKeyboard() {
        setFocusTraversable(true);
        setOnKeyPressed(this::handleKeyPressed);
        setOnKeyReleased(this::handleKeyReleased);
    }

    private void handleKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case A -> key.setKey_left(true);
            case D -> key.setKey_right(true);
            case W -> key.setKey_w(true);
            case J -> key.setKey_j(true);
            case K -> key.setKey_k(true);
        }
    }

    private void handleKeyReleased(KeyEvent e) {
        switch (e.getCode()) {
            case A -> key.setKey_left(false);
            case D -> key.setKey_right(false);
            case W -> key.setKey_w(false);
            case J -> key.setKey_j(false);
            case K -> key.setKey_k(false);
        }
    }

    private void initGame() {
        gameOverShown = false;
        score = 0;
        bullets.clear();
        rockets.clear();
        boomEffects.clear();
    
        player = new Player();
        player.changeLocation(width / 2, height / 2);
        player.setAlive(true);
        player.reset();
    
        requestFocus();
    
        startRocketSpawner();
        startGameLoop();
        
    }
    private void stopGameLoop() {
        if (timer != null) timer.stop();
        if (rocketTimer != null) rocketTimer.cancel();
        
    }
    private void startRocketSpawner() {
        if (rocketTimer != null) rocketTimer.cancel();

        rocketTimer = new Timer(true);
        rocketTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(PanelGame.this::addRockets);
            }
        }, 0, 2000); // Tốc độ xuất hiện rocket cố định (1 giây)
    }

    private void startGameLoop() {
        if (timer != null) timer.stop();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                drawBackground();
                updateGame();
                drawGame();
            }
        };
        timer.start();
    }

    

    private void addRockets() {
        if (!player.isAlive()) return;

        rockets.add(new Rocket(0, randY(), 0, 3));         // Left
        rockets.add(new Rocket(width, randY(), 180, 3));   // Right
        rockets.add(new Rocket(randX(), 0, 90, 3));        // Top
        rockets.add(new Rocket(randX(), height, 270, 3));  // Bottom
    }

    private int randX() {
        return rand.nextInt(width - 50) + 25;
    }

    private int randY() {
        return rand.nextInt(height - 50) + 25;
    }

    private void updateGame() {
        if (!player.isAlive()) {
            stopGameLoop();
            if (!gameOverShown) {
                gameOverShown = true;
                showGameOverButtons();
                
               
            }
        
        }
    
        float angle = player.getAngle();
        if (key.isKey_left()) angle -= 2f;
        if (key.isKey_right()) angle += 2f;

        if (key.isKey_w()) player.speedUp();
        else player.speedDown();

        if (key.isKey_j() || key.isKey_k()) {
            if (shotTime == 0) {
                double angleRad = Math.toRadians(player.getAngle());
                double bx = player.getX() + Player.PLAYER_SIZE / 2 + Math.cos(angleRad) * (Player.PLAYER_SIZE / 2);
                double by = player.getY() + Player.PLAYER_SIZE / 2 + Math.sin(angleRad) * (Player.PLAYER_SIZE / 2);
                int damage = key.isKey_j() ? 5 : 20;
                bullets.add(0, new Bullet(bx, by, player.getAngle(), damage, 3f));
                sound.soundShoot();
            }
            shotTime++;
            if (shotTime >= 15) shotTime = 0;
        } else {
            shotTime = 0;
        }

        player.changeAngle(angle);
        player.update();

        rockets.removeIf(r -> {
            r.update();
            if (!r.check(width, height)) return true;
            if (r.getShape().intersects(player.getShape().getBoundsInLocal())) {
                boolean alive = player.updateHP(20);
                sound.soundHit();
                boomEffects.add(new Effect(player.getX(), player.getY(), 20, 20, 100, 0.5f, Color.ORANGE));
                if (!alive) {
                    player.setAlive(false);
                    sound.soundDestroy();
                }
                return true;
            }
            return false;
        });

        bullets.removeIf(b -> {
            b.update();
            boolean hit = rockets.removeIf(r -> {
                if (r.getShape().intersects(b.getX(), b.getY(), b.getSize(), b.getSize())) {
                    if (key.isKey_k()) {
                        boomEffects.add(new Effect(r.getX(), r.getY(), 10, 10, 100, 0.5f, Color.RED));
                        sound.soundDestroy();
                        score++;
                        return true;
                    } else if (key.isKey_j()) {
                        if (!r.reduceHP(10)) {
                            boomEffects.add(new Effect(r.getX(), r.getY(), 10, 10, 100, 0.5f, Color.RED));
                            sound.soundDestroy();
                            score++;
                            return true;
                        } else {
                            sound.soundHit();
                        }
                    }
                }
                return false;
            });
            return hit || !b.check(width, height);
        });

        boomEffects.removeIf(e -> {
            e.update();
            return !e.check();
        });
    }

    private void drawBackground() {
        Image backgroundImage = new Image(getClass().getResource("/game/image/gameview.png").toExternalForm());
        gc.drawImage(backgroundImage, 0, 0, width, height);
    }

    private void drawGame() {
        if (player.isAlive()) player.draw(gc);
        bullets.forEach(b -> b.draw(gc));
        rockets.forEach(r -> r.draw(gc));
        boomEffects.forEach(e -> e.draw(gc));

        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font("Arial Black", 36));
        gc.setEffect(new javafx.scene.effect.DropShadow(10, Color.YELLOW));
        gc.fillText("Score: " + score, 50, 70);
        gc.setEffect(null);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
            "-fx-background-color: linear-gradient(#ff5400, #be1d00);"
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

        button.setPrefWidth(250);
        return button;
    }

    private void showGameOverButtons() {
        StackPane parent = (StackPane) getParent();
        if (parent == null) return;
       
        
        VBox buttons = new VBox(15);
        buttons.setStyle("-fx-alignment: center;");

        Button replayBtn = createStyledButton("🔁 Replay");
        Button backBtn = createStyledButton("🏠 Back to Menu");

        replayBtn.setOnAction(e -> {
            parent.getChildren().remove(buttons);
            initGame();
        });

        backBtn.setOnAction(e -> {
            if (onGameOver != null) onGameOver.accept(score); // lưu điểm
            parent.getChildren().remove(buttons);
            if (onBackToMenu != null) onBackToMenu.accept(true); // quay về menu
        });
        

        buttons.getChildren().addAll(replayBtn, backBtn);
        parent.getChildren().add(buttons);
    }
}