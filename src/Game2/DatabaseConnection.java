package Game2;

import MainForm.Utils.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Lớp để lưu trữ kết quả xác thực
public class DatabaseConnection {
    // Lớp nội bộ để lưu user_id và fullname
    public static class UserValidationResult {
        private final int userId;
        private final String fullname;

        public UserValidationResult(int userId, String fullname) {
            this.userId = userId;
            this.fullname = fullname;
        }

        public int getUserId() {
            return userId;
        }

        public String getFullname() {
            return fullname;
        }
    }

    private Connection getConnection() throws SQLException {
        return DatabaseHelper.getConnection();
    }

    public UserValidationResult validateUserWithPassword(String username, String password) {
        if (DatabaseHelper.isUserValid(username, password)) {
            String query = "SELECT user_id, fullname FROM users WHERE username = ? AND password = ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String fullname = rs.getString("fullname");
                    return new UserValidationResult(userId, fullname);
                }
            } catch (SQLException e) {
                System.err.println("Error validating user: " + e.getMessage());
            }
        }
        return new UserValidationResult(-1, null); // Trả về -1 và null nếu xác thực thất bại
    }

    public void updateScore(int userId, int score) {
        String query = "MERGE INTO player_rankings AS target " +
                      "USING (SELECT ? AS user_id, ? AS points) AS source " +
                      "ON target.user_id = source.user_id " +
                      "WHEN MATCHED AND source.points > target.points THEN " +
                      "    UPDATE SET points = source.points " +
                      "WHEN NOT MATCHED THEN " +
                      "    INSERT (user_id, points) VALUES (source.user_id, source.points);";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, score);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating score: " + e.getMessage());
        }
    }

    public List<String> getRankings() {
        List<String> rankings = new ArrayList<>();
        String query = "SELECT u.fullname, p.points FROM player_rankings p " +
                      "JOIN users u ON p.user_id = u.user_id " +
                      "ORDER BY p.points DESC";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String entry = rs.getString("fullname") + ": " + rs.getInt("points");
                rankings.add(entry);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching rankings: " + e.getMessage());
        }
        return rankings;
    }

    public void resetRankings() {
        String query = "DELETE FROM player_rankings";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Bảng xếp hạng đã được reset.");
        } catch (SQLException e) {
            System.err.println("Error resetting rankings: " + e.getMessage());
        }
    }
}