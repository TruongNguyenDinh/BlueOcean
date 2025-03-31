/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Utils;

public class checkInputData {

    private checkInputData() {
    }

    public static boolean isValidUserName(String userName) {
        return userName != null && userName.matches("^[a-zA-Z0-9_]{5,20}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^[A-Z]{1,}[a-zA-Z0-9@]*$");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._]+@\\w+\\.\\w{2,}$");
    }

    public static boolean isValidFullName(String fullname) {
        return fullname != null && fullname.matches("^[a-zA-ZÀ-Ỹà-ỹ]+( [a-zA-ZÀ-Ỹà-ỹ]+)*$");
    }

    public static boolean isValidNumberPhone(String numberphone) {
        return numberphone != null && numberphone.matches("0\\d{9}");
    }
    public static boolean isValidNameInGame(String nameInGame){
        return nameInGame != null && nameInGame.matches("^[a-zA-ZÀ-Ỹà-ỹ0-9_]+$");
    }
    public static boolean  isValidTagName(String tagName){
        return tagName !=null && tagName.matches("\\w{1,6}");
    }
}
