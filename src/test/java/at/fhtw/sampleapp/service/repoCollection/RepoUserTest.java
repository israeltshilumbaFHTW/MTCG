package at.fhtw.sampleapp.service.repoCollection;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepoUserTest {
   @Test
   void getStrongestCardsFromUser() {
       RepoUser repoUser = new RepoUser();
       List<String> cardIdList =  repoUser.getStrongestCardsFromUser(1);

       assertEquals(4, cardIdList.size());

   }
}