/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package AlertPkg;

import BlueOceanScene.Utils.MediaMusic;
import MainForm.Views.ForgottenPasswordView;
import MainForm.Views.LogInView;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertMain{
    public static void showAlert(boolean lock,Stage ownerStage, Alert.AlertType type, String title, String header, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type, content, ButtonType.OK);
            alert.setTitle(title);
            alert.setHeaderText(header);
            
            if (ownerStage != null) {
                alert.initModality(Modality.APPLICATION_MODAL); // Chặn cửa sổ chính
                alert.initOwner(ownerStage);
               
            }
            
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.setResizable(false);
            alertStage.initStyle(StageStyle.UNDECORATED); // Kiểu nhỏ gọn, không có maximize/minimize
            if (lock){
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        if (ownerStage != null) {
                            ownerStage.close(); // Đóng cửa sổ chính
                        }
                        alertStage.close(); // Đóng cửa sổ Alert
                    }
                });
            }
            else{
                alert.showAndWait().ifPresent(response -> {  
                    if (response == ButtonType.OK)
                    alertStage.close(); // Đóng cửa sổ Alert
                });
            }
        });
    }
    public static void checkLogOut(boolean lock,Stage ownerStage, Alert.AlertType type, String title, String header, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type, content, ButtonType.OK);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            if (ownerStage != null) {
                alert.initModality(Modality.APPLICATION_MODAL); // Chặn cửa sổ chính
                alert.initOwner(ownerStage);  
            }
            
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.setResizable(false);
            alertStage.initStyle(StageStyle.DECORATED); // Kiểu nhỏ gọn, không có maximize/minimize
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    if (lock && ownerStage != null) {
                        ownerStage.close();
                        MediaMusic.stopMusic();
                        Platform.runLater(() -> new LogInView().start(new Stage()));
                    }
                    else{
                        ownerStage.close();
                    }
                }
                alertStage.close();
            });
        });
    }
    public static void checkFotgot(boolean isForgetOpen,Stage ownerStage, Alert.AlertType type, String title, String header, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type, content, ButtonType.OK);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            if (ownerStage != null) {
                alert.initModality(Modality.APPLICATION_MODAL); // Chặn cửa sổ chính
                alert.initOwner(ownerStage);  
            }
            
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.setResizable(false);
            alertStage.initStyle(StageStyle.DECORATED); // Kiểu nhỏ gọn, không có maximize/minimize
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    if (!isForgetOpen && ownerStage != null) {
                        javafx.application.Platform.runLater(()->{
                            ownerStage.setIconified(true);
                            LogInView.setisRegisterOpen(true);
                            new ForgottenPasswordView().start(new Stage());
                        });
                    }
                    else{
                        ownerStage.close();
                    }
                }
                alertStage.close();
            });
        });
    }
}

