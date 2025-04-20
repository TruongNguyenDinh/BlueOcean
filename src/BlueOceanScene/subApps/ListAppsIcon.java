/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.subApps;

import BlueOceanScene.Utils.ReminderPanel;
import BlueOceanScene.Layouts.AppsLayout;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author truon
 */
public class ListAppsIcon {
    private final ReminderPanel reminderPanel = new ReminderPanel();
    public VBox showApps(ReadOnlyDoubleProperty widthProperty,String img,String nameApp,int index){
        
        ImageView icon = new ImageView(new Image(img));
        icon.setPreserveRatio(true);
        icon.fitWidthProperty().bind(widthProperty.multiply(0.3));
        icon.fitHeightProperty().bind(widthProperty.multiply(0.3));
        
        Text name = new Text();
        name.setText(nameApp);
        name.wrappingWidthProperty().bind(icon.fitWidthProperty().multiply(1.2)); 
        name.setStyle("-fx-font-size: 14px; -fx-fill: #003B46;"); // tùy chỉnh
        
        StackPane textContainer = new StackPane(name);
        textContainer.setAlignment(Pos.CENTER);
        textContainer.prefWidthProperty().bind(widthProperty);
        
        VBox vbox = new VBox(5); // spacing giữa ảnh và text
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.prefWidthProperty().bind(widthProperty.multiply(0.13));
        vbox.prefHeightProperty().bind(widthProperty.multiply(0.2));
        vbox.getChildren().addAll(icon, textContainer);
        vbox.getStyleClass().add("listApps");
        vbox.setOnMouseClicked(event -> {
            bgApps.showDetail(index,AppsLayout.argScene);
        });
        
        
        return vbox;
    }
}
