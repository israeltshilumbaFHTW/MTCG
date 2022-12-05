package at.fhtw.sampleapp.service.repoCollection.intermediateTables;

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

        repoCardDecks.addCardDeckId("99f8f8dc-e25e-4a95-aa2c-782823f36e2a", 2);
        repoCardDecks.addCardDeckId("1cb6ab86-bdb2-47e5-b6e4-68c5ab389334", 2);
        repoCardDecks.addCardDeckId("dfdd758f-649c-40f9-ba3a-8657f4b3439f", 2);
        repoCardDecks.addCardDeckId("e85e3976-7c86-4d06-9a80-641c2019a79f", 2);

        List<String> cardList = new ArrayList<>();
        List<Integer> deckIdList = new ArrayList<>();

        deckIdList.add(1);

        deckIdList.forEach(deck_id -> {
            List<String> queryCardIdLIst = repoCardDecks.getCardsInDeck(1);
            queryCardIdLIst.forEach(card_id -> {
                cardList.add(card_id);
            });
        });
        assertEquals(4, cardList.size());

    }

    @Test
    void getCardInDeck() {
    }
}