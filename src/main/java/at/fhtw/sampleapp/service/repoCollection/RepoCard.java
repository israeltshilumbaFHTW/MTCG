package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepoCard {
    private final Connection connection = DatabaseConnection.getDatabaseConnection();

    public boolean addCard(String card_id, String card_name, int card_damage) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cards VALUES (?,?,?)"
            );
            statement.setString(1, card_id);
            statement.setString(2, card_name);
            statement.setInt(3, card_damage);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Einfügen einer Karte");
            //connection.rollback();
        }
        return false;
    }

    public Card getCard(String card_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cards WHERE card_id=?"
            );

            statement.setString(1, card_id);
            ResultSet queryResult = statement.executeQuery();

            if (queryResult.next()) {
                Card card = new Card(
                queryResult.getString(1),
                queryResult.getString(2),
                queryResult.getInt(3)
                );
                return card;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: Get Card failed");
            return new Card(); //return empty card
        }
        return new Card();
    }
}
