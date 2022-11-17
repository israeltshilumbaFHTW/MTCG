package at.fhtw.sampleapp.service.session;
import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;

import java.util.Objects;

public class SessionDAL {

    public SessionDAL() {

    }

    public boolean userLoginDAL(String user_name, String user_password) {
        //get with username
        User user;
        RepoUser userRequest = new RepoUser();
        user = userRequest.getUser(user_name);
        //check if pws are identical
        if (!Objects.equals(user.getUser_password(), user_password)) {
            return false;
        }
        //if identical -> add user to sessions DB
        return true;
        //if not -> bad request
    }
}
