/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IconFactoryPkg;

import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

/**
 *
 * @author truon
 */
public class IconFact implements IconFactoryInterface{
    @Override
    public SVGPath createIcon(int x,int y,double sx, double sy,String path){
        SVGPath icon = new SVGPath();
        icon.setContent(path);
        icon.setLayoutX(x);
        icon.setLayoutY(y);
        icon.setScaleX(sx);
        icon.setScaleY(sy);
        icon.setFill(Color.WHITE);
        return icon;
    }
}
