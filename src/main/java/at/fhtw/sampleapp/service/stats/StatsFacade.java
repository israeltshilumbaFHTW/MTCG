package at.fhtw.sampleapp.service.stats;

import at.fhtw.sampleapp.model.Stats;
import at.fhtw.sampleapp.service.repoCollection.RepoStats;

public class StatsFacade {
    public Stats getUserStats(int user_id) {
        //Get userstats
        RepoStats repoStats = new RepoStats();
        return repoStats.getUserStats(user_id);
    }
}
