
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainForm.Utils;

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
/**
 * Kết thực hiện kết nối database ở đây
 */
public class DatabaseHelper {
    private static String fullname;
    private  static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=appchat;user=sa;password=truong;encrypt=false;";
    public static boolean isUserValid(String username, String password) {
        String sql = "SELECT COUNT(*), fullname FROM users WHERE username =? AND password = ? GROUP BY fullname";

        try (Connection con = DriverManager.getConnection(connectionUrl);
               PreparedStatement stmt = con.prepareStatement(sql) ){
            stmt.setString(1,username);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                fullname = rs.getString("fullname");
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
        String sql = "SELECT fullname FROM users WHERE username =? AND password = ?";
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

}
