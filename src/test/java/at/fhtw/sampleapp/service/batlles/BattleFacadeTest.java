package at.fhtw.sampleapp.service.batlles;

import at.fhtw.sampleapp.customExceptions.WaitTimeoutException;
import org.junit.jupiter.api.Test;

class BattleFacadeTest {

    @Test
    void initBattleWhenOnePlayerIsAlreadyWaiting() throws WaitTimeoutException {
        /*

        final int user_id = 1;
        final int userAlreadyWaiting_id = 2;
        RepoDecks repoDecks = new RepoDecks();
        int deck_id = repoDecks.getDeckIdWithUserId(userAlreadyWaiting_id);

        RepoWaiting repoWaiting = new RepoWaiting();
        repoWaiting.addToWaitingRoom(2, deck_id);

        BattleFacade battleFacade = new BattleFacade();
        int message = battleFacade.initBattle(user_id);

        assertEquals(1, message);
         */

    }

    @Test
    void initBattleWhenNooneIsWaiting() throws WaitTimeoutException {
        /*

        final int user_id = 1;
        BattleFacade battleFacade = new BattleFacade();
        int message = battleFacade.initBattle(user_id);

        assertEquals("NO OPPONENT AVAILABLE", message);
         */
    }
}