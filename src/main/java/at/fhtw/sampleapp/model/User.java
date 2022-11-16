package at.fhtw.sampleapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class User {
   private int user_id;
   private String user_name;
   private String user_password;

    public User(int user_id, String user_name, String user_password, int user_elo) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_elo = user_elo;
    }
    public int getUser_elo() {
        return user_elo;
    }

    public void setUser_elo(int user_elo) {
        this.user_elo = user_elo;
    }

    private int user_elo;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
}
