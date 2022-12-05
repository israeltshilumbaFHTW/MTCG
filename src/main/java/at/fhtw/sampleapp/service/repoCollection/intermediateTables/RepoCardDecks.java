package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoCardDecks {
    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public boolean addCardDeckId(String card_id, int deck_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO card_deck_link VALUES (?,?)"
            );
            statement.setString(1, card_id);
            statement.setInt(2, deck_id);
            statement.execute();
            return true;
    } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: addCardDeck");
            //connection.rollback();
        }
        return false;
    }

    public List<String> getCardsInDeck(int deck_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT card_id FROM card_deck_link WHERE deck_id=?"
            );
            statement.setInt(1, deck_id);
            ResultSet queryResult = statement.executeQuery();

            List<String> cardIdList = new ArrayList<>();
            while(queryResult.next()) {
                cardIdList.add(queryResult.getString(1));
            }
            return cardIdList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: error at getCardsInDeck");
            throw new RuntimeException(e);
        }
    }
}
