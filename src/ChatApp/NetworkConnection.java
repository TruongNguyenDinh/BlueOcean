/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChatApp;

/**
 *
 * @author Phuc Le
 */

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {
    private ConnectionThread connThread = new ConnectionThread();
    private Consumer<Serializable> onReceiveCallback;
    public NetworkConnection(Consumer<Serializable> onReceiveCallback){
        this.onReceiveCallback = onReceiveCallback;
        connThread.setDaemon(true);
        
    }
    public void startConnection() throws Exception {  
        connThread.start();
    }
    public void send(Serializable data) throws Exception{
        connThread.out.writeObject(data);
    }
    public void closeConnection() throws Exception{
 if (connThread.socket != null && !connThread.socket.isClosed()) {
        connThread.socket.close();
        }  
    }
    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();
    
    private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream out;
        
        @Override
        public void run(){           
           try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                   Socket socket = isServer() ? server.accept() : new Socket(getIP(),getPort());
                   ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                   ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);
                
                while (true)       
                {
                   Serializable data =  (Serializable) in.readObject();
                   onReceiveCallback.accept(data);    
                }
            }
            catch (Exception e){
                onReceiveCallback.accept("Connection closed");
                   
            }       
        }
    }        
}
