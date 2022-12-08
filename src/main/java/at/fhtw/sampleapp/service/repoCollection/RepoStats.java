package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepoStats {

    private Connection connection = DatabaseConnection.getDatabaseConnection();

    //GET
    //POST
    public boolean addDefaultStats(int user_elo, int user_wins, int user_losses, String user_name, int user_id) {
        try {

            PreparedStatement statement = connection.prepareStatement(
                    """
                            INSERT INTO stats
                            VALUES(DEFAULT,?,?,?,?,?)     
                            """
            );

            statement.setInt(1, user_id);
            statement.setString(2, user_name);
            statement.setInt(3, user_elo);
            statement.setInt(4, user_wins);
            statement.setInt(5, user_losses);
            statement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: addDefaultStats");

            return false;
        }
    }
    //UPDATE
}
