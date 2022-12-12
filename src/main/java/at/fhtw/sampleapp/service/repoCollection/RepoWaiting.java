package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Waiting;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoWaiting {

    private final Connection connection = DatabaseConnection.getDatabaseConnection();

    public List<Waiting> getWaitingPlayers() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                   """
                       SELECT * FROM waiting      
                       """
            );
            ResultSet queryResult;
            queryResult = statement.executeQuery();
            List<Waiting> waitingList = new ArrayList<>();
            while(queryResult.next()) { //if empty return false
                Waiting waitingPlayer = new Waiting(
                    queryResult.getInt(1),
                    queryResult.getInt(2),
                    queryResult.getBoolean(3)
                );
                waitingList.add(waitingPlayer);
            }
            if(waitingList.size() == 0) {
                return null;
            } else {
                return waitingList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addToWaitingRoom(int user_id, int deck_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        INSERT INTO waiting 
                        VALUES(?,?,true)      
                        """
            );
            statement.setInt(1, user_id);
            statement.setInt(2, deck_id);
            statement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
