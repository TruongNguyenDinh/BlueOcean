package game.component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Key {

    private final BooleanProperty key_enter = new SimpleBooleanProperty(false);
    private final BooleanProperty key_right = new SimpleBooleanProperty(false);
    private final BooleanProperty key_left = new SimpleBooleanProperty(false);
    private final BooleanProperty key_w = new SimpleBooleanProperty(false);
    private final BooleanProperty key_j = new SimpleBooleanProperty(false);
    private final BooleanProperty key_k = new SimpleBooleanProperty(false);

    public boolean isKey_enter() {
        return key_enter.get();
    }

    public void setKey_enter(boolean key_enter) {
        this.key_enter.set(key_enter);
    }

    public BooleanProperty key_enterProperty() {
        return key_enter;
    }

    public boolean isKey_right() {
        return key_right.get();
    }

    public void setKey_right(boolean key_right) {
        this.key_right.set(key_right);
    }

    public BooleanProperty key_rightProperty() {
        return key_right;
    }

    public boolean isKey_left() {
        return key_left.get();
    }

    public void setKey_left(boolean key_left) {
        this.key_left.set(key_left);
    }

    public BooleanProperty key_leftProperty() {
        return key_left;
    }

    public boolean isKey_w() {
        return key_w.get();
    }

    public void setKey_w(boolean key_w) {
        this.key_w.set(key_w);
    }

    public BooleanProperty key_spaceProperty() {
        return key_w;
    }

    public boolean isKey_j() {
        return key_j.get();
    }

    public void setKey_j(boolean key_j) {
        this.key_j.set(key_j);
    }

    public BooleanProperty key_jProperty() {
        return key_j;
    }

    public boolean isKey_k() {
        return key_k.get();
    }

    public void setKey_k(boolean key_k) {
        this.key_k.set(key_k);
    }

    public BooleanProperty key_kProperty() {
        return key_k;
    }
}