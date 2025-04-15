/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChatApp;

/**
 *
 * @author Phuc Le
 */
import java.io.Serializable;
import java.util.function.Consumer;
public class Server extends NetworkConnection {
    private int port;
    public Server(int port, Consumer<Serializable> onReceiveCallback){
        super(onReceiveCallback);
        this.port = port;  
    }

    @Override
    protected boolean isServer() {
        return true;
    }

    @Override
    protected String getIP() {
        return null;
    }

    @Override
    protected int getPort() {
        return port;
    }
    
}
