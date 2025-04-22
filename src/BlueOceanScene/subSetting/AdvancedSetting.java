/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlueOceanScene.subSetting;

import BlueOceanScene.Utils.ChangePassword;
import MainForm.Models.User;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author truon
 */
public class AdvancedSetting {
    
    private ChangePassword cp;
    public AdvancedSetting(){};
    private Stage parentStage;
    public AdvancedSetting(Stage parentStage){
        this.parentStage = parentStage;
    }
    public void setStage(Stage parentStage){
        this.parentStage = parentStage;
    }
    public VBox advancedSetting(){
        VBox root  = new VBox();
        Text title = new Text("Change Password");
        TextField passnow = new TextField();
        passnow.setFocusTraversable(false);
        passnow.setPromptText("Enter your current password");
        Text er1 = new Text("Hey ");er1.setFill(Color.RED);
        Text er2 = new Text(" ! Your password is incorrect!");er2.setFill(Color.RED);
        Text name = new Text(User.getUsername());
        name.setFont(Font.FontManagement.Roboto(15));
        HBox groupER = new HBox(er1,name,er2);
        groupER.setVisible(false);
        Button check = new Button("Check");
        check.setFocusTraversable(false);
        check.setOnAction(event->{
            String password  = passnow.getText();
            if(password.equals(User.getPassword())){
                groupER.setVisible(false);
                passnow.clear();
                cp = new ChangePassword(parentStage);
                Stage stage = new Stage();  // tạo cửa sổ mới
                cp.start(stage);            // khởi chạy giao diện
                stage.show();               // hiển thị cửa sổ
            }
            else{
                groupER.setVisible(true);
            }
        });
        HBox group = new HBox(passnow,check,groupER);
        group.setSpacing(10);
        root.setTranslateX(8);
        root.setSpacing(10);
        root.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-padding: 10 10 10 10;");
        root.getChildren().addAll(title,group);
        return root;
    }
}
