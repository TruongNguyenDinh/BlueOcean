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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import Font.FontManagement;
import AlertPkg.AlertMain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

/**
 *
 * @author truon
 */
public class Main extends Application {

    private final NewsLayout newsLayout = new NewsLayout();
    private final AppsLayout appsLayout = new AppsLayout();
    private final InfoLayout infoLayout = new InfoLayout();
    public static int id;
    private int ID;
    private static String username, fullname, nickname, phone, address, email;
    private static boolean gender;
    private static LocalDateTime createdAt;
    private static boolean newsOpens = true, apps = true, setting = true, info = true, profileFg = true;
    private double width, height;
    private HBox title_bar;
    private Pane bulletin_boardPane;
    private VBox group2;
    private Stage MainStage,stage;
    private ReminderPanel reminderPanel;
    private final List<String> paths = new ArrayList<>();
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) {
        openMainStage(id, username, fullname, nickname, phone, address, gender, email, createdAt);
    }

    public void openMainStage(int id, String ussername, String fullname, String nickname, String phone, String address, boolean gender, String email, LocalDateTime dateTime) {
        reminderPanel = new ReminderPanel(id);
        System.out.print("\nset ID " + id + "\n");
        paths.add("Opening Game of Thrones--GameofThrones.mp3");
        paths.add("Goodbye--Ramsey.mp3");
        paths.add("Ocean--Wanderlust(2023).mp3");
        MediaMusic.playPlaylist(paths);
        MediaMusic.callBackgroundMusic();

        MainStage = new Stage();// Tạo stage chính
        setStage(MainStage);
        System.out.print(getStage());
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        MainStage.setX(bounds.getMinX());
        MainStage.setY(bounds.getMinY());
        MainStage.setWidth(bounds.getWidth());
        MainStage.setHeight(bounds.getHeight());
        MainStage.setMaximized(true); // Tối đa hóa cửa sổ
        MainStage.initStyle(StageStyle.UNDECORATED);
        BorderPane MainPane = new BorderPane(); // Tạo các Node Pane
        MainPane.setStyle("-fx-background-color: #c4dfe6");//Tạo màu nền
        Scene scene = new Scene(MainPane);//Tao scene
        MainStage.setTitle("Blue Ocean - " + id);
        MainStage.setMaximized(true);
        MainStage.setResizable(false);
        MainStage.setScene(scene);
        
        ////
        ImageView close = new ImageView(new Image("Image/close.png"));
        close.setPreserveRatio(true); //1
        ImageView appsIcon = new ImageView(new Image("Image/apps32.png"));
        appsIcon.setPreserveRatio(true);
        ImageView settingIcon = new ImageView(new Image("Image/setting.png"));
        appsIcon.setPreserveRatio(true);
        ImageView infoIcon = new ImageView(new Image("Image/info.png"));
        infoIcon.setPreserveRatio(true);
        ImageView newsIcon = new ImageView(new Image("Image/news.png"));
        infoIcon.setPreserveRatio(true);
        ImageView profileIcon = new ImageView(new Image("Image/person.png"));
        infoIcon.setPreserveRatio(true);
        ImageView logoutIcon = new ImageView(new Image("Image/logout.png"));
        infoIcon.setPreserveRatio(true);

        //Add layout
        HBox newslayout = new HBox(newsLayout.newsLayout(scene)); //newsLayout
        HBox appslayout = new HBox(appsLayout.appsLayout(scene));
        HBox infolayout = new HBox(infoLayout.infoLayout(scene));
        HBox settinglayout = new HBox(SettingLayout.settinglayout(scene));
        HBox profileLayout = new HBox(ProfileLayout.profilelayout(scene));
        //Remain Pane
        Text labelNews = new Text("News");
        labelNews.setFill(Color.WHITE);
        Rectangle bgLogo = new Rectangle();
        bgLogo.setFill(Color.web("003B46"));
        Rectangle end_taskbar = new Rectangle();
        end_taskbar.setFill(Color.web("003B46"));
        Button newsBtn = new Button(labelNews.getText());
        newsBtn.getStyleClass().add("task3button");
        Button appsBtn = new Button("Apps");
        appsBtn.getStyleClass().add("task3button");
        Button infoBtn = new Button("Info");
        infoBtn.getStyleClass().add("task3button");
        Button settingBtn = new Button("Setting");
        settingBtn.getStyleClass().add("task3button");
        Button profile = new Button("Your Profile");
        profile.getStyleClass().add("task3button");
        Button logoutBtn = new Button("Log out");
        logoutBtn.getStyleClass().add("task3button");

        // Tắt focus
        newsBtn.setFocusTraversable(false);
        appsBtn.setFocusTraversable(false);
        infoBtn.setFocusTraversable(false);
        settingBtn.setFocusTraversable(false);
        logoutBtn.setFocusTraversable(false);
        profile.setFocusTraversable(false);
        Rectangle main_title_bar = new Rectangle();
        main_title_bar.setFill(Color.AQUA);
        Rectangle sub_title_bar = new Rectangle();
        sub_title_bar.setFill(Color.AQUAMARINE);
        Rectangle title_bar_close = new Rectangle();
        title_bar_close.setFill(Color.BEIGE);
        // Phân vùng 2_2 
        Rectangle bulletin_board = new Rectangle();
        bulletin_board.setFill(Color.WHITESMOKE);

        // Thiet lap vi tri chieu rong
        bgLogo.widthProperty().bind(scene.widthProperty().multiply(0.15));
        newsBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        appsBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        infoBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        settingBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        profile.prefWidthProperty().bind(bgLogo.widthProperty());
        logoutBtn.prefWidthProperty().bind(bgLogo.widthProperty());
        end_taskbar.widthProperty().bind(bgLogo.widthProperty());
        // title+bulletin_board
        main_title_bar.widthProperty().bind(scene.widthProperty().multiply(0.5));
        sub_title_bar.widthProperty().bind(scene.widthProperty().multiply(0.25));
        title_bar_close.widthProperty().bind(scene.widthProperty().multiply(0.05));
        bulletin_board.widthProperty().bind(scene.widthProperty().multiply(0.9));
        // Thiet lap vi tri chieu dai
        // Task3
        bgLogo.heightProperty().bind(scene.heightProperty().multiply(0.1));
        newsBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        appsBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        infoBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        settingBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        profile.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        logoutBtn.prefHeightProperty().bind(scene.heightProperty().multiply(0.06));
        end_taskbar.heightProperty().bind(scene.heightProperty().multiply(0.54));
        // title+bulltin_board
        main_title_bar.heightProperty().bind(scene.heightProperty().multiply(0.03));
        sub_title_bar.heightProperty().bind(main_title_bar.heightProperty());
        title_bar_close.heightProperty().bind(main_title_bar.heightProperty());
        bulletin_board.heightProperty().bind(scene.heightProperty().multiply(0.1));

        //Bám scene
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            System.out.print("Height scene\n" + newHeight.doubleValue());
            height = newHeight.doubleValue(); //
            bulletin_boardPane.setTranslateY(height * 0.005);
            newslayout.setTranslateY(height * 0.008);
            //

        });
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            System.out.print("Width scene\n" + newWidth.doubleValue());
            width = newWidth.doubleValue();//
            title_bar.setTranslateX(width * 0.05);
            bulletin_boardPane.setTranslateX(width * 0.005);

        });

        Text newsText = new Text("HELLO ");
        newsText.setFill(Color.CORNFLOWERBLUE);
        Label nickName = new Label(fullname);
        nickName.setTextFill(Color.CORAL);
        //Pane Button
        Pane logoBackground = new Pane(bgLogo);
        Pane newsPane = new Pane(newsBtn, newsIcon);
        newsPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            newsIcon.setFitWidth(newVal.doubleValue() * 0.08);
            newsIcon.setTranslateX(newVal.doubleValue() * 0.15);
        });
        newsPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            newsIcon.setFitHeight(newVal.doubleValue() * 0.34);
            newsIcon.setTranslateY(newVal.doubleValue() * 0.3);
        });
        Pane infoPane = new Pane(infoBtn, infoIcon);
        infoPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            infoIcon.setFitWidth(newVal.doubleValue() * 0.08);
            infoIcon.setTranslateX(newVal.doubleValue() * 0.15);
        });
        infoPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            infoIcon.setFitHeight(newVal.doubleValue() * 0.34);
            infoIcon.setTranslateY(newVal.doubleValue() * 0.3);
        });
        //
        Pane appsPane = new Pane(appsBtn, appsIcon);
        appsPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            appsIcon.setFitWidth(newVal.doubleValue() * 0.1);
            appsIcon.setTranslateX(newVal.doubleValue() * 0.15);
        });
        appsPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            appsIcon.setFitHeight(newVal.doubleValue() * 0.34);
            appsIcon.setTranslateY(newVal.doubleValue() * 0.3);
        });
        Pane settingPane = new Pane(settingBtn, settingIcon);
        settingPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            settingIcon.setFitWidth(newVal.doubleValue() * 0.08);
            settingIcon.setTranslateX(newVal.doubleValue() * 0.15);
        });
        settingPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            settingIcon.setFitHeight(newVal.doubleValue() * 0.34);
            settingIcon.setTranslateY(newVal.doubleValue() * 0.3);
        });
        Pane profilePane = new Pane(profile, profileIcon);
        profilePane.widthProperty().addListener((obs, oldVal, newVal) -> {
            profileIcon.setFitWidth(newVal.doubleValue() * 0.1);
            profileIcon.setTranslateX(newVal.doubleValue() * 0.13);
        });
        profilePane.heightProperty().addListener((obs, oldVal, newVal) -> {
            profileIcon.setFitHeight(newVal.doubleValue() * 0.45);
            profileIcon.setTranslateY(newVal.doubleValue() * 0.3);
        });
        Pane logoutPane = new Pane(logoutBtn, logoutIcon);
        logoutPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            logoutIcon.setFitWidth(newVal.doubleValue() * 0.08);
            logoutIcon.setTranslateX(newVal.doubleValue() * 0.15);
        });
        logoutPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            logoutIcon.setFitHeight(newVal.doubleValue() * 0.35);
            logoutIcon.setTranslateY(newVal.doubleValue() * 0.3);
        });
        VBox task_bar = new VBox(logoBackground, newsPane, appsPane, infoPane, settingPane, profilePane, logoutPane, end_taskbar);//gom task3

        //Title
        Label mousePosition = new Label();
        mousePosition.setTextFill(Color.CRIMSON);
        mousePosition.setTranslateY(4);
        mousePosition.setTranslateX(2);
        scene.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
            double x = e.getSceneX();
            double y = e.getSceneY();
            mousePosition.setText("X: " + x + " | " + "Y: " + y);
        });
        Pane maintt = new Pane(main_title_bar, mousePosition);

        Label nickNameLabel = new Label("Nickname: " + nickname);
        nickNameLabel.setTranslateX(2);
        nickNameLabel.setTranslateY(2);
        Label idLabel = new Label("ID: " + id);
        idLabel.setTranslateX(200);
        idLabel.setTranslateY(2);
        Pane sub_maintt = new Pane(sub_title_bar, nickNameLabel, idLabel);
        Pane maintt_close = new Pane(title_bar_close, close);
        maintt_close.widthProperty().addListener((obs, oldVal, newVal) -> {
            close.setTranslateX(newVal.doubleValue() * 0.4);
            close.setFitWidth(newVal.doubleValue() * 0.7);
        });
        maintt_close.heightProperty().addListener((obs, oldVal, newVal) -> {
            close.setLayoutY(newVal.doubleValue() * 0.1);
            close.setFitHeight(newVal.doubleValue() * 0.7);
        });
        maintt_close.setOnMouseClicked(e -> {
            AlertMain.checkLogOut(false, MainStage, Alert.AlertType.WARNING, "Chúng tôi sẽ nhớ bạn đấy :(", "Thoát", "Bạn có muốn thoát ứng dụng không?");

        });
        title_bar = new HBox(maintt, sub_maintt, maintt_close);

        //bulletin_board
        bulletin_boardPane = new Pane(bulletin_board, newsText, nickName);//gom bulletin_board
        // Bám 
        bulletin_boardPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            nickName.setLayoutX(bulletin_boardPane.getHeight() * 0.35);
            newsText.setLayoutX(bulletin_boardPane.getHeight() * 0.35);
        });
        bulletin_boardPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            nickName.setLayoutY(bulletin_boardPane.getHeight() * 0.35);
            newsText.setLayoutY(bulletin_boardPane.getHeight() * 0.35);
            nickName.setFont(FontManagement.Roboto(bulletin_boardPane.getHeight() * 0.35));
            newsText.setFont(FontManagement.Roboto(bulletin_boardPane.getHeight() * 0.2));
        });
        Group fakeGroup = new Group(newslayout, appslayout);
        fakeGroup.setVisible(false); // không hiển thị thật
        Platform.runLater(() -> {
            fakeGroup.applyCss();
            fakeGroup.layout();
            double h = bulletin_boardPane.getHeight();
            nickName.setLayoutX(h * 0.35);
            newsText.setLayoutX(h * 0.35);
            nickName.setLayoutY(h * 0.35);
            newsText.setLayoutY(h * 0.35);
            nickName.setFont(FontManagement.Roboto(h * 0.35));
            newsText.setFont(FontManagement.Roboto(h * 0.2));
        });
        HBox news_layout = new HBox(task_bar);
        group2 = new VBox(title_bar, bulletin_boardPane, newslayout);
        news_layout.getChildren().add(group2);

        newsBtn.getStyleClass().add("task3buttonisON");
        ////
        newsBtn.setOnMouseClicked(e -> {
            appsBtn.getStyleClass().remove("task3buttonisON");
            infoBtn.getStyleClass().remove("task3buttonisON");
            settingBtn.getStyleClass().remove("task3buttonisON");
            profile.getStyleClass().remove("task3buttonisON");
            apps = true;
            info = true;
            setting = true;
            profileFg = true;
            if (newsOpens) {
                newsBtn.getStyleClass().add("task3buttonisON");
            }

            newsOpens = false;
            news_layout.getChildren().remove(group2); // Xóa group2 cũ
            group2 = new VBox(title_bar, bulletin_boardPane, newslayout); // Tạo group2 mới
            news_layout.getChildren().add(group2); // Thêm lại group2 mới
        });

        // Khi click nút appsBtn
        appsBtn.setOnMouseClicked(e -> {
            newsBtn.getStyleClass().remove("task3buttonisON");
            infoBtn.getStyleClass().remove("task3buttonisON");
            settingBtn.getStyleClass().remove("task3buttonisON");
            profile.getStyleClass().remove("task3buttonisON");
            setting = true;
            newsOpens = true;
            info = true;
            profileFg = true;

            if (apps) {
                appsBtn.getStyleClass().add("task3buttonisON");
            }
            apps = false;
            news_layout.getChildren().remove(group2); // Xóa group2 cũ
            group2 = new VBox(title_bar, bulletin_boardPane, appslayout); // Tạo group2 mới
            news_layout.getChildren().add(group2); // Thêm lại group2 mới
        });
        infoBtn.setOnMouseClicked(e -> {
            newsBtn.getStyleClass().remove("task3buttonisON");
            appsBtn.getStyleClass().remove("task3buttonisON");
            settingBtn.getStyleClass().remove("task3buttonisON");
            profile.getStyleClass().remove("task3buttonisON");
            newsOpens = true;
            apps = true;
            setting = true;
            profileFg = true;
            if (info) {
                infoBtn.getStyleClass().add("task3buttonisON");
            }
            info = false;
            news_layout.getChildren().remove(group2); // Xóa group2 cũ
            group2 = new VBox(title_bar, bulletin_boardPane, infolayout); // Tạo group2 mới
            news_layout.getChildren().add(group2); // Thêm lại group2 mới
        });
        settingBtn.setOnMouseClicked(e -> {
            newsBtn.getStyleClass().remove("task3buttonisON");
            infoBtn.getStyleClass().remove("task3buttonisON");
            appsBtn.getStyleClass().remove("task3buttonisON");
            profile.getStyleClass().remove("task3buttonisON");
            newsOpens = true;
            apps = true;
            info = true;
            profileFg = true;
            if (setting) {
                settingBtn.getStyleClass().add("task3buttonisON");
            }
            setting = false;
            news_layout.getChildren().remove(group2); // Xóa group2 cũ
            group2 = new VBox(title_bar, bulletin_boardPane, settinglayout); // Tạo group2 mới
            news_layout.getChildren().add(group2); // Thêm lại group2 mới
        });
        profile.setOnMouseClicked(e -> {
            newsBtn.getStyleClass().remove("task3buttonisON");
            infoBtn.getStyleClass().remove("task3buttonisON");
            appsBtn.getStyleClass().remove("task3buttonisON");
            settingBtn.getStyleClass().remove("task3buttonisON");
            newsOpens = true;
            apps = true;
            info = true;
            setting = true;
            if (profileFg) {
                profile.getStyleClass().add("task3buttonisON");
            }
            profileFg = false;
            news_layout.getChildren().remove(group2); // Xóa group2 cũ
            group2 = new VBox(title_bar, bulletin_boardPane, profileLayout); // Tạo group2 mới
            news_layout.getChildren().add(group2); // Thêm lại group2 mới
        });
        logoutBtn.setOnMouseClicked(e -> {
            AlertMain.checkLogOut(true, MainStage, Alert.AlertType.WARNING, "Chúng tôi sẽ nhớ bạn đấy :(", "Đăng xuất", "Bạn có muốn đăng xuất ứng dụng không?");

        });
        scene.getStylesheets().add(getClass().getResource("../CSS/Style.css").toExternalForm());
        MainPane.setLeft(news_layout);
        MainStage.show();
    }

    public void recreateStage(StageStyle style) {
        Platform.runLater(() -> {
            Stage oldStage = this.MainStage;
            Scene oldScene = oldStage.getScene();

            Stage newStage = new Stage();
            newStage.initStyle(style);
            newStage.setScene(oldScene);
            newStage.setTitle("Ứng dụng của bạn");

            newStage.show();
            oldStage.close();

            this.MainStage = newStage;
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
