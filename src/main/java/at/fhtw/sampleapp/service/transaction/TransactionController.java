package at.fhtw.sampleapp.service.transaction;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.service.UserAuthorizationMap;

import java.util.Map;

public class TransactionController extends Controller {
    private TransactionDAL transactionDAL;

    public TransactionController(TransactionDAL transactionDAL) {
        this.transactionDAL = transactionDAL;
    }

    public Response buyCard(Request request) {
            Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();

            String authorisation = request.getHeaderMap().getHeader("Authorization");
            int user_id = userAuthorization.get(authorisation);
            if (this.transactionDAL.buyPackage(user_id)) {
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ message: \"Package purchased successfully\" }"
                );
            } else {
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{\"message\" : \"Error purchasing Package\"}"
                );
            }
    }
}
