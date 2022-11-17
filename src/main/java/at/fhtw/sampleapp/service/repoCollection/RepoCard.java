package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepoCard {
    private Connection connection = DatabaseConnection.getDatabaseConnection();
    public boolean addCard(int card_id, String card_name, int card_damage) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cards VALUES (?,?,?)"
            );
            statement.setInt(1, card_id);
            statement.setString(2, card_name);
            statement.setInt(3, card_damage);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Einfügen einer Karte");
            connection.rollback();
        }
        return false;
    }
}
