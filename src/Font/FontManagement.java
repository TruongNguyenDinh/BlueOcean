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
    public static Font Roboto (double size){
        return Font.loadFont(FontManagement.class.getResourceAsStream("/Font/Roboto-Medium.ttf"),size);
    }
    public static Font Pacifico(double size){
        return Font.loadFont(FontManagement.class.getResourceAsStream("/Font/Pacifico-Regular.ttf"), size);
    }
    public Font WinkySans(int size){
        return Font.loadFont(getClass().getResourceAsStream("/Font/WinkySans-VariableFont_wght.ttf"), size);
    }
    public static Font CorGara (double size){
        return Font.loadFont(FontManagement.class.getResourceAsStream("/Font/Cormorant_Garamond/CormorantGaramond-Medium.ttf"),size);
    }
    public static Font GreVib (double size){
        return Font.loadFont(FontManagement.class.getResourceAsStream("/Font/Great_Vibes/GreatVibes-Regular.ttf"),size);
    }
}
