/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Views;

import MainForm.Views.LogInView;
import MainForm.Controllers.FogottenPasswordController;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ImageFactory.ImgFactory;
import ShapeFactory.RectangleFactory;
import ShapeFactory.CircleFactory;
import TextFactory.TextFieldFact;
import TextFactory.TextLableFact;

/**
 *
 * @author truon
 */
public class ForgottenPasswordView extends Application {
    private List<Rectangle> rectangleList;
    private List<Circle> circleList; 
    private List<TextField> inputList;
    private List<TextField> inputList2;
    private List<Text> textLable;
    private List<Text> textLable2;
    private List<ImageView> imageList;
    private Text errorText;
    private Scene scene2;
    private Stage window;
    private final ImgFactory img = new ImgFactory();
    private final RectangleFactory rect = new RectangleFactory();
    private final CircleFactory cir = new CircleFactory();
    private final TextFieldFact textField = new TextFieldFact();
    private final TextLableFact label = new TextLableFact();
    private FogottenPasswordController fpc;
    @Override
    public void start(Stage primaryStage) {
        this.window  = primaryStage;
        ImageView whale20 = img.createImg(250, 5, 1, 100, 100, 1, 1, "Image/whale20.png");
        ImageView bgfg = img.createImg(0, 0, 1, 736, 1040, 1, 1, "Image/bgfg.jpeg");
        imageList = List.of(
                whale20,bgfg
        );
        
        Rectangle bg = rect.createRectangle(600, 600, "012A4A", 0, 0, 0, 0, 0.5);
        Rectangle subbg = rect.createRectangle(550, 525, "013A63", 20, 20, 25, 50, 0.65);
        Rectangle bgform = rect.createRectangle(300, 300, "014f86", 20, 20, 150, 150, 0.65);
        rectangleList  = List.of(
                 bg,subbg,bgform
        );
        
        Circle bglogo = cir.createCircle(300, 50, 50, "012A4A", 0.65);
        Circle subbglogo = cir.createCircle(300, 50, 40, "013A63", 0.65);
        circleList = List.of(
                bglogo,subbglogo
        );
        
        TextField userNameField  = textField.createFieldData(225, 200, "Username", "loginField");
        TextField phoneNumberField  = textField.createFieldData(225, 250, "PhoneNumber", "loginField");
        TextField tagNameField  = textField.createFieldData(225, 300, "Tag Name", "loginField");
        inputList = List.of(
                userNameField,phoneNumberField,tagNameField
        );
        
        Text userNameLable = label.createText(225, 195, "Username");
        Text phoneNumberLable = label.createText(225, 245, "PhoneNumber");
        Text tagNameLable = label.createText(225, 295, "TagName");
        textLable = List.of(
                userNameLable,phoneNumberLable,tagNameLable
        );
        
        Button send = new Button();
        send.setLayoutX(280);
        send.setLayoutY(340);
        send.setText("Send");
        send.getStyleClass().add("loginButton");
        send.setOnMouseClicked(event -> {
            String user = userNameField.getText();
            String phone = phoneNumberField.getText();
            String tag = tagNameField.getText();
            fpc = new FogottenPasswordController(user, phone, tag);
            if (fpc.checkYesNo()) {
                window.setTitle("Re Password");
                window.setScene(scene2());
            }
            else errorText.setVisible(true);
        });

        
        errorText = new Text(" ! <False> Can not find Username");
        errorText.setLayoutX(170);
        errorText.setLayoutY(400);
        errorText.getStyleClass().add("errorInput");
        errorText.setVisible(false);
               
        Pane root = new Pane(imageList.get(1));
        root.getChildren().addAll(rectangleList);
        root.getChildren().add(errorText);
        root.getChildren().addAll(circleList);
        root.getChildren().add(imageList.get(0));
        root.getChildren().addAll(inputList);
        root.getChildren().addAll(textLable);
        root.getChildren().addAll(send);
        
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(getClass().getResource("../../CSS/Style.css").toExternalForm());
        window.setTitle("Fogot Password");
        window.setOnCloseRequest(e->{closeForget(window);});
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    private Scene scene2(){
        Pane root = new Pane(imageList.get(1));
        TextField passworLabel  = textField.createFieldData(225, 250, "Password", "loginFieldPass");
        TextField passworLabel1  = textField.createFieldData(225, 300, "Password", "loginFieldPass");
        inputList2 = List.of(
                passworLabel,passworLabel1
        );
        Text Notification = label.createText(200, 220, "Enter new password for: " + inputList.get(0).getText());
        Text newPass = label.createText(225, 245, "New Password");
        Text newPassAgain = label.createText(225, 295, "Enter Password again");
        textLable2 = List.of(
              Notification,newPass,newPassAgain
        );
        Button Finish = new Button();
        Finish.setLayoutX(280);
        Finish.setLayoutY(340);
        Finish.getStyleClass().add("loginButton");
        Finish.setText("Finish");
        Finish.setOnMouseClicked(e->{
            String newpass = newPass.getText().trim();
            String newpass_again = newPass.getText().trim();
            if(fpc.changePassword(newpass,newpass_again)) closeForget(window);
            else errorText.setVisible(true);
        });
        root.getChildren().addAll(rectangleList);
        root.getChildren().addAll(circleList);
        root.getChildren().add(imageList.get(0));
        root.getChildren().addAll(inputList2);
        root.getChildren().addAll(textLable2);
        root.getChildren().add(Finish);
        scene2 = new Scene(root,600,600);
        scene2.getStylesheets().add(getClass().getResource("../../CSS/Style.css").toExternalForm());
        return scene2;
    }
    private void closeForget(Stage stage){
        LogInView.unlockForget();
        stage.close();
    }
    public static void main(String[] args) {
        launch(args);
    } 
}   
