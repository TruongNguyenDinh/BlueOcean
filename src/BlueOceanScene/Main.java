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
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font; 

import Font.FontManagement;
import GalleryFactory.GalleryItemFactory;
import javafx.scene.layout.Priority;

/**
 *
 * @author truon
 */
public class Main extends Application {
    private static String Id;
    private static boolean newsOpens = true,apps = false,setting = false,info = false;
    private double width,height;
    private HBox title_bar,groupPane,news_friend,layoutfinal;
    private Pane bulletin_boardPane,imgEventPane,infoClientPane,friendPane,timePane,bg_music_clientPane,newsListPane;
    private VBox img_info,overralPane,galleryContainer,notion_friend;
    private ImageView imageView;
    private final AnimationFx fx = new AnimationFx();
    private Label timer;
    private ScrollPane scrollPane ;
    double itemHeight; 
    private final ReminderPanel rm = new ReminderPanel();
    private final QuoteSlideshow quoteSlideshow = new QuoteSlideshow();
    private final String[] imagePaths = {
        "Image/1.jpeg",
        "Image/2.jpeg",
        "Image/3.jpeg"
    };
    private final String[] galleyPaths = {
      "Image/gallery.jpeg",  
      "Image/gallery1.jpeg",  
      "Image/gallery2.jpeg",  
      "Image/gallery3.jpeg",  
      "Image/gallery4.jpeg",  
    };
    private final String[] labelPaths = {
      "New Updates",  
      "NDTrường",  
      "LTPhúc",  
      "NXNhật",  
      "Letmagl",  
    };
    private final String[] linkPaths = {
      "https://www.youtube.com/watch?v=5ig9ZRLd8oY",
        "https://web.facebook.com/ndtruong24",
        "https://web.facebook.com/phuclevippro",
        "https://web.facebook.com/PogDad",
        "https://www.youtube.com/watch?v=LsfFYwpDngs"
            
    };
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
       MainPane.setStyle("-fx-background-color: #c4dfe6");
       //Tao scene
       Scene scene = new Scene(MainPane,1000,600);
       MainStage.setTitle("Blue Ocean - " + id);
//       MainStage.initStyle(StageStyle.UNDECORATED);
       MainStage.setMaximized(true);
//       MainStage.setResizable(false);
       MainStage.setScene(scene);
       if(newsOpens){
           layoutfinal = newsPane(scene);
       }
       //Các pane:
       galleryContainer = new VBox(10);
       //Phan vung
       //Phân vùng 1 - task bar
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

      // Phân vùng 2_1 - header
       Rectangle main_title_bar = new Rectangle();main_title_bar.setFill(Color.AQUA);
       Rectangle sub_title_bar = new Rectangle();sub_title_bar.setFill(Color.AQUAMARINE);
       Rectangle title_bar_close = new Rectangle();title_bar_close.setFill(Color.BEIGE);
       // Phân vùng 2_2 
       Rectangle bulletin_board = new Rectangle(); bulletin_board.setFill(Color.WHITESMOKE);
       // Phân cùng 2_3:1
       Rectangle bg_img_slide = new Rectangle();bg_img_slide.setFill(Color.WHITESMOKE);
       Rectangle img_slide = new Rectangle();
       img_slide.setFill(Color.BEIGE);
       Rectangle info_client = new Rectangle();info_client.setFill(Color.WHITESMOKE);
       Rectangle bg_quote_client = new Rectangle();bg_quote_client.setFill(Color.web("66b5ad"));
       Rectangle bg_info_client = new Rectangle();bg_info_client.setFill(Color.web("66a5ad"));
       Rectangle bg_music_client = new Rectangle();bg_music_client.setFill(Color.web("66A5AD"));
       // Phân vùng 2_3:2
       Rectangle date_time = new Rectangle();date_time.setFill(Color.WHITESMOKE);
       Rectangle friend_list = new Rectangle(); friend_list.setFill(Color.WHITESMOKE);
       Rectangle task_list = new Rectangle(); task_list.setFill(Color.WHITESMOKE); 
       Rectangle news_list = new Rectangle();news_list.setFill(Color.WHITESMOKE);
       
