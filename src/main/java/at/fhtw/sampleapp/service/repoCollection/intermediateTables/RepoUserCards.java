package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoUserCards {

    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public boolean addUserCard(int user_id, String card_id) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        INSERT INTO player_card_link
                        VALUES(?,?) 
                        """
            );

            statement.setInt(1, user_id);
            statement.setString(2, card_id);
            statement.execute();

            return true;
        } catch (SQLException e) {
            System.err.println("Error in addUserCard");
            return false;
        }
    }

    public List<String> getUserCardList(int user_id) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        SELECT card_id 
                        FROM player_card_link
                        WHERE user_id = ?
                        """
            );

            statement.setInt(1, user_id);
            ResultSet queryResult;
            queryResult = statement.executeQuery();

            List<String> cardList = new ArrayList<>();

            while(queryResult.next()) {
                cardList.add(queryResult.getString(1));
            }
            return cardList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getUserCardList");
            return new ArrayList<>();
        }
    }

    public boolean deleteUserCard(int user_id, String card_id) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        DELETE FROM player_card_link 
                        WHERE user_id = ?
                        AND card_id = ?
                        """
            );

            statement.setInt(1, user_id);
            statement.setString(2, card_id);
            statement.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleteUserCardList");
            return false;
        }
    }
}
