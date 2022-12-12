package at.fhtw.sampleapp.service.batlle;

import at.fhtw.sampleapp.CustomExceptions.WaitTimeoutException;
import at.fhtw.sampleapp.model.BattleModel;
import at.fhtw.sampleapp.model.Waiting;
import at.fhtw.sampleapp.service.batlle.battleLogic.Game;
import at.fhtw.sampleapp.service.batlle.battleLogic.PreGameRoom;
import at.fhtw.sampleapp.service.repoCollection.RepoWaiting;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;

import java.util.List;

public class BattleFacade {
    private static final int WAITING_TIME_SECONDS = 10;
    private static final int ONE_SECOND = 1000;

    public int initBattle(int user_id) throws WaitTimeoutException {
        //check if a player is waiting
        RepoWaiting repoWaiting = new RepoWaiting();
        RepoDecks repoDecks = new RepoDecks();
        List<Waiting> waitingPlayerList = repoWaiting.getWaitingPlayers();

        if(waitingPlayerList == null) { //no player is waiting -> you are first
            /*Todo: - no player waiting
                    - put him in the waiting Room
                    - add current player
            */
            int deck_id = repoDecks.getDeckIdWithUserId(user_id);
            repoWaiting.addToWaitingRoom(user_id, deck_id);

            try {
                waitingRoom();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("waiting Room Exception");
            }
            //this code is after the waiting room session
            waitingPlayerList = repoWaiting.getWaitingPlayers();

            if (waitingPlayerList.size() == 1 ) { //no player was added
                //return "NO OPPONENT AVAILABLE";
                //throw new WaitTimeoutException("No Opponent Available");
                //Todo: rollback connection
            }
            //ToDo: second player was found in the list -> what should the first player do?
            //return "FIST PLAYER FOUND A SECOND PLAYER AFTER WAITING";
            return -1;

        } else { //a player is already waiting -> you are second

            /*Todo: - 1) add second player name to table
                    - 2) start battle() some function
                    - 3) the battle function should be synchronized
                    - 4) only one thread can access this function and do the fight
                    - 5) maybe a class would be better than a function ngl
                    - 6) before that: Test if this logic works
            */
            //1)
            repoWaiting.addToWaitingRoom(user_id, repoDecks.getDeckIdWithUserId(user_id));
            //start battleLogicFunction
            List<Waiting> waitingList = repoWaiting.getWaitingPlayers();
            PreGameRoom preGameRoom = new PreGameRoom(waitingList.get(0).getUser_id(), waitingList.get(1).getUser_id());
            List<BattleModel> playerList = preGameRoom.getBattleModelsOfPlayers();
            Game game = new Game(playerList);
            int winner = game.start();
            //start battle with the cards

            //return "SECOND PLAYER FOUND A PLAYER ALREADY WAITING";
            return winner;
        }
    }

    private synchronized void waitingRoom() throws InterruptedException {
        int timerSeconds = 0;
        while (timerSeconds < WAITING_TIME_SECONDS) {
            Thread.sleep(ONE_SECOND);
            timerSeconds = timerSeconds + 1;
        }
    }
}
