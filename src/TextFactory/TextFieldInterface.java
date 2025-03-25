/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TextFactory;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author truon
 */
public interface TextFieldInterface {
    public TextField createFieldData(int x,int y,String content,String path );
    public PasswordField createFieldPassword(int x,int y,String content,String path);
}
