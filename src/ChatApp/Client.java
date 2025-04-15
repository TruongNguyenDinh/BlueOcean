/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChatApp;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 *
 * @author Phuc Le
 */
public class Client extends NetworkConnection {
    
    private String ip;
    private int port;
    public Client(String ip, int port,Consumer<Serializable> onReceiveCallback){
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;  
    }
    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {   
        return ip;

    }

    @Override
    protected int getPort() {
        return port;
    }
    
}
