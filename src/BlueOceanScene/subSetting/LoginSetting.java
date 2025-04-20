/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.subSetting;

import BlueOceanScene.Utils.WriterQuickLogin;
import LanguagePackage.LanguageManager;
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
    private static Node cancelNode = null;
    public static VBox loginSetting(){
        VBox root;
        Button setup = new Button(LanguageManager.get("BO.LoginSetting.setup"));
        setup.setFocusTraversable(false);
        Button save = new Button(LanguageManager.get("BO.LoginSetting.save"));
        save.setFocusTraversable(false);
        Button cancel = new Button(LanguageManager.get("BO.LoginSetting.cancel"));
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
            quickLogin.setPromptText(LanguageManager.get("BO.LoginSetting.quickLogin"));
        });
        Text intro =  new Text(LanguageManager.get("BO.LoginSetting.intro"));
        HBox introHBox = new HBox(intro);
        introHBox.setSpacing(10);
        StringProperty symbol = new SimpleStringProperty();
        quickLogin.setPromptText(LanguageManager.get("BO.LoginSetting.quickLogin1"));
        quickLogin.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isEmpty()) return;
            // Bỏ hết dấu `/` rồi thêm lại một cái ở đầu
                String cleaned = newVal.replace("/"+User.getId()+".", "");
                if(checkInputData.isValidQuickLogin(cleaned)){
                    saveNode = group1.lookup("saveBtn");
                    cancelNode = group1.lookup("cancelBtn");
                    if(saveNode!=null){
                        group1.getChildren().remove(save);
                    }
                    if(cancelNode!=null){
                        group1.getChildren().remove(cancel);
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
            if (!group1.getChildren().contains(cancel)) {
                group1.getChildren().add(cancel);
                cancel.setId("cancelBtn");
            }
        });
        
        save.setOnAction(event->{
            quickLogin.setEditable(false);              
            quickLogin.setFocusTraversable(false);      
            quickLogin.setMouseTransparent(true);
            
            saveNode = group1.lookup("saveBtn");
            if(saveNode!=null){
                    group1.getChildren().remove(save);
            }
            WriterQuickLogin.writer(quickLogin.getText());
            quickLogin.clear();
            group1.getChildren().removeAll(save, cancel);
        });
        cancel.setOnAction(event -> {
            quickLogin.setEditable(false);              
            quickLogin.setFocusTraversable(false);      
            quickLogin.setMouseTransparent(true);
            quickLogin.clear();

            group1.getChildren().removeAll(save, cancel);
        });

        
        HBox helpFinal = new HBox();
        Text helpSymbolPart1 = new Text(LanguageManager.get("BO.LoginSetting.helpSymbolPart1"));
        Text helpSymbolPart2 = new Text(LanguageManager.get("BO.LoginSetting.helpSymbolPart2"));
        Text note = new Text(LanguageManager.get("BO.LoginSetting.note"));
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
