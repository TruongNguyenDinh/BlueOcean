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
    private MainScene mainScene = new MainScene();
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
<<<<<<< HEAD
    // Khóa chính = username
    // SELECT inputdata FROM username
    
    // String = res
    private boolean find(){
        // Giả sử this.username = truong
        // String SQL = "SELECT " + this.username+ "FROM Users";
        //  SELECT truong FROM Users
        /// ResultSet rs = stmt.executeQuery(SQL);
        // {value,value}

        //  Duyệt qua kết quả và hiển thị dữ liệu
        //
        //      String username = rs.getString(); => username truong
        ///         username nhat
        ///     rs: ""
        ///     rs: username
        ///     Kiem tra rs != rong ""
        ///      return tre=ue
        //       you = input("enter") 
        //      enter: sfdsddsdddd
        //      String email = rs.getString("email");
        //      if(!username.equals("") return true; 
        //      System.out.println("Username: " + username + ", Email: " + email);
        //  }

        ///
        ///

        return true;
    }
    public void getAccountId() {
        if (checkUsername() && checkPassword()) {
            if (find()) {
                AccountId = this.username;
                openChatApp();

            }
            //Cho vòa trong đây
            else {}
        }
        System.out.println("AccountId: " + AccountId);
    }
    
    public void openChatApp() {
    Platform.runLater(() -> {
        loginStage.close(); // Đóng cửa sổ đăng nhập
        mainScene.openMainStage(AccountId); // Mở cửa sổ chính
=======
    
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
        
>>>>>>> database
    });
}
}
