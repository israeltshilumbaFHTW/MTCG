package at.fhtw.sampleapp.service.transaction;

import at.fhtw.sampleapp.service.repoCollection.RepoPackages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDALTest {

    @Test
    void buyPackage() {

        RepoPackages repoPackages = new RepoPackages();

        TransactionDAL transactionDAL = new TransactionDAL();
        transactionDAL.buyPackage(1);
        transactionDAL.buyPackage(2);
    }
}