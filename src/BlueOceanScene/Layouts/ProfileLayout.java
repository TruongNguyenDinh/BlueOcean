/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.Layouts;

import BlueOceanScene.Utils.AnimationFx;
import BlueOceanScene.subProfile.previewSub;
import Font.FontManagement;
import LanguagePackage.LanguageManager;
import MainForm.Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import MainForm.Models.UserTemps;
import MainForm.Utils.DatabaseHelper;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
/**
 *
 * @author truon
 */
public class ProfileLayout {
    public static final AnimationFx ProfileFX = new AnimationFx();
    private static HBox settingLayout;
    private static StackPane detailPane;
    private static VBox previewPane;
    private static Node nn = null;
    private static Node em = null;
    private static Node adr = null;
    private static Node pn = null;
    private static Node gd = null;
    private static TextField addressField;
    private static final UserTemps userTemps = new UserTemps();
    private static final Set<Integer> isOpenSave = new HashSet<>(); 
    public static StackPane getpreview(){
        return ProfileLayout.detailPane;
    }
    public static HBox profilelayout(Scene scene){
        
        Label label = new Label(LanguageManager.get("BO.profilelayout.label"));
        label.setTranslateY(80);
        label.setTranslateX(-10);
        label.setFont(Font.font(20));
        
        ImageView gear1 = new ImageView(new Image("Image/Gear/gear1.png"));
        gear1.setPreserveRatio(true);
        gear1.setFitWidth(300);
        gear1.setFitHeight(200);
        gear1.setScaleY(-1);
        ProfileFX.rotation(gear1,1);
        
        ImageView gear2 = new ImageView(new Image("Image/Gear/gear2.png"));
        gear2.setPreserveRatio(true);
        gear2.setFitWidth(250);
        gear2.setFitHeight(150);
        gear2.setTranslateX(-120);
        gear2.setRotate(20);
        ProfileFX.rotation(gear2,0);
        
        Circle avt = new Circle(100,Color.BLANCHEDALMOND);
        avt.setTranslateY(-250);
        
        TextField userField = new TextField(User.getUsername());
        userField.setEditable(false);               // Không cho gõ
        userField.setFocusTraversable(false);       // Không nhận focus qua TAB
        userField.setMouseTransparent(true);  
        userField.setMaxWidth(200);
        userField.setEditable(false);
        userField.setTranslateY(-130);
        
        TextField fullNameField = new TextField(User.getFullname());
        fullNameField.setEditable(false);               // Không cho gõ
        fullNameField.setFocusTraversable(false);       // Không nhận focus qua TAB
        fullNameField.setMouseTransparent(true);  
        fullNameField.setMaxWidth(200);
        fullNameField.setTranslateY(-90);
        
        TextField idField = new TextField("ID: "+User.getId());
        idField.setEditable(false);               // Không cho gõ
        idField.setFocusTraversable(false);       // Không nhận focus qua TAB
        idField.setMouseTransparent(true);  
        idField.setMaxWidth(50);
        idField.setEditable(false);
        idField.setTranslateY(-130);
        idField.setTranslateX(130);
        
        TextField nickNameField = new TextField();
        nickNameField.setPromptText(User.getNickname());
        nickNameField.setEditable(false);               // Không cho gõ
        nickNameField.setFocusTraversable(false);       // Không nhận focus qua TAB
        nickNameField.setMouseTransparent(true);  
        nickNameField.setMaxWidth(200);
        nickNameField.setEditable(false);
        nickNameField.setTranslateY(-50);
        
        ComboBox genderField = new ComboBox();
        if(User.getGender()) genderField.setValue(LanguageManager.get("BO.profilelayout.genderField.male"));
        else genderField.setValue(LanguageManager.get("BO.profilelayout.genderField.female"));
        genderField.getItems().addAll(LanguageManager.get("BO.profilelayout.genderField.male"),
                LanguageManager.get("BO.profilelayout.genderField.female"));
        genderField.setEditable(false);               // Không cho gõ
        genderField.setFocusTraversable(false);       // Không nhận focus qua TAB
        genderField.setMouseTransparent(true);  
        genderField.setMaxWidth(200);
        genderField.setEditable(false);
        genderField.setTranslateY(-10);
        
        TextField phoneField = new TextField();
        phoneField.setPromptText(User.getPhone());
        phoneField.setEditable(false);               // Không cho gõ
        phoneField.setFocusTraversable(false);       // Không nhận focus qua TAB
        phoneField.setMouseTransparent(true);  
        phoneField.setMaxWidth(200);
        phoneField.setEditable(false);
        phoneField.setTranslateY(30);
        
        addressField = new TextField();
        addressField.setPromptText(User.getAddress());
        addressField.setEditable(false);               // Không cho gõ
        addressField.setFocusTraversable(false);       // Không nhận focus qua TAB
        addressField.setMouseTransparent(true);  
        addressField.setMaxWidth(200);
        addressField.setEditable(false);
        addressField.setTranslateY(70);
        
        TextField emailField = new TextField();
        emailField.setPromptText(User.getEmail());
        emailField.setEditable(false);               // Không cho gõ
        emailField.setFocusTraversable(false);       // Không nhận focus qua TAB
        emailField.setMouseTransparent(true);  
        emailField.setMaxWidth(200);
        emailField.setEditable(false);
        emailField.setTranslateY(110);
        
        Text user = new Text(LanguageManager.get("user.user"));
        user.setTranslateX(-172);
        user.setTranslateY(-130);
        user.setFont(FontManagement.Roboto(15));
        
        Text fullname = new Text(LanguageManager.get("user.fullname"));
        fullname.setTranslateX(-155);
        fullname.setTranslateY(-90);
        fullname.setFont(FontManagement.Roboto(15));
        
        Text nickname = new Text(LanguageManager.get("user.nickname1"));
        nickname.setTranslateX(-152);
        nickname.setTranslateY(-50);
        nickname.setFont(FontManagement.Roboto(15));
        
        Text gender = new Text(LanguageManager.get("user.gender"));
        gender.setTranslateX(-162);
        gender.setTranslateY(-10);
        gender.setFont(FontManagement.Roboto(15));
        
        Text phonenumber = new Text(LanguageManager.get("user.phone1"));
        phonenumber.setTranslateX(-165);
        phonenumber.setTranslateY(30);
        phonenumber.setFont(FontManagement.Roboto(15));
        
        Text address = new Text(LanguageManager.get("user.address"));
        address.setTranslateX(-160);
        address.setTranslateY(70);
        address.setFont(FontManagement.Roboto(15));
        
        Text email = new Text(LanguageManager.get("user.email"));
        email.setTranslateX(-170);
        email.setTranslateY(110);
        email.setFont(FontManagement.Roboto(15));
        
        Button editBtn = new Button(LanguageManager.get("BO.profilelayout.editBtn"));
        editBtn.setTranslateY(150);
        editBtn.setTranslateX(-80);
        editBtn.setFocusTraversable(false);

        Button saveBtn = new Button(LanguageManager.get("BO.profilelayout.saveBtn"));
        saveBtn.setTranslateX(80);
        saveBtn.setTranslateY(150);
        saveBtn.setFocusTraversable(false);
        

        
        Rectangle detailBG = new Rectangle();detailBG.setFill(Color.WHITESMOKE);
        Rectangle nothingBg = new Rectangle(); nothingBg.setFill(Color.WHITESMOKE);
        
        detailBG.heightProperty().bind(scene.heightProperty().multiply(0.84));
        detailBG.widthProperty().bind(scene.widthProperty().multiply(0.35));
        nothingBg.heightProperty().bind(scene.heightProperty().multiply(0.84));
        nothingBg.widthProperty().bind(scene.widthProperty().multiply(0.65));
        
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double height = newHeight.doubleValue();
            settingLayout.setTranslateY(height*0.008);
        });
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double width = newWidth.doubleValue();
            nothingBg.setTranslateX(width*0.005);
            settingLayout.setTranslateX(width*0.005);
        });
        
        Label previewText =  new Label(LanguageManager.get("BO.profilelayout.previewText"));
        StackPane.setAlignment(previewText, Pos.TOP_LEFT);
        StackPane.setMargin(previewText, new Insets(20));
        previewText.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
