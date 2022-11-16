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
        this.userController = new UserController(new UserDAL());
    }

    @Override
    public Response handleRequest(Request request) {
//Bug Fixing
       /*
        if (request.getMethod() == Method.GET &&
            request.getPathParts().size() > 1) {
           //successful database query
            return this.userController.getUser(request.getPathParts().get(1));
        } else if (request.getMethod() == Method.GET) {
            return this.userController.getUser();
        } else if (request.getMethod() == Method.POST) {
            //TODO: Post Method
            return null;
            //return this.userController.
        }
*/
        try {

            if (request.getMethod() == Method.GET &&
                    request.getPathParts().size() > 1) {
                //successful database query
                return this.userController.getUser(request.getPathParts().get(1));
            } else if (request.getMethod() == Method.GET) {
                return this.userController.getUser();
            } else if (request.getMethod() == Method.POST) {
                //TODO: Post Method
                return null;
                //return this.userController.
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
