package game.obj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Bullet {

    private double x;
    private double y;
    private final Ellipse shape;
    private final Color color = Color.WHITE;
    private final float angle;
    private double size;
    private float speed = 1f;

    public Bullet(double x, double y, float angle, int size, float speed) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.size = size;
        this.speed = speed;
        shape = new Ellipse(size / 2, size / 2);
    }

    public void update() {
        x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;
    }

    public boolean check(int width, int height) {
        return !(x <= -size || y < -size || x > width || y > height);
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, size, size);
    }

    public javafx.scene.shape.Shape getShape() {
        return new Ellipse(x + size / 2, y + size / 2, size / 2, size / 2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public double getCenterX() {
        return x + size / 2;
    }

    public double getCenterY() {
        return y + size / 2;
    }
}
