package Game1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

public class MenuScene {
    public MenuScene(Stage stage) {
        StackPane root = new StackPane();

        // Ảnh nền làm mờ
        URL imageUrl = getClass().getResource("/Game1/hinhnen.png");
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(500);
            imageView.setFitHeight(500);
            imageView.setPreserveRatio(false);
            imageView.setEffect(new GaussianBlur(20));
            root.getChildren().add(imageView);
        } else {
            System.out.println("Không tìm thấy ảnh nền");
        }

        // Overlay tối nhẹ
        Rectangle overlay = new Rectangle(500, 500, Color.rgb(0, 0, 0, 0.4));
        root.getChildren().add(overlay);

        // Load font an toàn
        Font font;
        try {
            font = Font.loadFont(getClass().getResourceAsStream("/Game1/fonts/Boldonse-Regular.ttf"), 15);
            if (font == null) throw new Exception("Font null");
        } catch (Exception e) {
//            System.out.println("Lỗi font, dùng mặc định: " + e.getMessage());
            font = Font.font("Roboto", 10);
        }

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        Label title = new Label("SELECT LEVEL");
        title.setFont(font);
        title.setTextFill(Color.WHITE);
        title.setEffect(new DropShadow(10, Color.CYAN));

        // ComboBox chế độ chơi
        ComboBox<String> modeBox = new ComboBox<>();
        modeBox.getItems().addAll("PLAY 2P", "AI - EASY", "AI - HARD");
        modeBox.setValue("PLAY 2P");
        modeBox.setStyle("-fx-font-family: 'Roboto'; -fx-font-size: 18px; -fx-background-color: aqua;");

        // 👉 Căn giữa chữ đang hiển thị
        modeBox.setButtonCell(new ListCell<>() {
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(item);
            setAlignment(Pos.CENTER); // căn giữa nội dung
        }
    }
});

    // 👉 Căn giữa các item trong danh sách
    modeBox.setCellFactory(listView -> new ListCell<>() {
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(item);
            setAlignment(Pos.CENTER); // căn giữa nội dung
        }
    }
});
        modeBox.setOnMouseEntered(e -> {
            modeBox.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-background-color: rgba(255,255,255,0.1);" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: white;"
            );
        });
        
        modeBox.setOnMouseExited(e -> {
            modeBox.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-background-color: rgba(255,255,255,0.72);" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: white;"
            );
        });
        
        DropShadow comboGlow = new DropShadow();
        comboGlow.setColor(Color.CYAN);
        comboGlow.setRadius(20);
        comboGlow.setSpread(0.5);
        modeBox.setEffect(comboGlow);
        modeBox.setPrefWidth(200);

        // Nút chơi
        Button playBtn = createNeonButton("START", "#00ffff", font);
        Button exitBtn = createNeonButton("EXIT", "#ff4d4d", font);

        playBtn.setOnAction(e -> {
            String selected = modeBox.getValue();
            String mode;
            switch (selected) {
                case "AI - EASY" -> mode = "EASY";
                case "AI - HARD" -> mode = "HARD";
                default -> mode = "PVP";
            }
            new GameController(stage, mode);
        });

        exitBtn.setOnAction(e -> stage.close());

        vbox.getChildren().addAll(title, modeBox, playBtn, exitBtn);
        root.getChildren().add(vbox);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Menu Game");
        stage.setScene(scene);
        stage.show();
    }

    private Button createNeonButton(String text, String glowColor, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 15; -fx-background-radius: 15; -fx-padding: 10 20;");
        DropShadow neonGlow = new DropShadow();
        neonGlow.setColor(Color.web(glowColor));
        neonGlow.setRadius(20);
        neonGlow.setSpread(0.6);
        button.setEffect(neonGlow);

        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-border-color: white; -fx-border-radius: 15;");
            button.setScaleX(1.05);
            button.setScaleY(1.05);
        });

        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 15;");
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });

        return button;
    }
}