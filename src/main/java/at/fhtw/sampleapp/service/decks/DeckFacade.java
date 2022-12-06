package at.fhtw.sampleapp.service.decks;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoCardDecks;

import java.util.ArrayList;
import java.util.List;

public class DeckFacade {

    public DeckFacade() {}


    public List<Card> getDefaultDeck(int user_id) { //if user has an unconfigured deck
        RepoUser repoUser = new RepoUser();
        RepoDecks repoDecks = new RepoDecks();

        List<Card> highestDamageCards = new ArrayList<>();
        highestDamageCards = repoUser.getStrongestCardsFromUser(user_id);

        repoDecks.addDeck(user_id, highestDamageCards.get(0).getCard_id(), highestDamageCards.get(1).getCard_id(),  highestDamageCards.get(2).getCard_id(),  highestDamageCards.get(3).getCard_id());
        //change defaultDeck to false
        repoUser.updateDefaultDeckBoolean(user_id);
        return highestDamageCards;
        //ToDo: in case there is an error: add rollback, return empty list
    }
}
