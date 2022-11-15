package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAL {
    private List<User> userData;


    public UserDAL() { //addUser
        //remove sampleData
        int sampleId = 1;
        String sampleName = "Israel";
        String samplePassword = "Password";

        userData = new ArrayList<>();
        userData.add(new User(sampleId, sampleName, samplePassword));
    }

    public User getUser(int user_id) {
        User foundUser = this.userData.stream()
                .filter(user -> user_id == user.getUser_id())
                .findAny()
                .orElse(null);
        return foundUser;
    }

}
