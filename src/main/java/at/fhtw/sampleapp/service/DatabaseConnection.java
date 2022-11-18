package at.fhtw.sampleapp.service;
import java.sql.*;

public class DatabaseConnection {

    private static Connection connection = null;
    private static DatabaseConnection databaseConnection;
    private DatabaseConnection() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "swe1user";
        String pass = "swe1pw";

        //FAIL: Kann nicht eingefügt werden, es verlangt exceptions von files, die viel höher sind
        //connection.setAutoCommit(false);

        try{
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.err.println("Fehler in der DatabaseConnection");
        }
    }
    public static Connection getDatabaseConnection(){
        if (connection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return connection;
    }
}
