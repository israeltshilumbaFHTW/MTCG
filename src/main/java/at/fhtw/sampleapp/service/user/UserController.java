package at.fhtw.sampleapp.service.user;

import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.customExceptions.DBAccessException;
import at.fhtw.sampleapp.customExceptions.UnexpectedErrorException;
import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.service.DatabaseConnection;
import at.fhtw.sampleapp.service.UserAuthorizationMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import com.fasterxml.jackson.core.type.TypeReference;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserController extends Controller {
    private UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    //GET /user/:id
    public Response getUsers(String user_id) {
        try {
            User userData = this.userFacade.getUser(Integer.parseInt(user_id));
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

    public Response getUser(Request request) {
        System.out.printf("GET user/%s", request.getPathParts().get(1));

        if (request.getHeaderMap().getHeader("Authorization") == null) {
            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"User is unauthorized\" }"
            );
        }

        if (!request.getHeaderMap().getHeader("Authorization").contains(request.getPathParts().get(1))) {
            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"You can only view your Profile\" }"
            );
        }
        Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
        String authorisation = request.getHeaderMap().getHeader("Authorization");
        int user_id = userAuthorization.get(authorisation);

        try {
            User user = this.userFacade.getUser(user_id);

            String userDataJSON = this.getObjectMapper().writeValueAsString(user);

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
                    "{ \"message\" : \"Internal Server Error\" }."
            );
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"User is unauthorized\" }."
            );
        }
    }

    public Response getUsers() {
        try {
            List<User> userListData = this.userFacade.getAllUsers();

            String userDataJSON = this.getObjectMapper().writeValueAsString(userListData);

            DatabaseConnection.commitTransaction();
            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    userDataJSON
            );
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing error");
            e.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }."
            );
        }
    }

    public Response postUser(Request request) {

        try {

            User user = this.getObjectMapper().readValue(request.getBody(), User.class);

            if (this.userFacade.addUser(user.getUser_username(), user.getUser_password())) {
                DatabaseConnection.commitTransaction();
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ \"message\": \"Success\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DatabaseConnection.rollbackTransaction();
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{ \"message\" : \"Username not available\" }"
        );
    }

    public Response updateUser(Request request) {

        if (request.getHeaderMap().getHeader("Authorization") == null) {

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"User is unauthorized\" }"
            );
        }

        if (!request.getHeaderMap().getHeader("Authorization").contains(request.getPathParts().get(1))) {

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"You can only edit your Profile\" }"
            );
        }

        Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
        String authorisation = request.getHeaderMap().getHeader("Authorization");
        int user_id = userAuthorization.get(authorisation);

        System.out.println("Basic" + request.getPathParts().get(1));
        System.out.println(authorisation);

        try {
            User user = this.getObjectMapper().readValue(request.getBody(), new TypeReference<User>() {
            });

            if (this.userFacade.updateUser(user, user_id)) {

                DatabaseConnection.commitTransaction();
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ \"message\": \"User successfully updated\" }"
                );
            } else {

                DatabaseConnection.rollbackTransaction();
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{\"message\" : \"Bad Request\"}"
                );
            }
        } catch (UnexpectedErrorException e) {
            e.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"An unexpected error occurred\" }"
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"An unexpected error occurred\" }"
            );
        }
    }
}
