/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainForm.Controllers;

/**
 *
 * @author truon
 */
import MainForm.Models.User;
import MainForm.Utils.EmailSender;

public class EmailApp {

    public static void actionSendEmail(String content){
        try {
                EmailSender.sendEmail(User.getEmail(), "Blue Ocean >> Retrieve Password",
                        "This is an automated email, please do not reply.\n"
                                + "Verification code: "+content);
            } catch (Exception e) {
                System.out.println(e);
            }
    }
}