       ///Text
       Label music_backgoundText = new Label("Music background: " );
       //img
       ImageView cd = new ImageView(new Image("Image/cd.png"));
       cd.setPreserveRatio(true);
       cd.fitWidthProperty().bind(Bindings.multiply(scene.widthProperty(), 0.08));
       cd.fitHeightProperty().bind(Bindings.multiply(scene.heightProperty(), 0.08));
       cd.setRotate(50);  // Xoay ảnh 45 độ
       fx.rotation(cd);
       ImageView play = new ImageView(new Image("Image/play.png"));
       play.setPreserveRatio(true);
       play.fitWidthProperty().bind(Bindings.multiply(scene.widthProperty(), 0.045));
       play.fitHeightProperty().bind(Bindings.multiply(scene.heightProperty(), 0.025));
       ImageView pause = new ImageView(new Image("Image/pause.png"));
       pause.fitWidthProperty().bind(Bindings.multiply(scene.widthProperty(), 0.01));
       pause.fitHeightProperty().bind(Bindings.multiply(scene.heightProperty(), 0.023));
       pause.setVisible(false);
       play.setOnMouseClicked(e->{
            pause.setVisible(true);
            play.setVisible(false);
            fx.stopRotation();
       });
       pause.setOnMouseClicked(e->{
            play.setVisible(true);
            pause.setVisible(false);
            fx.startRotation();
       });
       
       
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
       bg_info_client.widthProperty().bind(scene.widthProperty().multiply(0.33));
       bg_quote_client.widthProperty().bind(bg_info_client.widthProperty().multiply(1));
       bg_music_client.widthProperty().bind(bg_info_client.widthProperty());
       date_time.widthProperty().bind(scene.widthProperty().multiply(0.524));
       friend_list.widthProperty().bind(date_time.widthProperty().multiply(0.47));
       task_list.widthProperty().bind(date_time.widthProperty().multiply(0.47));
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
       bg_info_client.heightProperty().bind(scene.heightProperty().multiply(0.23));
       bg_quote_client.heightProperty().bind(bg_info_client.heightProperty().multiply(1));
       bg_music_client.heightProperty().bind(scene.heightProperty().multiply(0.11));
       date_time.heightProperty().bind(scene.heightProperty().multiply(0.1));
       friend_list.heightProperty().bind(scene.heightProperty().multiply(0.425));
       task_list.heightProperty().bind(scene.heightProperty().multiply(0.315));
       news_list.heightProperty().bind(scene.heightProperty().multiply(0.74));
       
       //Calendar 
        CalendarView calendarView = new CalendarView();
        BorderPane calendarUI = calendarView.createCalendar(friend_list.heightProperty());
       
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
            notion_friend.setTranslateX(width*0.003);
            overralPane.setTranslateX(width*0.007);
            });
       Text newsText = new Text("HELLO ");
       newsText.setFill(Color.CORNFLOWERBLUE);
       Label nickName = new Label("Nguyễn Đình Trường");
       nickName.setTextFill(Color.CORAL);
       
       //Gallery
       
       
       timer = new Label();fx.Timer(timer);
       timer.setTextFill(Color.CADETBLUE);
       Label greeting = fx.greetingText("");
       greeting.setTextFill(Color.BLUEVIOLET);
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
       
       
       bulletin_boardPane = new Pane(bulletin_board,newsText,nickName);
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
//       title_bar.setTranslateX(width*0.1);
//       maintt.setStyle("-fx-border-color: red;");
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
       noitice.setFill(Color.DARKCYAN);
       imgEventPane = new Pane(bg_img_slide,noitice,imgPane);
       
