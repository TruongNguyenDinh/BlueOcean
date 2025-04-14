/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

import Font.FontManagement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author truon
 */
public class SettingLayout {
    private static AnimationFx fx = new AnimationFx();
    private static HBox settingLayout;
    private static boolean isOpenSoundSetting = true;
    public static HBox settinglayout(Scene scene){
        Label label = new Label("Không có gì để hiển thị");
        label.setTranslateY(80);
        label.setTranslateX(-10);
        label.setFont(Font.font(20));
        
        ImageView gear1 = new ImageView(new Image("Image/Gear/gear1.png"));
        gear1.setPreserveRatio(true);
        gear1.setFitWidth(300);
        gear1.setFitHeight(200);
        gear1.setScaleY(-1);
        fx.rotation(gear1,1);
        
        ImageView gear2 = new ImageView(new Image("Image/Gear/gear2.png"));
        gear2.setPreserveRatio(true);
        gear2.setFitWidth(250);
        gear2.setFitHeight(150);
        gear2.setTranslateX(-120);
        gear2.setRotate(20);
        fx.rotation(gear2,0);
        
        Rectangle modeBg = new Rectangle();modeBg.setFill(Color.WHITESMOKE);
        Rectangle detailModebg = new Rectangle(); detailModebg.setFill(Color.WHITESMOKE);
        
        modeBg.heightProperty().bind(scene.heightProperty().multiply(0.84));
        modeBg.widthProperty().bind(scene.widthProperty().multiply(0.2));
        detailModebg.heightProperty().bind(scene.heightProperty().multiply(0.84));
        detailModebg.widthProperty().bind(scene.widthProperty().multiply(0.8));
        
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double height = newHeight.doubleValue();
            settingLayout.setTranslateY(height*0.008);
        });
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double width = newWidth.doubleValue();
            detailModebg.setTranslateX(width*0.005);
            settingLayout.setTranslateX(width*0.005);
        });
         //apps layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Cho phép giãn hết cỡ
        // Thêm vài nội dung
        gridPane.add(new Label("Username:"), 0, 0);
        gridPane.add(new TextField(), 1, 0);
        
        Button Sound = new Button("Âm thanh");
        Sound.setFocusTraversable(false);
        Sound.setTranslateY(20);
        Sound.getStyleClass().add("listSetting");
        StackPane modePane = new StackPane(modeBg,Sound);
        StackPane.setAlignment(Sound, Pos.TOP_LEFT);
        Sound.prefWidthProperty().bind(modePane.widthProperty());
        Sound.prefHeightProperty().bind(modePane.widthProperty().multiply(0.2));
        modePane.widthProperty().addListener((obs,oldVal,newVal)->{
//            Sound.prefWidth(newVal.doubleValue());
        });
        modePane.heightProperty().addListener((obs,oldVal,newVal)->{
            Sound.prefHeight(newVal.doubleValue()*0.3);
        });
        
        StackPane graphTimeInfoPane = new StackPane(detailModebg,gear1,label,gear2);
        Sound.setOnAction(e ->{
            if (isOpenSoundSetting){
                gear1.setVisible(false);
                label.setVisible(false);
                gear2.setVisible(false);
                graphTimeInfoPane.getChildren().add(MucBackSetting.volume());  
                isOpenSoundSetting = false;
            }
            
        });
        
        graphTimeInfoPane.widthProperty().addListener((obs,oldVal,newVal)->{
        
        });
        graphTimeInfoPane.heightProperty().addListener((obs,oldVal,newVal)->{
        
        });
        settingLayout = new HBox(modePane,graphTimeInfoPane);
        return settingLayout;
    }  
}
