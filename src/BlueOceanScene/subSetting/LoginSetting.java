/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.subSetting;

import BlueOceanScene.Utils.WriterQuickLogin;
import MainForm.Models.User;
import MainForm.Utils.checkInputData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 *
 * @author truon
 */
public class LoginSetting {   
    private static Node saveNode = null;
    public static VBox loginSetting(){
        VBox root;
        Button setup = new Button("Thiết lập");
        setup.setFocusTraversable(false);
        Button save = new Button("Lưu");
        save.setFocusTraversable(false);
        
        TextField quickLogin = new TextField();
        quickLogin.setMaxWidth(200);
        quickLogin.setEditable(false);               // Không cho gõ
        quickLogin.setFocusTraversable(false);       // Không nhận focus qua TAB
        quickLogin.setMouseTransparent(true);
        HBox group1 = new HBox(quickLogin,setup);
        group1.setSpacing(15);
        setup.setOnAction(event->{
            quickLogin.setEditable(true);
            quickLogin.setMouseTransparent(false);
        });
        Text intro =  new Text("Đăng nhập nhanh");
        HBox introHBox = new HBox(intro);
        introHBox.setSpacing(10);
        StringProperty symbol = new SimpleStringProperty();
        quickLogin.setPromptText("Nhập lệnh đăng nhập nhanh");
        quickLogin.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isEmpty()) return;
            // Bỏ hết dấu `/` rồi thêm lại một cái ở đầu
                String cleaned = newVal.replace("/"+User.getId()+".", "");
                if(checkInputData.isValidQuickLogin(cleaned)){
                    saveNode = group1.lookup("saveBtn");
                    if(saveNode!=null){
                        group1.getChildren().remove(save);
                    }
                    String result = "/"+User.getId()+"." + cleaned;
                    // Chỉ set lại nếu khác để tránh lặp vô hạn
                    if (!newVal.equals(result)) {
                        quickLogin.setText(result);
                    }
                }
                else {
                    // Nếu sai định dạng, quay về oldVal hợp lệ trước đó
                    quickLogin.setText(oldVal);
                }
            if (!group1.getChildren().contains(save)) {
                group1.getChildren().add(save);
                save.setId("saveBtn");
            }
        });
        
        save.setOnAction(event->{
            quickLogin.setEditable(false);              
            quickLogin.setFocusTraversable(false);      
            quickLogin.setMouseTransparent(true);
            quickLogin.clear();
            saveNode = group1.lookup("saveBtn");
            if(saveNode!=null){
                    group1.getChildren().remove(save);
            }
            if(WriterQuickLogin.writer(quickLogin.getText())){
                
            }
            else{
                
            }
        });
        
        HBox helpFinal = new HBox();
        Text helpSymbolPart1 = new Text("Để đăng nhập nhanh bạn hãy gõ - ");
        Text helpSymbolPart2 = new Text(" - ở mục tài khoản");
        Text note = new Text("👉 Chú ý: Bạn có thể nhập tối đa 7 kí tự");
        symbol.bind(quickLogin.textProperty());
        Text symbol_ = new Text();
        symbol_.setFill(Color.RED);
        symbol_.textProperty().bind(symbol);
        
        helpFinal.getChildren().addAll(helpSymbolPart1,symbol_,helpSymbolPart2);
        root =  new VBox(intro,group1,helpFinal,note);
        VBox.setMargin(intro, new Insets(0, 0, 20, 0)); // Cách dưới 20px

        root.setTranslateX(8);
        root.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-padding: 10 10 10 10;");
        return root;
    }
}
