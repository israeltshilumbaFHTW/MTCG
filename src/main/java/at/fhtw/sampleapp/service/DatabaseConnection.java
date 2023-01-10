package at.fhtw.sampleapp.service;

import at.fhtw.sampleapp.customExceptions.DBAccessException;
import at.fhtw.sampleapp.customExceptions.UnexpectedErrorException;

import java.sql.*;

public class DatabaseConnection implements AutoCloseable{

    private static Connection connection = null;
    private static DatabaseConnection databaseConnection;

    private DatabaseConnection() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "swe1user";
        String pass = "swe1pw";

        //FAIL: Kann nicht eingefügt werden, es verlangt exceptions von files, die viel höher sind

        try {
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.err.println("Fehler in der DatabaseConnection");
        }
    }

    public static Connection getDatabaseConnection() {
        if (connection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return connection;
    }

    public static void commitTransaction() {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollbackTransaction() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void finishWork() throws DBAccessException {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBAccessException("Schließen der Connection nicht erfolgreich");
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.finishWork();
    }}
