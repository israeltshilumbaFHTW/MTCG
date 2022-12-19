package at.fhtw.sampleapp.service.batlles.battleLogic.PostGame;

import java.util.ArrayList;
import java.util.List;

public class EloCalculator {
    public static List<Integer> updateEloRatings(int player1Elo, int player2Elo, boolean player1Won) {
        final int K = 40;
        // Calculate the expected score for each player
        double player1ExpectedScore = 1.0 / (1.0 + Math.pow(10.0, (player2Elo - player1Elo) / 400.0));
        double player2ExpectedScore = 1.0 / (1.0 + Math.pow(10.0, (player1Elo - player2Elo) / 400.0));

        // Calculate the new Elo ratings for each player
        int player1NewElo = player1Elo;
        int player2NewElo = player2Elo;
        if (player1Won) {
            player1NewElo += K * (1 - player1ExpectedScore);
            player2NewElo += K * (0 - player2ExpectedScore);
        } else {
            player1NewElo += K * (0 - player1ExpectedScore);
            player2NewElo += K * (1 - player2ExpectedScore);
        }

        List<Integer> playerNewElos = new ArrayList<>();

        playerNewElos.add(player1NewElo);
        playerNewElos.add(player2NewElo);

        return playerNewElos;
    }
}
