package at.fhtw.sampleapp.service.transaction;

import at.fhtw.sampleapp.service.repoCollection.RepoPackages;
import org.junit.jupiter.api.Test;

class TransactionFacadeTest {

    @Test
    void buyPackage() {

        RepoPackages repoPackages = new RepoPackages();

        TransactionFacade transactionFacade = new TransactionFacade();
        transactionFacade.buyPackage(1);
        transactionFacade.buyPackage(2);
    }
}