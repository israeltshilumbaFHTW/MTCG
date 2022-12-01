package at.fhtw.sampleapp.service.cards;

import at.fhtw.sampleapp.model.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardFacadeTest {

    @Test
    public void getCardsFromPlayer() {
        CardFacade cardFacade = new CardFacade();
        List<Card> cardList = new ArrayList<>();
        cardList = cardFacade.getCardsFromPlayer(1);
        assertEquals(20, cardList.size());
    }
}