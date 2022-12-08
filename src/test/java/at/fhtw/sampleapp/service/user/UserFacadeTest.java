package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    @Test
    void createDuplicateUser_DAL() {
        UserFacade userFacade = new UserFacade();
        //create user that already exists
        assertEquals(true, userFacade.addUser("herbert", "schneckerl"));
        assertEquals(false, userFacade.addUser("herbert", "schneckerl"));
    }

    @Test
    void getUserTest_DAL() {
        UserFacade userFacade = new UserFacade();
        //get herbert with name
        User user = new User(1, "herbert", "schneckerl", 20, true );
        User queryName = userFacade.getUser("herbert");
        assertEquals(queryName.getUser_id(), user.getUser_id());
        //get herbert with id
        User queryId = userFacade.getUser(1);
        assertEquals(queryId.getUser_id(), user.getUser_id());
    }

    @Test
    void getUserBalance() {
        UserFacade userFacade = new UserFacade();

    }
}