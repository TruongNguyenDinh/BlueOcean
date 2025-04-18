/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author truon
 */
public class HelpQuickLogin {
    // Dòng này tạo ra duy nhất một đối tượng HelpQuickLogin khi chương trình chạy. 
    //Tức là mọi nơi gọi HelpQuickLogin.getInstance() sẽ đều dùng cùng một object.
    private static final HelpQuickLogin instance = new HelpQuickLogin(); 
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    
    // Ngăn khởi tạo từ class khác
    private HelpQuickLogin(){}
    
    // Trả về duy nhất một đối tượng
    public static HelpQuickLogin getInstance(){
        return instance;
    }
    // Các property
    public StringProperty usernameProperty(){
        return username;
    }
    public StringProperty passwordProperty(){
        return password;
    }
    
    //getter và setter
    public String getUsername(){
        return username.get();
    }
    
    public void setUsername(String username){
        this.username.set(username);
    }
    
    public String getPassword(){
        return password.get();
    }
    
    public void setPassword(String password){
        this.password.set(password);
    }
}
//Notes
//StringProperty là một interface
///Không thể khởi tạo trực tiếp.
///Dùng các lớp con như SimpleStringProperty
///hoặc ReadOnlyStringProperty để tạo instance.
///
///SimpleStringProperty
///Có thể khởi tạo trực tiếp (thường dùng) bằng cách new SimpleStringProperty()
