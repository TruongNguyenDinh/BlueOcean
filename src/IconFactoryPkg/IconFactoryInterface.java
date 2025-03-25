/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IconFactoryPkg;

import javafx.scene.shape.SVGPath;

/**
 *
 * @author truon
 */
public interface IconFactoryInterface {
    public SVGPath createIcon(int x,int y,double sx, double sy,String path);
}
