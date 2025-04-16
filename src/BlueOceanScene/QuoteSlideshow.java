/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene;

import Font.FontManagement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Random;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
/**
 *
 * @author truon
 */
public class QuoteSlideshow  {
    private static final String QUOTE_FOLDER = "src/Local";
    private final List<QuoteItem> quotes = new ArrayList<>();
    private final Random random = new Random();
    private Text topicLabel;
    private Text quoteText;
    private Text authorText;
    private ReadOnlyDoubleProperty currentHeightProperty;


    
    public VBox runQuote(ReadOnlyDoubleProperty weightProperty,ReadOnlyDoubleProperty heightProperty){
        this.currentHeightProperty = heightProperty;
        VBox root;
        loadQuotes();
        
        topicLabel = new Text();
        topicLabel.setStyle("-fx-font-weight: bold;");
        topicLabel.wrappingWidthProperty().bind(weightProperty);

        Pane topicPane = new Pane(topicLabel);
        quoteText = new Text();
        quoteText.wrappingWidthProperty().bind(weightProperty);
        Pane quotePane = new Pane(quoteText);
        
        authorText = new Text();
        authorText.setStyle("-fx-font-style: italic;");
        StackPane authorPane = new StackPane(authorText);
        authorPane.setAlignment(Pos.BOTTOM_RIGHT);
        
        root = new VBox(10,topicPane,quotePane,authorPane);
        weightProperty.addListener((obs,oldVal,newVal)->{
            root.setPrefWidth(newVal.doubleValue()*0.85);
        });
        heightProperty.addListener((obs,oldVal,newVal)->{
            root.setPrefHeight(newVal.doubleValue()*0.7);
            updateTopicFont(currentHeightProperty.get());
            System.out.println("Height changed: " + newVal);
        });
        showNextQuotes();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30),e->showNextQuotes()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return root;
    }
    private void showNextQuotes() {
        if(quotes.isEmpty()){
            quoteText.setText("Something did not work :( ");
        }
        
        FadeTransition fadeOutTopic = new FadeTransition(Duration.seconds(1),topicLabel);
        fadeOutTopic.setFromValue(1.0);
        fadeOutTopic.setToValue(0.0);
        
        FadeTransition fadeOutQuotes = new FadeTransition(Duration.seconds(1),quoteText);
        fadeOutQuotes.setFromValue(1.0);
        fadeOutQuotes.setToValue(0.0);
        
        FadeTransition fadeOutAuthor = new FadeTransition(Duration.seconds(1),authorText);
        fadeOutAuthor.setFromValue(1.0);
        fadeOutAuthor.setToValue(0.0);
        
        fadeOutQuotes.setOnFinished(event ->{
            QuoteItem quote = quotes.get(random.nextInt(quotes.size()));
            
            topicLabel.setText(quote.getTopic());
            quoteText.setText(quote.getQuotes());
            authorText.setText("-"+quote.getAuthor());
            FadeTransition fadeInQuotes = new FadeTransition(Duration.seconds(1),quoteText);
            fadeInQuotes.setFromValue(0.0);
            fadeInQuotes.setToValue(1.0);
            fadeInQuotes.play();
            
            FadeTransition fadeInTopic = new FadeTransition(Duration.seconds(1), topicLabel);
            fadeInTopic.setFromValue(0.0);
            fadeInTopic.setToValue(1.0);
            fadeInTopic.play();
            
            FadeTransition fadeInAuthor = new FadeTransition(Duration.seconds(1), authorText);
            fadeInAuthor.setFromValue(0.0);
            fadeInAuthor.setToValue(1.0);    
            fadeInAuthor.play();
        });
        fadeOutTopic.play();
        fadeOutQuotes.play();
        fadeOutAuthor.play();
        
    }
    
    private void loadQuotes() {
        File folder = new File(QUOTE_FOLDER);
        if (folder.exists() && folder.isDirectory()){
            File[] files = folder.listFiles((dir,name)->name.endsWith(".txt"));
            if(files!=null){
                for(File file:files){
                    String topic = file.getName().replace(".txt", "");
                    try{
                        List<String> lines = Files.readAllLines(file.toPath());
                        for(String line : lines){
                            if(line.trim().isEmpty()) continue;    
                            String[] parts = line.split(" — ",2);
                            String quote = parts[0].trim();
                            String author = (parts.length>1) ? parts[1].trim(): "Unknow";
                            
                            quotes.add(new QuoteItem(topic,quote,author));
                        }
                    }
                    catch(IOException event){
                        event.printStackTrace();
                    }
                }
            }
        }
    }
    private void updateTopicFont(double height) {
        double size = height * 0.07;
        System.out.println("Updating font size to: " + size);
        quoteText.setFont(FontManagement.Roboto(height*0.1));
        topicLabel.setFont(FontManagement.Roboto(height*0.15));
        authorText.setFont(FontManagement.Roboto(height*0.07));
 // scale theo chiều cao
    }
}
