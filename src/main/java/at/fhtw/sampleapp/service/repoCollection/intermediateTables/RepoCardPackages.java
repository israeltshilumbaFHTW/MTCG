package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoCardPackages {
    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public boolean addCardPackageId(String card_id, int package_id) throws SQLException {
       try {
           PreparedStatement statement = connection.prepareStatement(
                   "INSERT INTO card_package_link VALUES (?,?)"
           );
           statement.setString(1, card_id);
           statement.setInt(2, package_id);
           statement.execute();
           return true;

       } catch (SQLException e) {
           e.printStackTrace();
           System.err.println("Fehler beim einfügen in die card_package_link Tabelleo");
           connection.rollback();
       }
       return false;
    }

    public List<String> getCardInPackage(int package_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT card_id FROM card_package_link WHERE package_id=?"
            );
            statement.setInt(1, package_id);
            ResultSet queryResult = statement.executeQuery();

            List<String> cardIdList = new ArrayList<>();
            while(queryResult.next()) {
               cardIdList.add(queryResult.getString(1));
            }
            return cardIdList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: error at getCardInPackage");
            throw new RuntimeException(e);
        }
    }

}
