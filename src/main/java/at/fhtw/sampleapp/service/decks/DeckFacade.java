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
        RepoCardDecks repoCardDecks = new RepoCardDecks();
        //ToDo: add entry to deck_table (auto increment table)
        RepoDecks repoDecks = new RepoDecks();
        repoDecks.addDeck(user_id);
        //ToDO: get highest entry in decks table -> our deck_id
        //ToDo: can be simplified by using joined tables
        int deckCount = repoDecks.getDeckCount();
        //ToDo: get highest Damage card_id
        List<Card> highestDamageCards = new ArrayList<>();
        highestDamageCards = repoUser.getStrongestCardsFromUser(user_id);

        //ToDo: add entry to card_deck_link table
        highestDamageCards.forEach(
               card -> {
                   repoCardDecks.addCardDeckId(card.getCard_id(), deckCount);
               }
        );
        //change defaultDeck to false
        repoUser.updateDefaultDeckBoolean(user_id);
        return highestDamageCards;
        //ToDo: in case there is an error: add rollback, return empty list
    }
}
