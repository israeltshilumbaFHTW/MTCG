package at.fhtw.sampleapp.service.batlles;

import at.fhtw.httpserver.utils.Router;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.model.UserCardModel;
import at.fhtw.sampleapp.service.batlles.battleLogic.BattleEventHandler;
import at.fhtw.sampleapp.service.batlles.battleLogic.BattleRules;
import at.fhtw.sampleapp.service.batlles.battleLogic.documentation.Documentation;

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

    public String startGame() {
        System.out.printf("Start Game");
        Documentation documentation = Documentation.getDocumentation();

        documentation.addBattleLog("Start Game\n");
        //check match up
        while(true) {

            battleEventHandler.incrementRound();
            System.out.printf("Round %d\n", battleEventHandler.getRounds());
            String roundDocMessage = "Round " + battleEventHandler.getRounds();

            documentation.addBattleLog(roundDocMessage);

            int randomIndex = (int) (Math.random() * 10) % player1.getCardList().size();
            Card player1Card = player1.getCardList().get(randomIndex);

            randomIndex = (int) (Math.random() * 10) % player2.getCardList().size();
            Card player2Card = player2.getCardList().get(randomIndex);

            System.out.printf("%s vs. %s \n", player1Card.getCard_name(), player2Card.getCard_name());
            String cardNameDocMessage = player1Card.getCard_name() + "vs." + player2Card.getCard_name();
            documentation.addBattleLog(cardNameDocMessage);

            int winner = battleEventHandler.getRoundWinner(player1Card, player2Card);
            if (winner == 1) {
                System.out.printf("Player 1 wins Round: %s\n", battleEventHandler.getRounds());
                String player1WinsMessage = "Player 1 wins Round: " + battleEventHandler.getRounds();
                documentation.addBattleLog(player1WinsMessage);

            } else if(winner == 2) {
                String player2WinsMessage = "Player 2 wins Round: " + battleEventHandler.getRounds();
                System.out.printf("Player 2 wins Round: %s\n", battleEventHandler.getRounds());
                documentation.addBattleLog(player2WinsMessage);

            } else {
                System.out.printf("Draw in Round: %s\n", battleEventHandler.getRounds());
                String drawDocMessage = "Draw in Round" + battleEventHandler.getRounds();
                documentation.addBattleLog(drawDocMessage);

            }

            //update players
            this.player1 = battleEventHandler.getPlayer1();
            this.player2 = battleEventHandler.getPlayer2();

            //game should end
            if(this.player1.getCardList().size() == 0){
                System.out.printf("Player 1 is out of Cards\n");
                System.out.printf("Player 2 wins\n");

                String player1outOfCardMessage = "Player 1 is out of Cards\n Player 2 wins\n";
                documentation.addBattleLog(player1outOfCardMessage);

                return "Player 2 wins\n";
            }

            if(this.player2.getCardList().size() == 0){
                System.out.printf("Player 2 is out of Cards\n");
                System.out.printf("Player 1 wins\n");

                String player2outOfCardMessage = "Player 1 is out of Cards\n Player 2 wins\n";
                documentation.addBattleLog(player2outOfCardMessage);

                return "Winner 1 wins\n";
            }

            if(battleEventHandler.getRounds() >= 100) {
                System.out.printf("Match ended in a Draw\n");

                String matchEndsInDrawMessage = "Match ended in a Draw\n";
                return "Match ended in a Draw\n";
            }
        }
    }
}
