package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Stats;
import at.fhtw.sampleapp.service.DatabaseConnection;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoStats {

    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public RepoStats(){}
    //GET
    public Stats getUserStats(int user_id) {
       try {

           PreparedStatement statement = connection.prepareStatement(
                   """
                           SELECT user_name, user_elo, user_wins, user_losses, user_draws
                           FROM stats
                           WHERE user_id = ?
                           """
           );

          statement.setInt(1, user_id);
          ResultSet queryResult = statement.executeQuery();
          Stats userStats = new Stats();
          if(queryResult.next()) {
              userStats = new Stats(
                      queryResult.getString(1),
                      queryResult.getInt(2),
                      queryResult.getInt(3),
                      queryResult.getInt(4),
                      queryResult.getInt(5)
              );
          }
           return userStats;
       } catch (SQLException e) {
           e.printStackTrace();
           System.err.println("Erron in getUserStats");
           return new Stats();
       }
    }
    public List<Stats> getScoreboard() {
        try {

            PreparedStatement statement = connection.prepareStatement(
                    """
                            SELECT user_name, user_elo, user_wins, user_losses, user_draws
                            FROM stats
                            
                            ORDER By user_elo DESC
                            """
            );

            ResultSet queryResult = statement.executeQuery();
            Stats userStats = new Stats();
            List<Stats> scoreboard = new ArrayList<>();

            while(queryResult.next()) {
                userStats = new Stats(
                        queryResult.getString(1),
                        queryResult.getInt(2),
                        queryResult.getInt(3),
                        queryResult.getInt(4),
                        queryResult.getInt(5)
                );
                scoreboard.add(userStats);
            }

            return scoreboard;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erron in getScoreboard");
            return new ArrayList<>();
        }
    }


    public int getUserElo(int user_id) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            SELECT user_elo
                            FROM stats
                            WHERE user_id = ?
                            """
            );
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();

            int user_elo = 0;

            if (resultSet.next()) {
                user_elo = resultSet.getInt(1);
            }

            return user_elo;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getUserElo");
            //ToDo: add Rollback
        }
        return -2000;
    }
    //POST
    public boolean addDefaultStats(int user_elo, int user_wins, int user_losses, int user_draws, String user_name, int user_id) {
        try {

            PreparedStatement statement = connection.prepareStatement(
                    """
                            INSERT INTO stats
                            VALUES(DEFAULT,?,?,?,?,?,?)     
                            """
            );

            statement.setInt(1, user_id);
            statement.setString(2, user_name);
            statement.setInt(3, user_elo);
            statement.setInt(4, user_wins);
            statement.setInt(5, user_losses);
            statement.setInt(6, user_draws);
            statement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: addDefaultStats");

            return false;
        }
    }
    //UPDATE

    public void updateUserWins(int user_id, int user_wins) {

        try {

            PreparedStatement statement = connection.prepareStatement(
                    """
                            UPDATE stats
                            SET user_wins = ?
                            WHERE user_id = ?
                            """
            );

            statement.setInt(1, user_wins);
            statement.setInt(2, user_id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: updateUserWins");
        }
    }

    public void updateUserLosses(int user_id, int user_losses) {

        try {

            PreparedStatement statement = connection.prepareStatement(
                    """
                            UPDATE stats
                            SET user_losses = ?
                            WHERE user_id = ?
                            """
            );

            statement.setInt(1, user_losses);
            statement.setInt(2, user_id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: updateUserLosses");
        }
    }
    public void updateUserElo(int user_id, int user_elo) {

        try {

            PreparedStatement statement = connection.prepareStatement(
                    """
                            UPDATE stats
                            SET user_elo = ?
                            WHERE user_id = ?
                            """
            );

            statement.setInt(1, user_elo);
            statement.setInt(2, user_id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: updateUserElo");
        }
    }
    public void updateUserDraws(int user_id, int user_draws) {

        try {

            PreparedStatement statement = connection.prepareStatement(
                    """
                            UPDATE stats
                            SET user_draws = ?
                            WHERE user_id = ?
                            """
            );

            statement.setInt(1, user_draws);
            statement.setInt(2, user_id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: updateUserElo");
        }
    }
}
