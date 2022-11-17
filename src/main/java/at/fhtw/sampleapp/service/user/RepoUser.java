package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RepoUser {
    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public User getUser(int user_id) {
        User user = null;
        try {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM players WHERE user_id=?"
            );

            statement.setInt(1, user_id);
            ResultSet queryResult = statement.executeQuery();
            if (queryResult.next()) {
                user = new User(
                        queryResult.getInt(1),
                        queryResult.getString(2),
                        queryResult.getString(3),
                        queryResult.getInt(4)
                );
            }
        } catch (SQLException e) {
            System.err.println("Fehler bei der DB Query");
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("NULLPOINTER IM REPO USER FILE");
        }
        return user;
    }

    public User getUser(String user_name) {
        User user = null;
        try {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM players WHERE user_name=?"
            );

            statement.setString(1, user_name);
            ResultSet queryResult = statement.executeQuery();
            if (queryResult.next()) {
                user = new User(
                        queryResult.getInt(1),
                        queryResult.getString(2),
                        queryResult.getString(3),
                        queryResult.getInt(4)
                );
            }
        } catch (SQLException e) {
            System.err.println("Fehler bei der DB Query");
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("NULLPOINTER IM REPO USER FILE");
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new LinkedList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM players"
            );
            ResultSet queryResult = statement.executeQuery();

            while (queryResult.next()) {
                User user = new User(queryResult.getInt(1),
                        queryResult.getString(2),
                        queryResult.getString(3),
                        queryResult.getInt(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean postUser(String user_name, String user_password, int user_elo) {
         try {
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO players VALUES(DEFAULT,?,?,?)"
             );
             statement.setString(1,user_name);
             statement.setString(2,user_password);
             statement.setInt(3,user_elo);
             boolean success = statement.execute();
             return true;
         } catch (SQLException e) {
             e.printStackTrace();
             System.err.println("Fehler beim Einfügen eines Users");
         } catch (NullPointerException e) {
             e.printStackTrace();
             System.err.println("Null wird zurückgesendet");
         }
        return false;
    }
}
