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
import BlueOceanScene.Main;
import MainForm.Utils.DatabaseHelper;
import MainForm.Utils.checkInputData;
import java.time.LocalDateTime;
import javafx.application.Platform;
import MainForm.Models.User;
public class LogInController {
    private static int id;
    public static String username,fullname,nickname,phone,address,email;
    private static LocalDateTime createdAt;
    private static boolean gender;
    public static String password;
    private Stage loginStage;
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
    public String getFullname(){return LogInController.fullname;}

    public int getAccountId() {
        if (checkUsername() && checkPassword()) {
            if (find()) {
               String res =DatabaseHelper.getFullname(LogInController.username,LogInController.password);
                String[] parts = res.split("-",10);
                LogInController.id = Integer.parseInt(parts[0]);
                LogInController.username = parts[1];
                LogInController.fullname = parts[2] + " " + parts[3]; // Vì fullname chứa dấu `-`
                LogInController.nickname = parts[4];
                LogInController.phone = parts[5];
                LogInController.address = parts[6];
                LogInController.gender = parts[7].equals("1"); // 1: Nam, 0: Nữ (hoặc tùy theo logic của bạn)
                LogInController.email = parts[8];
                LogInController.createdAt = LocalDateTime.parse(parts[9]);
                System.out.print(fullname);
                User user = new User(id, username,password, fullname, nickname, phone, address, gender, email, createdAt);
                openDashboard();
            }
            else {
                return 0;
            }   
        }
        else{
            return 1;
        }
        return 1;
    }
        public void openDashboard() {
            Platform.runLater(() -> {
                Main main = new Main();
                loginStage.close(); // Đóng cửa sổ đăng nhập
                main.openMainStage(id,username,fullname,nickname,phone,address,gender,email,createdAt); // Mở cửa sổ chính
            });
        }
}
