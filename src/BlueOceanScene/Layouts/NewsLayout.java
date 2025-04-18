/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.Layouts;

import BlueOceanScene.Utils.AnimationFx;
import BlueOceanScene.Utils.MediaMusic;
import BlueOceanScene.Utils.QuoteSlideshow;
import BlueOceanScene.Utils.ReminderPanel;
import BlueOceanScene.View.CalendarView;
import Font.FontManagement;
import GalleryFactory.GalleryItemFactory;
import MainForm.Models.User;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author truon
 */
public class NewsLayout {
    private double height,width;
    private HBox groupPane,news_friend;
    private Pane imgEventPane,infoClientPane,friendPane,timePane,bg_music_clientPane,newsListPane;
    private VBox img_info,overralPane,galleryContainer,notion_friend;
    private ImageView imageView;
    private final AnimationFx fx = new AnimationFx();
    private Label timer;
    private ScrollPane scrollPane ;
    double itemHeight; 
    private ReminderPanel rm;
    private final QuoteSlideshow quoteSlideshow = new QuoteSlideshow();
    private final ScrollPane scrollPaneNotion = new ScrollPane();
    private CalendarView calendarView;
    private BorderPane calendarUI;
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
    public HBox newsLayout(Scene scene){
        imageView = new ImageView(new Image("Image/1.jpeg"));
        galleryContainer = new VBox(10);
        timer = new Label();fx.Timer(timer);
        timer.setTextFill(Color.CADETBLUE);
        Label greeting = fx.greetingText("");
        greeting.setTextFill(Color.BLUEVIOLET);
        HBox newsLayout;
        Rectangle bg_img_slide = new Rectangle();bg_img_slide.setFill(Color.WHITESMOKE);
        Rectangle img_slide = new Rectangle();
        img_slide.setFill(Color.BEIGE);
        Rectangle info_client = new Rectangle();info_client.setFill(Color.WHITESMOKE);
        Rectangle bg_quote_client = new Rectangle();bg_quote_client.setFill(Color.web("66b5ad"));
        Rectangle bg_info_client = new Rectangle();bg_info_client.setFill(Color.web("66a5ad"));
        Rectangle bg_music_client = new Rectangle();bg_music_client.setFill(Color.web("66A5AD"));
        // Phân vùng 2_3:2
        ImageView afternoon = new ImageView(new Image("Image/Day/morning.jpg"));
        Rectangle date_time = new Rectangle();date_time.setFill(Color.WHITESMOKE);
        Rectangle friend_list = new Rectangle(); friend_list.setFill(Color.WHITESMOKE);
        Rectangle task_list = new Rectangle(); task_list.setFill(Color.WHITESMOKE); 
        Rectangle news_list = new Rectangle();news_list.setFill(Color.WHITESMOKE);
        
        calendarView = new CalendarView();
        calendarUI = calendarView.createCalendar(scene.heightProperty(),date_time.widthProperty());
        
        ///Text
       Label music_backgoundText = new Label("You're listening: " ); // 1
       Label current_music = new Label(MediaMusic.getCurrentSongName());
       current_music.setWrapText(true);
       current_music.setMaxWidth(300);
       MediaMusic.setLabel(current_music);
       current_music.setLayoutY(2);
       current_music.setTextFill(Color.ROYALBLUE);
       
       //img
       ImageView cd = new ImageView(new Image("Image/cd.png")); //1
       cd.setPreserveRatio(true); //1
       cd.fitWidthProperty().bind(Bindings.multiply(scene.widthProperty(), 0.08));
       cd.fitHeightProperty().bind(Bindings.multiply(scene.heightProperty(), 0.08));
       cd.setRotate(50);  
       fx.rotation(cd,1);
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
            MediaMusic.stopMusic();
       });
       pause.setOnMouseClicked(e->{
            play.setVisible(true);
            pause.setVisible(false);
            fx.startRotation();
            MediaMusic.playMusic();
       });
       
       bg_img_slide.widthProperty().bind(scene.widthProperty().multiply(0.35));
       img_slide.widthProperty().bind(scene.widthProperty().multiply(0.25));
       info_client.widthProperty().bind(bg_img_slide.widthProperty());
       bg_info_client.widthProperty().bind(scene.widthProperty().multiply(0.33));
       bg_quote_client.widthProperty().bind(bg_info_client.widthProperty().multiply(1));
       bg_music_client.widthProperty().bind(bg_info_client.widthProperty());
       date_time.widthProperty().bind(scene.widthProperty().multiply(0.524));
       afternoon.fitWidthProperty().bind(scene.widthProperty().multiply(0.5)); /////////////////////////
       friend_list.widthProperty().bind(date_time.widthProperty().multiply(0.47));
       scrollPaneNotion.prefWidthProperty().bind(date_time.widthProperty().multiply(0.47));
       news_list.widthProperty().bind(date_time.widthProperty().multiply(0.47));
       
       
       bg_img_slide.heightProperty().bind(scene.heightProperty().multiply(0.46));
       img_slide.heightProperty().bind(scene.heightProperty().multiply(0.3));
       info_client.heightProperty().bind(scene.heightProperty().multiply(0.38));
       bg_info_client.heightProperty().bind(scene.heightProperty().multiply(0.23));
       bg_quote_client.heightProperty().bind(bg_info_client.heightProperty().multiply(1));
       bg_music_client.heightProperty().bind(scene.heightProperty().multiply(0.11));
       date_time.heightProperty().bind(scene.heightProperty().multiply(0.1));
       afternoon.fitHeightProperty().bind(scene.heightProperty().multiply(0.1));
       friend_list.heightProperty().bind(scene.heightProperty().multiply(0.425));
       scrollPaneNotion.prefHeightProperty().bind(scene.heightProperty().multiply(0.315));
       news_list.heightProperty().bind(scene.heightProperty().multiply(0.74));
       
       scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            img_info.setTranslateY(height*0.03);
            infoClientPane.setTranslateY(height*0.007);
            news_friend.setTranslateY(height*0.007);
            overralPane.setTranslateY(height*0.03);
       });
       
       scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            width = newWidth.doubleValue();
            img_info.setTranslateX(width*0.005);
            notion_friend.setTranslateX(width*0.003);
            overralPane.setTranslateX(width*0.007);
           
       });
       
       //Calendar 
