package at.fhtw.sampleapp.service.batlle;

import at.fhtw.sampleapp.CustomExceptions.WaitTimeoutException;
import at.fhtw.sampleapp.service.repoCollection.RepoBattle;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleFacadeTest {

    @Test
    void initBattleWhenOnePlayerIsAlreadyWaiting() throws WaitTimeoutException {
        final int user_id = 1;
        final int userAlreadyWaiting_id = 2;
        RepoDecks repoDecks = new RepoDecks();
        int deck_id = repoDecks.getDeckIdWithUserId(userAlreadyWaiting_id);

        RepoBattle repoBattle = new RepoBattle();
        repoBattle.addToWaitingRoom(2, deck_id);

        BattleFacade battleFacade = new BattleFacade();
        String message = battleFacade.initBattle(user_id);

        assertEquals("SECOND PLAYER FOUND A PLAYER ALREADY WAITING", message);

    }

    @Test
    void initBattleWhenNooneIsWaiting() throws WaitTimeoutException {
        final int user_id = 1;
        BattleFacade battleFacade = new BattleFacade();
        String message = battleFacade.initBattle(user_id);

        assertEquals("NO OPPONENT AVAILABLE", message);
    }
}