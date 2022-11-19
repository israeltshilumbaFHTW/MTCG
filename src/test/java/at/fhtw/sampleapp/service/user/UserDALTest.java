package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserDALTest {

    @Test
    void createDuplicateUser_DAL() {
        UserDAL userDAL = new UserDAL();
        //create user that already exists
        boolean success = true;
        assertEquals(userDAL.createUser_DAL("herbert", "schneckerl"), success);
        assertNotEquals(userDAL.createUser_DAL("herbert", "schneckerl"), success);
    }

    @Test
    void getUserTest_DAL() {
        UserDAL userDAL = new UserDAL();
        //get herbert with name
        User user = new User(1, "herbert", "schneckerl", 1000, 20 );
        User queryName = userDAL.getUser_DAL("herbert");
        assertEquals(queryName.getUser_id(), user.getUser_id());
        //get herbert with id
        User queryId = userDAL.getUser_DAL(1);
        assertEquals(queryId.getUser_id(), user.getUser_id());
    }

    @Test
    void getUserBalance() {
        UserDAL userDAL = new UserDAL();

    }
}