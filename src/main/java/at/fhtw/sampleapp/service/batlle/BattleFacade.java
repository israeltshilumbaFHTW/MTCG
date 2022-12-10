package at.fhtw.sampleapp.service.batlle;

import at.fhtw.sampleapp.CustomExceptions.WaitTimeoutException;
import at.fhtw.sampleapp.model.Waiting;
import at.fhtw.sampleapp.service.repoCollection.RepoBattle;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;

import java.util.List;

public class BattleFacade {
    private static final int WAITING_TIME_SECONDS = 10;
    private static final int ONE_SECOND = 1000;

    public String initBattle(int user_id) throws WaitTimeoutException {
        //check if a player is waiting
        RepoBattle repoBattle = new RepoBattle();
        List<Waiting> waitingPlayerList = repoBattle.waitingPlayer();

        if(waitingPlayerList == null) { //no player is waiting -> you are first
            /*Todo: - no player waiting
                    - put him in the waiting Room
                    - add current player

            */
            RepoDecks repoDecks = new RepoDecks();
            int deck_id = repoDecks.getDeckIdWithUserId(user_id);
            repoBattle.addToWaitingRoom(user_id, deck_id);

            try {
                waitingRoom();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("waiting Room Exception");
            }
            //this code is after the waiting room session
            waitingPlayerList = repoBattle.waitingPlayer();

            if (waitingPlayerList.size() == 1 ) { //no player was added
                return "NO OPPONENT AVAILABLE";
                //throw new WaitTimeoutException("No Opponent Available");
                //Todo: rollback connection
            }
            //ToDo: second player was found in the list -> what should the first player do?
            return "FIST PLAYER FOUND A SECOND PLAYER AFTER WAITING";

        } else { //a player is already waiting -> you are second

            return "SECOND PLAYER FOUND A PLAYER ALREADY WAITING";
            /*Todo: - add second player name to table
                    - start battle() some function
                    - the battle function should be synchronized
                    - only one thread can access this function and do the fight
                    - maybe a class would be better than a function ngl
                    - before that: Test if this logic works
            */
        }
    }

    public void waitingRoom() throws InterruptedException {
        int timerSeconds = 0;
        while (timerSeconds < WAITING_TIME_SECONDS) {
            Thread.sleep(ONE_SECOND);
            timerSeconds = timerSeconds + 1;
        }
    }
}
