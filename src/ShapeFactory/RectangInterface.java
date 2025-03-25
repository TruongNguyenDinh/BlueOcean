/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShapeFactory;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author truon
 */
public interface RectangInterface {
    Rectangle createRectangle(int w,int h,String color, int arcw,int arch,int x, int y, double opacity);
}
