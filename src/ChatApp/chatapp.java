/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChatApp;

/**
 *
 * @author Phuc Le
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class chatapp extends Application{
    private boolean isServer = false;
    
    private TextArea messages = new TextArea();
    private NetworkConnection connection = isServer ? createServer() : createClient();
    private Parent createContent(){   
        messages.setPrefHeight(550);
        TextField input = new TextField();
        input.setPromptText("Nhap vao:");
        input.setOnAction(event -> {
            String message = isServer ? "Server :" : "Client : ";
            message += input.getText();
            input.clear();
            
            messages.appendText(message +  "\n");
            
            try {
                connection.send(message);
            } catch (Exception e) {
            messages.appendText("Failed to send\n");
            }
        });
        
        VBox root = new VBox(20, messages, input);
        root.setPrefSize(600,600);
        return root;
    }
    @Override
    public void init() throws Exception{
        connection.startConnection();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();        
    }

    @Override
    public void stop() throws Exception{
        connection.closeConnection();
    }
    private Server createServer(){
        return new Server(55555, data -> {
            Platform.runLater(() -> {
               messages.appendText(data.toString()+ "\n");
            });
        });
    }
    private Client createClient(){
        return new Client("127.0.0.1", 55555, data -> {
            Platform.runLater(() -> {
               messages.appendText(data.toString()+ "\n");
            });
        });
    }
    public static void main(String[] args){
        launch(args);
    }        

    
}
