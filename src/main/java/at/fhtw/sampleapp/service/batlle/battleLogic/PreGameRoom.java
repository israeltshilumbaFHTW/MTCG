package at.fhtw.sampleapp.service.batlle.battleLogic;

import at.fhtw.sampleapp.model.BattleModel;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.model.Waiting;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;
import at.fhtw.sampleapp.service.repoCollection.RepoWaiting;

import java.util.ArrayList;
import java.util.List;

public class PreGameRoom {
    //gets Data that is necessary for a fight
    private int user_id_1;
    private int user_id_2;

    public PreGameRoom(int user_id_1, int user_id_2) {
        this.user_id_1 = user_id_1;
        this.user_id_2 = user_id_2;
    }


    public List<BattleModel> getBattleModelsOfPlayers() {
        List<Card> user1CardList = getCardList(user_id_1);
        List<Card> user2CardList = getCardList(user_id_2);
        BattleModel battleModel1 = new BattleModel(user1CardList, user_id_1);
        BattleModel battleModel2 = new BattleModel(user2CardList, user_id_2);

        List<BattleModel> playerList = new ArrayList<>();
        playerList.add(battleModel1);
        playerList.add(battleModel2);

        return playerList;
    }

    private List<Card> getCardList(int user_id) {
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
}
