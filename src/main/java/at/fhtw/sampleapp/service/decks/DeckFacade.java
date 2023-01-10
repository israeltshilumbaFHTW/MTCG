package at.fhtw.sampleapp.service.decks;

import at.fhtw.sampleapp.customExceptions.CardNotOwnedException;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.cards.CardFacade;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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

    public boolean updateDeck (int user_id, List<String> cardIdList) throws CardNotOwnedException {
        RepoCard repoCard = new RepoCard();
        RepoUser repoUser = new RepoUser();
        RepoDecks repoDecks = new RepoDecks();
        AtomicInteger cardExistsCount = new AtomicInteger(0);
        //error handling missing
        //check if cards are owned
        //card fassade getCardsFromPlayer has all player cards
        CardFacade cardFacade = new CardFacade();
        List<Card> cardList = cardFacade.getCardsFromPlayer(user_id);

        cardIdList.forEach(cardId -> {
            cardList.forEach(
                    card -> {
                        if(Objects.equals(card.getCard_id(), cardId)) {
                            cardExistsCount.set(cardExistsCount.get() + 1);
                        }
                    }
            );
        });

        if(cardExistsCount.get() != 4) {
            System.err.println(cardExistsCount.get() + "Card Count");
            throw new CardNotOwnedException("Card Not Owned");
        }
        //update Deck
        return repoDecks.updateDeck(user_id, cardIdList.get(0), cardIdList.get(1), cardIdList.get(2), cardIdList.get(3));
    }
}
