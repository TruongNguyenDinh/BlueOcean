/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
/**
 *
 * @author truong
 */
public class Register extends Application {
    private Font pacifico, roboto;
    private Button buttonRegis;
    private CheckBox yeno;
    private List<Text> textLabels;
    private List<TextField> inputFields;
    private Text agreeText;
    private Text licenseText;
    private Text haveAnAccount;
    private Text textGoBack;
    private Text errorText;

    @Override
    public void start(Stage registorStage) {
        // Load fonts
        pacifico = Font.loadFont(getClass().getResourceAsStream("/Font/Pacifico-Regular.ttf"), 70);
        roboto = Font.loadFont(getClass().getResourceAsStream("/Font/Roboto-Regular.ttf"), 15);

        // Ảnh nền
        ImageView bgregistor = new ImageView(new Image("Image/bglogin.jpg"));
        bgregistor.setFitWidth(1000);
        bgregistor.setFitHeight(600);
        //whale
        ImageView wave = new ImageView(new Image("Image/wave.png"));
        wave.setLayoutX(180);
        wave.setLayoutY(150);
        wave.setFitHeight(400);
        wave.setFitWidth(600);
        wave.setOpacity(0.3);
        // Nền phụ
        Rectangle subBackground = createRectangle(10, 10, 980, 580, "012a4a", 0.9);
        Rectangle borderRegister = createRectangle(180, 150, 600, 400, "014f86", 0.5);
        borderRegister.setArcHeight(20);
        borderRegister.setArcWidth(20);

        // Tiêu đề
        Text registerText = createText(350, 300, "Register", pacifico, "a9d6e5");
        animateText(registerText);

        // Điều khoản
        yeno = new CheckBox();
        yeno.setLayoutX(300);
        yeno.setLayoutY(415);
        yeno.setOnAction(e -> buttonRegis.setVisible(yeno.isSelected()));
        
        agreeText = createText(320, 430, "Tôi đồng ý với các", roboto, "ffffff");
        licenseText = createText(445, 430, "điều khoản và giấy phép.", roboto, "ff0000");
        licenseText.setStyle("-fx-cursor: hand;");
        licenseText.setOnMouseClicked(e -> TermsandLicense.openFile());

        // Form đăng ký
        inputFields = List.of(
            createTextField(300, 200, "Tài khoản đăng nhập"), //0
            createPasswordField(300, 250, "Mật khẩu"), //1
            createPasswordField(300, 300, "Nhập lại mật khẩu"),
            createTextField(300, 350, "Email"),
            createTextField(500, 200, "Họ và tên"),
            createTextField(500, 250, "Số điện thoại"),
            createTextField(500, 300, "Tên trong game"),
            createTextField(500, 350, "Tag Name")
        );

        textLabels = List.of(
            createText(300, 195, "Tên đăng nhập"),
            createText(300, 245, "Mật khẩu"),
            createText(300, 295, "Nhập lại mật khẩu"),
            createText(300, 345, "Email"),
            createText(500, 195, "Họ và tên"),
            createText(500, 245, "Số điện thoại"),
            createText(500, 295, "Tên trong game"),
            createText(500, 345, "Tag Game")
        );
        //Lỗi dữ liệu
        errorText = new Text("(Lỗi!)-> Vui lòng đọc bấm vào điều khoản và giấy phép.\n\tHãy xem tại mục 1.1 để biết thêm thông tin nhập liệu.");
        errorText.setLayoutX(255);
        errorText.setLayoutY(500);
        errorText.getStyleClass().add("errorInput");
        errorText.setVisible(false);
        // Nút đăng ký
        buttonRegis = new Button("Register");
        buttonRegis.setLayoutX(500);
        buttonRegis.setLayoutY(450);
        buttonRegis.getStyleClass().add("buttonRegis");
        buttonRegis.setVisible(false);
        buttonRegis.setOnMouseClicked(e ->{
//            closeRegister(registorStage);
            boolean un = checkInputData.isValidUserName(inputFields.get(0).getText());
            boolean pw = false;
            if (inputFields.get(1).getText().equals(inputFields.get(2).getText()))
            { pw = checkInputData.isValidPassword(inputFields.get(1).getText());}
            boolean em = checkInputData.isValidEmail(inputFields.get(3).getText());
            boolean fn = checkInputData.isValidFullName(inputFields.get(4).getText());
            boolean np = checkInputData.isValidNumberPhone(inputFields.get(5).getText());
            boolean nig =checkInputData.isValidNameInGame(inputFields.get(6).getText());
            boolean tn = checkInputData.isValidTagName(inputFields.get(7).getText());
            if(un && pw && em && fn && np && nig && tn) {
                System.out.print("Push Data");
                errorText.setVisible(false);
            }
            else {
                errorText.setVisible(false);
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),event->{
                        errorText.setVisible(true);
                }));
                timeline.setCycleCount(1);
                timeline.play();  
            }
            System.out.printf("un: %b\npw: %b\nfn: %b\nnp: %b\nnig: %b\ntn: %b\nem: %b\n",un,pw,fn,np,nig,tn,em);
            
        });

        // Có tài khoản chưa?
        haveAnAccount = createText(300, 405, "I have had an account.", roboto, "ffffff");
        // Nút quay lại
        textGoBack = createText(450, 405, "Go back", roboto, "ff0000");
        textGoBack.setStyle("-fx-cursor:hand;");
        textGoBack.setOnMouseClicked(e ->closeRegister(registorStage));
        // Ẩn các phần tử
        setVisible(false);
        Pane root = new Pane(
            bgregistor, subBackground,wave, borderRegister, registerText,
            buttonRegis, textGoBack, haveAnAccount, yeno, licenseText, agreeText,errorText
        );
        root.getChildren().addAll(inputFields);
        root.getChildren().addAll(textLabels);

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("../CSS/Style.css").toExternalForm());
        registorStage.setTitle("Welcome to Blue Ocean!");
        registorStage.setScene(scene);
        registorStage.setResizable(false);
        registorStage.setOnCloseRequest(e -> closeRegister(registorStage));
        registorStage.show();

        javafx.application.Platform.runLater(root::requestFocus);
    }
    private TextField createTextField(int x, int y, String prompt) {
        TextField tf = new TextField();
        tf.setLayoutX(x);
        tf.setLayoutY(y);
        tf.setPromptText(prompt);
        tf.setFocusTraversable(false);
        tf.getStyleClass().add("textFieldRegis");
        return tf;
    }

    private PasswordField createPasswordField(int x, int y, String prompt) {
        PasswordField pf = new PasswordField();
        pf.setLayoutX(x);
        pf.setLayoutY(y);
        pf.setPromptText(prompt);
        pf.getStyleClass().add("textFieldRegis");
        return pf;
    }

    private Text createText(int x, int y, String content) {
        return createText(x, y, content, roboto, "ffffff");
    }

    private Text createText(int x, int y, String content, Font font, String color) {
        Text text = new Text(content);
        text.setLayoutX(x);
        text.setLayoutY(y);
        text.setFont(font);
        text.setFill(Color.web(color));
        return text;
    }

    private Rectangle createRectangle(int x, int y, int width, int height, String color, double opacity) {
        Rectangle rect = new Rectangle(width, height, Color.web(color));
        rect.setLayoutX(x);
        rect.setLayoutY(y);
        rect.setOpacity(opacity);
        return rect;
    }

    private void setVisible(boolean isVisible) {
        textLabels.forEach(label -> label.setVisible(isVisible));
        inputFields.forEach(field -> field.setVisible(isVisible));
        yeno.setVisible(isVisible);
        licenseText.setVisible(isVisible);
        agreeText.setVisible(isVisible);
        haveAnAccount.setVisible(isVisible);
        textGoBack.setVisible(isVisible);
        
    }

    private void animateText(Text text) {
        TranslateTransition moveText = new TranslateTransition(Duration.seconds(2), text);
        moveText.setFromY(0);
        moveText.setToY(-200);
        moveText.setCycleCount(1);
        moveText.setAutoReverse(false);
        moveText.play();
        moveText.setOnFinished(e ->setVisible(true));
    }
    private void closeRegister(Stage stage){
        LogInView.unlockRegister();
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
