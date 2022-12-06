package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoDecks {

    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public boolean addDeck(int user_id, String card_1_id, String card_2_id, String card_3_id, String card_4_id) { //Todo: change card_1_id,...,card_2_id
        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        INSERT INTO decks VALUES(DEFAULT,?,?,?,?,?)
                        """
            );


            statement.setInt(1, user_id);
            statement.setString(2, card_1_id);
            statement.setString(3, card_2_id);
            statement.setString(4, card_3_id);
            statement.setString(5, card_4_id);

            return statement.execute(); //returns a boolean

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: addDeck in RepoDecks");
        }
        return false;
    }


    public boolean updateDeck(int user_id, String card_1_id, String card_2_id, String card_3_id, String card_4_id) { //Todo: change card_1_id,...,card_2_id
        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        UPDATE decks
                        SET card_1_id = ?, card_2_id = ?, card_3_id = ?, card_4_id = ?
                        WHERE user_id = ?
                        """
            );

            statement.setString(1, card_1_id);
            statement.setString(2, card_2_id);
            statement.setString(3, card_3_id);
            statement.setString(4, card_4_id);
            statement.setInt(5, user_id);

            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: addDeck in RepoDecks");
        }
        return false;
    }
    public int getDeckCount() {
        int deckCount = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT count(*) AS count FROM decks;"
            );
            ResultSet queryResult = statement.executeQuery();

            while (queryResult.next()) {
                deckCount = queryResult.getInt(1);
            }
            return deckCount;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim count der Packages");
            //connection.rollback();
            return 0;
        }
        //return packageCount;
    }

    public List<String> getDeck(int user_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        SELECT card_1_id, card_2_id, card_3_id, card_4_id
                        FROM decks
                        WHERE user_id = ?
                        """
            );
            statement.setInt(1, user_id);
            ResultSet queryResult = statement.executeQuery();
            List<String> cardIdList = new ArrayList<>();

            if(queryResult.next()) {
               cardIdList.add(queryResult.getString(1));
               cardIdList.add(queryResult.getString(2));
               cardIdList.add(queryResult.getString(3));
               cardIdList.add(queryResult.getString(4));
            }

            return cardIdList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getDeck");
        }
        return new ArrayList<>();
    }
}
