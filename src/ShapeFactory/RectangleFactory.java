/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShapeFactory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author truon
 */
public class RectangleFactory implements RectangInterface {
    @Override
    public Rectangle createRectangle(int w,int h,String color, int arcw,int arch,int x, int y, double opacity){
        Rectangle rect = new Rectangle(w,h,Color.web(color));
        rect.setLayoutX(x);
        rect.setLayoutY(y);
        rect.setArcHeight(arch);
        rect.setArcWidth(arcw);
        rect.setOpacity(opacity);
        return rect;
    }
}
