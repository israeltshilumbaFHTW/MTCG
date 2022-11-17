package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
