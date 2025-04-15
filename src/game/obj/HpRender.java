package game.obj; 

  

import javafx.scene.canvas.GraphicsContext; 

import javafx.scene.paint.Color; 

  

public class HpRender { 

  

    final HP hp; 

  

    public HpRender(HP hp) { 

        this.hp = hp; 

    } 

  

    protected void hpRender(GraphicsContext gc, double x, double y, double width) { 

        if (hp.getCurrentHp() > 0) { // Chỉ vẽ thanh máu nếu HP lớn hơn 0 

            double hpY = y - 8; // Đặt thanh máu phía trên đối tượng 

            double hpHeight = 5; // Chiều cao của thanh máu 

  

            // Vẽ nền của thanh máu 

            gc.setFill(Color.rgb(50, 50, 50)); // Màu nền (HP mất) 

            gc.fillRect(x, hpY, width, hpHeight); 

  

            // Vẽ phần HP còn lại 

            gc.setFill(Color.rgb(0, 200, 0)); // Màu HP còn lại (xanh lá) 

            double hpSize = (hp.getCurrentHp() / hp.getMAX_HP()) * width; 

            gc.fillRect(x, hpY, hpSize, hpHeight); 

  

            // Vẽ viền cho thanh máu (tùy chọn, để làm nổi bật) 

            gc.setStroke(Color.BLACK); 

            gc.strokeRect(x, hpY, width, hpHeight); 

        } 

    } 

  

    public boolean updateHP(double cutHP) { 

        hp.setCurrentHp(hp.getCurrentHp() - cutHP); 

        return hp.getCurrentHp() > 0; 

    } 

  

    public double getHP() { 

        return hp.getCurrentHp(); 

    } 

  

    public void resetHP() { 

        hp.setCurrentHp(hp.getMAX_HP()); 

    } 

} 

