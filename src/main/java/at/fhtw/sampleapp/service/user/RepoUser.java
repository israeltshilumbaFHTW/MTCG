package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepoUser {
    private Connection connection = DatabaseConnection.getDatabaseConnection();


    public void getAllUsers() throws SQLException {
       PreparedStatement statement = connection.prepareStatement("""
              SELECT * FROM players
              """
       );
    }
}
