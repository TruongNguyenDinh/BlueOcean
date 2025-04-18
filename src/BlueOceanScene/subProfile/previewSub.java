/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.subProfile;

import Font.FontManagement;
import MainForm.Models.User;
import MainForm.Utils.checkInputData;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


/**
 *
 * @author truon
 */
public class previewSub  {
    private static boolean flag;
    private static final VBox rootAll = new VBox();
    public static VBox getRoot(){
        return previewSub.rootAll;
    } 
    
    private static void setFlag(boolean flag){
        previewSub.flag = flag;
    }
    public static boolean getFlag(){
        return flag;
    }
    public static VBox Preview(){
        VBox root = new VBox();
        Circle avt = new Circle(100,Color.BLANCHEDALMOND);
        
        HBox avtBox = new HBox(avt);
        avtBox.setAlignment(Pos.TOP_CENTER); // căn giữa ngang

        
        Text user = new Text("User");
        user.setFont(FontManagement.Roboto(15));
        
        TextField idField = new TextField();
        idField.setPromptText("ID: "+User.getId());
        idField.setEditable(false);               // Không cho gõ
        idField.setFocusTraversable(false);       // Không nhận focus qua TAB
        idField.setMouseTransparent(true);  
        idField.setMinWidth(50);
        idField.setEditable(false);
        TextField userField = new TextField();
        userField.setPromptText(User.getUsername());
        userField.setEditable(false);               // Không cho gõ
        userField.setFocusTraversable(false);       // Không nhận focus qua TAB
        userField.setMouseTransparent(true);  
        userField.setMinWidth(200);
        userField.setEditable(false);
        HBox subGroup1 = new HBox(userField,idField);
        subGroup1.setSpacing(5);
        HBox group1 = new HBox(user,subGroup1);
        group1.setSpacing(45); // khoảng cách 10 pixels giữa các phần tử con

        
        Text fullname = new Text("FullName");
        fullname.setFont(FontManagement.Roboto(15));
        
        TextField fullNameField = new TextField();
        fullNameField.setPromptText(User.getFullname());
        fullNameField.setEditable(false);               // Không cho gõ
        fullNameField.setFocusTraversable(false);       // Không nhận focus qua TAB
        fullNameField.setMouseTransparent(true);  
        fullNameField.setMinWidth(200);
        fullNameField.setEditable(false);
        
        HBox group2 = new HBox(fullname,fullNameField);
        group2.setSpacing(12);
        root.setSpacing(10);
        root.getChildren().addAll(avtBox,group1,group2);
        return root;
    }
    public static HBox updatenickname(String str){
        HBox root = new HBox();   
        Text nickname = new Text("NickName");
        nickname.setFont(FontManagement.Roboto(15));
        
        TextField nickNameField = new TextField(str);
        nickNameField.setEditable(false);               // Không cho gõ
        nickNameField.setFocusTraversable(false);       // Không nhận focus qua TAB
        nickNameField.setMouseTransparent(true);  
        nickNameField.setMinWidth(200);
        nickNameField.setEditable(false);
        root.getChildren().addAll(nickname,nickNameField);
        if(!checkInputData.isValidNameInGame(str)){
            nickNameField.getStyleClass().add("false_input");
            previewSub.setFlag(true);
        }
        else {
            Platform.runLater(() -> {
                nickNameField.getStyleClass().remove("false_input");
                previewSub.setFlag(false);
            });
        }
        root.setSpacing(8);
        return root; 
    }
    public static HBox updategender(String str){
        HBox root = new HBox();
        
        Text gender = new Text("Gender");
        gender.setFont(FontManagement.Roboto(15));
        
        TextField genderField = new TextField(str);
        genderField.setEditable(false);               // Không cho gõ
        genderField.setFocusTraversable(false);       // Không nhận focus qua TAB
        genderField.setMouseTransparent(true);  
        genderField.setMinWidth(200);
        genderField.setEditable(false);
        root.getChildren().addAll(gender,genderField);
        root.setSpacing(28);
        return root;
    }
    public static HBox updatephone(String str){
        HBox root = new HBox();
        
        Text phonenumber = new Text("Phone");
        phonenumber.setFont(FontManagement.Roboto(15));
        
        TextField phoneField = new TextField(str);
        phoneField.setPromptText(User.getPhone());
        phoneField.setEditable(false);               // Không cho gõ
        phoneField.setFocusTraversable(false);       // Không nhận focus qua TAB
        phoneField.setMouseTransparent(true);  
        phoneField.setMinWidth(200);
        phoneField.setEditable(false);
        root.getChildren().addAll(phonenumber,phoneField);
        System.out.print(checkInputData.isValidFullName(str));
        if(!checkInputData.isValidNumberPhone(str)){
            phoneField.getStyleClass().add("false_input");
            previewSub.setFlag(true);
        }
        else {
            previewSub.setFlag(false);
             Platform.runLater(() -> {
                phoneField.getStyleClass().remove("false_input");
                
            });
        }
        root.setSpacing(34);
        return root;
    }
    public static HBox updateaddress(String str){
        HBox root = new HBox();
        
        Text address = new Text("Address");
        address.setFont(FontManagement.Roboto(15));
        
        TextField addressField = new TextField(str);
        addressField.setPromptText(User.getAddress());
        addressField.setEditable(false);               // Không cho gõ
        addressField.setFocusTraversable(false);       // Không nhận focus qua TAB
        addressField.setMouseTransparent(true);  
        addressField.setMinWidth(200);
        addressField.setEditable(false);
        root.getChildren().addAll(address,addressField);
        root.setSpacing(21);
        previewSub.setFlag(false);
        return root;
    }
    public static HBox updateemail(String str){
        HBox root = new HBox();
        
        Text email = new Text("Email");
        email.setFont(FontManagement.Roboto(15));
        
        TextField emailField = new TextField(str);
        emailField.setPromptText(User.getEmail());
        emailField.setEditable(false);               // Không cho gõ
        emailField.setFocusTraversable(false);       // Không nhận focus qua TAB
        emailField.setMouseTransparent(true);  
        emailField.setMinWidth(200);
        emailField.setEditable(false);
        root.getChildren().addAll(email,emailField);
        if(!checkInputData.isValidEmail(str)){
            emailField.getStyleClass().add("false_input");
            previewSub.setFlag(true);
        }
        else {
             Platform.runLater(() -> {
                emailField.getStyleClass().remove("false_input");
                previewSub.setFlag(false);
            });
        }
        root.setSpacing(40);
        return root;
    }
}
