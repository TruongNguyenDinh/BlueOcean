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
public class TextFieldFact implements TextFieldInterface {
    @Override
    public TextField createFieldData(int x,int y,String content,String path ){
        TextField textField = new TextField();
        textField.setPromptText(content);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.getStyleClass().add(path);
        return textField;
    }
    @Override
    public PasswordField createFieldPassword(int x,int y,String content,String path){
        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(x);
        passwordField.setLayoutY(y);
        passwordField.setPromptText(content);
        passwordField.getStyleClass().add(path);
        return passwordField;
    }
}