//        CalendarView calendarView = new CalendarView();
//        BorderPane calendarUI = calendarView.createCalendar(friend_list.heightProperty());
        System.out.print(imageView);
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
        bg_music_clientPane = new Pane(bg_music_client,music_backgoundText,cd,controlPane,current_music);
        bg_music_clientPane.widthProperty().addListener((obs,oldVal,newVal)->{
            music_backgoundText.setLayoutX(newVal.doubleValue()*0.03);
            current_music.setLayoutX(newVal.doubleValue()*0.3);
            controlPane.setLayoutX(newVal.doubleValue()*0.3);
        });
        bg_music_clientPane.heightProperty().addListener((obs,oldVal,newVal)->{
            music_backgoundText.setFont(FontManagement.Roboto(bg_music_clientPane.getHeight() * 0.2));
            current_music.setFont(FontManagement.Roboto(bg_music_clientPane.getHeight() * 0.2));
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
       
       img_info = new VBox();
        img_info.setSpacing(3); // Khoảng cách 10 pixel giữa các phần tử
        img_info.getChildren().addAll(imgEventPane, infoClientPane);
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
       rm = new ReminderPanel();
       VBox notionVBox = rm.Notion(User.getId());

        scrollPaneNotion.setContent(notionVBox);
        scrollPaneNotion.setFitToWidth(true);
        scrollPaneNotion.setFitToHeight(true);


       scrollPaneNotion.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
       Pane taskPane = new Pane(scrollPaneNotion);
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
       news.setTextFill(Color.SADDLEBROWN);
       
       newsListPane = new Pane(news_list,news);
       newsListPane.heightProperty().addListener((obs,oldVal,newVal)->{
           news.setFont(Font.font(newVal.doubleValue()*0.045));
           
       });
       newsListPane.widthProperty().addListener((obs,oldVal,newVal)->{
           news.setLayoutX(newVal.doubleValue()*0.4);
       });
                   
//       // thiết lập friend cùng ngang với news
       news_friend = new HBox(newsListPane,notion_friend);
       // thiết lập time nằm trên khối friend + time
       overralPane = new VBox();
        overralPane.setSpacing(3); // Khoảng cách 10 pixel giữa các phần tử
        overralPane.getChildren().addAll(timePane, news_friend);
       
       groupPane = new HBox(img_info,overralPane);
//       bulletin_boardPane.setStyle("-fx-border-color: red;");    
       
        Platform.runLater(() -> {
            
            StackPane calendarUIPane = new StackPane(calendarUI);
            calendarUIPane.setLayoutX(0);
            calendarUIPane.setLayoutY(0);
            friendPane.getChildren().add(calendarUIPane);
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
            
            
        });
       newsLayout = new HBox(groupPane);
        return newsLayout;
    }
   
    
}
