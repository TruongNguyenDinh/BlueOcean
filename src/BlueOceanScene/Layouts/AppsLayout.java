/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.Layouts;

import BlueOceanScene.subApps.ListAppsIcon;
import Font.FontManagement;
import LanguagePackage.LanguageManager;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


/**
 *
 * @author truon
 */
public class AppsLayout {
    public static StackPane detailappbgPane;
    public static Scene argScene; 
    public static HBox appsLayout = new HBox(); 
    private final String[] imgPaths = {
        "Image/Intro TicTacToe/logoTTT.png",
        "Image/NoteApp/NoteLogo.png",
        "Image/ShootDuck/logoRocket.png",
        "Image/FlappyBird/logo.png"
    };
    private final String[] appNames = {
      "Tic Tac Toe",
        "Note App",
        "Rocket Shooting",
        "Flappy Bird"
    };
    public HBox appsLayout(Scene scene){
        Text logoText;
        FlowPane AppsPane;
        VBox AppPane;
        ListAppsIcon appsIcon = new ListAppsIcon();
        argScene = scene;
        AppsPane = new FlowPane();
        StackPane stolocbgPane;
        
        
        
        ImageView bgApp = new ImageView(new Image("Image/BackGroundApps/BgAll.jpg"));
        Rectangle stolocbg = new Rectangle();stolocbg.setFill(Color.WHITESMOKE);
        Rectangle gridApps = new Rectangle();gridApps.setFill(Color.ANTIQUEWHITE);
        Rectangle detailappbg = new Rectangle(); detailappbg.setFill(Color.WHITESMOKE);
        
        stolocbg.heightProperty().bind(scene.heightProperty().multiply(0.84));
        stolocbg.widthProperty().bind(scene.widthProperty().multiply(0.2));
        detailappbg.heightProperty().bind(scene.heightProperty().multiply(0.84));
        bgApp.fitHeightProperty().bind(scene.heightProperty().multiply(0.84));
        detailappbg.widthProperty().bind(scene.widthProperty().multiply(0.8));
        bgApp.fitWidthProperty().bind(scene.widthProperty().multiply(0.8));
        gridApps.heightProperty().bind(stolocbg.heightProperty().multiply(0.9));
        gridApps.widthProperty().bind(stolocbg.widthProperty().multiply(1));
        
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double height = newHeight.doubleValue();
            appsLayout.setTranslateY(height*0.008);
        });
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double width = newWidth.doubleValue();
            detailappbg.setTranslateX(width*0.005);
            bgApp.setTranslateX(width*0.005);
            appsLayout.setTranslateX(width*0.005);
        });
        
        
         //apps layout
        TextField terminal = new TextField();
        terminal.setFocusTraversable(false);
        terminal.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                // Khi mất focus (click ra ngoài)
                terminal.setEditable(false);
            }
        });
        terminal.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                terminal.setEditable(true);
                terminal.requestFocus(); // Tự động focus lại
            }
        });
        terminal.setPromptText(LanguageManager.get("BO.appslayout.terminal"));
        stolocbgPane= new StackPane(stolocbg);
        for(int i = 0;i<imgPaths.length;i++){
                AppPane = appsIcon.showApps(stolocbgPane.widthProperty(), 
                        imgPaths[i],
                        appNames[i],
                        i+1);
                AppsPane.getChildren().add(AppPane);
                AppsPane.requestLayout();    
        }
        stolocbgPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Cập nhật AppsPane ngay lập tức
            AppsPane.setTranslateX(newVal.doubleValue()*0.05);
            AppsPane.setHgap(newVal.doubleValue() * 0.01);
            AppsPane.setVgap(newVal.doubleValue() * 0.01);
            AppsPane.setPadding(new Insets(newVal.doubleValue() * 0.01));
            AppsPane.setPrefWrapLength(newVal.doubleValue() * 0.9); // Độ rộng tối đa trước khi xuống dòng
        });
        stolocbgPane.getChildren().add(AppsPane);
        stolocbgPane.getChildren().add(terminal);
        StackPane.setAlignment(terminal, Pos.BOTTOM_CENTER);
        logoText = new Text(LanguageManager.get("BO.appslayout.logoText"));
        MainForm.Utils.AnimationFx lgfx = new MainForm.Utils.AnimationFx();
        lgfx.logoFx(logoText);   // Cho hình ảnh resize mượt hơn



//        bgApp.setPreserveRatio(true);  // Giữ tỉ lệ gốc của ảnh
        detailappbgPane = new StackPane(bgApp,logoText);
        detailappbgPane.widthProperty().addListener((obs,oldVal,newVal)->{
            logoText.setFont(FontManagement.Pacifico(newVal.doubleValue()*0.08));
            logoText.setTranslateX(-newVal.doubleValue()*0.07);
//            bgApp.setFitWidth(newVal.doubleValue()*0.9);
        });
        detailappbgPane.heightProperty().addListener((obs,oldVal,newVal)->{
//            bgApp.setFitHeight(newVal.doubleValue()*0.99);
        });
        Platform.runLater(() -> {
        });

        appsLayout = new HBox(stolocbgPane,detailappbgPane);
        
        return appsLayout;
           
    }
    public Pane noteAppDetail(){
        Pane root = new Pane();
        return root;
    }
    
    public static void clearAppsLayout(){
        detailappbgPane.getChildren().clear();
    }
    
    
}