//       imgPane.setStyle("-fx-border-color: red;"); 
       imgEventPane.widthProperty().addListener((obs,oldVal,newVal)->{
           imgPane.setLayoutX(newVal.doubleValue()*0.15);
           noitice.setLayoutX(newVal.doubleValue()*0.4);
       });
       imgEventPane.heightProperty().addListener((obs,oldVal,newVal)->{
           imgPane.setLayoutY(newVal.doubleValue()*0.15);
           noitice.setLayoutY(newVal.doubleValue()*0.1);
            noitice.setFont(FontManagement.Roboto(imgEventPane.getHeight() * 0.05));
       });
        VBox quoteVBox = new VBox(quoteSlideshow.runQuote(bg_quote_client.widthProperty(), bg_quote_client.heightProperty()));
        Pane bg_info_clientPane = new Pane(bg_info_client,quoteVBox);
        bg_info_clientPane.widthProperty().addListener((obs,oldVal,newVal)->{
            quoteVBox.setLayoutX(newVal.doubleValue()*0.01);
            
        });
        bg_info_clientPane.heightProperty().addListener((obs,oldVal,newVal)->{
            quoteVBox.setLayoutY(newVal.doubleValue()*0.25);
            
        });
        
        
        StackPane controlPane = new StackPane(play, pause); // Xếp chồng play và pause
        controlPane.setAlignment(Pos.CENTER); // Căn giữa
        bg_music_clientPane = new Pane(bg_music_client,music_backgoundText,cd,controlPane);
        bg_music_clientPane.widthProperty().addListener((obs,oldVal,newVal)->{
            music_backgoundText.setLayoutX(newVal.doubleValue()*0.03);
            controlPane.setLayoutX(newVal.doubleValue()*0.3);
        });
        bg_music_clientPane.heightProperty().addListener((obs,oldVal,newVal)->{
            music_backgoundText.setFont(FontManagement.Roboto(bg_music_clientPane.getHeight() * 0.2));
            cd.setLayoutY(newVal.doubleValue()*0.2);
            controlPane.setLayoutY(newVal.doubleValue()*0.5);
        });
       //Gallery
       VBox bg_info_music_clientPane = new VBox();
       Region spacer = new Region();
       spacer.prefHeightProperty().bind(bg_info_music_clientPane.heightProperty().multiply(0.04));
       bg_info_music_clientPane.getChildren().addAll(bg_info_clientPane,spacer,bg_music_clientPane);
       
       bg_info_music_clientPane.heightProperty().addListener((obs,newVal,oldVal)->{
           bg_music_clientPane.setLayoutY(newVal.doubleValue()*0.9);
       });
       
       infoClientPane = new Pane(info_client,bg_info_music_clientPane);
       infoClientPane.widthProperty().addListener((obs,oldVal,newVal)->{
           bg_info_music_clientPane.setLayoutX(newVal.doubleValue()*0.025);
       });
       infoClientPane.heightProperty().addListener((obs,oldVal,newVal)->{
           bg_info_music_clientPane.setLayoutY(newVal.doubleValue()*0.03);
       });
       
       img_info = new VBox(imgEventPane,infoClientPane);
       //Img day
       //time_friend_news
       timePane = new Pane(date_time, timer, greeting);
       timePane.heightProperty().addListener((obs,oldVal,newVal)->{
           timer.setLayoutY(timePane.getHeight()*0.25);
           timer.setFont(FontManagement.Roboto(timePane.getHeight() * 0.55));
           greeting.setFont(FontManagement.Roboto(timePane.getHeight() * 0.35));

       });
       timePane.widthProperty().addListener((obs,oldVal,newVal)->{
           timer.setLayoutX(timePane.getWidth()*0.35);
           greeting.setLayoutX(timePane.getWidth()*0.05);
       });
       
       
       Label what = new Label("What should I do today ?");
       
       VBox notionVBox = rm.Notion();
       ScrollPane scrollPaneNotion = new ScrollPane(notionVBox);
       scrollPaneNotion.setFitToWidth(true);
        scrollPaneNotion.setFitToHeight(true);
//       notionVBox.setPrefHeight(300); // hoặc một giá trị nào đó tạm

       scrollPaneNotion.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
       Pane  taskPane = new Pane(task_list,scrollPaneNotion);
       taskPane.prefWidthProperty().bind(task_list.widthProperty());
       taskPane.prefHeightProperty().bind(task_list.heightProperty());
       taskPane.heightProperty().addListener((obs,oldVal,newVal)->{
           scrollPaneNotion.setPrefHeight(newVal.doubleValue());
       });
       taskPane.widthProperty().addListener((obs,oldVal,newVal)->{
           scrollPaneNotion.setPrefWidth(newVal.doubleValue());

       });
       Pane whatPane = new Pane(what);
       
       friendPane = new Pane(friend_list,whatPane);
       friendPane.widthProperty().addListener((obs,oldVal,newVal)->{
           whatPane.layoutXProperty().bind(friendPane.widthProperty().subtract(whatPane.widthProperty()).divide(2));
           
       });
       friendPane.heightProperty().addListener((obs,oldVal,newVal)->{
           whatPane.setLayoutY(newVal.doubleValue()*0.9);
           what.setFont(Font.font(newVal.doubleValue()*0.05));
       });
       
       notion_friend = new VBox(friendPane,taskPane);
       VBox.setVgrow(taskPane, Priority.ALWAYS);

       
       
       Label news = new Label("News");
       
       newsListPane = new Pane(news_list,news);
       newsListPane.heightProperty().addListener((obs,oldVal,newVal)->{
           news.setFont(Font.font(newVal.doubleValue()*0.04));
           
       });
       newsListPane.widthProperty().addListener((obs,oldVal,newVal)->{
           news.setLayoutX(newVal.doubleValue()*0.4);
       });
                   
