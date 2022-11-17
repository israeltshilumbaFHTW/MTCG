package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;

import java.util.List;

public class UserDAL {
    private List<User> userData;


    public UserDAL() { //addUser
        //remove sampleData
    }

    public List<User> getAllUsers_DAL() {
        List<User> userList = null;
        RepoUser userRequest = new RepoUser();

        userList = userRequest.getAllUsers();
        return userList;
    }

    public User getUser_DAL(int user_id) {
        User user = null;
        RepoUser userRequest = new RepoUser();

        user = userRequest.getUser(user_id);
        return user;
    }

    public User getUser_DAL(String user_name) {
        User user = null;
        RepoUser userRequest = new RepoUser();

        user = userRequest.getUser(user_name);
        return user;
    }

    public boolean createUser_DAL(String user_name, String user_password) {
        final int defaultElo = 1000;
        final int defaultMoney = 20;
        //check if username is already in use
        RepoUser userRequest = new RepoUser();
        boolean success = false;
        if (userRequest.getUser(user_name) == null) { //user_name nicht vergeben
            success = userRequest.postUser(user_name, user_password, defaultElo, defaultMoney);
        }
        return success;
    }


}
