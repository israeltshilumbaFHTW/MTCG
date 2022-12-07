package at.fhtw.sampleapp.service.transaction;

import at.fhtw.sampleapp.CustomExceptions.NotEnoughMoneyException;
import at.fhtw.sampleapp.CustomExceptions.PackageNotAvailableException;
import at.fhtw.sampleapp.CustomExceptions.UnexpectedErrorException;
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