/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Models;



/**
 *
 * @author truon
 */
public class UserTemps {
    private  String nickname,phone,address,email;
    private  boolean gender;
    public UserTemps (){}
    public UserTemps(String nickname,String phone,String address,boolean gender,String email){
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.email = email;
    }
    public void setNickname(String nickname1){
        this.nickname = nickname1;
    }
    public  String getNickname(){
        return nickname;
    }
    public void setPhone(String phone1){
        this.phone = phone1;
    }
    public  String getPhone(){
        return phone;
    }
    public void setAddress(String address1){
        this.address = address1;
    }
    public  String getAddress(){
        return address;
    }
    public void setGender(boolean gender1){
        this.gender = gender1;
    }
    public boolean getGender(){
        return gender;
    }
    public void setEmail(String email1){
        this.email = email1;
    }
    public  String getEmail(){
        return email;
    }
}
