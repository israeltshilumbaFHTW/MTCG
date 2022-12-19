package at.fhtw.sampleapp.service.batlles.battleLogic.PostGame;

import at.fhtw.sampleapp.model.Stats;
import at.fhtw.sampleapp.service.repoCollection.RepoStats;

import java.util.ArrayList;
import java.util.List;

public class PostGameFacade {


    //get players Elo scores:
    public List<Integer> getPlayerElos(int user_id_1, int user_id_2) {
        RepoStats repoStats = new RepoStats();
        int user_elo_1 = repoStats.getUserElo(user_id_1);
        int user_elo_2 = repoStats.getUserElo(user_id_2);

        List<Integer> eloList = new ArrayList<>();
        eloList.add(user_elo_1);
        eloList.add(user_elo_2);

        return eloList;
    }

    public void updatePlayerElos(int user_id_1, int user_elo_1, int user_id_2, int user_elo_2) {
        RepoStats repoStats = new RepoStats();

        repoStats.updateUserElo(user_id_1, user_elo_1);
        repoStats.updateUserElo(user_id_2, user_elo_2);
    }

    public void addWin(int user_id) {
        Stats stats = new Stats();
        RepoStats repoStats = new RepoStats();

        stats = repoStats.getUserStats(user_id);
        int user_wins = stats.getStats_wins() + 1;

        repoStats.updateUserWins(user_id, user_wins);
    }
    public void addLoss(int user_id) {
        Stats stats = new Stats();
        RepoStats repoStats = new RepoStats();

        stats = repoStats.getUserStats(user_id);
        int user_losses = stats.getStats_losses() + 1;

        repoStats.updateUserLosses(user_id, user_losses);
    }
    public void addDraw(int user_id) {
        Stats stats = new Stats();
        RepoStats repoStats = new RepoStats();

        stats = repoStats.getUserStats(user_id);
        int user_draws = stats.getStats_draws() + 1;

        repoStats.updateUserDraws(user_id, user_draws);
    }

}
