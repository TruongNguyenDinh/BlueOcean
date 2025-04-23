/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlueOceanScene.Utils;

import AlertPkg.AlertMain;
import BlueOceanScene.Main;
import MainForm.Models.User;
import MainForm.Utils.DatabaseHelper;
import MainForm.Utils.checkInputData;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author truon
 */
public class ChangePassword extends Application {
    public ChangePassword(){}
    private Stage parentStage;
    public ChangePassword(Stage parentStage){
        this.parentStage = parentStage;
    }
    

    
    @Override
    public void start(Stage primaryStage){
        VBox container = new VBox();
        ImageView background = new ImageView(new Image("Image/bgchange.jpeg"));
        ImageView avtbg = new ImageView(new Image("Image/avtbg.png"));

        avtbg.setPreserveRatio(true);
        background.setPreserveRatio(true); 
        avtbg.setFitHeight(130); 
        background.setFitHeight(800); 
        StackPane root = new StackPane(background);
        StackPane groupavt = new StackPane();
        
        System.out.println(new Image("Image/bgchange.jpeg"));
        
        
        Rectangle rectBG = new Rectangle(370,570,Color.web("072527"));
        rectBG.setOpacity(0.75);
        Rectangle subrectBG = new Rectangle(330,530,Color.web("34AAD8"));
        subrectBG.setOpacity(0.2);
        
        Circle avtCircle = new Circle(70);
        avtCircle.setOpacity(0.5);
        groupavt.getChildren().addAll(avtCircle,avtbg);
        
        Text np = new Text("New Password");
        np.getStyleClass().add("text-style");
        np.setFill(Color.WHITE);
        TextField newPass = new TextField();
        newPass.setMaxWidth(200);
        VBox npBox = new VBox(np,newPass);
        
        Text npa = new Text("New Password again");
        npa.getStyleClass().add("text-style");
        npa.setFill(Color.WHITE);
        TextField newPassAgain = new TextField();
        newPassAgain.setMaxWidth(200);
        VBox npaBox = new VBox(npa,newPassAgain);
        
        npBox.setAlignment(Pos.CENTER);
        npaBox.setAlignment(Pos.CENTER);
        
        Text er1 = new Text("Error -> Two passwords are not the same");
        er1.getStyleClass().add("errorInput1");
        er1.setTranslateY(170);
        er1.setVisible(false);
        Text er2 = new Text("Error -> Incorrect syntax");
        er2.getStyleClass().add("errorInput1");
        er2.setTranslateY(170);
        er2.setVisible(false);
        Text er3 = new Text("Error -> Cannot change password \ndue to something does not work! ");
        er3.getStyleClass().add("errorInput1");
        er3.setTranslateY(170);
        er3.setVisible(false);
        Button ok = new Button("OK");
        ok.getStyleClass().add("button33");
        container.getChildren().addAll(npBox,npaBox);
        
        ok.setOnAction(e->{
            String newpass = newPass.getText();
            String newpassagain = newPassAgain.getText();
            if(newpass.equals(newpassagain)){
                if(checkInputData.isValidPassword(newpass)){
                    if(DatabaseHelper.changePassword(User.getUsername(), User.getPhone(), newpass)){
                        er1.setVisible(false);
                        er2.setVisible(false);
                        er3.setVisible(false);
                        primaryStage.close();
                        AlertMain.noiticeLogout(Main.getStage(), Alert.AlertType.INFORMATION,"Log Out", "Only 1 more step to complete password change", "You must log out to complete the password change.");
                    }
                    else{
                        er1.setVisible(false);
                        er2.setVisible(false);
                        er3.setVisible(true);
                    }
                }
                else{
                    er1.setVisible(false);
                    er2.setVisible(true);
                    er3.setVisible(false);
                }
            }
            else{
                er1.setVisible(true);
                er2.setVisible(false);
                er3.setVisible(false);
            }
        });
        
        VBox contentBox = new VBox(5); // khoảng cách giữa avt và container
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(groupavt, container,ok);
        
        root.getChildren().addAll(rectBG,subrectBG,contentBox,er1,er2,er3);
//        StackPane.setAlignment(container, Pos.CENTER);
        Scene scene = new Scene(root,400,600);
        scene.getStylesheets().add(getClass().getResource("../../CSS/style.css").toExternalForm());
        primaryStage.setTitle("Change password - "+User.getFullname());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
