package at.fhtw.sampleapp.service.user;

import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.controller.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;

import java.util.List;

public class UserController extends Controller {
    private UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    //GET /user/:id
    public Response getUser(String user_id) {
       try {
           User userData = this.userFacade.getUser_DAL(Integer.parseInt(user_id));
           String userDataJSON = this.getObjectMapper().writeValueAsString(userData);

           return new Response(
                   HttpStatus.OK,
                   ContentType.JSON,
                   userDataJSON
           );
       } catch (JsonProcessingException e) {
           System.err.println("JSON processing error");
           e.printStackTrace();
           return new Response(
                   HttpStatus.INTERNAL_SERVER_ERROR,
                   ContentType.JSON,
                   "{ \"message\" : \"Internal Server Error\" }"
           );
       }
    }

    public Response getUser() {
        try {
            List<User> userListData = this.userFacade.getAllUsers_DAL();

            String userDataJSON = this.getObjectMapper().writeValueAsString(userListData);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    userDataJSON
            );
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing error");
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }

    public Response postUser(Request request) {
        try {
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);

            if (this.userFacade.createUser_DAL(user.getUser_name(), user.getUser_password())) {
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ message: \"Success\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{ \"message\" : \"Username not available\" }"
        );
    }

}
