package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepoCard {
    private final Connection connection = DatabaseConnection.getDatabaseConnection();

    public boolean addCard(String card_id, String card_name, int card_damage, String card_class, String card_type, String card_element) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cards VALUES (?,?,?,?,?,?)"
            );
            statement.setString(1, card_id);
            statement.setString(2, card_name);
            statement.setInt(3, card_damage);
            statement.setString(4, card_class);
            statement.setString(5, card_type);
            statement.setString(6, card_element);
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
                        queryResult.getString(1), //card_id
                        queryResult.getString(2), //card_name
                        queryResult.getInt(3), //card_damage
                        queryResult.getString(4), //card_class
                        queryResult.getString(5), //card_type
                        queryResult.getString(6) //card_element
                );
                return card;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: Get Card failed");
            return new Card();
        }
        return new Card();
    }
}
