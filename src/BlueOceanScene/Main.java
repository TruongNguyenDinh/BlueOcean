/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

import IconFactoryPkg.IconFact;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Font.FontManagement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author truon
 */
public class Main extends Application {
    private static String Id;
    private static boolean newsOpens = true,apps = false,setting = false,info = false;
    private IconFact icon = new IconFact();
    private double width,height;
    private HBox title_bar,groupPane,news_friend,layoutfinal;
    private Pane bulletin_boardPane,imgEventPane,infoClientPane,friendPane,timePane;
    private VBox img_info,overralPane;
    private ImageView imageView;
    private AnimationFx fx = new AnimationFx();
    private Label timer;
    private String[] imagePaths = {
        "Image/1.jpeg",
        "Image/2.jpeg",
        "Image/3.jpeg"
    };
    public static void setId(String id) {
        Id = id;
    }
    @Override
    public void start(Stage primaryStage) {
        openMainStage(Id);
    }
    private HBox newsPane(Scene scene){
        HBox news = new HBox();
        return  news;
    }
    public void openMainStage (String id){
        // Tạo stage chính
        Stage MainStage = new Stage();
       // Tạo các Node Pane
       BorderPane MainPane = new BorderPane();
       //Tạo màu nền
       MainPane.setStyle("-fx-background-color: #bad96d");
       //Tao scene
       Scene scene = new Scene(MainPane,1000,700);
       MainStage.setTitle("Blue Ocean");
//       MainStage.initStyle(StageStyle.UNDECORATED);
       MainStage.setMaximized(true);
       MainStage.setScene(scene);
       if(newsOpens){
           layoutfinal = newsPane(scene);
       }
       //Phan vung
       //Phân vùng 1 - task bar
       Rectangle bgLogo = new Rectangle();
       Rectangle end_taskbar = new Rectangle();
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

      // Phân vùng 2_1 - header
       Rectangle main_title_bar = new Rectangle();main_title_bar.setFill(Color.AQUA);
       Rectangle sub_title_bar = new Rectangle();sub_title_bar.setFill(Color.AQUAMARINE);
       Rectangle title_bar_close = new Rectangle();title_bar_close.setFill(Color.BEIGE);
       // Phân vùng 2_2 
       Rectangle bulletin_board = new Rectangle();
       // Phân cùng 2_3:1
       Rectangle bg_img_slide = new Rectangle();
       Rectangle img_slide = new Rectangle();
       img_slide.setFill(Color.BEIGE);
       Rectangle info_client = new Rectangle();
       // Phân vùng 2_3:2
       Rectangle date_time = new Rectangle();
       Rectangle friend_list = new Rectangle();
       Rectangle news_list = new Rectangle();
       
       //Thiet lap vi tri chieu rong
       //Phan vung 1 - task bar
       bgLogo.widthProperty().bind(scene.widthProperty().multiply(0.15));
       newsBtn.prefWidthProperty().bind(bgLogo.widthProperty());
       appsBtn.prefWidthProperty().bind(bgLogo.widthProperty());
       infoBtn.prefWidthProperty().bind(bgLogo.widthProperty());
       settingBtn.prefWidthProperty().bind(bgLogo.widthProperty());
       profile.prefWidthProperty().bind(bgLogo.widthProperty());
       logoutBtn.prefWidthProperty().bind(bgLogo.widthProperty());
       end_taskbar.widthProperty().bind(bgLogo.widthProperty());
       //Phan vung 2
       main_title_bar.widthProperty().bind(scene.widthProperty().multiply(0.5));
       sub_title_bar.widthProperty().bind(scene.widthProperty().multiply(0.25));
       title_bar_close.widthProperty().bind(scene.widthProperty().multiply(0.05));
       bulletin_board.widthProperty().bind(scene.widthProperty().multiply(0.9));
       bg_img_slide.widthProperty().bind(scene.widthProperty().multiply(0.35));
       img_slide.widthProperty().bind(scene.widthProperty().multiply(0.25));
       info_client.widthProperty().bind(bg_img_slide.widthProperty());
       date_time.widthProperty().bind(scene.widthProperty().multiply(0.524));
       friend_list.widthProperty().bind(date_time.widthProperty().multiply(0.47));
       news_list.widthProperty().bind(date_time.widthProperty().multiply(0.47));
       
       // Thiet lap vi tri chieu dai
       //Phan vung 1
       bgLogo.heightProperty().bind(scene.heightProperty().multiply(0.1));
       newsBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
       appsBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
       infoBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
       settingBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
       profile.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
       logoutBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
       end_taskbar.heightProperty().bind(scene.heightProperty().multiply(0.54));
       //Phan vung 2
       main_title_bar.heightProperty().bind(scene.heightProperty().multiply(0.03));
       sub_title_bar.heightProperty().bind(main_title_bar.heightProperty());
       title_bar_close.heightProperty().bind(main_title_bar.heightProperty());
       bulletin_board.heightProperty().bind(scene.heightProperty().multiply(0.1));
       bg_img_slide.heightProperty().bind(scene.heightProperty().multiply(0.46));
       img_slide.heightProperty().bind(scene.heightProperty().multiply(0.3));
       info_client.heightProperty().bind(scene.heightProperty().multiply(0.38));
       date_time.heightProperty().bind(scene.heightProperty().multiply(0.1));
       friend_list.heightProperty().bind(scene.heightProperty().multiply(0.74));
       news_list.heightProperty().bind(scene.heightProperty().multiply(0.74));
       
        //Bám scene
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            height = newHeight.doubleValue();
            bulletin_boardPane.setTranslateY(height*0.005);
            img_info.setTranslateY(height*0.01);
            infoClientPane.setTranslateY(height*0.005);
            news_friend.setTranslateY(height*0.005);
            overralPane.setTranslateY(height*0.01);
        });
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            width = newWidth.doubleValue();
            title_bar.setTranslateX(width*0.05);
            bulletin_boardPane.setTranslateX(width*0.005);
            img_info.setTranslateX(width*0.005);
            friendPane.setTranslateX(width*0.003);
            overralPane.setTranslateX(width*0.007);
            
            System.out.println(width);
            });
       Text newsText = new Text("HELLO ");
       newsText.setFill(Color.CORNFLOWERBLUE);
       newsText.setLayoutX(10);
       newsText.setLayoutY(20);
       timer = new Label();fx.Timer(timer);
       timer.setTextFill(Color.WHITE);
       
       //Thiet lap pha vung
       //Task_bar
       Pane logoBackground = new Pane(bgLogo);
       Pane newsPane = new Pane(newsBtn);
       Pane infoPane = new Pane(infoBtn);
       Pane appsPane = new Pane(appsBtn);
       Pane settingPane = new Pane(settingBtn);
       Pane profilePane = new Pane(profile);
       Pane logoutPane = new Pane(logoutBtn);
       
       VBox task_bar = new VBox(logoBackground,newsPane,appsPane,infoPane,settingPane,profilePane,logoutPane,end_taskbar);
       //Phân vùng 2
       //bulletin_board
       bulletin_boardPane = new Pane(bulletin_board,newsText);
       //Title
       Pane maintt = new Pane(main_title_bar);
       Pane sub_maintt = new Pane(sub_title_bar);
       Pane maintt_close = new Pane(title_bar_close);
       title_bar = new HBox(maintt,sub_maintt,maintt_close);
