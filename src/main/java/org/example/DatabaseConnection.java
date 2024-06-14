package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/funn";
    public static final String USER = "root";
    public static final String PASS = "root";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void checkDatabaseConnection() {
        try (Connection conn = connect()) {
            if (conn != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // write a method for insert

    public static void insert(String tablename, String username, String age, String address) {
        String sql = "INSERT INTO " + tablename + " (username, age, address) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, age);
            pstmt.setString(3, address);

            pstmt.executeUpdate();
            System.out.println("Record inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delete(String tablename, String username) {
        String sql = "DELETE FROM " + tablename + " WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("No record found with the username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update(String tablename, String username, String newAge) {
        String sql = "UPDATE " + tablename + " SET age = ? WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newAge);
            pstmt.setString(2, username);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No record found with the username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
