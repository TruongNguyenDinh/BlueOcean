/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author truon
 */
public class ImgFactory implements ImageInterface{
    @Override
    public ImageView createImg(int x, int y,double opacity,int fw,int fh, double scaleX, double scaleY,String path){
        ImageView img = new ImageView(new Image(path));
        img.setLayoutX(x);
        img.setLayoutY(y);
        img.setOpacity(opacity);
        img.setScaleX(scaleX);
        img.setScaleY(scaleY);
        img.setOpacity(opacity);
        img.setFitHeight(fh);
        img.setFitWidth(fw);
        return img;
    }
    public static Image getIcon(){
        Image icon =  new Image("Image/BlueOcean2.png");
        return icon;
    }
}
