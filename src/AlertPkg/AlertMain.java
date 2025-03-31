/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package AlertPkg;

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
            
            // ✅ Nếu có cửa sổ chính, đặt làm cha
            if (ownerStage != null) {
                alert.initOwner(ownerStage);
                alert.initModality(Modality.APPLICATION_MODAL); // Chặn cửa sổ chính
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
}



