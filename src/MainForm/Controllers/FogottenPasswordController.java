/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package MainForm.Controllers;
import MainForm.Utils.checkInputData;



/**
 *
 * @author truon
 */
public class FogottenPasswordController {
    private String userName,phoneNumber,tagName;
    public FogottenPasswordController(){}
    
    public FogottenPasswordController(String userName,String phoneNumber,String tagName){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.tagName = tagName;
    }
    // Hàm kiểm tra nếu có tài khoản thì cho phép đi tới mục đổi mật khẩu
    public boolean checkYesNo() {
        if (checkInputData.isValidFullName(this.userName) &&
            checkInputData.isValidNumberPhone(this.phoneNumber) &&
            checkInputData.isValidTagName(this.tagName)) {
            ///////////////// Xử lý thông tin đầu vào bằng việc tìm trong database có hay không người dùng này/////////////
            ///Dưới là ví dụ cho trường hợp tìm thấy ở trong database
            if (this.userName.equals("Truong") && 
                this.phoneNumber.equals("0362361299") &&
                this.tagName.equals("11200")) {
                System.out.println("Thông tin khớp!");
                return true;
            }
        }
        return false;
    }
    public boolean changePassword(String newPassword, String newPassword_){
        ///////////////////////////Gửi mật khẩu đến database ////////////////
        //////////Tạo một hàm kiểm tra xem nếu gửi thành công thì trả về true còn không thì false   
        return true;
    }
}
