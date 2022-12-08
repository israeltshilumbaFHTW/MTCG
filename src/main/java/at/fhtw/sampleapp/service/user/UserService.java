package at.fhtw.sampleapp.service.user;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class UserService implements Service {
    private final UserController userController;

    public UserService() {
        this.userController = new UserController(new UserFacade());
    }

    @Override
    public Response handleRequest(Request request) {
        try {

            if (request.getMethod() == Method.GET &&
                    request.getPathParts().size() > 1) {
                //successful database query
                System.out.println("GET user/username");
                return this.userController.getUser(request);
            } else if (request.getMethod() == Method.GET) {
                return this.userController.getUsers();
            } else if (request.getMethod() == Method.POST) {

                //TODO: Post Method
                    return this.userController.postUser(request);
            } else if (request.getMethod() == Method.PUT) {
                return this.userController.updateUser(request);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("NULLPOINTER IM USER SERVICE");
        }
        //unsuccessful query
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "[]"
        );
    }

}
