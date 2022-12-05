package at.fhtw.sampleapp.service.decks;

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
        deckFacade.getDefaultDeck(1);
        deckFacade.getDefaultDeck(2);
        //get kienboec deck_id
        List<String> cardsInDeck = new ArrayList<>();
        //List<String> cardsInDeck2 = new ArrayList<>();

        cardsInDeck = repoCardDecks.getCardsInDeck(1);
        //cardsInDeck2 = repoCardDecks.getCardsInDeck(2);
        assertEquals(4, cardsInDeck.size());
        //assertEquals(4, cardsInDeck2.size());
    }
}