//       // thiết lập friend cùng ngang với news
       news_friend = new HBox(newsListPane,notion_friend);
       // thiết lập time nằm trên khối friend + time
       overralPane = new VBox(timePane,news_friend);
       
       groupPane = new HBox(img_info,overralPane);
//       bulletin_boardPane.setStyle("-fx-border-color: red;");    
       
        Platform.runLater(() -> {
           
//            scrollPaneNotion.applyCss();
//            scrollPaneNotion.layout();
           
            StackPane calendarUIPane = new StackPane(calendarUI);
            calendarUIPane.setLayoutX(0);
            calendarUIPane.setLayoutY(0);
            calendarUIPane.prefWidthProperty().bind(friend_list.widthProperty());
            calendarUIPane.prefHeightProperty().bind(friend_list.heightProperty().multiply(0.7));

            friendPane.getChildren().add(calendarUIPane);
            // Đảm bảo calendarUI co giãn theo calendarUIPane
//            calendarUI.prefWidthProperty().bind(calendarUIPane.widthProperty());
//            calendarUI.prefHeightProperty().bind(calendarUIPane.heightProperty());
            for (int i = 0; i < galleyPaths.length; i++) {
                VBox galleryItem = GalleryItemFactory.createGalleryItem(
                    galleyPaths[i], 
                    labelPaths[i], 
                    linkPaths[i],
                    news_list.widthProperty(),
                    itemHeight
                );
                galleryContainer.getChildren().add(galleryItem);
            }
            
            scrollPane = new ScrollPane();
            scrollPane.setContent(galleryContainer);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefHeight(news_list.getHeight());
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            // đảm bảo galleryContainer giãn theo scrollPane
            galleryContainer.setMaxWidth(Double.MAX_VALUE);
            galleryContainer.setFocusTraversable(false);
            
            // scrollPane trùm lên news_list
            scrollPane.layoutXProperty().bind(news_list.xProperty());
            scrollPane.layoutYProperty().bind(news_list.yProperty());
            scrollPane.prefWidthProperty().bind(news_list.widthProperty());
            scrollPane.prefHeightProperty().bind(news_list.heightProperty().multiply(0.915));
            Platform.runLater(() -> {
                    scrollPane.setTranslateY(newsListPane.getHeight() * 0.08);
            });
            // Đặt scrollPane vào newsListPane
            scrollPane.setFocusTraversable(false);
            newsListPane.getChildren().add(scrollPane);
            newsListPane.setFocusTraversable(false);
            newsListPane.heightProperty().addListener((obs,oldVal,newVal)->{
                Platform.runLater(() -> {
                    scrollPane.setTranslateY(newsListPane.getHeight() * 0.08);
                });
//                scrollPane.setTranslateY(newVal.doubleValue()*0.1);
            });
            double heightt = bg_info_music_clientPane.getHeight();
            bg_music_clientPane.setLayoutY(heightt*0.9);
            double h = bulletin_boardPane.getHeight();
            nickName.setLayoutX(h * 0.35);
            newsText.setLayoutX(h * 0.35);
            nickName.setLayoutY(h * 0.35);
            newsText.setLayoutY(h * 0.35);
            nickName.setFont(FontManagement.Roboto(h * 0.35));
            newsText.setFont(FontManagement.Roboto(h * 0.2));
        });
       
       VBox group2 = new VBox(title_bar,bulletin_boardPane,groupPane);
       
       HBox news_layout = new HBox(task_bar,group2);
       MainPane.setLeft(news_layout);
       MainStage.show();
    }
    private HBox newsLayout(){
        HBox newsLayout;
        
        
        newsLayout = new HBox();
        return newsLayout;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
