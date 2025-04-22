/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Models;

import java.time.LocalDateTime;


/**
 *
 * @author truon
 */
public class User {
    
    public static int id;
    private static String username,fullname,nickname,phone,address,email,password;
    private static boolean gender;
    private static LocalDateTime dateTime;
    public User (){}
    public User(int id,String username,String password,String fullname,String nickname,String phone,String address,boolean gender,String email,LocalDateTime dateTime){
        User.id = id;
        User.username = username;
        User.fullname = fullname;
        User.nickname = nickname;
        User.phone = phone;
        User.address = address;
        User.gender = gender;
        User.email = email;
        User.dateTime = dateTime;
        User.password = password;
    }
    public static int getId(){
        return id;
    }
    public static String getUsername(){
        return username;
    }
    public static String getFullname(){
        return fullname;
    }
    public static String getNickname(){
        return nickname;
    }
    public static String getPhone(){
        return phone;
    }
    public static String getAddress(){
        return address;
    }
    public static boolean getGender(){
        return gender;
    }
    
    public static void setEmail(String email){
        User.email = email;
    }
    public static String getEmail(){
        return email;
    }
    public static LocalDateTime getDatetime(){
        return dateTime;
    }
    public static String getPassword(){
        return password;
    }
}
