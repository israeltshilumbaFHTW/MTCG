package at.fhtw.sampleapp.service.batlles.battleLogic;

import at.fhtw.sampleapp.model.UserCardModel;
import at.fhtw.sampleapp.model.Card;

import java.util.Objects;

public class BattleEventHandler {
    private final int PLAYER1 = 1;
    private final int PLAYER2 = 2;
    private final int DRAW = 0;
    private int rounds;
    private UserCardModel player1;
    private UserCardModel player2;

    public BattleEventHandler(UserCardModel player1, UserCardModel player2) {
        this.rounds = 0;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getRoundWinner(Card player1Card, Card player2Card) {
        double player1CardDamage = player1Card.getCard_damage();
        double player2CardDamage = player2Card.getCard_damage();
        BattleRules battleRules = new BattleRules();
        //Get rules
        //Check if it's a special interaction
        String interaction = battleRules.getSpecialInteraction(player1Card.getCard_class(), player2Card.getCard_class());

        if(Objects.equals(player1Card.getCard_class(), interaction)) {
            //card 1 won interaction
            removeLoserCard(player2Card, 2);
            return PLAYER1;
        }

        if(Objects.equals(player2Card.getCard_class(), interaction)) {
            //card 2 won interaction
            removeLoserCard(player1Card, 1);
            return PLAYER2;
        }

        player1CardDamage = player1CardDamage * battleRules.getEffect(player1Card, player2Card);
        player2CardDamage = player2CardDamage * battleRules.getEffect(player2Card, player1Card);

        if(player1CardDamage > player2CardDamage) {
            //remove Card from player 2
            removeLoserCard(player2Card, 2);
            System.out.printf("Player 1 deals %.2f damage\n", player1CardDamage);
            System.out.printf("Player 2 deals %.2f damage\n", player2CardDamage);
            return PLAYER1;
        }

        if(player1CardDamage < player2CardDamage) {
            //remove Card from player 1
            removeLoserCard(player1Card, 1);
            System.out.printf("Player 1 deals %.2f damage\n", player1CardDamage);
            System.out.printf("Player 2 deals %.2f damage\n", player2CardDamage);
            return PLAYER2;
        }

        //do nothing
        return DRAW;
    }
    private void removeLoserCard(Card card, int whichPlayer) {
        if(whichPlayer == 1) {
            this.player1.getCardList().remove(card);
            this.player2.getCardList().add(card);
        }

        if(whichPlayer == 2) {
            this.player2.getCardList().remove(card);
            this.player1.getCardList().add(card);
        }
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void incrementRound() {
        this.rounds = this.rounds + 1;
    }

    public int getRounds() {
        return this.rounds;
    }

    public UserCardModel getPlayer1() {
        return player1;
    }

    public UserCardModel getPlayer2() {
        return player2;
    }
}
