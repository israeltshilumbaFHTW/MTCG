package at.fhtw.sampleapp.model;

import java.util.List;

public class BattleModel {
    List<Card> cardList;
//missing Card Description
    private int user_id;

    public BattleModel(List<Card> cardList, int user_id) {
        this.cardList = cardList;
        this.user_id = user_id;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
