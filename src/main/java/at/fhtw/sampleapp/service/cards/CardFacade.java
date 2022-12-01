package at.fhtw.sampleapp.service.cards;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoCardPackages;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserPackages;

import java.util.ArrayList;
import java.util.List;

public class CardFacade {
    public CardFacade() {

    }

    public List<Card> getCardsFromPlayer(int user_id) {
        //ToDo: get packages with user_id
        RepoUserPackages repoUserPackages = new RepoUserPackages();
        List<Integer> packageIdList = repoUserPackages.getUserPackages(user_id);

        //ToDo: get all card_ids with package_ids
        RepoCardPackages repoCardPackages = new RepoCardPackages();
        List<String> cardIdList = new ArrayList<>();
        packageIdList.forEach(package_id -> {
            List<String> queryCardIdList = repoCardPackages.getCardInPackage(1);
            //add each card_id to cardList
            queryCardIdList.forEach(card_id -> {
                cardIdList.add(card_id);
            });
        });

        //ToDo: get Cards
        RepoCard repoCard = new RepoCard();
        List<Card> cardList = new ArrayList<>();
        cardIdList.forEach(card_id -> {
            cardList.add(repoCard.getCard(card_id));
        });

        return cardList;
    }
}
