package at.fhtw.sampleapp.service.decks;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoCardDecks;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckFacadeTest {

    @Test
    void getDefaultDeck() {
        RepoCardDecks repoCardDecks = new RepoCardDecks();
        DeckFacade deckFacade = new DeckFacade();
        //test kienboec cards
        List<Card> cardList = deckFacade.getDefaultDeck(1);
        //get kienboec deck_id
        List<String> cardsInDeck = new ArrayList<>();

        assertEquals("d04b736a-e874-4137-b191-638e0ff3b4e7", cardList.get(0).getCard_id());
    }
}