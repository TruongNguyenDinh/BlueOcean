package GalleryFactory;
import java.awt.Desktop;
import java.net.URI;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class GalleryItemFactory {

    public static VBox createGalleryItem(String imagePath, String labelText,String linkPath, ReadOnlyDoubleProperty parentWidth,
                                double height) {
        // Tạo ImageView
         // Tạo ImageView
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(parentWidth.multiply(1));
        imageView.fitHeightProperty().bind(parentWidth.multiply(height)); // 60% heightRatio

        // Tạo Text và bao bằng StackPane để căn giữa
        Text text = new Text(labelText);
        text.setTextAlignment(TextAlignment.CENTER);
        text.wrappingWidthProperty().bind(parentWidth.multiply(1));

        StackPane textContainer = new StackPane(text);
        textContainer.setAlignment(Pos.CENTER);
        textContainer.prefWidthProperty().bind(parentWidth);

        // Tạo VBox chứa ảnh và text
        VBox vbox = new VBox(5); // spacing giữa ảnh và text
        vbox.setAlignment(Pos.CENTER);
        vbox.setFillWidth(true);

        vbox.prefWidthProperty().bind(parentWidth);
        vbox.prefHeightProperty().bind(parentWidth.multiply(height));
        vbox.setMaxWidth(Double.MAX_VALUE); 
        vbox.getChildren().addAll(imageView, textContainer);

        vbox.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URI(linkPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Bạn đã click vào: " + labelText);
            // Thêm hành động khác ở đây nếu cần
        });
        return vbox;
    }
}
