package game.obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class HP {

    private final DoubleProperty MAX_HP = new SimpleDoubleProperty(); // Giá trị HP tối đa
    private final DoubleProperty currentHp = new SimpleDoubleProperty(); // Giá trị HP hiện tại

    /**
     * Constructor với giá trị HP tối đa và HP hiện tại.
     * @param MAX_HP Giá trị HP tối đa.
     * @param currentHp Giá trị HP hiện tại.
     */
    public HP(double MAX_HP, double currentHp) {
        this.MAX_HP.set(MAX_HP);
        this.currentHp.set(Math.min(currentHp, MAX_HP)); // Đảm bảo currentHp không vượt quá MAX_HP
    }

    /**
     * Constructor mặc định với giá trị HP là 0.
     */
    public HP() {
        this(0, 0); // Default constructor with initial values
    }

    /**
     * Lấy giá trị HP tối đa.
     * @return Giá trị HP tối đa.
     */
    public double getMAX_HP() {
        return MAX_HP.get();
    }

    /**
     * Đặt giá trị HP tối đa.
     * @param MAX_HP Giá trị HP tối đa mới.
     */
    public void setMAX_HP(double MAX_HP) {
        this.MAX_HP.set(MAX_HP);
        if (currentHp.get() > MAX_HP) {
            currentHp.set(MAX_HP); // Đảm bảo currentHp không vượt quá MAX_HP khi MAX_HP thay đổi
        }
    }

    /**
     * Lấy thuộc tính HP tối đa (dùng cho binding).
     * @return Thuộc tính HP tối đa.
     */
    public DoubleProperty MAX_HPProperty() {
        return MAX_HP;
    }

    /**
     * Lấy giá trị HP hiện tại.
     * @return Giá trị HP hiện tại.
     */
    public double getCurrentHp() {
        return currentHp.get();
    }

    /**
     * Đặt giá trị HP hiện tại.
     * @param currentHp Giá trị HP hiện tại mới.
     */
    public void setCurrentHp(double currentHp) {
        if (currentHp < 0) {
            this.currentHp.set(0); // Đảm bảo currentHp không âm
        } else if (currentHp > MAX_HP.get()) {
            this.currentHp.set(MAX_HP.get()); // Đảm bảo currentHp không vượt quá MAX_HP
        } else {
            this.currentHp.set(currentHp);
        }
    }

    /**
     * Lấy thuộc tính HP hiện tại (dùng cho binding).
     * @return Thuộc tính HP hiện tại.
     */
    public DoubleProperty currentHpProperty() {
        return currentHp;
    }

    /**
     * Giảm máu của đối tượng.
     * @param amount Số máu cần giảm.
     * @return `true` nếu đối tượng vẫn còn sống, `false` nếu máu <= 0.
     */
    public boolean reduceHp(double amount) {
        setCurrentHp(getCurrentHp() - amount);
        return getCurrentHp() > 0;
    }
}