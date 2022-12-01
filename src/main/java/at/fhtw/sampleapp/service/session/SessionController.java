package at.fhtw.sampleapp.service.session;

import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
public class SessionController extends Controller{

    private SessionFacade sessionFacade;
    public SessionController(SessionFacade sessionFacade) {
        this.sessionFacade = sessionFacade;
    }

    public Response userLogin(Request request) {

        try {
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);

            if (this.sessionFacade.userLoginDAL(user.getUser_name(), user.getUser_password())) {
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ message: \"Login successful\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Response(
                HttpStatus.FORBIDDEN,
                ContentType.JSON,
                "{\"message\" : \"Username or Password wrong\"}"
        );
    }
}
