package at.fhtw.sampleapp.service.session;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

import javax.net.ssl.SSLSessionContext;

public class SessionService implements Service {
    private final SessionController sessionController;

    public SessionService() {
        this.sessionController = new SessionController(new SessionDAL());
    }

    @Override
    public Response handleRequest(Request request) {
        if(request.getMethod() == Method.POST) {
            return this.sessionController.userLogin(request);
        }

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "[]"
        );
    }
}
