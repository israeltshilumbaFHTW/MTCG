package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepoPackages {
    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public int getPackageCount() throws SQLException {
        int packageCount = 0;
        try {
           PreparedStatement statement = connection.prepareStatement(
                   "SELECT count(*) AS count FROM packages;"
           );
          ResultSet queryResult = statement.executeQuery();

          while(queryResult.next()) {
              packageCount = queryResult.getInt(1);
          }
          return packageCount;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim count der Packages");
            connection.rollback();
        }
        return packageCount;
    }

    public boolean addPackage(int package_id) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO packages VALUES(?)"
            );
            statement.setInt(1,package_id);
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim einfügen eines Package");
            connection.rollback();
        }
        return false;
    }
}
