/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import Font.FontManagement;

/**
 *
 * @author truon
 */
public class Main extends Application {
    private final NewsLayout newsLayout = new NewsLayout();
    private static String Id;
    private static boolean newsOpens = true,apps = false,setting = false,info = false;
    private double width,height;
    private HBox title_bar;
    private Pane bulletin_boardPane;

    @Override
    public void start(Stage primaryStage) {
        openMainStage(Id);
    }
    private HBox newsPane(Scene scene){
        HBox news = new HBox();
        return  news;
    }
    public void openMainStage (String id){
        Stage MainStage = new Stage();// Tạo stage chính
        BorderPane MainPane = new BorderPane(); // Tạo các Node Pane
        MainPane.setStyle("-fx-background-color: #c4dfe6");//Tạo màu nền
        Scene scene = new Scene(MainPane,1000,600);//Tao scene
        MainStage.setTitle("Blue Ocean - " + id);
 //       MainStage.initStyle(StageStyle.UNDECORATED);
        MainStage.setMaximized(true);
 //       MainStage.setResizable(false);
        MainStage.setScene(scene);
        
        //Add layout
        HBox newslayout = new HBox(newsLayout.newsLayout(scene)); //newsLayout
        //Remain Pane
        Rectangle bgLogo = new Rectangle();bgLogo.setFill(Color.web("003B46"));
        Rectangle end_taskbar = new Rectangle();end_taskbar.setFill(Color.web("003B46"));
        Button newsBtn = new Button("News");
        Button appsBtn = new Button("Apps");
        Button infoBtn = new Button("Info");
        Button settingBtn = new Button("Setting");
        Button profile = new Button("Your Profile");
        Button logoutBtn = new Button("Log out");
        //// Tắt focus
        newsBtn.setFocusTraversable(false);
        appsBtn.setFocusTraversable(false);
        infoBtn.setFocusTraversable(false);
        settingBtn.setFocusTraversable(false);
        logoutBtn.setFocusTraversable(false);
        profile.setFocusTraversable(false);
        Rectangle main_title_bar = new Rectangle();main_title_bar.setFill(Color.AQUA);
        Rectangle sub_title_bar = new Rectangle();sub_title_bar.setFill(Color.AQUAMARINE);
        Rectangle title_bar_close = new Rectangle();title_bar_close.setFill(Color.BEIGE);
        // Phân vùng 2_2 
        Rectangle bulletin_board = new Rectangle(); bulletin_board.setFill(Color.WHITESMOKE);
        //Thiet lap vi tri chieu rong
        bgLogo.widthProperty().bind(scene.widthProperty().multiply(0.15));
        newsBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        appsBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        infoBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        settingBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        profile.prefWidthProperty().bind(bgLogo.widthProperty());
        logoutBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        end_taskbar.widthProperty().bind(bgLogo.widthProperty());
        //title+bulletin_board
        main_title_bar.widthProperty().bind(scene.widthProperty().multiply(0.5));
        sub_title_bar.widthProperty().bind(scene.widthProperty().multiply(0.25));
        title_bar_close.widthProperty().bind(scene.widthProperty().multiply(0.05));
        bulletin_board.widthProperty().bind(scene.widthProperty().multiply(0.9));
        // Thiet lap vi tri chieu dai
        //Task3
        bgLogo.heightProperty().bind(scene.heightProperty().multiply(0.1));
        newsBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        appsBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        infoBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        settingBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        profile.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        logoutBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        end_taskbar.heightProperty().bind(scene.heightProperty().multiply(0.54));
        //title+bulltin_board
        main_title_bar.heightProperty().bind(scene.heightProperty().multiply(0.03));
        sub_title_bar.heightProperty().bind(main_title_bar.heightProperty());
        title_bar_close.heightProperty().bind(main_title_bar.heightProperty());
        bulletin_board.heightProperty().bind(scene.heightProperty().multiply(0.1));
         //Bám scene
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            height = newHeight.doubleValue(); //
            bulletin_boardPane.setTranslateY(height*0.005);
            newslayout.setTranslateY(height*0.008);
        });
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            width = newWidth.doubleValue();//
            title_bar.setTranslateX(width*0.05);
            bulletin_boardPane.setTranslateX(width*0.005);
        });

        Text newsText = new Text("HELLO ");
        newsText.setFill(Color.CORNFLOWERBLUE);
        Label nickName = new Label("Nguyễn Đình Trường");
        nickName.setTextFill(Color.CORAL);
        //Pane Button
        Pane logoBackground = new Pane(bgLogo);
        Pane newsPane = new Pane(newsBtn);
        Pane infoPane = new Pane(infoBtn);
        Pane appsPane = new Pane(appsBtn);
        Pane settingPane = new Pane(settingBtn);
        Pane profilePane = new Pane(profile);
        Pane logoutPane = new Pane(logoutBtn);
        VBox task_bar = new VBox(logoBackground,newsPane,appsPane,infoPane,settingPane,profilePane,logoutPane,end_taskbar);//gom task3
       //bulletin_board
        bulletin_boardPane = new Pane(bulletin_board,newsText,nickName);//gom bulletin_board
        double h = bulletin_boardPane.getHeight();
        nickName.setLayoutX(h * 0.35);
        newsText.setLayoutX(h * 0.35);
        nickName.setLayoutY(h * 0.35);
        newsText.setLayoutY(h * 0.35);
        nickName.setFont(FontManagement.Roboto(h * 0.35));
        newsText.setFont(FontManagement.Roboto(h * 0.2));
       // Bám 
        bulletin_boardPane.widthProperty().addListener((obs,oldVal,newVal)->{
            nickName.setLayoutX(bulletin_boardPane.getHeight()*0.35);
            newsText.setLayoutX(bulletin_boardPane.getHeight()*0.35);
        });
        bulletin_boardPane.heightProperty().addListener((obs,oldVal,newVal)->{
            nickName.setLayoutY(bulletin_boardPane.getHeight()*0.35);
            newsText.setLayoutY(bulletin_boardPane.getHeight()*0.35);
            nickName.setFont(FontManagement.Roboto(bulletin_boardPane.getHeight() * 0.35));
            newsText.setFont(FontManagement.Roboto(bulletin_boardPane.getHeight() * 0.2));
        });
       
        //Title
        Pane maintt = new Pane(main_title_bar);
        Pane sub_maintt = new Pane(sub_title_bar);
        Pane maintt_close = new Pane(title_bar_close);
        title_bar = new HBox(maintt,sub_maintt,maintt_close);

        VBox group2 = new VBox(title_bar,bulletin_boardPane,newslayout);

        HBox news_layout = new HBox(task_bar,group2);
        MainPane.setLeft(news_layout);
        MainStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
