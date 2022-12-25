package at.fhtw.sampleapp.service.transaction;

import at.fhtw.sampleapp.customExceptions.NotEnoughMoneyException;
import at.fhtw.sampleapp.customExceptions.PackageNotAvailableException;
import at.fhtw.sampleapp.customExceptions.UnexpectedErrorException;
import at.fhtw.sampleapp.service.repoCollection.RepoPackages;
import org.junit.jupiter.api.Test;

class TransactionFacadeTest {

    @Test
    void buyPackage() {

        RepoPackages repoPackages = new RepoPackages();

        TransactionFacade transactionFacade = new TransactionFacade();
        try {
            transactionFacade.buyPackage(1);
            transactionFacade.buyPackage(2);
        } catch (PackageNotAvailableException | UnexpectedErrorException | NotEnoughMoneyException e) {
            e.printStackTrace();
        }
    }
}