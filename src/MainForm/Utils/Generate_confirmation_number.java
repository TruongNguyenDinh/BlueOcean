/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainForm.Utils;

import java.util.Random;

/**
 *
 * @author truon
 */
public class Generate_confirmation_number {
    public static String generate_number(){
        Random ran = new Random();
        int  number = ran.nextInt(900000)+100000;
        return String.valueOf(number);
    }
}
