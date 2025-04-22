/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Utils;

import MainForm.Controllers.RegistorController;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class  AnimationFx {
    private Timeline logoTimeline;
   public AnimationFx(){}
   private double offset = 0;
   //Hiệu ứng nước chảy cho textlogo
   public void logoFx(Text content){
        logoTimeline = new Timeline(
            new KeyFrame(javafx.util.Duration.millis(50), e -> {
                offset += 0.005; // Dịch chuyển màu từ từ để hiệu ứng mượt
                if (offset > 5) offset = 0; // Reset vị trí nếu chạy hết vòng
                content.setFill(new LinearGradient(
                    offset, 0, offset + 0.3, 0, true, CycleMethod.REFLECT, 
                    new Stop(0, Color.web("012a4a")),  
                    new Stop(0.1, Color.web("013a63")), 
                    new Stop(0.2, Color.web("01497c")), 
                    new Stop(0.3, Color.web("014f86")),
                    new Stop(0.4, Color.web("2a6f97")), 
                    new Stop(0.5, Color.web("2c7da0")),
                    new Stop(0.6, Color.web("468faf")), 
                    new Stop(0.7, Color.web("61a5c2")),
                    new Stop(0.8, Color.web("89c2d9"))
                ));
            })
        );
        logoTimeline.setCycleCount(Timeline.INDEFINITE);
        logoTimeline.play();
   }
    public void stopLogoFx() {
        if (logoTimeline != null) {
            logoTimeline.stop(); // Dừng timeline
            logoTimeline.getKeyFrames().clear(); // Xóa keyframes nếu muốn cleanup
            logoTimeline = null; // Giải phóng biến nếu không dùng nữa
        }
    }

   public void registerFx(AtomicBoolean  finished,Text content,Node...nodesToHide){
        RegistorController.Visiable(finished.get(), nodesToHide);
        TranslateTransition moveText = new TranslateTransition(Duration.seconds(2), content);
        moveText.setFromY(0);
        moveText.setToY(-200);
        moveText.setCycleCount(1);
        moveText.setAutoReverse(false);
        moveText.play();
        moveText.setOnFinished(e ->{
            finished.set(true);
            RegistorController.Visiable(finished.get(), nodesToHide);
            moveText.setOnFinished(null);
        });
   }
   public void errorsShow(Text text){
    text.setVisible(false);
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),event->{
        text.setVisible(true);
    }));
    timeline.setCycleCount(3);
    timeline.play(); 
    timeline.setOnFinished(e -> {
        timeline.stop(); 
    });
   }
}


