package at.fhtw.sampleapp.service.transaction;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.CustomExceptions.NotEnoughMoneyException;
import at.fhtw.sampleapp.CustomExceptions.PackageNotAvailableException;
import at.fhtw.sampleapp.CustomExceptions.UnexpectedErrorException;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.service.UserAuthorizationMap;

import java.util.Map;

public class TransactionController extends Controller {
    private TransactionFacade transactionFacade;

    public TransactionController(TransactionFacade transactionFacade) {
        this.transactionFacade = transactionFacade;
    }

    public Response buyCard(Request request) {
            Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();

            String authorisation = request.getHeaderMap().getHeader("Authorization");
            int user_id = userAuthorization.get(authorisation);
            try {

                if (this.transactionFacade.buyPackage(user_id)) {
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
            } catch (NotEnoughMoneyException err) {
                err.printStackTrace();
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{\"message\" : \"Not enough money\"}"
                );
            } catch (PackageNotAvailableException e) {
                e.printStackTrace();

                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{\"message\" : \"Package Not Available\"}"
                );
            } catch (UnexpectedErrorException e) {
                e.printStackTrace();
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{\"message\" : \"Unexpected Error\"}"
                );
            }
    }
}
