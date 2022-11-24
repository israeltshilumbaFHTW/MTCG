package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

import at.fhtw.sampleapp.model.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepoCardDecksTest {
//decks müssen in Table eingefügt werden, sonst geht das nicht
//ToDo: Decks brauchen eine UserId
    @Test
    void addCardDeckId() {
        RepoCardDecks repoCardDecks = new RepoCardDecks();

        repoCardDecks.addCardDeckId("testcard1", 1);
        repoCardDecks.addCardDeckId("testcard2", 1);
        repoCardDecks.addCardDeckId("testcard3", 1);


        List<String> cardList = new ArrayList<>();
        List<Integer> deckIdList = new ArrayList<>();

        deckIdList.add(1);

        deckIdList.forEach(deck_id -> {
            List<String> queryCardIdLIst = repoCardDecks.getCardInDeck(1);
            queryCardIdLIst.forEach(card_id -> {
                cardList.add(card_id);
            });
        });
        assertEquals(3, cardList.size());

    }

    @Test
    void getCardInDeck() {
    }
}