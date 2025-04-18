/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.Models;
/**
 *
 * @author truon
 */
public class QuoteItem {
    private final String topic;
    private final String quotes;
    private final String author;
    public QuoteItem(String topic,String quotes,String author){
        this.topic = topic;
        this.quotes = quotes;
        this.author = author;
    }
    public String getTopic(){
        return topic;
    }
    public String getQuotes(){
        return quotes;
    }
    public String getAuthor(){
        return author;
    }
}
