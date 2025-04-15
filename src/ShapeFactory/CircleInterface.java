package ShapeFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javafx.scene.shape.Circle;
/**
 *
 * @author truon
 */
public interface CircleInterface {
    Circle createCircle(int x,int y,int radius, String color,double opacity);
}
