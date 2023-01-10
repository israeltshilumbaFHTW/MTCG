package at.fhtw.sampleapp.service.transaction;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.customExceptions.DBAccessException;
import at.fhtw.sampleapp.customExceptions.NotEnoughMoneyException;
import at.fhtw.sampleapp.customExceptions.PackageNotAvailableException;
import at.fhtw.sampleapp.customExceptions.UnexpectedErrorException;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.service.DatabaseConnection;
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

                DatabaseConnection.commitTransaction();
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ \"message\": \"Package purchased successfully\" }"
                );
            } else {
                DatabaseConnection.rollbackTransaction();
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{\"message\" : \"Error purchasing Package\"}"
                );
            }
        } catch (NotEnoughMoneyException err) {
            err.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    "{\"message\" : \"Not enough money\"}"
            );
        } catch (PackageNotAvailableException e) {
            e.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    "{\"message\" : \"Package Not Available\"}"
            );
        } catch (UnexpectedErrorException e) {
            e.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    "{\"message\" : \"" + e.getMessage() + "\"}"
            );
        }
    }
}
