package at.fhtw.sampleapp.service.transaction;

import at.fhtw.sampleapp.CustomExceptions.PackageNotAvailableException;
import at.fhtw.sampleapp.CustomExceptions.UnexpectedErrorException;
import at.fhtw.sampleapp.service.repoCollection.RepoPackages;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserPackages;
import at.fhtw.sampleapp.CustomExceptions.NotEnoughMoneyException;

public class TransactionFacade {

    public TransactionFacade() {
    }

    public boolean buyPackage(int user_id) throws NotEnoughMoneyException, PackageNotAvailableException, UnexpectedErrorException {
        //check if user has enough money
        RepoUserPackages repoUserPackages = new RepoUserPackages();
        RepoUser repoUser = new RepoUser();
        RepoPackages repoPackages = new RepoPackages();

        int lowestUnownedPackageId = 1;

        int balance = repoUser.getUserBalance(user_id);
        if (balance < 5) {
            throw new NotEnoughMoneyException("Not Enough Money");
        }

        int availablePackageId = repoPackages.getFirstAvailablePackage();
        if (availablePackageId == -1) {
            throw new PackageNotAvailableException("Package not available");
        }
        //add Package
        if(!repoUserPackages.addUserPackage(availablePackageId, user_id)){
           throw new UnexpectedErrorException("Unexpected Error: Couldn't get Package");
        }
        //make Package unavailable
        repoPackages.makePackageUnavailable(availablePackageId);
        //reduce player money
        balance = balance - 5;
        if(!repoUser.updateUserBalance(user_id, balance)){
            throw new UnexpectedErrorException("Error in Database");
        }

        return true;
    }
}
