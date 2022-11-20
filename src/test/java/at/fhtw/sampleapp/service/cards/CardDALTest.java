package at.fhtw.sampleapp.service.cards;

import at.fhtw.sampleapp.model.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardDALTest {

    @Test
    public void getCardsFromPlayer() {
        CardDAL cardDAL = new CardDAL();
        List<Card> cardList = new ArrayList<>();
        cardList = cardDAL.getCardsFromPlayer(1);
        assertEquals(20, cardList.size());
    }
}