package at.fhtw.sampleapp.service.cards;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.UserAuthorizationMap;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public class CardController extends Controller {
    private CardFacade cardFacade;

    public CardController(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public Response getCardsWithId(Request request) {

        try {
            Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
            String authorization = request.getHeaderMap().getHeader("Authorization");
            int user_id = userAuthorization.get(authorization);

            List<Card> cardList = this.cardFacade.getCardsFromPlayer(user_id);

            String userCardJSON = this.getObjectMapper().writeValueAsString(cardList);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    userCardJSON
            );
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing error");
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Error getting Cards\" }"
            );

        } catch (NullPointerException e) {
            e.printStackTrace();

            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"User is unauthorized\" }"
            );
        }
    }
}
