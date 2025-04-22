/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.Layouts;

import BlueOceanScene.Utils.AnimationFx;
import BlueOceanScene.subSetting.AdvancedSetting;
import BlueOceanScene.subSetting.LoginSetting;
import BlueOceanScene.subSetting.MucBackSetting;
import LanguagePackage.LanguageManager;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.util.Random;
/**
 *
 * @author truon
 */
public class SettingLayout {
    private static Random random = new Random();
    private static final AnimationFx fx = new AnimationFx();
    private static HBox settingLayout;
    private static final Node volume = MucBackSetting.volume();
    private static final Node quickLog = LoginSetting.loginSetting();
    private static final AdvancedSetting adv = new AdvancedSetting();
    private static final Node advancedSetting = adv.advancedSetting();
    private static List<Button> listButton;
    private static StackPane modePane;
    public static HBox settinglayout(Scene scene){
        volume.setId("volume");
        quickLog.setId("quickLogin");
        advancedSetting.setId("advancedSetting");
        Label label = new Label(LanguageManager.get("BO.profilelayout.label"));
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

        
        modePane = new StackPane();
        Button login = new Button(LanguageManager.get("BO.settinglayout.login"));
        Button Sound = new Button(LanguageManager.get("BO.settinglayout.Sound"));
        Button advanced = new Button(LanguageManager.get("BO.settinglayout.advanced"));
        listButton = List.of(login,Sound,advanced);
        
        for(Button btn: listButton){
            btn.setFocusTraversable(false);
            btn.getStyleClass().add("listSetting");
            btn.setMinWidth(modeBg.getWidth());
            btn.prefWidthProperty().bind(modePane.widthProperty());
            btn.prefHeightProperty().bind(modePane.heightProperty().multiply(0.1));
            
        }
        VBox buttonBox = new VBox(login,Sound,advanced);
        buttonBox.setSpacing(5);
        buttonBox.setTranslateY(3);
        modePane.getChildren().addAll(modeBg,buttonBox);
        StackPane.setAlignment(buttonBox, Pos.TOP_LEFT);
        
        VBox listSettingBox = new VBox();
        listSettingBox.setSpacing(1);
        StackPane graphTimeInfoPane = new StackPane(detailModebg,gear1,label,gear2);
        
        Sound.setOnAction(e ->{
            graphTimeInfoPane.getChildren().clear();
            graphTimeInfoPane.getChildren().addAll(detailModebg,listSettingBox);
            listSettingBox.getChildren().removeIf(node -> 
                "volume".equals(node.getId()));
                
            gear1.setVisible(false);
            label.setVisible(false);
            gear2.setVisible(false);
            listSettingBox.getChildren().add(volume); 
        });
        login.setOnAction(e->{
            graphTimeInfoPane.getChildren().clear();
            graphTimeInfoPane.getChildren().addAll(detailModebg,listSettingBox);
            listSettingBox.getChildren().removeIf(node -> 
                "quickLogin".equals(node.getId()));
            gear1.setVisible(false);
            label.setVisible(false);
            gear2.setVisible(false);
            listSettingBox.getChildren().add(quickLog); 
            
        });
        advanced.setOnAction(e->{
            graphTimeInfoPane.getChildren().clear();
            graphTimeInfoPane.getChildren().addAll(detailModebg,listSettingBox);
            listSettingBox.getChildren().removeIf(node -> 
                "advancedSetting".equals(node.getId()));
            gear1.setVisible(false);
            label.setVisible(false);
            gear2.setVisible(false);
            listSettingBox.getChildren().add(advancedSetting); 
            
        });
        
        
        settingLayout = new HBox(modePane,graphTimeInfoPane);
        return settingLayout;
    }  
}
