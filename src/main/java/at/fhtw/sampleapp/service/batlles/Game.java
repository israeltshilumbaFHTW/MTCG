package at.fhtw.sampleapp.service.batlles;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.model.UserCardModel;
import at.fhtw.sampleapp.service.batlles.battleLogic.BattleEventHandler;
import at.fhtw.sampleapp.service.batlles.battleLogic.BattleRules;

public class Game {

    private UserCardModel player1;
    private UserCardModel player2;
    private BattleRules battleRules;
    private BattleEventHandler battleEventHandler;

    public Game(UserCardModel player1, UserCardModel player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.battleEventHandler = new BattleEventHandler(this.player1, this.player2);
    }

    public void startGame() {
        //check match up
        while(true) {

            battleEventHandler.incrementRound();
            System.out.printf("Round %d\n", battleEventHandler.getRounds());

            int randomIndex = (int) (Math.random() * 10) % player1.getCardList().size();
            Card player1Card = player1.getCardList().get(randomIndex);

            randomIndex = (int) (Math.random() * 10) % player2.getCardList().size();
            Card player2Card = player2.getCardList().get(randomIndex);

            System.out.printf("%s vs. %s \n", player1Card.getCard_name(), player2Card.getCard_name());

            int winner = battleEventHandler.getRoundWinner(player1Card, player2Card);
            if (winner == 1) {
                System.out.printf("Player 1 wins Round: %s\n", battleEventHandler.getRounds());
            } else if(winner == 2) {
                System.out.printf("Player 2 wins Round: %s\n", battleEventHandler.getRounds());
            } else {
                System.out.printf("Draw in Round: %s\n", battleEventHandler.getRounds());
            }

            //update players
            this.player1 = battleEventHandler.getPlayer1();
            this.player2 = battleEventHandler.getPlayer2();

            //game should end
            if(this.player1.getCardList().size() == 0){
                System.out.printf("Player 1 is out of Cards\n");
                System.out.printf("Player 2 wins\n");
                return;
            }

            if(this.player2.getCardList().size() == 0){
                System.out.printf("Player 2 is out of Cards\n");
                System.out.printf("Player 1 wins\n");
                return;
            }

            if(battleEventHandler.getRounds() >= 100) {
                System.out.printf("Match ended in a Draw\n");
                return;
            }
        }
    }
}
