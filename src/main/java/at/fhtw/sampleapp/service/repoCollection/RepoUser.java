package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RepoUser {
    private Connection connection = DatabaseConnection.getDatabaseConnection();

    //GET REQUESTS
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
                        queryResult.getBoolean(5),
                        queryResult.getString(6),
                        queryResult.getString(7),
                        queryResult.getString(8)
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
                    "SELECT * FROM players WHERE user_username=?"
            );

            statement.setString(1, user_name);
            ResultSet queryResult = statement.executeQuery();
            if (queryResult.next()) {
                user = new User(
                        queryResult.getInt(1),
                        queryResult.getString(2),
                        queryResult.getString(3),
                        queryResult.getInt(4),
                        queryResult.getBoolean(5)
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
                        queryResult.getBoolean(5),
                        queryResult.getString(6),
                        queryResult.getString(7),
                        queryResult.getString(8)
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
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


    public boolean getDefaultDeckBoolean(int user_id) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            SELECT user_defaultDeck
                            FROM players
                            WHERE user_id = ?
                            """
            );
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            boolean defaultDeck = false;

            if (resultSet.next()) {
                defaultDeck = resultSet.getBoolean(1);
            }

            return defaultDeck;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim ??ndern des User balance");
            //ToDo: add Rollback
        }
        return false;
    }

    //UPDATE REQUESTS
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
            System.err.println("Fehler beim ??ndern des User balance");
            //ToDo: add Rollback
        }
        return false;
    }

    public boolean updateName(int user_id, String user_name) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE players SET user_name=? WHERE user_id=?"
            );
            statement.setString(1, user_name);
            statement.setInt(2, user_id);
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Update Name error");
            //ToDo: add Rollback
        }
        return false;
    }

    public boolean updateImage(int user_id, String user_image) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE players SET user_image=? WHERE user_id=?"
            );
            statement.setString(1, user_image);
            statement.setInt(2, user_id);
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Update Name error");
            //ToDo: add Rollback
        }
        return false;
    }

    public boolean updateBio(int user_id, String user_bio) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE players SET user_bio=? WHERE user_id=?"
            );
            statement.setString(1, user_bio);
            statement.setInt(2, user_id);
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Update Name error");
            //ToDo: add Rollback
        }
        return false;
    }

    //POST REQUEST
    public boolean postUser(String user_username, String user_password, int user_money, boolean defaultDeck) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO players VALUES(DEFAULT,?,?,?,?,?,?,?)"
            );
            statement.setString(1, user_username);
            statement.setString(2, user_password);
            statement.setInt(3, user_money);
            statement.setBoolean(4, defaultDeck);
            statement.setString(5, "bio");
            statement.setString(6, "image");
            statement.setString(7, "name");

            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Einf??gen eines Users");
        }
        return false;
    }


    public boolean updateDefaultDeckBoolean(int user_id, boolean defaultDeckBoolean) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE players SET user_defaultdeck=? WHERE user_id=?"
            );
            statement.setBoolean(1, defaultDeckBoolean);
            statement.setInt(2, user_id);
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim ??ndern des User balance");
            //ToDo: add Rollback
        }
        return false;
    }

    public List<Card> getStrongestCardsFromUser(int user_id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """ 
                            SELECT c.card_id, card_name, card_damage, card_class, card_type, card_element
                            FROM player_card_link
                                LEFT JOIN cards c
                                    on player_card_link.card_id = c.card_id
                            WHERE user_id = ?
                            ORDER BY card_damage DESC
                            LIMIT 4 OFFSET 0
                            """
            );

            statement.setInt(1, user_id);
            ResultSet queryResult = statement.executeQuery();

            List<Card> cardList = new ArrayList<>();

            while (queryResult.next()) {
                Card card = new Card(
                        queryResult.getString(1),
                        queryResult.getString(2),
                        queryResult.getInt(3),
                        queryResult.getString(4),
                        queryResult.getString(5),
                        queryResult.getString(6)
                        );
                cardList.add(card);
            }

            return cardList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: getStrongestCardsFromUser -> RepoUser");
        }
        return new ArrayList<>();
    }
}
