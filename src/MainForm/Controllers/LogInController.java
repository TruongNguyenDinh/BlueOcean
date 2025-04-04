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
    private static String fullname;
    public static String username,password;
    private Stage loginStage;
//    private MainScene mainScene = new MainScene();
    public LogInController(){}
    public  LogInController(Stage loginStage,String username,String password){
        LogInController.username = username;
        LogInController.password = password;
        this.loginStage = loginStage;
    }
    public boolean checkUsername(){
        return checkInputData.isValidUserName(LogInController.username);
    }
    public boolean checkPassword(){
        return checkInputData.isValidPassword(LogInController.password);
    }
    private boolean find() {
        return DatabaseHelper.isUserValid(username, password);
    }
    public String getFullname(){return this.fullname;}
    public void getAccountId() {
       
        if (checkUsername() && checkPassword()) {
            if (find()) {
                LogInController.fullname =DatabaseHelper.getFullname(LogInController.username,LogInController.password);
//                fullname = DatabaseHelper.fullname();
                System.out.print(fullname);
                openChatApp();

            }
            //Cho vòa trong đây
            else {}
        }
    }
    
        public void openChatApp() {
            Platform.runLater(() -> {
                loginStage.close(); // Đóng cửa sổ đăng nhập
                MainScene.openMainStage(fullname); // Mở cửa sổ chính

            });
        }
    public static void main (String[] args){
        LogInController.username = "Truong";
        LogInController.password = "Truong";
        
        String a = DatabaseHelper.getFullname(LogInController.username, LogInController.password);
        System.out.print(a);
        System.out.print(LogInController.fullname);
    }

}
