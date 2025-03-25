/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlueOceanScene;

/**
 *
 * @author truon
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneOptions extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chọn độ phân giải");

        // Tạo danh sách độ phân giải
        ComboBox<String> resolutionBox = new ComboBox<>();
        resolutionBox.getItems().addAll(
            "800 x 600", 
            "1024 x 768", 
            "1280 x 720", 
            "1920 x 1080", 
            "2560 x 1440", 
            "3840 x 2160"
        );

        // Xử lý khi chọn độ phân giải
        resolutionBox.setOnAction(event -> {
            String selectedResolution = resolutionBox.getValue();
            if (selectedResolution != null) {
                String[] parts = selectedResolution.split(" x ");
                int width = Integer.parseInt(parts[0]);
                int height = Integer.parseInt(parts[1]);
                primaryStage.setWidth(width);
                primaryStage.setHeight(height);
            }
        });

        // Giao diện
        VBox root = new VBox(10, resolutionBox);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
