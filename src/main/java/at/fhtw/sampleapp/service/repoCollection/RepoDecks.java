package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepoDecks {

    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public boolean addDeck(int user_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO decks VALUES(DEFAULT,?)"
            );

            statement.setInt(1, user_id);
            return statement.execute(); //returns a boolean

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
}
