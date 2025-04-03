/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainForm.Controllers;

import javafx.stage.Stage;

/**
 *
 * @author truong
 */
import BlueOceanScene.MainScene;
import MainForm.Utils.DatabaseHelper;
import MainForm.Utils.checkInputData;
import javafx.application.Platform;
public class LogInController {
    private String AccountId;
    public String username,password;
    private Stage loginStage;
    public LogInController(){}
    public  LogInController(Stage loginStage,String username,String password){
        this.username = username;
        this.password = password;
        this.loginStage = loginStage;
    } 
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public boolean checkUsername(){
        return checkInputData.isValidUserName(this.username);
    }
    public boolean checkPassword(){
        return checkInputData.isValidPassword(this.password);
    }
    
    public void getAccountId(){
    if(checkUsername() && checkPassword()){
        if(DatabaseHelper.isUserValid(username, password)) {
            AccountId = username;
            openChatApp();
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    System.out.println("AccountId: " + AccountId);
}

    public void openChatApp() {
    Platform.runLater(() -> {
        loginStage.close(); // Đóng cửa sổ đăng nhập
        MainScene.openMainStage(AccountId); // Mở cửa sổ chính
        
    });
}
}
