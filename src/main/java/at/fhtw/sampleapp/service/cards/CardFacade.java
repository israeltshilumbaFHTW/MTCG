package at.fhtw.sampleapp.service.cards;

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
}
