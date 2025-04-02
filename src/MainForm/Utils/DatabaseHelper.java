package MainForm.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        }
    }
}