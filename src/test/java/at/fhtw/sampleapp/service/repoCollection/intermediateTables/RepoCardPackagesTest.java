package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepoCardPackagesTest {

    @Test
    void getCardInPackage() {
        RepoCardPackages repoCardPackages = new RepoCardPackages();
        List<Integer> packageIdList = new ArrayList<>();
        packageIdList.add(1);
        packageIdList.add(2);
        packageIdList.add(3);

       List<String> cardList = new ArrayList<>();
        packageIdList.forEach(package_id -> {
            List<String> queryCardIdList = repoCardPackages.getCardInPackage(1);
           //add each card_id to cardList
           queryCardIdList.forEach( card_id -> {
               cardList.add(card_id);
           });
        });
        assertEquals(15, cardList.size());
    }
}