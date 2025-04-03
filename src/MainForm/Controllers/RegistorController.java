/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainForm.Controllers;

import MainForm.Utils.checkInputData;
import javafx.scene.Node;

/**
 *
 * @author truon
 */
public class RegistorController {
    private String userName,password,phonenumber,email,tagname,fullname,nickname;
    public RegistorController(){}
    public RegistorController(String username,String password,String phonenumber,String email,String tagname,String fullname,String nickname){
        this.userName = username;
        this.password = password;
        this.tagname = tagname;
        this.phonenumber = phonenumber;
        this.fullname = fullname;
        this.email = email;
        this.nickname = nickname;
    }
    public static void Visiable(boolean isVisible,Node...nodes){
        for(Node node:nodes){
            if(node != null){
                node.setVisible(isVisible);
            }
        }
    }
    private boolean checkUsername(){
        return checkInputData.isValidUserName(this.userName);
    }
    private boolean checkPassword(){
        return checkInputData.isValidPassword(this.password);
    }
    private boolean checkPhonenumber(){
        return checkInputData.isValidNumberPhone(this.phonenumber);
    }
    private boolean checkFullname(){
        return checkInputData.isValidFullName(this.fullname);
    }
    private boolean checkEmail(){
        return checkInputData.isValidEmail(this.email);
    }
    private boolean checkTagname(){
        return checkInputData.isValidTagName(this.tagname);
    }
    private boolean checkNickname(){
        return checkInputData.isValidNameInGame(this.nickname);
    }    
    ///
    //// Phương thức gửi đến dữ liệu đến database
    private boolean sendData(){
        
        //giả sử gửi được dữ liệu
        return false;
    }
    //// Template
    public boolean checkAllData(){
        if(checkUsername()&&checkPassword()&&checkPhonenumber()&&checkFullname()
                &&checkEmail()&&checkTagname()&&checkNickname())
        {
            if(sendData())return true;
            else return false;
        }
        
        else return false;
     }
       
}
