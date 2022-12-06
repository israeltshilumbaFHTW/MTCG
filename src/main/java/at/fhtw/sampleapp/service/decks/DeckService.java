package at.fhtw.sampleapp.service.decks;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class DeckService implements Service{
    private final DeckController deckController;

    public DeckService() {
        this.deckController = new DeckController(new DeckFacade());
    }

    @Override
    public Response handleRequest(Request request) {
        try {
            if(request.getMethod() == Method.GET) {
                return this.deckController.getCardsInUserDeck(request);
            } else if(request.getMethod() == Method.PUT) {
                return this.deckController.changeCurrentDeck(request);
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
