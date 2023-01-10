package at.fhtw.sampleapp.service.batlles;

import at.fhtw.sampleapp.customExceptions.PlayerAlreadyInQueueException;
import at.fhtw.sampleapp.customExceptions.WaitTimeoutException;
import at.fhtw.sampleapp.model.UserCardModel;
import at.fhtw.sampleapp.model.Waiting;
import at.fhtw.sampleapp.service.batlles.battleLogic.PreGameRoom;
import at.fhtw.sampleapp.service.repoCollection.RepoWaiting;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class BattleFacade {
    private static final int WAITING_TIME_SECONDS = 5;
    private static final int ONE_SECOND = 1000;
    private volatile boolean endFlag = false;

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
            endFlag = false;
            int deck_id = repoDecks.getDeckIdWithUserId(user_id);
            repoWaiting.addToWaitingRoom(user_id, deck_id);

            try {
                waitingRoom();
            } catch (InterruptedException e) {

                repoWaiting.emptyWaiting();
                e.printStackTrace();
                System.err.println("waiting Room Exception");
            }
            //this code is after the waiting room session

            waitingPlayerList = repoWaiting.getWaitingPlayers();
            if(waitingPlayerList == null) {
                return "Battle already ended";
            }
            if (waitingPlayerList.size() == 1 ) { //no player was added

                repoWaiting.emptyWaiting();
                return "NO OPPONENT AVAILABLE";
                //throw new WaitTimeoutException("No Opponent Available");
                //Todo: rollback connection
            }
            //ToDo: second player was found in the list -> what should the first player do?
            //check the logs
            if(endFlag) {
                return "Battle already ended";
            }
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
            if(playerIsAlreadyWaiting.get()) throw new PlayerAlreadyInQueueException("Player is already waiting"); //cant fight against one self

            //add player to waiting list and start the game
            repoWaiting.addToWaitingRoom(user_id, repoDecks.getDeckIdWithUserId(user_id));
            String winner = getWinner(user_id, repoWaiting, repoDecks);

            if(!endFlag) {
                return winner;
            } else return "No opponent in queue";
        }
    }

    private synchronized String getWinner(int user_id, RepoWaiting repoWaiting, RepoDecks repoDecks) {
        List<Waiting> waitingList = repoWaiting.getWaitingPlayers();
        PreGameRoom preGameRoom = new PreGameRoom(waitingList.get(0).getUser_id(), waitingList.get(1).getUser_id());

        List<UserCardModel> playerList = preGameRoom.getBattleModelsOfPlayers();
        Game game = new Game(playerList.get(0), playerList.get(1));
        String winner = game.startGame();

        repoWaiting.emptyWaiting();
        endFlag = true;
        return winner;
    }

    private void waitingRoom() throws InterruptedException {
        int timerSeconds = 0;
        while (timerSeconds < WAITING_TIME_SECONDS) {
            Thread.sleep(ONE_SECOND);
            timerSeconds = timerSeconds + 1;

            if(endFlag) {
                break;
            }

        }
    }

}
