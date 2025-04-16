package game.obj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rocket extends HpRender {

    public static final double ROCKET_SIZE = 50;
    private double x;
    private double y;
    private double speed; // Tốc độ di chuyển
    private final double MAX_SPEED = 0.5; // Tốc độ tối đa
    private float angle; // Góc di chuyển
    private final Image image;
    private final HP hp; // Hệ thống máu của rocket

    /**
     * Constructor chính để khởi tạo Rocket.
     * 
     * @param x     Vị trí x ban đầu.
     * @param y     Vị trí y ban đầu.
     * @param angle Góc di chuyển.
     * @param speed Tốc độ di chuyển.
     */
    public Rocket(double x, double y, float angle, double speed) {
        super(new HP(20, 20)); // Khởi tạo HP với giá trị tối đa và hiện tại là 20
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        this.hp = new HP(20, 20); // Rocket có 20 HP
        this.image = new Image(getClass().getResource("/game/image/rocket.png").toExternalForm());
    }

    /**
     * Cập nhật vị trí của Rocket dựa trên góc và tốc độ.
     */
    public void update() {
        x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;
    }

    /**
     * Thay đổi vị trí của Rocket.
     * 
     * @param x Vị trí x mới.
     * @param y Vị trí y mới.
     */
    public void changeLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Thay đổi góc di chuyển của Rocket.
     * 
     * @param angle Góc mới.
     */
    public void changeAngle(float angle) {
        if (angle < 0) {
            angle = 359;
        } else if (angle > 359) {
            angle = 0;
        }
        this.angle = angle;
    }

    /**
     * Vẽ Rocket và thanh máu của nó.
     * 
     * @param gc GraphicsContext để vẽ.
     */
    public void draw(GraphicsContext gc) {
        gc.save(); // Lưu trạng thái hiện tại của GraphicsContext
        gc.translate(x, y);
        gc.rotate(angle + 45); // Điều chỉnh góc để vẽ đúng hướng
        gc.drawImage(image, -ROCKET_SIZE / 2, -ROCKET_SIZE / 2, ROCKET_SIZE, ROCKET_SIZE);
        gc.restore(); // Khôi phục trạng thái cũ

        // Vẽ thanh máu
        drawHpBar(gc);
    }

    /**
     * Vẽ thanh máu phía trên Rocket.
     * 
     * @param gc GraphicsContext để vẽ.
     */
    private void drawHpBar(GraphicsContext gc) {
        double hpBarWidth = ROCKET_SIZE;
        double hpBarHeight = 5;
        double hpX = x - ROCKET_SIZE / 2;
        double hpY = y - ROCKET_SIZE / 2 - 10;

        // Vẽ nền thanh máu
        gc.setFill(Color.rgb(50, 50, 50)); // Màu nền (HP mất)
        gc.fillRect(hpX, hpY, hpBarWidth, hpBarHeight);

        // Vẽ phần HP còn lại
        gc.setFill(Color.rgb(0, 200, 0)); // Màu HP còn lại (xanh lá)
        double hpSize = (hp.getCurrentHp() / hp.getMAX_HP()) * hpBarWidth;
        gc.fillRect(hpX, hpY, hpSize, hpBarHeight);

        // Vẽ viền cho thanh máu
        gc.setStroke(Color.BLACK);
        gc.strokeRect(hpX, hpY, hpBarWidth, hpBarHeight);
    }

    /**
     * Lấy hình dạng của Rocket để kiểm tra va chạm.
     * 
     * @return Hình chữ nhật đại diện cho Rocket.
     */
    public Rectangle getShape() {
        return new Rectangle(x - ROCKET_SIZE / 2, y - ROCKET_SIZE / 2, ROCKET_SIZE, ROCKET_SIZE);
    }

    /**
     * Kiểm tra nếu Rocket ra khỏi màn hình.
     * 
     * @param width  Chiều rộng màn hình.
     * @param height Chiều cao màn hình.
     * @return true nếu Rocket vẫn trong màn hình, false nếu ra ngoài.
     */
    public boolean check(int width, int height) {
        return x + ROCKET_SIZE / 2 >= 0 && x - ROCKET_SIZE / 2 <= width &&
               y + ROCKET_SIZE / 2 >= 0 && y - ROCKET_SIZE / 2 <= height;
    }

    /**
     * Lấy giá trị x của Rocket.
     * 
     * @return Giá trị x.
     */
    public double getX() {
        return x;
    }

    /**
     * Lấy giá trị y của Rocket.
     * 
     * @return Giá trị y.
     */
    public double getY() {
        return y;
    }

    /**
     * Lấy góc di chuyển của Rocket.
     * 
     * @return Góc di chuyển.
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Lấy hệ thống máu của Rocket.
     * 
     * @return Đối tượng HP.
     */
    public HP getHp() {
        return hp;
    }

    /**
     * Tăng tốc độ di chuyển của Rocket.
     * 
     * @param increment Giá trị tăng thêm.
     */
    public void increaseSpeed(double increment) {
        speed += increment;
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
    }

    /**
     * Giảm tốc độ di chuyển của Rocket.
     * 
     * @param decrement Giá trị giảm.
     */
    public void decreaseSpeed(double decrement) {
        speed -= decrement;
        if (speed < 0) {
            speed = 0;
        }
    }

    /**
     * Giảm máu của Rocket.
     * 
     * @param damage Số máu bị giảm.
     * @return true nếu Rocket vẫn còn sống, false nếu máu <= 0.
     */
    public boolean reduceHP(int damage) {
        return hp.reduceHp(damage);
    }
}