//        previewText.setFont(Font.font(25));
        Label notice = new Label(LanguageManager.get("BO.profilelayout.notice1")+
                User.getFullname()+
                LanguageManager.get("BO.profilelayout.notice2"));
        notice.setStyle(" -fx-font-size: 25px;-fx-text-fill: green;");
        notice.setWrapText(true);
        notice.setMaxWidth(700);
        StackPane.setAlignment(notice, Pos.BOTTOM_LEFT);
        StackPane.setMargin(notice, new Insets(20));
        
        detailPane = new StackPane(detailBG,avt,
                userField,fullNameField,idField,nickNameField,genderField,phoneField,addressField,emailField,
                user,fullname,nickname,gender,phonenumber,address,email,
                editBtn,saveBtn);
        detailPane.widthProperty().addListener((obs,oldVal,newVal)->{
//            Sound.prefWidth(newVal.doubleValue());
        });
        
        detailPane.heightProperty().addListener((obs,oldVal,newVal)->{
            
        });
        
        StackPane nothingInfoPane = new StackPane(nothingBg,gear1,label,gear2);
        previewPane = previewSub.getRoot();
        previewPane.setTranslateY(15);
        previewPane.setTranslateX(-70);
        previewPane.setMaxWidth(50);
        previewPane.setMinHeight(500);
        previewPane.setSpacing(10);
        
        
        nothingInfoPane.widthProperty().addListener((obs,oldVal,newVal)->{
        
        });
        nothingInfoPane.heightProperty().addListener((obs,oldVal,newVal)->{
        
        });
        editBtn.setOnAction(e->{
            previewPane.getChildren().clear();
            nothingInfoPane.getChildren().clear();
            nothingInfoPane.getChildren().add(nothingBg);
            previewPane.getChildren().add(previewSub.Preview());
            nickNameField.setEditable(true);
            nickNameField.setMouseTransparent(false);
            nickNameField.textProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    nn = previewPane.lookup("#nicknameBox");
                    nickNameField.setPromptText(newVal);
                    if (nn != null) {
                        previewPane.getChildren().remove(nn);
                    }
                    HBox nickname1 = new HBox(previewSub.updatenickname(newVal));
                    if(!previewSub.getFlag()){
                        userTemps.setNickname(newVal);
                        isOpenSave.add(1);
                        isOpenSave.remove(-1);
                        saveBtn.setDisable(keySave(isOpenSave));
                    }
                    else{ 
                        isOpenSave.add(-1);
                        isOpenSave.remove(1);
                        saveBtn.setDisable(keySave(isOpenSave));
                    }
                    nickname1.setId("nicknameBox");
                    previewPane.getChildren().add(nickname1);
                }
            });
            
            genderField.setEditable(true);
            genderField.setMouseTransparent(false);
            genderField.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    gd = previewPane.lookup("#genderBox");
                    genderField.setValue(newVal);
                    if (gd != null) {
                        previewPane.getChildren().remove(gd);
                    }
                    HBox gender1 = new HBox(previewSub.updategender(newVal.toString()));
                    if (newVal.toString().equals("Nam"))userTemps.setGender(true);
                    else userTemps.setGender(false);
                    gender1.setId("genderBox");
                    previewPane.getChildren().add(gender1);
                }
            });
            
            phoneField.setEditable(true);
            phoneField.setMouseTransparent(false);
            phoneField.textProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    pn = previewPane.lookup("#phoneBox");
                    if (pn != null) {
                        previewPane.getChildren().remove(pn);
                    }
                    HBox phone1 = new HBox(previewSub.updatephone(newVal));
                    if(!previewSub.getFlag()){
                        userTemps.setPhone(newVal);
                        isOpenSave.add(2);
                        isOpenSave.remove(-2);
                        saveBtn.setDisable(keySave(isOpenSave));
                    }
                    else{
                        isOpenSave.add(-2);
                        isOpenSave.remove(2);
                        saveBtn.setDisable(keySave(isOpenSave));
                    }
                    phoneField.setPromptText(newVal);
                    phone1.setId("phoneBox");
                    previewPane.getChildren().add(phone1);
                }
            });
            
            addressField.setEditable(true);
            addressField.setMouseTransparent(false);
            addressField.textProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    adr = previewPane.lookup("#addressBox");
                    if (adr != null) {
                        previewPane.getChildren().remove(adr);
                    }
                    HBox address1 = new HBox(previewSub.updateaddress(newVal));
                    address1.setId("addressBox");
                    previewPane.getChildren().add(address1);
                    userTemps.setAddress(newVal);
                }
            });
            
            
            emailField.setEditable(true);
            emailField.setMouseTransparent(false);
            emailField.textProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    em = previewPane.lookup("#emailBox");
                    if (em != null) {
                        previewPane.getChildren().remove(em);
                    }
                    HBox email1 = new HBox(previewSub.updateemail(newVal));
                    if(!previewSub.getFlag()){
                        userTemps.setEmail(newVal);
                        isOpenSave.add(3);
                        isOpenSave.remove(-3);
                        saveBtn.setDisable(keySave(isOpenSave));
                    }
                    else{
                        isOpenSave.remove(3);
                        isOpenSave.add(-3);
                        saveBtn.setDisable(keySave(isOpenSave));
                    }
                    email1.setId("emailBox");
                    previewPane.getChildren().add(email1);
                }
            });
            
            nothingInfoPane.getChildren().addAll(previewText,previewPane,notice);
            editBtn.setDisable(true);
            
        });
        saveBtn.setDisable(true);
        saveBtn.setOnAction(e->{
            boolean a = fillData();
            previewPane.getChildren().clear(); // xoá tất cả trong previewPane
            nothingInfoPane.getChildren().remove(previewPane);
            nothingInfoPane.getChildren().remove(previewText);
            nothingInfoPane.getChildren().remove(notice);
            nothingInfoPane.getChildren().addAll(gear1,label,gear2);
            editBtn.setDisable(false);
            nickNameField.setEditable(false);
            nickNameField.setMouseTransparent(true);
            nickNameField.setFocusTraversable(false); 
            nickNameField.clear();
            genderField.setEditable(false);
            genderField.setMouseTransparent(true);
            genderField.setFocusTraversable(false);
            phoneField.setEditable(false);
            phoneField.setMouseTransparent(true);
            phoneField.setFocusTraversable(false); 
            phoneField.clear();
            addressField.setEditable(false);
            addressField.setMouseTransparent(true);
            addressField.setFocusTraversable(false); 
            addressField.clear();
            emailField.setEditable(false);
            emailField.setMouseTransparent(true);
            emailField.setFocusTraversable(false); 
            emailField.clear();
            saveBtn.requestFocus();
            
            
        });
        settingLayout = new HBox(detailPane,nothingInfoPane);
        return settingLayout;
    }
    private static boolean keySave(Set<Integer> lock) {
        for (Integer i : lock) {
            if (i < 0) return true;
        }
        return false;
    }
    private static boolean fillData(){
        
        String nickname = (userTemps.getNickname() != null && !userTemps.getNickname().isEmpty()) 
                    ? userTemps.getNickname() 
                    : User.getNickname();
        String email = (userTemps.getEmail() !=null && !userTemps.getEmail().isEmpty())
                ? userTemps.getEmail()
                : User.getEmail();
        String phone = (userTemps.getPhone()!= null && !userTemps.getPhone().isEmpty())
                ? userTemps.getPhone()
                :User.getPhone();
        Boolean gender = userTemps.getGender();
        if(gender==null){
            
        }
        String address = (userTemps.getAddress()!=null && !userTemps.getAddress().isEmpty())
                ? userTemps.getAddress()
                : User.getAddress();
        int id = User.getId();
        return DatabaseHelper.changeInfor(
                id, 
                nickname,
                email,
                address,
                phone,
                gender);
    } 
}
