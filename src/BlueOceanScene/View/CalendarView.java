package BlueOceanScene.View;
import BlueOceanScene.Utils.ReminderPanel;
import MainForm.Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalendarView {
    private final ReminderPanel reminderPanel = new ReminderPanel();
    private final GridPane calendarGrid = new GridPane();
    private final ComboBox<Integer> monthBox = new ComboBox<>();
    private final ComboBox<Integer> yearBox = new ComboBox<>();
    private final LocalDate today = LocalDate.now();
    private double height,width;
    private Label label;
    private ReadOnlyDoubleProperty heightPro;
    private List<Label> weekdayLabels;
    
    
    public BorderPane createCalendar(ReadOnlyDoubleProperty heightpro,ReadOnlyDoubleProperty widthpro) {
//        width = heightProperty.get()*0.47;
//        height = heightProperty.get()*0.425;
        widthpro.addListener((obs, oldVal, newVal) -> {
            height = newVal.doubleValue()*0.425;
            updateWeekdayLabelFont();
        });
        widthpro.addListener((obs, oldVal, newVal) -> {
            width = newVal.doubleValue()*0.47;
            updateWeekdayLabelFont();
        });

        BorderPane root = new BorderPane();
        root.prefWidthProperty().bind(heightpro.multiply(0.425));
        root.prefHeightProperty().bind(widthpro.multiply(0.7*0.47));
        root.setPadding(new Insets(10));
        
        // Top - chọn tháng và năm
        for (int m = 1; m <= 12; m++) monthBox.getItems().add(m);
        monthBox.setValue(today.getMonthValue());
        monthBox.setFocusTraversable(false);

        for (int y = 1990; y <= 2100; y++) yearBox.getItems().add(y);
        yearBox.setValue(today.getYear());
        yearBox.setFocusTraversable(false);

        monthBox.setOnAction(e -> {
            updateCalendar();
        });
        yearBox.setOnAction(e -> updateCalendar());

        HBox top = new HBox(10, new Label("Tháng:"), monthBox, new Label("Năm:"), yearBox);
        top.setAlignment(Pos.CENTER);
        root.setTop(top);

        // Center - lưới lịch
        calendarGrid.setGridLinesVisible(true);
        calendarGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        calendarGrid.setAlignment(Pos.CENTER);
        root.setCenter(calendarGrid);

        updateCalendar();
        
        return root;
    }

    private void updateCalendar() {
        
        calendarGrid.getChildren().clear();
        
        String[] weekdays = {"Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
        weekdayLabels = new ArrayList<>();
        for (int i = 0; i < weekdays.length; i++) {
            label = new Label(weekdays[i]);
            label.setFont(Font.font(height*0.036));
            label.setPadding(new Insets(5));
            label.setAlignment(Pos.CENTER);
            calendarGrid.add(wrapInPane(label), i, 0);
             weekdayLabels.add(label);
        }

        Integer  month = monthBox.getValue();
        Integer year = yearBox.getValue();
        if (month == null || year == null) return;
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDay = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();

        int startDayOfWeek = firstDay.getDayOfWeek().getValue(); // T2 = 1

        int day = 1;
        int row = 1;

        // Fill blanks before first day
        for (int i = 1; i < startDayOfWeek; i++) {
            calendarGrid.add(new Pane(), i - 1, row);
        }

        // Fill days
        for (int col = startDayOfWeek - 1; col < 7; col++) {
            calendarGrid.add(createDayPane(day++, year, month), col, row);
        }
        row++;

        while (day <= daysInMonth) {
            for (int col = 0; col < 7; col++) {
                if (day > daysInMonth) break;
                calendarGrid.add(createDayPane(day++, year, month), col, row);
            }
            row++;
        }
    }

    private Pane createDayPane(int day, int year, int month) {
        Label label1 = new Label(String.valueOf(day));
        label1.setPadding(new Insets(5));
        label1.setAlignment(Pos.TOP_LEFT);
        label1.setFont(Font.font(13));

        LocalDate date = LocalDate.of(year, month, day);
        StackPane pane = (StackPane) wrapInPane(label1);

        // Kiểm tra nếu là ngày hiện tại
        if (date.equals(today)) {
            label1.setTextFill(Color.BLUE);
            label1.setFont(Font.font(null, Font.getDefault().getSize() + 1));
            pane.setStyle("-fx-border-color: lightgray; -fx-background-color: lightblue;"); // Tô màu nền
        }
        
        label1.setOnMouseClicked((MouseEvent e) -> {
            Stage newStage = new Stage();
//            VBox content = new VBox(new Label("Ngày đã chọn: " + date));
            BorderPane reminderPaneWithDate = reminderPanel.getReminderPane(date,User.getId()); 
            Scene newScene = new Scene(reminderPaneWithDate, 850, 300);
            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.setOnCloseRequest(close->{
                reminderPanel.stop();
            });
            newStage.setTitle("Thông tin ngày");
            newStage.show();
        });

    return pane;
}

    private Pane wrapInPane(Label label) {
        StackPane pane = new StackPane(label);
        pane.setPrefSize(60, 60);
        pane.setStyle("-fx-border-color: lightgray;");
        return pane;
    }
    private void updateWeekdayLabelFont() {
        if (weekdayLabels != null) {
            for (Label lbl : weekdayLabels) {
                lbl.setFont(Font.font(height * 0.035));
                lbl.setMaxWidth(Double.MAX_VALUE); // để nó co giãn đều
                lbl.setAlignment(Pos.CENTER);
            }
        }
    }
}
