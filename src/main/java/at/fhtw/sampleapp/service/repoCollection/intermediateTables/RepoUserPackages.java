package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoUserPackages {
    private Connection connection = DatabaseConnection.getDatabaseConnection();

    public List<Integer> getUserPackages(int user_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT package_id FROM player_package_link WHERE user_id=?"
            );
            statement.setInt(1,user_id);
            ResultSet queryResult = statement.executeQuery();
            List<Integer> packageIdList = new ArrayList<Integer>();

            while(queryResult.next()) {
                packageIdList.add(queryResult.getInt(1));
            }
            return packageIdList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler bei RepoUserPackages.");
        }
        return new ArrayList<>();
    }

    public boolean addUserPackage(int package_id, int user_id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO player_package_link VALUES(?,?)"
            );
            statement.setInt(1, user_id);
            statement.setInt(2, package_id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim hinzufügen eines UserPackages");
            //ToDo: add Rollback
        }
        return false;
    }
}
