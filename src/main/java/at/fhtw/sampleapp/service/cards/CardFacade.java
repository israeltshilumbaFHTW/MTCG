package at.fhtw.sampleapp.service.cards;

import at.fhtw.sampleapp.customExceptions.CardNotOwnedException;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoCardPackages;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserCards;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserPackages;

import java.util.ArrayList;
import java.util.List;

public class CardFacade {
    public CardFacade() {
    }

    public List<Card> getCardsFromPlayer(int user_id) {
        RepoUserCards repoUserCards = new RepoUserCards();
        //ToDo: Change this mess

        List<String> cardIdList = repoUserCards.getUserCardList(user_id);

        //ToDo: get Cards
        RepoCard repoCard = new RepoCard();

        List<Card> cardList = new ArrayList<>();
        cardIdList.forEach(card_id -> cardList.add(repoCard.getCard(card_id)));

        return cardList;
    }

    public Card getCardFromPlayer(int user_id, String card_id) throws CardNotOwnedException {
        List<Card> cardList = getCardsFromPlayer(user_id);
        Card foundCard = null;

        for (Card card_iterator : cardList) {
            if(card_iterator.getCard_id().equals(card_id)) {
                foundCard = card_iterator;
                break;
            }
        }
        if(foundCard == null) {
            throw new CardNotOwnedException("Card not owned");
        }

        return foundCard;
    }
}
