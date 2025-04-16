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
    //// Phương thức gửi đến dữ liệu đến database
     private boolean find() {
        return DatabaseHelper.testConnection();
    }
    public boolean sendData(){
        // Ktra nếu kết nối csdl được thì gửi 
        if (find()){
           boolean success = DatabaseHelper.sendData(userName, password, phonenumber, email, fullname, nickname);
           if(success){
               System.out.print("Ok");
               return true;
           }else {
               System.out.print("NOT OK");
               return false;
           }
        }
        //giả sử gửi được dữ liệu
        return false;
    }
   
    //// Template
    public boolean checkAllData(){
        
        if(checkUsername()&&checkPassword()&&checkPhonenumber()&&checkFullname()
                &&checkEmail()&&checkNickname())
            
        {
            System.out.print("OK");
            
            return true;
        }
        
        else return false;
     }
    // Mục tiêu: tìm xem có username trùng không ?
    // -> thêm 
    public static void main(String[] args){
        
        String usernamE = "NhungNT";
        String  passworD = "Truong123";
        String fullnamE = "Ngu Thượng Nhung";
        String phonenumbeR = "0362361299";
        String nicknamE = "Ma Kiếm";
        String emaiL = "Truong@gmai.com";
        RegistorController a = new RegistorController(usernamE,passworD,phonenumbeR,emaiL,fullnamE,nicknamE);
        boolean k = a.checkAllData();
        boolean x = a.checkNickname();
        System.out.print(x);
    }
}
