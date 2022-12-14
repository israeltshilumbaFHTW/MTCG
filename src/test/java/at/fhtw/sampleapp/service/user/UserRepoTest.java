package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.service.DatabaseConnection;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserPackages;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepoTest {
    @Test
    public void getUserBalance() {
        RepoUser repoUser = new RepoUser();
        //kienboec ist User 1
        int user_money = repoUser.getUserBalance(1);
        assertEquals(user_money, 20);
    }

    @Test
    public void addUserPackage() {
        RepoUserPackages repoUserPackages = new RepoUserPackages();
        //only works if packages table has an entry with id 1
        boolean success = repoUserPackages.addUserPackage(1,1);
        assertEquals(true, success);
    }

    @Test
    public void updateUserBalance() {
        RepoUser repoUser = new RepoUser();
        boolean success = repoUser.updateUserBalance(1, 15);
        DatabaseConnection.commitTransaction();
        int userBalance = repoUser.getUserBalance(1);

        assertEquals(15, userBalance);
    }

}
