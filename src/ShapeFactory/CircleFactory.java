/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShapeFactory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author truon
 */
public class CircleFactory implements CircleInterface{
    @Override
    public Circle createCircle(int x,int y,int radius, String color,double opacity){
        Circle circle = new Circle(radius,Color.web(color));
        circle.setLayoutX(x);
        circle.setLayoutY(y);
        circle.setOpacity(opacity);
        return circle;
    }
}
