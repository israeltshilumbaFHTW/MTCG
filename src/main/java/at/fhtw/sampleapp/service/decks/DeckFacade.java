package at.fhtw.sampleapp.service.decks;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;

import java.util.ArrayList;
import java.util.List;

public class DeckFacade {

    public DeckFacade() {}

    public List<Card> getDeck(int user_id) {
        RepoDecks repoDecks = new RepoDecks();
        RepoCard repoCard = new RepoCard();
        List<Card> cardList = new ArrayList<>();

        List<String> cardIdList = repoDecks.getDeck(user_id);

        cardIdList.forEach(
                cardId -> {
                    //get Card with Card ID
                    cardList.add(repoCard.getCard(cardId));
                }
        );
        return cardList;
    }

    public List<Card> getDefaultDeck(int user_id) { //if user has an unconfigured deck
        RepoUser repoUser = new RepoUser();
        RepoDecks repoDecks = new RepoDecks();

        List<Card> highestDamageCards = new ArrayList<>();
        highestDamageCards = repoUser.getStrongestCardsFromUser(user_id);

        repoDecks.addDeck(user_id, highestDamageCards.get(0).getCard_id(), highestDamageCards.get(1).getCard_id(),  highestDamageCards.get(2).getCard_id(),  highestDamageCards.get(3).getCard_id());
        //change defaultDeck to false
        repoUser.updateDefaultDeckBoolean(user_id, false);
        return highestDamageCards;
        //ToDo: in case there is an error: add rollback, return empty list
    }
    //add custom deck
    public boolean changeCustomizedDeck(int user_id, List<String> cardIdList) {
        //put into deck table these ids
        //RepoDecks addDeck()
        //change defaultdeckbool to false
        RepoUser repoUser = new RepoUser();
        RepoDecks repoDecks = new RepoDecks();
        //error handling missing
        repoDecks.addDeck(user_id, cardIdList.get(0), cardIdList.get(1), cardIdList.get(2), cardIdList.get(3));
        repoUser.updateDefaultDeckBoolean(user_id, true);

        return true;
    }

    public boolean getDefaultDeckBoolean(int user_id) {
        //get default boolean and return it
        RepoUser repoUser = new RepoUser();
        return repoUser.getDefaultDeckBoolean(user_id);
    }

    public boolean updateDeck(int user_id, List<String> cardIdList) {

        RepoUser repoUser = new RepoUser();
        RepoDecks repoDecks = new RepoDecks();
        //error handling missing
        return repoDecks.updateDeck(user_id, cardIdList.get(0), cardIdList.get(1), cardIdList.get(2), cardIdList.get(3));
    }
}
