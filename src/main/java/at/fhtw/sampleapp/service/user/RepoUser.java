package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepoUser {
    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public List<User> getAllUsers() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM players"
        );
        ResultSet queryResult = statement.executeQuery();

        List<User> userList = null;

        while (queryResult.next()) {
            User user = new User(queryResult.getInt(1),
                    queryResult.getString(2),
                    queryResult.getString(3),
                    queryResult.getInt(4));
            userList.add(user);
        }

        return userList;
    }
}
