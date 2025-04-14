package MainForm.Utils;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author truong
 * Kết thực hiện kết nối database ở đây
 */

public class DatabaseHelper {
    private static String fullname;
    private  static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=appchat;user=sa;password=truong;encrypt=false;";
    
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
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection con = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id"); 
                String un = rs.getString("username"); 
                String fn =rs.getString("fullname"); 
                String nn = rs.getString("nickname"); 
                String p = rs.getString("phone"); 
                String ad = rs.getString("address"); 
                String gen = rs.getString("gender"); 
                String e = rs.getString("email"); 
                
                java.sql.Timestamp timestamp = rs.getTimestamp("created_at");
                String createdAt = timestamp.toLocalDateTime().toString();
                
                
                return id+"-"+un+"-"+"-"+fn+"-"+nn+"-"+p+"-"+ad+"-"+gen+"-"+e+"-"+createdAt;
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
        }
    }
}