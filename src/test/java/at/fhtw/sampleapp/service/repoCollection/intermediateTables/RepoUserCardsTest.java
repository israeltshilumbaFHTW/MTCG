package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepoUserCardsTest {

    @Test
    void deleteUserCard() {
        RepoUserCards repoUserCards = new RepoUserCards();

        List<String> numberBeforeTrade = repoUserCards.getUserCardList(2);
        repoUserCards.deleteUserCard(2, "951e886a-0fbf-425d-8df5-af2ee4830d85");
        List<String> numberAfterTrade = repoUserCards.getUserCardList(2);
        assertEquals(numberBeforeTrade.size(), numberAfterTrade.size() + 1);
    }
}