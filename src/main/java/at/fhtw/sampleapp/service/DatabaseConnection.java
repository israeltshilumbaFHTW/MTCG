package at.fhtw.sampleapp.service;
import java.sql.*;

public class DatabaseConnection {

    private static Connection connection = null;
    private static DatabaseConnection databaseConnection;
    private DatabaseConnection() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "swe1user";
        String pass = "swe1pw";

        try{
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public static Connection getDatabaseConnection() {
        if (connection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return connection;
    }
}
