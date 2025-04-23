/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.subSetting;

import BlueOceanScene.Utils.MediaMusic;
import LanguagePackage.LanguageManager;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 *
 * @author truon
 */
public class MucBackSetting  {
    public static HBox volume(){
        VBox preroot = new VBox();
        Text title = new Text(LanguageManager.get("BO.MucBackSetting.title"));
        Slider volumeSlider = new Slider(0, 100, 80); // 0.0 = tắt tiếng, 1.0 = max
         volumeSlider.setShowTickLabels(true);
            volumeSlider.setShowTickMarks(true);
            volumeSlider.setMajorTickUnit(10);
            volumeSlider.setBlockIncrement(5);
            volumeSlider.setMaxWidth(900); // Chiều dài mong muốn

        // Lắng nghe thay đổi giá trị thanh trượt
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            MediaMusic.setVolume1(newVal.doubleValue());
        });
        
        
        preroot.getChildren().addAll(title,volumeSlider);
        
        HBox root = new HBox(preroot);
        HBox.setHgrow(preroot, Priority.ALWAYS); // Cho phép VBox mở rộng
        root.setTranslateX(8);
        root.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-padding: 10 10 10 10;");
        VBox.setMargin(title, new Insets(0, 0, 20, 0)); // Cách dưới 20px
        return root;
    }
}
