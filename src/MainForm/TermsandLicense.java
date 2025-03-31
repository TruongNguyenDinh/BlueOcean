/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class TermsandLicense {
    private TermsandLicense(){
        
    }
    public static void openFile() { // Để gọi được từ class khác
        String filePath = "D:\\Java\\BlueOceanClient\\src\\TermsAndLicense\\TermsAndLicense.docx";
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File không tồn tại: " + filePath);
            return;
        }

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Desktop không được hỗ trợ trên hệ thống này!");
        }
    }
}