//       title_bar.setTranslateX(width*0.1);
       maintt.setStyle("-fx-border-color: red;");
       //Img-info client
       imageView = new ImageView(new Image("Image/1.jpeg"));
//       imageView.setPreserveRatio(true);
       Pane imgPane = new Pane(img_slide,imageView);
       img_slide.widthProperty().addListener((obs,oldVal,newVal)->{
           imageView.setFitWidth(newVal.doubleValue());
       });
       img_slide.heightProperty().addListener((obs,oldVal,newVal)->{
           imageView.setFitHeight(newVal.doubleValue());
       });
       AnimationFx.createSlideshowAnimation(imageView, imagePaths);
       Text noitice = new Text("New Events");
       noitice.setFill(Color.AQUA);
       imgEventPane = new Pane(bg_img_slide,noitice,imgPane);
       
       imgPane.setStyle("-fx-border-color: red;"); 
       imgEventPane.widthProperty().addListener((obs,oldVal,newVal)->{
           imgPane.setLayoutX(newVal.doubleValue()*0.15);
           noitice.setLayoutX(newVal.doubleValue()*0.45);
       });
       imgEventPane.heightProperty().addListener((obs,oldVal,newVal)->{
           imgPane.setLayoutY(newVal.doubleValue()*0.15);
           noitice.setLayoutY(newVal.doubleValue()*0.1);
           System.out.println("img:"+img_slide.getHeight());
       });
       
       infoClientPane = new Pane(info_client);
       img_info = new VBox(imgEventPane,infoClientPane);
       //time_friend_news
       timePane = new Pane(date_time,timer);
       
       timePane.heightProperty().addListener((obs,oldVal,newVal)->{
           timer.setLayoutY(timePane.getHeight()*0.25);
           timer.setFont(FontManagement.Roboto(timePane.getHeight() * 0.55));

       });
       timePane.widthProperty().addListener((obs,oldVal,newVal)->{
           timer.setLayoutX(timePane.getWidth()*0.35);
       });
       
       friendPane = new Pane(friend_list);
       Pane newsListPane = new Pane(news_list);
       // thiết lập friend cùng ngang với news
       news_friend = new HBox(newsListPane,friendPane);
       // thiết lập time nằm trên khối friend + time
       overralPane = new VBox(timePane,news_friend);
       
       groupPane = new HBox(img_info,overralPane);
       bulletin_boardPane.setStyle("-fx-border-color: red;");      
       VBox group2 = new VBox(title_bar,bulletin_boardPane,groupPane);
       
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
