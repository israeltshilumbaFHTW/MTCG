package at.fhtw.sampleapp.service.batlles.battleLogic.PostGame;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EloCalculatorTest {

    @Test
    void testWithDefaultRating() {
        int player1Elo = 1000;
        int player2Elo = 1000;
        boolean player1Won = false;

        //winner should win 40 and loser should lose 40
        List<Integer> eloList = EloCalculator.updateEloRatings(player1Elo, player2Elo, player1Won);

        assertEquals(960, eloList.get(0));
        assertEquals(1040, eloList.get(1));

    }

    @Test
    void testWhereOnePlayerHasWayHigherElo() {
        int player1Elo = 1000;
        int player2Elo = 100;
        boolean player1Won = true;

        //if Elo difference is too big: Player1 doesn't gain any Elo, player 2 loses 1 elo

        List<Integer> eloList = EloCalculator.updateEloRatings(player1Elo, player2Elo, player1Won);

        assertEquals(1000, eloList.get(0));
        assertEquals(99, eloList.get(1));

        //
    }
    @Test
    void testWhereOnePlayerHasWayHigherEloButLoses() {
        int player1Elo = 1000;
        int player2Elo = 100;
        boolean player1Won = false;

        //if Elo difference is too big, but player 1 loses anyway: he loses 80 elo, and player 2 wins 79 due to round error

        List<Integer> eloList = EloCalculator.updateEloRatings(player1Elo, player2Elo, player1Won);

        assertEquals(920, eloList.get(0));
        assertEquals(179, eloList.get(1));

    }
}