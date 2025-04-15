/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Views;

import MainForm.Controllers.LogInController;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.SVGPath;

import ShapeFactory.CircleFactory;
import ShapeFactory.RectangleFactory;
import ImageFactory.ImgFactory;
import TextFactory.TextLableFact;
import TextFactory.TextFieldFact;
import Font.FontManagement;
import IconFactoryPkg.IconFact;
import IconFactoryPkg.IconSource;
import MainForm.Utils.AnimationFx;
/**
 *
 * @author truong
 */
public class LogInView extends Application {
    
    // Trạng thái của cửa sổ đăng ký và quên mật khẩu
    private static boolean isRegisterOpen = false;
    private static boolean isForgetOpen = false;
    private static Stage loginStage; // Tham chiếu đến cửa sổ đăng nhập
    
    // Danh sách các thành phần giao diện
    private List<Text> textLable;
    private List<ImageView> imageList;
    private List<Rectangle> rectangleList;
    private List<SVGPath> iconList;
    
    // Quản lý phông chữ
    private Font pacifico, roboto;
    private final FontManagement createFont = new FontManagement();
    
    // Factory tạo hình ảnh, văn bản, biểu tượng, hình khối
    private final CircleFactory circleFactory = new CircleFactory(); 
    private final RectangleFactory rectFactory = new RectangleFactory();
    private final ImgFactory img = new ImgFactory();
    private final TextLableFact content = new TextLableFact();
    private final TextLableFact LogoText = new TextLableFact();
    private final TextFieldFact textField = new TextFieldFact();
    private final IconFact icon = new IconFact();
    
