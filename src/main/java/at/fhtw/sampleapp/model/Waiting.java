package at.fhtw.sampleapp.model;

public class Waiting {
    private int user_id;
    private int deck_id;
    private boolean waiting;

    public Waiting(){}
    public Waiting(int user_id, int deck_id, boolean waiting) {
        this.user_id = user_id;
        this.deck_id = deck_id;
        this.waiting = waiting;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(int deck_id) {
        this.deck_id = deck_id;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
}
