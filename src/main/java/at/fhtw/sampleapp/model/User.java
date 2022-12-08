package at.fhtw.sampleapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class User {
    private int user_id;
    @JsonAlias({"Username"})
    private String user_username;
    @JsonAlias({"Name"})
    private String user_name;
    @JsonAlias({"Password"})
    private String user_password;
    private int user_money;
    private boolean defaultDeck;
    @JsonAlias({"Bio"})
    private String user_bio;
    @JsonAlias({"Image"})
    private String user_image;
    private int user_elo;

    public User() {
    }

    public User(String user_username, String user_password) {
        this.user_username = user_username;
        this.user_password = user_password;
    }

    public User(int user_id, String user_username, String user_password, int user_elo, int user_money, boolean defaultDeck, String user_bio, String user_image, String user_name) {
        this.user_id = user_id;
        this.user_username = user_username;
        this.user_password = user_password;
        this.user_elo = user_elo;
        this.user_money = user_money;
        this.defaultDeck = defaultDeck;
        this.user_bio = user_bio;
        this.user_image = user_image;
        this.user_name = user_name;
    }

    public User(int user_id, String user_username, String user_password, int user_elo, int user_money, boolean defaultDeck) {
        this.user_id = user_id;
        this.user_username = user_username;
        this.user_password = user_password;
        this.user_elo = user_elo;
        this.user_money = user_money;
        this.defaultDeck = defaultDeck;
    }

    //GETTER SETTER
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_money() {
        return user_money;
    }

    public void setUser_money(int user_money) {
        this.user_money = user_money;
    }

    public boolean isDefaultDeck() {
        return defaultDeck;
    }

    public void setDefaultDeck(boolean defaultDeck) {
        this.defaultDeck = defaultDeck;
    }

    public String getUser_bio() {
        return user_bio;
    }

    public void setUser_bio(String user_bio) {
        this.user_bio = user_bio;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public int getUser_elo() {
        return user_elo;
    }

    public void setUser_elo(int user_elo) {
        this.user_elo = user_elo;
    }
}
