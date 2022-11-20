package at.fhtw.sampleapp.service.repoCollection;

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
                        queryResult.getInt(4),
                        queryResult.getInt(5)
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
                        queryResult.getInt(4),
                        queryResult.getInt(5)
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
                        queryResult.getInt(4),
                        queryResult.getInt(5)
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean postUser(String user_name, String user_password, int user_elo, int user_money) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO players VALUES(DEFAULT,?,?,?,?)"
            );
            statement.setString(1, user_name);
            statement.setString(2, user_password);
            statement.setInt(3, user_elo);
            statement.setInt(4, user_money);
            boolean success = statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Einfügen eines Users");
        }
        return false;
    }


    public int getUserBalance(int user_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT user_money FROM players WHERE user_id=?"
            );

            statement.setInt(1, user_id);
            ResultSet queryResult = statement.executeQuery();
            int userBalance = 0;
            if (queryResult.next()) {
                userBalance = queryResult.getInt(1);
            }
            return userBalance;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Kontostand");
        }
        return 0;
    }

    public boolean updateUserBalance(int user_id, int user_money) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE players SET user_money=? WHERE user_id=?"
            );
            statement.setInt(1, user_money);
            statement.setInt(2, user_id);
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim ändern des User balance");
            //ToDo: add Rollback
        }
        return false;
    }
}
