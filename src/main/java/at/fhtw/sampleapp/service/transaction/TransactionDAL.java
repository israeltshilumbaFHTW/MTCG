package at.fhtw.sampleapp.service.transaction;

import at.fhtw.sampleapp.service.repoCollection.RepoPackages;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserPackages;

import java.util.List;

public class TransactionDAL {

    public TransactionDAL() {
    }

    public boolean buyPackage(int user_id) {
        //check if user has enough money
        RepoUserPackages repoUserPackages = new RepoUserPackages();
        RepoUser repoUser = new RepoUser();
        RepoPackages repoPackages = new RepoPackages();

        int lowestUnownedPackageId = 1;

        int balance = repoUser.getUserBalance(user_id);
        if (balance < 5) {
            return false;
        }

        //check if package the with the lowest id is owned
        List<Integer> packageIdList = repoUserPackages.getUserPackages(user_id);
        while (true) {
            if (packageIdList.contains(lowestUnownedPackageId)) { //package already owned
                lowestUnownedPackageId = lowestUnownedPackageId + 1;
            } else {
                break;
            }
        }
        //check if package exists
        if (!repoPackages.getPackage(lowestUnownedPackageId)) {
           return false;
        }
        //add player and package to player_package_link
        if(!repoUserPackages.addUserPackage(lowestUnownedPackageId, user_id)){
            return false;
        }

        //reduce player money
        balance = balance - 5;
        if(!repoUser.updateUserBalance(user_id, balance)){
            return false;
        }

        return true;
    }
}
