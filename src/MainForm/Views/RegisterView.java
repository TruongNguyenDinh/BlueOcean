/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Views;

import MainForm.Controllers.RegistorController;
import AlertPkg.AlertMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;
import ShapeFactory.RectangleFactory;
import TextFactory.TextFieldFact;
import TextFactory.TextLableFact;
import ImageFactory.ImgFactory;
import Font.FontManagement;
import MainForm.Utils.AnimationFx;
import MainForm.TermsandLicense;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 *
 * @author truong
 */
public class RegisterView extends Application {
    private Text agreeText;
    public void setAgreeText(Text agreeText) {
        this.agreeText = agreeText;
    }

    @Override
    public void start(Stage registorStage) {
        //Khởi tạo biến
        Font pacifico,roboto;
        Button buttonRegis ;
        CheckBox yeno;
        List<Text> textLabels,errors;
        List<TextField> inputFields;
        Text licenseText,errorSending,haveAnAccount,textGoBack,errorInput;
        RectangleFactory rect ;
        ImgFactory img;
        TextLableFact label;
        AtomicBoolean isVisible ;
        AnimationFx fx ;
        TextFieldFact field;
        Pane root;
        Scene scene;
        
        fx = new AnimationFx();
        img = new ImgFactory();
        root = new Pane();
        rect = new RectangleFactory();
        yeno = new CheckBox();
        label = new TextLableFact();
        field = new TextFieldFact();
        isVisible = new AtomicBoolean(false);
        buttonRegis = new Button();

        buttonRegis.setText("Register");
        buttonRegis.setLayoutX(500);
        buttonRegis.setLayoutY(450);
        buttonRegis.getStyleClass().add("buttonRegis");
        buttonRegis.setVisible(false);
        
        Stage primaryStage = registorStage;
        primaryStage.getIcons().add(ImgFactory.getIcon());
        // Load fonts
        pacifico = FontManagement.Pacifico(70);
        roboto = FontManagement.Roboto(15);

        // Ảnh 
        ImageView bgregistor = img.createImg(0, 0, 1, 1000, 600, 1, 1, "Image/bglogin.jpg");
        ImageView wave = img.createImg(180, 250, 0.3, 600, 300, 1, 1, "Image/wave.png");
        // Nền phụ
        Rectangle subBackground = rect.createRectangle(980,580,"012a4a",10, 10, 10,10, 0.9);
        Rectangle borderRegister = rect.createRectangle(600,400,"014f86",10, 10, 180,150, 0.5);
        // Tiêu đề
        Text registerText = label.createText(350, 300, "Register", pacifico, "a9d6e5");
        // Điều khoản
        yeno.setLayoutX(300);
        yeno.setLayoutY(415);
        yeno.setOnAction(e -> buttonRegis.setVisible(yeno.isSelected()));
        
        agreeText = label.createText(320, 430, "Tôi đồng ý với các", roboto, "ffffff");
        
        licenseText = label.createText(445, 430, "điều khoản và giấy phép.", roboto, "ff0000");
        licenseText.setStyle("-fx-cursor: hand;");
        licenseText.setOnMouseClicked(e -> TermsandLicense.openFile());

        // Form đăng ký
        TextField usernameField = field.createFieldData(300, 200, "Tài khoản đăng nhập","textFieldRegis");
        TextField passwordField = field.createFieldData(300, 250, "Tài khoản đăng nhập","textFieldRegis");
        TextField password_againField = field.createFieldData(300, 300, "Nhập lại mật khẩu","textFieldRegis");
        TextField emailField = field.createFieldData(400, 350, "Email","textFieldRegis");
        TextField fullnameField = field.createFieldData(500, 200, "Họ và tên","textFieldRegis");
        TextField phonenumberField = field.createFieldData(500, 250, "Số điện thoại","textFieldRegis");
        TextField nicknameField = field.createFieldData(500, 300, "Biệt danh","textFieldRegis");
        inputFields = List.of(
            usernameField,passwordField,password_againField,emailField,fullnameField,phonenumberField,nicknameField
        );
        
        Text usernameText = label.createText(300, 195, "Tên đăng nhập");
        Text passwordText = label.createText(300, 245, "Mật khẩu");
        Text password_againText  = label.createText(300, 295, "Nhập lại mật khẩu");
        Text emailText = label.createText(400, 345, "Email");
        Text fullnameText = label.createText(500, 195, "Họ và tên");
        Text numberphoneText = label.createText(500, 245, "Số điện thoại");
        Text nicknameText = label.createText(500, 295, "Tên trong game");
        textLabels = List.of(
            usernameText,passwordText,password_againText,emailText,
            fullnameText,numberphoneText,nicknameText
        );
        
        //Các thông báo lỗi
        ////Lỗi nhập liệu
        errorInput = label.createText(260, 500,"""
            (Lỗi!)-> Hai mật khẩu không giống nhau hoặc bị để trống !!!
            """);
        errorInput.getStyleClass().add("errorInput");
        errorInput.setVisible(false);
        //Lỗi gửi request
        errorSending = label.createText(350,500,"""
            (Lỗi!)-> Kết nối tới server
            """);
        errorSending.getStyleClass().add("errorInput");
        errorSending.setVisible(false);
        errors = List.of(errorInput,errorSending);
        // Nút đăng ký
        buttonRegis.setOnMouseClicked(e ->{
//            closeRegister(registorStage);
            String userName = usernameField.getText();
            String password = passwordField.getText();
            String passwordCheck = password_againField.getText();
            String email = emailField.getText();
            String phonenumber  = phonenumberField.getText();
            String fullname = fullnameField.getText();
            String nickname = nicknameField.getText();
            
            if (password.equals(passwordCheck)&& !"".equals(password)){
                RegistorController rgc = new RegistorController(userName, password, phonenumber, email, fullname, nickname);
                if(rgc.checkAllData()){
                    errorSending.setVisible(false);
                    errorInput.setVisible(false);
                    rgc.sendData();
                    closeRegister(registorStage);
                    AlertMain.showAlert(true,primaryStage,Alert.AlertType.INFORMATION, 
                       "Thông báo", "Chúc mừng", "Chúc mừng bạn đã đăng kí tài khoản thành công");
                }
                else{
                    AlertMain.showAlert(false,primaryStage,Alert.AlertType.ERROR, 
                       "Thông báo", "Lỗi", "Lỗi dữ liệu đầu vào!");
                    errorInput.setVisible(false);
                    fx.errorsShow(errorSending);
                }
            }
            else {
                errorSending.setVisible(false);
                fx.errorsShow(errorInput);
            }            
        });
        // Có tài khoản chưa?
        haveAnAccount = label.createText(300, 405, "I have had an account.", roboto, "ffffff");
        // Nút quay lại
        textGoBack = label.createText(450, 405, "Go back", roboto, "ff0000");
        textGoBack.setStyle("-fx-cursor:hand;");
        textGoBack.setOnMouseClicked(e ->closeRegister(registorStage));
        
        // Hiện các phần tử khi chữ Registor chạy xong
        fx.registerFx(isVisible, registerText,yeno,licenseText,agreeText,haveAnAccount,textGoBack,
            usernameField,passwordField,password_againField,emailField,fullnameField,phonenumberField,nicknameField,
            usernameText,passwordText,password_againText,emailText,fullnameText,numberphoneText,nicknameText
        );
        root.getChildren().addAll(
            bgregistor, subBackground,wave, borderRegister, registerText,
            buttonRegis, textGoBack, haveAnAccount, yeno, licenseText, agreeText
        );
        root.getChildren().addAll(inputFields);
        root.getChildren().addAll(textLabels);
        root.getChildren().addAll(errors);

        scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("../../CSS/Style.css").toExternalForm());
        
        registorStage.setTitle("Welcome to Blue Ocean!");
        registorStage.setScene(scene);
        registorStage.setResizable(false);
//        registorStage.setOnCloseRequest(e -> closeRegister(registorStage));
        registorStage.show();
        
        javafx.application.Platform.runLater(root::requestFocus);
    }
    private void closeRegister(Stage stage){
        LogInView.unlockRegister();
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