    private LogInController lgc;
    @Override
    public void start(Stage primaryStage) {
        loginStage = primaryStage;
        
        // Load font chữ
        pacifico = createFont.Pacifico(70);
        roboto = createFont.Roboto(15);
        
        // Load hình ảnh nền và logo
        ImageView img1 = img.createImg(0, 0, 1, 1000, 600, 1, 1, "Image/bglogin.jpg");
        ImageView img2 = img.createImg(500, 200, 0.85, 499, 499, 0.8, 0.8, "Image/whale.png");
        ImageView img3 = img.createImg(700, 45, 1, 100, 100, 1, 1, "Image/whale20.png");
        imageList = List.of(img1, img2, img3);
        
        /// Create Rectangle
        Rectangle rectbg1 = rectFactory.createRectangle(490,580,"0E2A39",20,20,500,10,0.9);
        Rectangle rectbg2 = rectFactory.createRectangle(300,300,"014f86",20,20,600,100,1);
        rectangleList = List.of( rectbg1,rectbg2 );
        
        // Tạo các icon
        SVGPath userIcon = icon.createIcon(410, -75, 0.045, 0.045, IconSource.user());
        SVGPath passwordIcon = icon.createIcon(410, -25, 0.045, 0.045, IconSource.password());
        iconList = List.of(userIcon, passwordIcon);
        
        // Tạo vòng tròn avatar
        Circle avt1 = circleFactory.createCircle(750, 90, 50, "0E2A39", 0.9);
        Circle avt2 = circleFactory.createCircle(750, 90, 45, "2a6f97", 1);
        
        //// TextLable
        Text userNameText = content.createText(650,160,"Username");
        Text passwordText = content.createText(650,210,"Password");
        Text notAccountText = content.createText(650,320,"Don't have an account ?",roboto,"BFFFF9");
        Text forgotPassText = content.createText(650,300,"I forgot my password.",roboto,"A9D6E5");
        Text registerText = content.createText(815,320,"Register",roboto,"ff0000");
        Text posRegisterText = content.createText(800,300,"Here !",roboto,"ff0000");
        Text errorLoginText = content.createText(650,340,"Lỗi đăng nhập",roboto,"ff0000");
        Text logoText = LogoText.createText(100,150,"Blue Ocean",pacifico,"BFFFF9"); 
        textLable = List.of(
            userNameText,passwordText,logoText,notAccountText,registerText,forgotPassText,posRegisterText,errorLoginText
        );
        
        // Hiệu ứng chuyển động nước
        AnimationFx lgfx = new AnimationFx();
        lgfx.logoFx(logoText);
        
        // Trường nhập tài khoản
        
        TextField usernameField = textField.createFieldData(650, 165, "Enter username", "loginField");
        PasswordField passwordField = textField.createFieldPassword(650, 215, "Enter password", "loginFieldPass");
        
        // Chuyển đổi hiện / ẩn mật khẩu
        TextField showandhide = textField.createFieldData(650, 215, "Enter password", "loginField");
        showandhide.setVisible(false); 
        ToggleButton showPasswordButton = new ToggleButton("👁");
        showPasswordButton.setLayoutX(805);
        showPasswordButton.setLayoutY(220);
        showPasswordButton.getStyleClass().add("showpassbutton");
        //// Sự kiện bật/tắt hiển thị mật khẩu
        showPasswordButton.setOnAction(event -> {
            if (showPasswordButton.isSelected()) {
                showandhide.setText(passwordField.getText());
                showandhide.setVisible(true);
                passwordField.setVisible(false);
            } 
            else{
                passwordField.setText(showandhide.getText());
                passwordField.setVisible(true);
                showandhide.setVisible(false);
            }
        });
        //// Đồng bộ mật khẩu giữa hai trường
        passwordField.textProperty().addListener((obs, oldText, newText) -> showandhide.setText(newText));
        showandhide.textProperty().addListener((obs, oldText, newText) -> passwordField.setText(newText));
        
        // Nút đăng nhập
        Button loginButton = new Button("Đăng nhập");
        loginButton.getStyleClass().add("loginButton");
        loginButton.setLayoutX(745);
        loginButton.setLayoutY(250);
        loginButton.setDisable(true);
        loginButton.setOnAction(event -> {
        lgc = new LogInController(loginStage, usernameField.getText(), passwordField.getText());
        lgc.getAccountId();
//            if(!un || !pw) {
//                count++;
//                textLable.get(7).setVisible(false);
//                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e->{
//                        textLable.get(7).setVisible(true);
//                }));
//                timeline.setCycleCount(1);
//                timeline.play();
//                if (count == 5){
//                    count =0;
//                    openForget();
//                    
//                }
//            }
//            else textLable.get(7).setVisible(false);
        });
        //Set disable cho nút đăng nhập
        usernameField.textProperty().addListener((obs,oldText,newText)->{
            loginButton.setDisable(newText.trim().isEmpty());
        });
        // Quên mật khẩu
        posRegisterText.setOnMouseClicked(event->openForget());
        
        // Lỗi nhập
        errorLoginText.setVisible(false);
        
        // Mở cửa số đăng kí
        registerText.setOnMouseClicked(event ->openRegister());
        
        // Dùng Pane để đặt vị trí tự do
        Pane loginPane = new Pane(
                img1,
                rectbg1,rectbg2,
                avt1,avt2,
                imageList.get(1),imageList.get(2),
                usernameField,passwordField,showandhide,showPasswordButton,
                loginButton
        );
        loginPane.getChildren().addAll(textLable);
        loginPane.getChildren().addAll(iconList);
        Scene loginScene = new Scene(loginPane, 1000, 600);
        //Load style
        loginScene.getStylesheets().add(getClass().getResource("../../CSS/Style.css").toExternalForm());
        loginStage.setTitle("Blue Screen Client");
        loginStage.setResizable(false);
        loginStage.setScene(loginScene);
        loginStage.show();
        javafx.application.Platform.runLater(()->loginStage.requestFocus());
    }
    /**
     * @param args the command line arguments
     */
    private void openRegister(){
        // Kiểm tra để tránh mở nhiều registor
        if(isRegisterOpen == false){
            javafx.application.Platform.runLater(()->{
                loginStage.setIconified(true);
                isRegisterOpen = true;
                new RegisterView().start(new Stage());
            });
        }
    }
    public static void unlockRegister() {
        // Hiển thị lại Login
        loginStage.setIconified(false); 
        isRegisterOpen = false; // Cho phép mở lại RegisterView
    }
    private void openForget(){
        // Kiểm tra để tránh mở nhiều registor
        if(isForgetOpen == false){
            javafx.application.Platform.runLater(()->{
                loginStage.setIconified(true);
                isForgetOpen = true;
                new ForgottenPasswordView().start(new Stage());
            });
        }
    }
    public static void unlockForget() {
        loginStage.setIconified(false); 
        isForgetOpen = false;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
