/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author truon
 */
public class AnimationFx {
    private RotateTransition rotateTransition;
    public Label Timer(Label time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time.setText(LocalTime.now().format(formatter));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return time;
    }
    public Label greetingText(String content){
        Label greetingLable = new Label();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event->{
            int hour = LocalTime.now().getHour();
            String greeting;
            if (hour>=0 && hour<12){
                greeting = "Good Morning";
            }
            else if (hour>=12 && hour <18){
                greeting = "Good Afternoon";
            }   
            else {
                greeting = "Good Evening";
            }
            greetingLable.setText(greeting);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return greetingLable; 
    }
    public static ImageView createSlideshowAnimation(ImageView imageView, String[] imagePaths) {
        // Khởi tạo chỉ số hình ảnh trong phạm vi hàm
        final int[] currentImageIndex = {0}; // Sử dụng mảng để có thể thay đổi giá trị trong lambda
        
        // Load hình ảnh đầu tiên
        updateImage(imageView, imagePaths, currentImageIndex[0]);
        
        // Tạo hiệu ứng fade out
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), imageView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        
        // Tạo hiệu ứng fade in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), imageView);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        
        // Kết hợp các transition
        SequentialTransition sequence = new SequentialTransition(fadeOut, fadeIn);
        
        // Tạo Timeline để chuyển đổi hình ảnh
        Timeline timeline = new Timeline(new KeyFrame(
            Duration.seconds(5), // Thời gian hiển thị mỗi ảnh
            event -> {
                sequence.play();
                fadeOut.setOnFinished(e -> {
                    currentImageIndex[0] = (currentImageIndex[0] + 1) % imagePaths.length;
                    updateImage(imageView, imagePaths, currentImageIndex[0]);
                });
            }
        ));
        
        timeline.setCycleCount(Timeline.INDEFINITE); // Lặp vô hạn
        timeline.play();
        
        return imageView;
    }
    
    private static void updateImage(ImageView imageView, String[] imagePaths, int index) {
        Image image = new Image(imagePaths[index]);
        imageView.setImage(image);
    }
    public ImageView rotation(ImageView imageView,int direction) {
        rotateTransition = new RotateTransition(Duration.seconds(3), imageView);
        if (direction==0){
          rotateTransition.setByAngle(-360); // Xoay 360 độ  
        }
        else {
            rotateTransition.setByAngle(360); // Xoay 360 độ
        }
        
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Lặp vô hạn
        rotateTransition.setAxis(javafx.geometry.Point3D.ZERO.add(0, 0, 1)); // Xoay theo trục Z
        rotateTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);
        rotateTransition.setOnFinished(event -> {
        // Bạn có thể thêm mã xử lý sau khi vòng quay kết thúc nếu cần
            rotateTransition.play(); // Tiếp tục quay lại từ góc ban đầu
        });
        rotateTransition.play();
        
        return imageView;
    }

    // Hàm bắt đầu xoay ảnh
    public void startRotation() {
        rotateTransition.play();
    }

    // Hàm dừng xoay ảnh
    public void stopRotation() {
        rotateTransition.stop();
    }

    // Trả về ImageView để thêm vào giao diện
}
