package Game2;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class FlappyBirdGame extends Canvas {
    private final int boardWidth;
    private final int boardHeight;
    
    // Images
    private Image backgroundImg;
    private Image birdImg;
    private Image topPipeImg;
    private Image bottomPipeImg;
    
    // Bird properties
    private final int birdX;
    private final int birdY;
    private final int birdWidth = 34;
    private final int birdHeight = 24;
    
    // Game objects
    private Bird bird;
    private List<Pipe> pipes;
    
    // Game physics
    private final int velocityX = -2;
    private double velocityY = 0; 
    private final double gravity = 0.2; 
    private final double jumpForce = -5.0;
    
    // Game state
    private boolean gameOver = false;
    private int score = 0;
    private boolean isFirstStart = true;
    
    // Timers
    private AnimationTimer gameLoop;
    private long gameStartTime = 0;
    private long lastPipeTime = 0;
    private final long pipeInterval = 1_500_000_000; // 1.5 giây
    private final long initialDelay = 2_000_000_000; // 2 giây
    
    private final Random random = new Random();
    private final GraphicsContext gc;
    
    // Callback khi game over
    private Consumer<Integer> gameOverCallback;
    
    public FlappyBirdGame(int width, int height) {
        super(width, height);
        this.boardWidth = width;
        this.boardHeight = height;
        this.birdX = boardWidth / 4;
        this.birdY = boardHeight / 2;
        
        this.gc = this.getGraphicsContext2D();
        
        loadImages();
        initializeGame();
    }
    
    public void setOnGameOver(Consumer<Integer> callback) {
        this.gameOverCallback = callback;
    }
    
    private void loadImages() {
        try {
            String resourcePath = "src" + File.separator + "Game2" + File.separator + "resources" + File.separator;
            
            File bgFile = new File(resourcePath + "flappybirdbg.png");
            File birdFile = new File(resourcePath + "flappybird.png"); 
            File topPipeFile = new File(resourcePath + "toppipe.png");
            File bottomPipeFile = new File(resourcePath + "bottompipe.png");
            
            backgroundImg = new Image(bgFile.toURI().toString());
            birdImg = new Image(birdFile.toURI().toString());
            topPipeImg = new Image(topPipeFile.toURI().toString());
            bottomPipeImg = new Image(bottomPipeFile.toURI().toString());
            
            System.out.println("Images loaded successfully");
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            createPlaceholderImages();
        }
    }
    
    private void createPlaceholderImages() {
        int width = 100;
        int height = 100;
        
        backgroundImg = createColorImage(width, height, Color.SKYBLUE);
        birdImg = createColorImage(birdWidth, birdHeight, Color.YELLOW);
        topPipeImg = createColorImage(64, 512, Color.GREEN);
        bottomPipeImg = createColorImage(64, 512, Color.GREEN);
        
        System.out.println("Created placeholder images");
    }
    
    private Image createColorImage(int width, int height, Color color) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, width, height);
        return canvas.snapshot(null, null);
    }
    
    private void initializeGame() {
        bird = new Bird(birdImg, birdX, birdY, birdWidth, birdHeight);
        pipes = new ArrayList<>();
        
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameStartTime == 0) {
                    gameStartTime = now;
                    lastPipeTime = now;
                }
                
                if (now - gameStartTime > initialDelay && now - lastPipeTime >= pipeInterval) {
                    placePipes();
                    lastPipeTime = now;
                }
                
                update();
                render();
                
                if (gameOver) {
                    this.stop();
                    if (gameOverCallback != null) {
                        gameOverCallback.accept(score);
                    }
                }
            }
        };
    }
    
    public void startGame() {
        gameLoop.start();
    }
    
    private void placePipes() {
        int pipeX = boardWidth;
        int pipeWidth = 64;
        int pipeHeight = 512;
        /////////////////////////////////////
        int openingSpace = 150;
        
        int minTopHeight = -350;
        int maxTopHeight = -100;
        int topPipeY = random.nextInt(maxTopHeight - minTopHeight + 1) + minTopHeight;
        
        Pipe topPipe = new Pipe(topPipeImg, pipeX, topPipeY, pipeWidth, pipeHeight, true);
        pipes.add(topPipe);
        
        int bottomPipeY = topPipeY + pipeHeight + openingSpace;
        Pipe bottomPipe = new Pipe(bottomPipeImg, pipeX, bottomPipeY, pipeWidth, pipeHeight, false);
        pipes.add(bottomPipe);
    }
    public static int time() {
        long millis = System.currentTimeMillis();
        return (int) (millis / 5000); // Mỗi 5 giây tăng 1 đơn vị
    }
    private void update() {
        if (gameOver) return;
        
        // Update bird physics
        velocityY += gravity;
        bird.y += velocityY;
        
        // Keep bird from going above the screen
        bird.y = Math.max(bird.y, 0);
        
        // Update pipes and check collisions
        Iterator<Pipe> iterator = pipes.iterator();
        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();
            pipe.x += velocityX;
            
            // Remove pipes that are off-screen
            if (pipe.x + pipe.width < 0) {
                iterator.remove();
                continue;
            }
            
            // Check if pipe is passed for scoring (chỉ tính điểm cho ống trên)
            if (pipe.isTopPipe && !pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 1; // Cộng 1 điểm cho mỗi cặp ống
                pipe.passed = true;
                // Đánh dấu ống dưới tương ứng (nếu có) để tránh tính điểm kép
                markPairedPipeAsPassed(pipe);
                System.out.println("Cộng 1 điểm, score = " + score + ", pipe.x = " + pipe.x);
            }
            
            // Check for collision
            if (improvedCollision(bird, pipe)) {
                gameOver = true;
            }
        }
        
        // Check if bird hit the ground
        if (bird.y + bird.height > boardHeight) {
            bird.y = boardHeight - bird.height;
            gameOver = true;
        }
    }
    
    private void markPairedPipeAsPassed(Pipe topPipe) {
        // Tìm ống dưới tương ứng (cùng tọa độ x)
        for (Pipe pipe : pipes) {
            if (!pipe.isTopPipe && pipe.x == topPipe.x && !pipe.passed) {
                pipe.passed = true;
                break;
            }
        }
    }
    
    private void render() {
        gc.clearRect(0, 0, boardWidth, boardHeight);
        
        // Draw background
        gc.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight);
        
        // Draw pipes
        for (Pipe pipe : pipes) {
            gc.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height);
        }
        
        // Draw bird
        gc.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height);
        
        // Draw score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 32));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(String.valueOf(score), 10, 35);
        
        // Hiển thị hướng dẫn khi bắt đầu chơi
        if (isFirstStart && pipes.isEmpty()) {
            gc.setFont(new Font("Arial", 22));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Nhấn SPACE để nhảy", boardWidth/2, boardHeight/2);
            gc.fillText("Tránh các ống!", boardWidth/2, boardHeight/2 + 30);
        }
    }
    
    private boolean improvedCollision(Bird bird, Pipe pipe) {
        int hitboxMargin = 5;
        int birdHitboxX = bird.x + hitboxMargin;
        int birdHitboxY = bird.y + hitboxMargin;
        int birdHitboxWidth = bird.width - 2 * hitboxMargin;
        int birdHitboxHeight = bird.height - 2 * hitboxMargin;
        
        return birdHitboxX < pipe.x + pipe.width &&
               birdHitboxX + birdHitboxWidth > pipe.x &&
               birdHitboxY < pipe.y + pipe.height &&
               birdHitboxY + birdHitboxHeight > pipe.y;
    }
    
    public void handleKeyPress(KeyEvent e) {
        if (e.getCode() == KeyCode.SPACE) {
            velocityY = jumpForce;
            isFirstStart = false;
        }
    }
    
    private static class Bird {
        Image img;
        int x;
        int y;
        int width;
        int height;
        
        Bird(Image img, int x, int y, int width, int height) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
    
    private static class Pipe {
        Image img;
        int x;
        int y;
        int width;
        int height;
        boolean passed = false;
        boolean isTopPipe;
        
        Pipe(Image img, int x, int y, int width, int height, boolean isTopPipe) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.isTopPipe = isTopPipe;
        }
    }
}