/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

/**
 *
 * @author truon
 */
import javafx.scene.chart.PieChart;
public class TimeOpenPieChart {
    public  PieChart createPieChart(){
        PieChart chart = new PieChart();
        chart.setTitle("Tổng thời gian dùng hôm nay");
        chart.setLabelLineLength(10); // Thiết lập đường kẻ từ biểu đồ đến nhãn
        chart.setLegendSide(javafx.geometry.Side.RIGHT); // Đặt vị trí chú thích bên phải biểu đồ
        chart.setLabelsVisible(true); // Cho phép hiển thị nhãn
        return chart;
    }
    
}
