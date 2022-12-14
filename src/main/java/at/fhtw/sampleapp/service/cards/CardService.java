package at.fhtw.sampleapp.service.cards;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class CardService implements Service {
    private final CardController cardController;

    public CardService() {
        this.cardController = new CardController(new CardFacade());
    }

    @Override
    public Response handleRequest(Request request) {
        try {
            if (request.getMethod() == Method.GET && request.getPathParts().size() > 1) {
                return this.cardController.getCardsWithId(request);
            } else if(request.getMethod() == Method.GET) {
                return this.cardController.getAllUserCards(request);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("NULLPOINTER IM CARDSERVICE");
        }

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "[]"
        );
    }
}
