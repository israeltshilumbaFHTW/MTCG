package at.fhtw.sampleapp.service.packages;

import at.fhtw.sampleapp.model.Card;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PackageTest_DAL {

    @Test
    //check if Transaction gets cancelled if a card in the middle is invalid
    public void addIncorrectCardFormat() throws SQLException {
        /*

        Card card1 = new Card("cardId1", "dark magician", 20, );
        Card card2 = new Card("cardId2", "blue eyes white dragon",30);
        Card card3 = new Card("cardId1", "exodia", 40); //same id as 1

        List<Card> cardList = new LinkedList<>();
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);

        PackageFacade packageFacade = new PackageFacade();

        boolean success = packageFacade.addPackage(cardList);
        assertNotEquals(success, false);
         */
    }
}
