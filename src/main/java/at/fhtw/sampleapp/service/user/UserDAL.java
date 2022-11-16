package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;

import java.util.ArrayList;
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
}
