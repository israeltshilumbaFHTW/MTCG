package at.fhtw.sampleapp.service;

import java.sql.*;

public class DatabaseConnection {

    static DatabaseConnection databaseConnection = new DatabaseConnection();
    private static Connection connection = null;
    private DatabaseConnection() {

        String url = "jdbc:postgresql://localhost:5432/swe1db";
        String user = "swe1db";
        String pass = "swe1pw";

        try{
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public static DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }
}
