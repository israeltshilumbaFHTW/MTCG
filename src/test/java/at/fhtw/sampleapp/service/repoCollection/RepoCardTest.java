package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepoCardTest {

    @Test
    void getCard() {
        RepoCard repoCard = new RepoCard();
        Card card = repoCard.getCard("845f0dc7-37d0-426e-994e-43fc3ac83c08"); //WaterGoblin
        assertEquals("WaterGoblin", card.getCard_name());
    }
}