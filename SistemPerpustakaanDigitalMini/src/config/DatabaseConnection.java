package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/library_db",
                        "root",
                        ""
                );

                System.out.println("Database Connected!");

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Database Connection Failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}