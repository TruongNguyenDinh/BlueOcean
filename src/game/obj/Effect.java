package game.obj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Effect {

    private final double x;
    private final double y;
    private final double maxDistance;
    private final int maxSize;
    private final Color color;
    private final int totalEffect;
    private final float speed;
    private double currentDistance;
    private ModelBoom[] booms;
    private double alpha = 1.0;

    public Effect(double x, double y, int totalEffect, int maxSize, double maxDistance, float speed, Color color) {
        this.x = x;
        this.y = y;
        this.totalEffect = totalEffect;
        this.maxSize = maxSize;
        this.maxDistance = maxDistance;
        this.speed = speed;
        this.color = color;
        createRandom();
    }

    private void createRandom() {
        booms = new ModelBoom[totalEffect];
        float per = 360f / totalEffect;
        Random ran = new Random();
        for (int i = 1; i <= totalEffect; i++) {
            int r = ran.nextInt((int) per) + 1;
            int boomSize = ran.nextInt(maxSize) + 1;
            float angle = i * per + r;
            booms[i - 1] = new ModelBoom(boomSize, angle);
        }
    }

    public void draw(GraphicsContext gc) {
        gc.save(); // Save the current state of the GraphicsContext
        gc.setFill(color);
        gc.translate(x, y);
        for (ModelBoom b : booms) {
            double bx = Math.cos(Math.toRadians(b.getAngle())) * currentDistance;
            double by = Math.sin(Math.toRadians(b.getAngle())) * currentDistance;
            double boomSize = b.getSize();
            double space = boomSize / 2;
            if (currentDistance >= maxDistance - (maxDistance * 0.7f)) {
                alpha = (maxDistance - currentDistance) / (maxDistance * 0.7f);
            }
            if (alpha > 1) {
                alpha = 1;
            } else if (alpha < 0) {
                alpha = 0;
            }
            gc.setGlobalAlpha(alpha); // Set transparency
            gc.fillRect(bx - space, by - space, boomSize, boomSize); // Draw the rectangle
        }
        gc.restore(); // Restore the previous state of the GraphicsContext
    }

    public void update() {
        currentDistance += speed;
    }

    public boolean check() {
        return currentDistance < maxDistance;
    }
}