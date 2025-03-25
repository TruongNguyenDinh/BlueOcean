/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;

class  AnimationFx {
   public AnimationFx(){}
   private double offset = 0;
   //Hiệu ứng nước chảy cho textlogo
   public void logoFx(Text content){
       Timeline timeline = new Timeline(
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
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
   }
}


