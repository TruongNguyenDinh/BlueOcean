/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.Utils;
import LanguagePackage.LanguageManager;
import MainForm.Models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.awt.Toolkit;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.cell.CheckBoxTableCell;
import javax.sound.sampled.*;
import javafx.util.Duration;

import MainForm.Utils.DatabaseHelper;
/**
 *
 * @author truon
 */
public class ReminderPanel {
    private static final ObservableList<Reminder> reminders = FXCollections.observableArrayList();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
    private Timer timer;
    private String msg;
    private int id;

    private boolean remindersChanged= false;
            private static void sortRemindersByTime() {
            FXCollections.sort(reminders, Comparator.comparing(Reminder::getTime));
        }
    public ReminderPanel(int id){
        this.id = id;
    }

    // Lớp Reminder giữ nguyên
    public static class Reminder {
        private final SimpleStringProperty message;
        private final LocalDateTime time;
        private BooleanProperty selected; 
        private boolean isModified;
        private int id;

        public Reminder(String message, LocalDateTime time) {
            this.message = new SimpleStringProperty(message);
            this.time = time;
            this.selected = new SimpleBooleanProperty(false);
        }
        public Reminder(String message, LocalDateTime time,int id) {
            this.message = new SimpleStringProperty(message);
            this.time = time;
            this.selected = new SimpleBooleanProperty(false);
            this.id = id;
            this.isModified = false; 
            this.message.addListener((obs,oldVal,newVal)->{
                if(!newVal.equals(oldVal)){
                    setModified(true);
                }
            });
        }
        
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        
        public String getMessage() {
            return message.get();
        }
        
        public BooleanProperty selectedProperty() {
            return selected;
        }

        public LocalDateTime getTime() {
            return time;
        }
        public boolean isModified() {
            return isModified;
        }
        private void setModified(boolean modified) {
            this.isModified = modified;
        }
        public String toFileString() {
            return getMessage() + "||" + time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

        public static Reminder fromFileString(String line) {
            String[] parts = line.split("\\|\\|");
            return new Reminder(parts[0], LocalDateTime.parse(parts[1]));
        }
    }

    // Constructor
    public ReminderPanel() {
        startTimer(); // Bắt đầu timer khi khởi tạo
    }

