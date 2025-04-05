package MainForm.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author truong
 * Kết thực hiện kết nối database ở đây
 */

public class DatabaseHelper {
    private static String fullname;
    private  static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=appchat;user=sa;password=123;encrypt=false;";
    
    public static boolean testConnection(){
        try (Connection con = DriverManager.getConnection(connectionUrl)){
               System.out.printf("Ket noi ok");
               return true;
        }   catch(SQLException e){
            System.out.printf("0 ok");
            return false;
        }
        
    }
    public static boolean isUserValid(String username, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE username =? AND password = ? ";
        try (Connection con = DriverManager.getConnection(connectionUrl);
               PreparedStatement stmt = con.prepareStatement(sql) ){
            stmt.setString(1,username);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1)>0;
            }
        } catch (SQLException e) {
            // Xử lý lỗi kết nối
            System.err.println("Loi: " + e.getMessage());
//            e.printStackTrace();

        }
        return false;
    }
    

    public static String getFullname(String username, String password) {
        String sql = "SELECT fullname FROM users WHERE username = ? AND password = ?";
        try (Connection con = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("fullname"); // Trả về fullname nếu tìm thấy
            }
        } catch (SQLException e) {
            System.err.println("Loi: " + e.getMessage());
        }
        return null; // Trả về null nếu không tìm thấy người dùng
    }
    public static boolean sendData(String userName, String password, String phonenumber, String email, String fullname, String nickname){
        String SQL = "INSERT INTO users ( username, fullname, nickname, phone, password , email ) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, userName);
            stmt.setString(2, fullname);
            stmt.setString(3, nickname);
            stmt.setString(4, phonenumber);
            stmt.setString(5, password);
            stmt.setString(6, email);
        int rowsinserted = stmt.executeUpdate();
        return rowsinserted > 0;
            
        }catch(SQLException e) {
            System.err.println("Loi: " + e.getMessage());
            return false;
        }
        
    } 
    public static boolean accoutExist(String username, String phonenumber) {
        String sql = "SELECT 1 FROM users WHERE username = ? AND phone = ? ";
        try (Connection con = DriverManager.getConnection(connectionUrl);
               PreparedStatement stmt = con.prepareStatement(sql) ){
            stmt.setString(1,username);
            stmt.setString(2,phonenumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1)>0;
            }
        } catch (SQLException e) {
            // Xử lý lỗi kết nối
            System.err.println("Loi: " + e.getMessage());
//            e.printStackTrace();

        }
        return false;
    }
    public static boolean changePassword(String username, String phonenumber, String newpassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ? AND phone = ? ";
        try (Connection con = DriverManager.getConnection(connectionUrl);
               PreparedStatement stmt = con.prepareStatement(sql) ){
            stmt.setString(1,newpassword);
            stmt.setString(2,username);
            stmt.setString(3, phonenumber);
            int rowupdate = stmt.executeUpdate();
           return rowupdate > 0;
        } catch (SQLException e) {
            // Xử lý lỗi kết nối
            System.err.println("Loi: " + e.getMessage());
//            e.printStackTrace();

        }
        return false;
    }
    


    public static void main(String args[]){
        try(Connection con = DriverManager.getConnection(connectionUrl)){
            System.out.print("Ket noi thanh cong ");
        }catch (SQLException e){
            System.err.println("Loi" + e.getMessage());
=======

/**
 * Kết thực hiện kết nối database ở đây
 */
public class DatabaseHelper {

    public static void main(String[] args) {
        // Chuỗi kết nối đến SQL Server
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=chatapp;user=sa;password=123456789;encrypt=false;";

        Connection con = null;

        try {
            // Kết nối đến cơ sở dữ liệu
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Kết nối thành công!");
        } catch (SQLException e) {
            // Xử lý lỗi kết nối
            System.err.println("Lỗi kết nối: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng kết nối
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Đóng kết nối thành công!");
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
>>>>>>> b1dc403f82c092185320080e05c56df8aebb36b7
        }
    }
}