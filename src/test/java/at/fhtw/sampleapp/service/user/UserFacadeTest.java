package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.service.DatabaseConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    @Test
    void createDuplicateUser_DAL() {
        UserFacade userFacade = new UserFacade();
        //create user that already exists
        assertEquals(true, userFacade.addUser("herbert", "schneckerl"));
        DatabaseConnection.commitTransaction();
        assertEquals(false, userFacade.addUser("herbert", "schneckerl"));
        DatabaseConnection.commitTransaction();
    }

    @Test
    void getUserTest_Facade() {
        UserFacade userFacade = new UserFacade();
        //get herbert with name
        User user = new User(1, "herbert", "schneckerl", 20, true );
        User queryName = userFacade.getUser("herbert");
        assertEquals(queryName.getUser_id(), user.getUser_id());
        assertEquals(queryName.getUser_name(), user.getUser_name());
        assertEquals(queryName.getUser_username(), user.getUser_username());

        //DatabaseConnection.commitTransaction();
    }


}