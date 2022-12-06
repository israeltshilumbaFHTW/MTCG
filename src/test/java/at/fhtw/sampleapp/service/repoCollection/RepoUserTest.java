package at.fhtw.sampleapp.service.repoCollection;

import at.fhtw.sampleapp.model.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepoUserTest {
   @Test
   void getStrongestCardsFromUser() {
       RepoUser repoUser = new RepoUser();
       List<Card> cardList =  repoUser.getStrongestCardsFromUser(1);

       assertEquals(4, cardList.size());

   }

   @Test
    void updateDefaultDeckBoolean() {
       RepoUser repoUser = new RepoUser();
       repoUser.updateDefaultDeckBoolean(1, false);

   }
}