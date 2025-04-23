/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainForm.Controllers;

import MainForm.Utils.DatabaseHelper;
import MainForm.Utils.checkInputData;
import javafx.scene.Node;

/**
 *
 * @author truon
 */
public class RegistorController {
    private static String userName,password,phonenumber,email,fullname,nickname;
    public RegistorController(){}
    public RegistorController(String username,String password,String phonenumber,String email,String fullname,String nickname){
        RegistorController.userName = username;
        RegistorController.password = password;
        RegistorController.phonenumber = phonenumber;
        RegistorController.fullname = fullname;
        RegistorController.email = email;
        RegistorController.nickname = nickname;
    }
    
    public static void Visiable(boolean isVisible,Node...nodes){
        for(Node node:nodes){
            if(node != null){
                node.setVisible(isVisible);
            }
        }
    }
    
    private boolean checkUsername(){
        return checkInputData.isValidUserName(RegistorController.userName);
    }
    private boolean checkPassword(){
        return checkInputData.isValidPassword(RegistorController.password);
    }
    private boolean checkPhonenumber(){
        return checkInputData.isValidNumberPhone(RegistorController.phonenumber);
    }
    private boolean checkFullname(){
        return checkInputData.isValidFullName(RegistorController.fullname);
    }
    private boolean checkEmail(){
        return checkInputData.isValidEmail(RegistorController.email);
    }
    private boolean checkNickname(){
        return checkInputData.isValidNameInGame(RegistorController.nickname);
    }    
     private boolean find() {
        return DatabaseHelper.testConnection();
    }
    
    public boolean sendData(){
        if (find()){
            return DatabaseHelper.sendData(userName, password, phonenumber, email, fullname, nickname);
        }
        return false;
    }
    //// Templat
    /// @return e
    public boolean checkAllData(){
        return checkUsername()&&checkPassword()&&checkPhonenumber()&&checkFullname()
                &&checkEmail()&&checkNickname();
    }
}
