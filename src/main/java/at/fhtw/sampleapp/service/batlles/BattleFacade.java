package at.fhtw.sampleapp.service.batlles;

import at.fhtw.sampleapp.CustomExceptions.PlayerAlreadyInQueueException;
import at.fhtw.sampleapp.CustomExceptions.WaitTimeoutException;
import at.fhtw.sampleapp.model.UserCardModel;
import at.fhtw.sampleapp.model.Waiting;
import at.fhtw.sampleapp.service.batlles.battleLogic.PreGameRoom;
import at.fhtw.sampleapp.service.repoCollection.RepoWaiting;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BattleFacade {
    private static final int WAITING_TIME_SECONDS = 10;
    private static final int ONE_SECOND = 1000;

    public String initBattle(int user_id) throws WaitTimeoutException, PlayerAlreadyInQueueException{
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
                return "NO OPPONENT AVAILABLE";
                //throw new WaitTimeoutException("No Opponent Available");
                //Todo: rollback connection
            }
            //ToDo: second player was found in the list -> what should the first player do?
            return "FIST PLAYER FOUND A SECOND PLAYER AFTER WAITING";
            //return -1;

        } else { //a player is already waiting -> you are second

            //check if player already has an entry
            List<Waiting> waitingList = repoWaiting.getWaitingPlayers();
            AtomicBoolean playerIsAlreadyWaiting = new AtomicBoolean(false);
            waitingList.forEach(
                   player -> {
                      if(player.getUser_id() == user_id) {
                          playerIsAlreadyWaiting.set(true);
                      }
                   }
            );
            if(playerIsAlreadyWaiting.get()) throw new PlayerAlreadyInQueueException("Player is already waiting");

            //add player to waiting list and start the game
            repoWaiting.addToWaitingRoom(user_id, repoDecks.getDeckIdWithUserId(user_id));

            //start battleLogicFunction
            waitingList = repoWaiting.getWaitingPlayers();
            PreGameRoom preGameRoom = new PreGameRoom(waitingList.get(0).getUser_id(), waitingList.get(1).getUser_id());
            List<UserCardModel> playerList = preGameRoom.getBattleModelsOfPlayers();
            BattleController.Game game = new BattleController.Game(playerList);
            String winner = game.start();
            //start battle with the cards

            return winner;
            //return winner;
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