    // Phương thức trả về giao diện ReminderPanel
    public BorderPane getReminderPane(LocalDate selectedDate,int id) {
        // Các thành phần giao diện
        TextField messageField = new TextField();
        messageField.setPromptText(LanguageManager.get("BO.ReminderPanel.messageField"));
        messageField.setFocusTraversable(false);
        DatePicker datePicker = new DatePicker();
        datePicker.setFocusTraversable(false);
        //gán ngày được chọn
        if (selectedDate != null) {
            datePicker.setValue(selectedDate);
        }
        FilteredList<Reminder> filteredReminders = new FilteredList<>(reminders, r -> true);
        if (selectedDate != null) {
            filteredReminders.setPredicate(r -> r.getTime().toLocalDate().equals(selectedDate));
        }
        TableView<Reminder> table = new TableView<>(filteredReminders);
        TableColumn<Reminder, String> messageCol = new TableColumn<>(LanguageManager.get("BO.ReminderPanel.messageCol"));
        messageCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMessage()));
        messageCol.setPrefWidth(500);
        datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                filteredReminders.setPredicate(r -> r.getTime().toLocalDate().equals(newDate));
            } else {
                filteredReminders.setPredicate(r -> true);
            }
        });

        
        ComboBox<Integer> hourComboBox = new ComboBox<>();
        ComboBox<Integer> minuteComboBox = new ComboBox<>();
        hourComboBox.setFocusTraversable(false);
        minuteComboBox.setFocusTraversable(false);
        // Đổ dữ liệu vào ComboBox
        for (int i = 0; i < 24; i++) {
            hourComboBox.getItems().add(i);
        }
        hourComboBox.setValue(12); // Giá trị mặc định

        for (int i = 0; i < 60; i++) {
            minuteComboBox.getItems().add(i);
        }
        minuteComboBox.setValue(0);
        

        TableColumn<Reminder, String> timeCol = new TableColumn<>(LanguageManager.get("BO.ReminderPanel.timeCol"));
        timeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime().format(formatter)));
        timeCol.setPrefWidth(150);

        table.getColumns().addAll(messageCol, timeCol);

        Button addBtn = new Button(LanguageManager.get("BO.ReminderPanel.addBtn"));
        Button deleteBtn = new Button(LanguageManager.get("BO.ReminderPanel.deleteBtn"));
        addBtn.setFocusTraversable(false);
        deleteBtn.setFocusTraversable(false);
        addBtn.setOnAction(e -> {
            msg = messageField.getText();
            
            LocalDate date = datePicker.getValue();
            int hour = hourComboBox.getValue();
            int minute = minuteComboBox.getValue();

            if (msg.isEmpty() || date == null) return;

            LocalDateTime reminderTime = LocalDateTime.of(date, LocalTime.of(hour, minute));
            reminders.add(new Reminder(msg, reminderTime));
            remindersChanged = true;
            saveRemindersToSQL(id);
            messageField.clear();
        });

        deleteBtn.setOnAction(e -> {
            Reminder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                int reminderId = selected.getId();
//                reminders.remove(selected);
                DatabaseHelper.deleteNotes(User.getId(), reminderId);
                remindersChanged = true;
            }
        });

        loadRemindersFromSQL(id);

        VBox timeBox = new VBox(5, datePicker, new HBox(5, hourComboBox, minuteComboBox));
        VBox inputBox = new VBox(10, messageField, timeBox, addBtn, deleteBtn);
        inputBox.setPadding(new Insets(10));
        table.setPrefHeight(250);
        table.setFocusTraversable(false);
        BorderPane root = new BorderPane();
        root.setLeft(inputBox);
        root.setCenter(table);

        return root;
    }

    // Khởi động timer để kiểm tra nhắc nhở
    private void startTimer() {
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                javafx.application.Platform.runLater(() -> {
                    LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
                    Iterator<Reminder> it = reminders.iterator();
                    while (it.hasNext()) {
                        Reminder r = it.next();
                        if (r.getTime().equals(now)) {
                            Toolkit.getDefaultToolkit().beep(); // Hoặc dùng playSound()
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nhắc nhở: " + r.getMessage());
                            alert.show();
                            it.remove();
                        }
                    }
                });
            }
        }, 0, 1000 * 30); // Kiểm tra mỗi 30s
    }
    public VBox Notion(int id) {
        reminders.clear(); 
        loadRemindersFromSQL(id);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30),event->{
//            reminders.clear(); 
            loadRemindersFromSQL(id);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        LocalDate today = LocalDate.now();
        FilteredList<Reminder> filteredReminders = new FilteredList<>(reminders, r -> true);

        if (today != null) {
            filteredReminders.setPredicate(r -> r.getTime().toLocalDate().equals(today));
        }
        TableView<Reminder> table = new TableView<>(filteredReminders);
        VBox vbox = new VBox(10); // VBox chứa các nhiệm vụ, khoảng cách 10px giữa các dòng
        vbox.setPadding(new Insets(10));
        
        TableColumn<Reminder, String> messageCol = new TableColumn<>(LanguageManager.get("BO.ReminderPanel.messageCol"));
        messageCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMessage()));
        
        TableColumn<Reminder, String> timeCol = new TableColumn<>(LanguageManager.get("BO.ReminderPanel.timeCol"));
        timeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime().format(formatter)));
        
        TableColumn<Reminder, Boolean> checkBoxCol = new TableColumn<>(LanguageManager.get("BO.ReminderPanel.checkBoxCol"));
        checkBoxCol.setCellValueFactory(data -> data.getValue().selectedProperty());
        checkBoxCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        table.getColumns().addAll(messageCol, timeCol,checkBoxCol);
        table.setFocusTraversable(false);
        vbox.getChildren().add(table);

        return vbox;
}
    // Lưu nhắc nhở vào file khi cần
    public void saveRemindersToSQL(int id) {
        if (!remindersChanged) {
        }
        else{
            if(DatabaseHelper.testConnection()){
                if(reminders !=null && !reminders.isEmpty()){
                    for(Reminder r : reminders){ // for each
                        if (r.getId() == 0) {
                            // reminder mới, chưa có id -> insert
                            DatabaseHelper.saveNotes(id, r.getMessage(), r.getTime());
                        }
                    }
                     remindersChanged = false;
                }
            }
            else{
            }
        }
    }

    // Tải nhắc nhở từ file
    private void loadRemindersFromSQL(int id) {
        reminders.clear(); 
        List<Reminder> list = DatabaseHelper.loadNotes(id);
        if(list!= null){
            
            for(Reminder r:list){
                    if(r.getTime().isBefore(LocalDateTime.now())){
                        r.selectedProperty().set(true);
                    }
                    r.setModified(false);
                    reminders.add(r);
            }
            sortRemindersByTime(); 
        }
//      
    }

    // Phát âm thanh từ file WAV (tùy chọn)
    private void playSound() {
        try (AudioInputStream audio = AudioSystem.getAudioInputStream(new File("alert.wav"))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để dừng timer nếu cần
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
        saveRemindersToSQL(this.id);
    }
}