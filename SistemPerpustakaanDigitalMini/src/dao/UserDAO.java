package dao;

import config.DatabaseConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // =========================
    // REGISTER USER
    // =========================
    public boolean register(User user) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            System.out.println("Register Error: " + e.getMessage());
            return false;
        }
    }

    // =========================
    // LOGIN USER
    // =========================
    public User login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Login Error: " + e.getMessage());
        }

        return null;
    }

    // =========================
    // CEK USERNAME DUPLIKAT
    // (biar ga double user)
    // =========================
    public boolean isUsernameExists(String username) {
        String query = "SELECT id FROM users WHERE username = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Check Username Error: " + e.getMessage());
        }

        return false;
    }
}