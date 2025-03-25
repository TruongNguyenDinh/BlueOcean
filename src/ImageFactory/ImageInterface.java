/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageFactory;

import javafx.scene.image.ImageView;

/**
 *
 * @author truon
 */
public interface ImageInterface {
    ImageView createImg(int x, int y,double opacity,int fw,int fh, double scaleX, double scaleY,String path);
}
