/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;
import javafx.geometry.Rectangle2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author truon
 */
public class MainScene extends Application {
    private static String Id;
    
    public static void setId(String id) {
        Id = id;
    }

    @Override
    public void start(Stage primaryStage) {
        openMainStage(Id);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void openMainStage(String id) {
        Id = id;  // Lưu Id
        
        // Tạo cửa sổ chính mới
        Stage mainStage = new Stage();
        Text idText = new Text("Chào, " + Id);
        idText.setLayoutX(10);
        idText.setY(12);
        idText.setX(0);
        Rectangle2D sc = Screen.getPrimary().getVisualBounds();
        double sh = sc.getHeight();
        double sw = sc.getWidth();Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            
        // Đặt kích thước cửa sổ theo kích thước có thể hiển thị
//        mainStage.setX(visualBounds.getMinX());
//        mainStage.setY(visualBounds.getMinY());
//        mainStage.setWidth(visualBounds.getWidth());
//        mainStage.setHeight(visualBounds.getHeight());
        System.out.print("sh: "+sh+" sw: "+sw);
        StackPane root = new StackPane(idText);
        Scene scene = new Scene(root, 720, 480);

        mainStage.setTitle("Chat App - " + Id);
        mainStage.setMaximized(true);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
