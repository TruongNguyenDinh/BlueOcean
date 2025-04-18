/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Utils;

import MainForm.Models.HelpQuickLogin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author truon
 */
public class QuickLogin  {
    private String terminal;
    public QuickLogin(){}
    public QuickLogin(String string){
        this.terminal = string;
    }
    public boolean checkQuickLogin (){
//        System.out.println("username: ");
        try{
            String[] parts = terminal.split("/");// tách chuỗi thành 2 mảng theo / [] và [xxxx]
            String result = parts[1].trim();
            try{
                File folder = new File("src/Local").getCanonicalFile();
                File[] files = folder.listFiles((dir,name)->name.startsWith(result)&&name.endsWith(".quicklogin"));
                if(files!=null && files.length>0){
                    File file = files[0];
                    try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                        String line = reader.readLine();
                        if (line!=null){
                            String uspk[] = line.split(",");
                            if(uspk.length>=2){
                                String username = uspk[0];
                                String password = uspk[1];
                                sendData(username,password);
                                System.out.println("username: "+username+" password: "+password);
                                return true;
                            }
                            else{
                                System.out.println("uspk null");
                                return false;
                            }
                        }
                        else{
                            System.out.println("line null");
                            return false;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                        return false;
                    }
                }
                else{
                  System.out.println("files null");
                  return false;
                }
            }
            catch(IOException e){
                System.out.println(e);
                return false;
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }
        return false;
    }
    private void sendData(String username,String password){
        HelpQuickLogin.getInstance().setUsername(username);
        HelpQuickLogin.getInstance().setPassword(password);
    }
}
