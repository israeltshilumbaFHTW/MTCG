package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Trading;
import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoTrading {
    private final Connection connection = DatabaseConnection.getDatabaseConnection();
    //GET

    public Trading getTrade(String trading_id) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            SELECT *
                            FROM trading
                            WHERE trading_id = ?
                            """
            );
            statement.setString(1, trading_id);
            ResultSet resultSet = statement.executeQuery();

            Trading trade = null;
            if (resultSet.next()) {
                trade = new Trading(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
            }
            return trade;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getTrade");
            //ToDo: add Rollback
            return null;
        }
    }

    public int getUserIdFromTradingId(String trading_id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            SELECT user_id
                            FROM trading
                            WHERE trading_id = ?
                            """
            );

            statement.setString(1, trading_id);
            ResultSet resultSet = statement.executeQuery();
            int user_id = -1;

            if (resultSet.next()) {
                user_id = resultSet.getInt(1);
            }

            return user_id;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getTradeWithTradeId");
            return -1;
            //ToDo: add Rollback
        }
    }
    public List<Trading> getTradeWithTradeId(String trading_id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            SELECT *
                            FROM trading
                            WHERE trading_id = ?
                            """
            );
            statement.setString(1, trading_id);
            ResultSet resultSet = statement.executeQuery();

            List<Trading> tradeList = new ArrayList<>();
            while (resultSet.next()) {
                tradeList.add(
                        new Trading(
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5)
                        )
                );
            }
            return tradeList;

        } catch (
                SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getTradeWithTradeId");
            //ToDo: add Rollback
            return new ArrayList<>();
        }
    }
    public List<Trading> getTradeFromUser(int user_id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            SELECT *
                            FROM trading
                            WHERE user_id = ?
                            """
            );
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();

            List<Trading> tradeList = new ArrayList<>();
            while (resultSet.next()) {
                tradeList.add(
                        new Trading(
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5)
                        )
                );
            }
            return tradeList;

        } catch (
                SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getTrade");
            //ToDo: add Rollback
            return new ArrayList<>();
        }
    }

    public List<Trading> getAllTrades() {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            SELECT *
                            FROM trading
                            """
            );
            ResultSet resultSet = statement.executeQuery();

            List<Trading> tradingList = new ArrayList<>();

            while (resultSet.next()) {
                Trading trade = new Trading(

                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                );
                tradingList.add(trade);
            }
            return tradingList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getAllTrades");
            //ToDo: add Rollback
        }
        return new ArrayList<>();
    }

    //POST
    public boolean addTrade(int user_id, Trading trade) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            INSERT INTO trading
                            VALUES (?,?,?,?,?)
                            """
            );
            statement.setInt(1, user_id);
            statement.setString(2, trade.getTrading_id());
            statement.setString(3, trade.getCard_to_trade());
            statement.setString(4, trade.getCard_type());
            statement.setInt(5, trade.getTrading_minimum_damage());
            statement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in getAddTrading");
            //ToDo: add Rollback
            return false;
        }
    }

    //DELETE
    public void deleteTrade(String trading_id) {

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    """
                            DELETE FROM trading
                            WHERE trading_id=?
                            """
            );

            statement.setString(1, trading_id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleteTrade");
            //ToDo: add Rollback
        }
    }
}
