/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package LanguagePackage;

import java.util.Locale;
import java.util.ResourceBundle;



/**
 *
 * @author truon
 */
public class LanguageManager  {
    private static Locale currentLocale = Locale.ENGLISH;
    private static ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages",currentLocale);
    
    public static void setLocale(Locale locale){
        currentLocale = locale;
        bundle = ResourceBundle.getBundle("messages",currentLocale);
    }
    
    public static String get(String key){
        return bundle.getString(key);
    }
    public static Locale getCurrentLocale(){
        return currentLocale;
    }
}
