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

            while (queryResult.next()) {
                packageCount = queryResult.getInt(1);
            }
            return packageCount;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim count der Packages");
            connection.rollback();
            return 0;
        }
        //return packageCount;
    }

    public boolean addPackage(int package_id) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO packages VALUES(?,?)"
            );
            statement.setInt(1, package_id);
            statement.setBoolean(2, true);
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim einfügen eines Package");
            connection.rollback();
        }
        return false;
    }

    public boolean getPackage(int package_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT package_id FROM packages WHERE package_id=?"
            );
            statement.setInt(1, package_id);
            ResultSet queryResult = statement.executeQuery();

            int foundPackage = 0;
            if(queryResult.next()) {
                queryResult.getInt(1);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: Package konnte nicht gefunden werden");
            return false;
        }
    }

    public void makePackageUnavailable(int package_id) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        DELETE 
                        FROM packages
                        WHERE package_id = ?
                        """
            );
            statement.setInt(1, package_id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: Package konnte nicht gefunden werden");
        }
    }

    public int getFirstAvailablePackage() {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                        SELECT package_id
                        FROM packages
                        WHERE package_available = true
                        LIMIT 1 OFFSET 0
                        """
            );
            ResultSet resultSet;
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
              return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler: Package konnte nicht gefunden werden");
        }
        return -1;
    }
}
