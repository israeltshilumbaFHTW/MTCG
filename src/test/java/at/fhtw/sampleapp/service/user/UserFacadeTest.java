package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    @Test
    void createDuplicateUser_DAL() {
        UserFacade userFacade = new UserFacade();
        //create user that already exists
        boolean success = true;
        assertEquals(userFacade.createUser_DAL("herbert", "schneckerl"), success);
        assertNotEquals(userFacade.createUser_DAL("herbert", "schneckerl"), success);
    }

    @Test
    void getUserTest_DAL() {
        UserFacade userFacade = new UserFacade();
        //get herbert with name
        User user = new User(1, "herbert", "schneckerl", 1000, 20 );
        User queryName = userFacade.getUser_DAL("herbert");
        assertEquals(queryName.getUser_id(), user.getUser_id());
        //get herbert with id
        User queryId = userFacade.getUser_DAL(1);
        assertEquals(queryId.getUser_id(), user.getUser_id());
    }

    @Test
    void getUserBalance() {
        UserFacade userFacade = new UserFacade();

    }
}