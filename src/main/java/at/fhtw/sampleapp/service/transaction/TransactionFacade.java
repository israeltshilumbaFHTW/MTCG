package at.fhtw.sampleapp.service.transaction;

import at.fhtw.sampleapp.customExceptions.PackageNotAvailableException;
import at.fhtw.sampleapp.customExceptions.UnexpectedErrorException;
import at.fhtw.sampleapp.service.repoCollection.RepoPackages;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserCards;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserPackages;
import at.fhtw.sampleapp.customExceptions.NotEnoughMoneyException;
import at.fhtw.sampleapp.service.user.UserFacade;

public class TransactionFacade {

    public TransactionFacade() {
    }

    public boolean buyPackage(int user_id) throws NotEnoughMoneyException, PackageNotAvailableException, UnexpectedErrorException {
        //check if user has enough money
        RepoUserPackages repoUserPackages = new RepoUserPackages();
        RepoUser repoUser = new RepoUser();
        RepoPackages repoPackages = new RepoPackages();
        RepoUserCards repoUserCards = new RepoUserCards();

        int lowestUnownedPackageId = 1;

        int balance = repoUser.getUserBalance(user_id);
        if (balance < 5) {
            throw new NotEnoughMoneyException("Not Enough Money");
        }

        int availablePackageId = repoPackages.getFirstAvailablePackage();
        if (availablePackageId == -1) {
            throw new PackageNotAvailableException("Package not available");
        }
        //add Package to UserPackage Relation
        if(!repoUserPackages.addUserPackage(availablePackageId, user_id)){
           throw new UnexpectedErrorException("Unexpected Error: Couldn't get Package");
        }

        //add Cards from package to user_card_link
        UserFacade userFacade = new UserFacade();
        userFacade.addUserCards(user_id);

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
