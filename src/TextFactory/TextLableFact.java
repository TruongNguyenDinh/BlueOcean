/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextFactory;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author truon
 */
public class TextLableFact implements TextLabelInterface {
    private Font Pacifico70,Roboto;
    @Override
    public Text createText(int x, int y, String content) {
        Pacifico70 = Font.loadFont(getClass().getResourceAsStream("/Font/Pacifico-Regular.ttf"), 70);
        Roboto = Font.loadFont(getClass().getResourceAsStream("/Font/Roboto-Regular.ttf"), 15);
        return createText(x, y, content,Roboto, "ffffff");
    }

    public Text createText(int x, int y, String content, Font font, String color) {
        Text text = new Text(x, y, content);
        text.setFont(font);
        text.setFill(Color.web(color));
        return text;
    }
}
