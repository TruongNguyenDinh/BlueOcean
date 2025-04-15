package game.obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;

public class ModelBoom {

    private final DoubleProperty size = new SimpleDoubleProperty();
    private final FloatProperty angle = new SimpleFloatProperty();

    public ModelBoom(double size, float angle) {
        this.size.set(size);
        this.angle.set(angle);
    }

    public ModelBoom() {
    }

    public double getSize() {
        return size.get();
    }

    public void setSize(double size) {
        this.size.set(size);
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public float getAngle() {
        return angle.get();
    }

    public void setAngle(float angle) {
        this.angle.set(angle);
    }

    public FloatProperty angleProperty() {
        return angle;
    }
}
