/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextFactory;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author truon
 */
public interface TextLabelInterface {
    public Text createText(int x, int y, String content);
    public Text createText(int x, int y, String content,Font font,String color);
}
