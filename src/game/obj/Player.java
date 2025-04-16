package game.obj; 

import javafx.scene.canvas.GraphicsContext; import javafx.scene.image.Image; import javafx.scene.paint.Color; import javafx.scene.shape.Rectangle; 

public class Player extends HpRender { 

public static final double PLAYER_SIZE = 64; 
private double x; 
private double y; 
private final float MAX_SPEED = 2f; 
private float speed = 0f; 
private float angle = 0f; 
private final Rectangle playerShape; 
private final Image image; 
private final Image imageSpeed; 
private boolean speedUp; 
private boolean alive = true; 
 
public Player() { 
    super(new HP(100, 100)); // MAX_HP = 50, currentHp = 50 
    this.image = new Image(getClass().getResource("/game/image/plane.png").toExternalForm()); 
    this.imageSpeed = new Image(getClass().getResource("/game/image/plane_speed.png").toExternalForm()); 
    this.playerShape = new Rectangle(PLAYER_SIZE, PLAYER_SIZE); 
} 
 
public void changeLocation(double x, double y) { 
    this.x = x; 
    this.y = y; 
} 
 
public void update() { 
    x += Math.cos(Math.toRadians(angle)) * speed; 
    y += Math.sin(Math.toRadians(angle)) * speed; 
} 
 
public void changeAngle(float angle) { 
    if (angle < 0) { 
        angle = 359; 
    } else if (angle > 359) { 
        angle = 0; 
    } 
    this.angle = angle; 
} 
 
public void draw(GraphicsContext gc) { 
    gc.save(); // Save the current state of the GraphicsContext 
    gc.translate(x, y); 
    gc.rotate(angle + 45); 
    gc.drawImage(speedUp ? imageSpeed : image, -PLAYER_SIZE / 2, -PLAYER_SIZE / 2, PLAYER_SIZE, PLAYER_SIZE); 
    gc.restore(); // Restore the previous state of the GraphicsContext 
 
    // Render HP bar 
    hpRender(gc, x - PLAYER_SIZE / 2, y - PLAYER_SIZE / 2, PLAYER_SIZE); 
} 
 
public Rectangle getShape() { 
    // Adjust the rectangle to match the player's position and size 
    return new Rectangle(x - PLAYER_SIZE / 2, y - PLAYER_SIZE / 2, PLAYER_SIZE, PLAYER_SIZE); 
} 
 
public double getX() { 
    return x; 
} 
 
public double getY() { 
    return y; 
} 
 
public float getAngle() { 
    return angle; 
} 
 
public void speedUp() { 
    speedUp = true; 
    if (speed > MAX_SPEED) { 
        speed = MAX_SPEED; 
    } else { 
        speed += 0.05f; 
    } 
} 
 
public void speedDown() { 
    speedUp = false; 
    if (speed <= 0) { 
        speed = 0; 
    } else { 
        speed -= 0.01f; 
    } 
} 
 
public boolean isAlive() { 
    return alive; 
} 
 
public void setAlive(boolean alive) { 
    this.alive = alive; 
} 
 
public void reset() { 
    alive = true; 
    resetHP(); 
    angle = 0; 
    speed = 0; 
} 
 
public HP getHp() { 
    return hp; // Return the HP object for the player 
} 
  

} 