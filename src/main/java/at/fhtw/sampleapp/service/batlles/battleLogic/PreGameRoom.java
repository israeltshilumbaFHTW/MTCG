package at.fhtw.sampleapp.service.batlles.battleLogic;

import at.fhtw.sampleapp.model.UserCardModel;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;
import at.fhtw.sampleapp.service.user.UserFacade;

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


    public List<UserCardModel> getBattleModelsOfPlayers() {
        UserFacade userFacade = new UserFacade();

        List<Card> user1CardList = userFacade.getUserCardList(user_id_1); //dont get Cardlist -> get decklist
        List<Card> user2CardList = userFacade.getUserCardList(user_id_2);

        UserCardModel userCardModel1 = new UserCardModel(user1CardList, user_id_1);
        UserCardModel userCardModel2 = new UserCardModel(user2CardList, user_id_2);

        List<UserCardModel> playerList = new ArrayList<>();
        playerList.add(userCardModel1);
        playerList.add(userCardModel2);

        return playerList;
    }
}
