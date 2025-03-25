/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Font;

import javafx.scene.text.Font;

/**
 *
 * @author truon
 */
public class FontManagement {
    public FontManagement (){}
    public Font Roboto (int size){
        return Font.loadFont(getClass().getResourceAsStream("/Font/Roboto-Medium.ttf"),size);
    }
    public Font Pacifico(int size){
        return Font.loadFont(getClass().getResourceAsStream("/Font/Pacifico-Regular.ttf"), size);
    }
}
