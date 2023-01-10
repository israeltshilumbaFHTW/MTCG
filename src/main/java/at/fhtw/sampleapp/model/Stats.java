package at.fhtw.sampleapp.model;

public class Stats {
    private int stats_elo;
    private int stats_wins;
    private int stats_losses;
    private int stats_draws;
    private String user_name;

    public Stats(){}

    public Stats(String user_name, int stats_elo, int stats_wins, int stats_losses, int stats_draws) {
        this.stats_elo = stats_elo;
        this.stats_losses = stats_losses;
        this.stats_wins = stats_wins;
        this.user_name = user_name;
        this.stats_draws = stats_draws;
    }

    public int getStats_elo() {
        return stats_elo;
    }

    public void setStats_elo(int stats_elo) {
        this.stats_elo = stats_elo;
    }

    public int getStats_wins() {
        return stats_wins;
    }

    public void setStats_wins(int stats_wins) {
        this.stats_wins = stats_wins;
    }

    public int getStats_losses() {
        return stats_losses;
    }

    public void setStats_losses(int stats_losses) {
        this.stats_losses = stats_losses;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getStats_draws() {
        return stats_draws;
    }

    public void setStats_draws(int stats_draws) {
        this.stats_draws = stats_draws;
    }
}
