package at.fhtw.sampleapp.service.scoreboard;

import at.fhtw.sampleapp.model.Stats;
import at.fhtw.sampleapp.service.repoCollection.RepoStats;

import java.util.List;

public class ScoreboardFacade {
    ScoreboardFacade(){}

    public List<Stats> getScoreboard() {
        RepoStats repoStats = new RepoStats();
        return repoStats.getScoreboard();
    }
}
