package at.fhtw.sampleapp.service.batlle.battleLogic;

import at.fhtw.sampleapp.model.BattleModel;

import java.util.List;
import java.util.Random;

public class Game {
    private BattleModel player1;
    private BattleModel player2;

    public Game(List<BattleModel> playerList) {
        this.player1 = playerList.get(0);
        this.player2 = playerList.get(1);
    }

    public synchronized int start()  {
        //check if battle has already taken place
        Random random = new Random();
        int randomNumber = random.nextInt();
        return randomNumber;
    }
}
