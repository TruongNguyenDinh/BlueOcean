/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Controllers;
import MainForm.Utils.DatabaseHelper;
import MainForm.Utils.checkInputData;
/**
 *
 * @author truon
 */
public class FogottenPasswordController {
    private String userName,phoneNumber;
    public FogottenPasswordController(){}
    
    public FogottenPasswordController(String userName,String phoneNumber){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
       
    }
    public void setUsername(String username){
        this.userName = username;
    }
    public String getUsername(){
        return userName;
    }
    
    public void setPhonenumber(String phone){
        this.phoneNumber = phone;
    }
    public String getPhonenumber(){
        return phoneNumber;
    }
    public String checkYesNo() {
        if (checkInputData.isValidUserName(this.userName) &&
            checkInputData.isValidNumberPhone(this.phoneNumber)){
            return DatabaseHelper.getEmailIfAccountExists(this.userName, this.phoneNumber); 
        }
        else{
            return null;
        } 
    }
    public boolean changePassword(String userName, String phoneNumber, String newPassword){
        return DatabaseHelper.changePassword(userName, phoneNumber, newPassword);
    }
}
