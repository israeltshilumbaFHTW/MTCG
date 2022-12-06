package at.fhtw.sampleapp.service.repoCollection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepoDecksTest {

    @Test
    void addDeck() {
        RepoDecks repoDecks = new RepoDecks();
        //if table is empty assert that there is only one entry after test
        //assume that DB already has entries
        repoDecks.addDeck(1, "card_1", "card_2", "card_3", "card_4");
    }

    @Test
    void getDeckCount() {
        RepoDecks repoDecks = new RepoDecks();

        int deckCount = repoDecks.getDeckCount();

        assertEquals(1, deckCount);
    }
}