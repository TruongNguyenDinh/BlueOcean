/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

import Font.FontManagement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 *
 * @author truon
 */
public class InfoLayout {
    private HBox infoLayout;
    public HBox infoLayout(Scene scene){
        
        Text authorT = new Text("Author\n");
        Text authorT1 = new Text("Nguyen Dinh Truong\n\n");
        Text authorT2 = new Text("Le The Phuc\n\n");
        Text authorT3 = new Text("Nguyen Xuan Nhat\n\n");
        
        ImageView author = new ImageView(new Image("Image/BackGroundApps/author.jpeg"));
        ImageView infoStatus = new ImageView(new Image("Image/BackGroundApps/panel.jpg"));
        Rectangle authorbg = new Rectangle();authorbg.setFill(Color.WHITESMOKE);
        Rectangle gridApps = new Rectangle();gridApps.setFill(Color.ANTIQUEWHITE);
        Rectangle graphTimeInfobg = new Rectangle(); graphTimeInfobg.setFill(Color.WHITESMOKE);
        
        authorbg.heightProperty().bind(scene.heightProperty().multiply(0.84));
        author.fitHeightProperty().bind(scene.heightProperty().multiply(0.84));
        author.fitWidthProperty().bind(scene.widthProperty().multiply(0.2));
        authorbg.widthProperty().bind(scene.widthProperty().multiply(0.2));
        graphTimeInfobg.heightProperty().bind(scene.heightProperty().multiply(0.84));
        infoStatus.fitHeightProperty().bind(scene.heightProperty().multiply(0.84));
        infoStatus.fitWidthProperty().bind(scene.widthProperty().multiply(0.7));
        graphTimeInfobg.widthProperty().bind(scene.widthProperty().multiply(0.8));
        gridApps.heightProperty().bind(authorbg.heightProperty().multiply(0.9));
        gridApps.widthProperty().bind(authorbg.widthProperty().multiply(1));
        
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double height = newHeight.doubleValue();
            infoLayout.setTranslateY(height*0.008);
        });
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double width = newWidth.doubleValue();
            infoStatus.setTranslateX(width*0.005);
            infoLayout.setTranslateX(width*0.005);
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
        
        TextFlow authorBox = new TextFlow();
        authorBox.setTextAlignment(TextAlignment.CENTER);
        authorBox.setPadding(new Insets(10));
        authorBox.setMaxWidth(300);
        MainForm.Utils.AnimationFx lgfx = new MainForm.Utils.AnimationFx();
        lgfx.logoFx(authorT);
        StackPane authurInfoPane = new StackPane(author,authorBox);
        StackPane.setAlignment(authorBox, Pos.CENTER);
        authurInfoPane.widthProperty().addListener((obs,oldVal,newVal)->{
            authorT.setFont(FontManagement.GreVib(newVal.doubleValue()*0.15));
            authorT1.setFont(FontManagement.CorGara(newVal.doubleValue()*0.075));
            authorT2.setFont(FontManagement.CorGara(newVal.doubleValue()*0.08));
            authorT3.setFont(FontManagement.CorGara(newVal.doubleValue()*0.08));
        });
        authurInfoPane.heightProperty().addListener((obs,oldVal,newVal)->{
            authorBox.setTranslateY(newVal.doubleValue()*0.3);
            
        });
        authorBox.getChildren().add(authorT);
        authorBox.getChildren().add(authorT1);
        authorBox.getChildren().add(authorT2);
        authorBox.getChildren().add(authorT3);
//        StackPane.setAlignment(gridPane, Pos.TOP_LEFT);
        
//        Button openAppsBtn = new Button("Open");
//        openAppsBtn.getStyleClass().add("openAppBtn");
//        StackPane.setMargin(openAppsBtn, new Insets(0, 0, 0, 10)); // cách phải 10px, dưới 10px
        
        StackPane graphTimeInfoPane = new StackPane(infoStatus);
//        StackPane detailappbgPane = new StackPane(detailappbg,openAppsBtn);
//        openAppsBtn.prefWidthProperty().bind(detailappbgPane.widthProperty().multiply(0.9));
//        StackPane.setAlignment(openAppsBtn, Pos.BOTTOM_LEFT);
//        appLayout = new HBox(stolocbgPane,detailappbgPane);
        infoLayout = new HBox(authurInfoPane,graphTimeInfoPane);
        return infoLayout;
    }  
}
