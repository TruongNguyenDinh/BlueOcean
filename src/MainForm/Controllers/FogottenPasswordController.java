/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Controllers;
import MainForm.Utils.DatabaseHelper;
import MainForm.Utils.checkInputData;



/**
 *
 * @author truon
 */
public class FogottenPasswordController {
    private String userName,phoneNumber;
    private String Id;
    public FogottenPasswordController(){}
    
    public FogottenPasswordController(String userName,String phoneNumber){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
       
    }
    // Hàm kiểm tra nếu có tài khoản thì cho phép đi tới mục đổi mật khẩu
    public boolean checkYesNo() {
        if (checkInputData.isValidUserName(this.userName) &&
            checkInputData.isValidNumberPhone(this.phoneNumber))
             {
            ///////////////// Xử lý thông tin đầu vào bằng việc tìm trong database có hay không người dùng này/////////////
            ///Dưới là ví dụ cho trường hợp tìm thấy ở trong database
            boolean a = DatabaseHelper.accoutExist(this.userName, this.phoneNumber);
            if(a)
            {
                System.out.print("Run");
                return true;
            }else {
                System.out.print("Chay");
                return false;
            }
                /// TÌm được thì lấy id người dùng
                ///// Gán id người dùng vào biến id
        }else {
            System.out.print("Khong");
                 return false;
        } 
    }
    public boolean changePassword(String userName, String phoneNumber, String newPassword){
//        find ID(abc) ID-> đổi mật khẩu
        ///////////////////////////Gửi mật khẩu đến database ////////////////
//////////Tạo một hàm kiểm tra xem nếu gửi thành công thì trả về true còn không thì false
        boolean success = DatabaseHelper.changePassword(userName, phoneNumber, newPassword);
       if(success){
           return true;
       }else{
           return false;
       }
    }
    public static void main(String[] args){
        String Username = "Truong7";
        String Phonenumber = "097980984";
        String Newpassword = "Phuc123";
        
        FogottenPasswordController a = new FogottenPasswordController(Username,Phonenumber);
        boolean c = a.checkYesNo();
        System.out.print(c);
        a.changePassword(Username, Phonenumber, Newpassword);
       // boolean m = a.changePassword(Newpassword, Newpassword)
        
        
    }
}
