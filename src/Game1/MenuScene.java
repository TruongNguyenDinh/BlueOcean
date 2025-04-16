package Game1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;

public class MenuScene {
    public MenuScene(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        ComboBox<String> modeBox = new ComboBox<>();
        modeBox.getItems().addAll("Chơi 2 người", "Máy - Dễ", "Máy - Khó");
        modeBox.setValue("Chơi 2 người"); // mặc định

        Button startButton = new Button("Bắt đầu");
        Button exitButton = new Button("Thoát");

        // Xóa hết các nút cũ trước khi thêm mới
        root.getChildren().clear();
        
        // Thêm các thành phần vào VBox
        root.getChildren().addAll(modeBox, startButton, exitButton);

        startButton.setOnAction(e -> {
            String selected = modeBox.getValue();
            String mode; 
            switch (selected) {
                case "Máy - Dễ": mode = "EASY"; break;
                case "Máy - Khó": mode = "HARD"; break;
                default: mode = "PVP"; break;
            }
            new GameController(stage, mode);
        });

        // Thêm chức năng thoát ứng dụng
        exitButton.setOnAction(e -> Platform.exit());

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}