package at.fhtw.sampleapp.service.decks;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.CustomExceptions.CardNotOwnedException;
import at.fhtw.sampleapp.CustomExceptions.UnexpectedErrorException;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.UserAuthorizationMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Map;

public class DeckController extends Controller {
    private DeckFacade deckFacade;

    public DeckController(DeckFacade deckFacade) {this.deckFacade = deckFacade;}

    public Response changeCurrentDeck(Request request) {

        try {

            Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
            String authorization = request.getHeaderMap().getHeader("Authorization");
            int user_id = userAuthorization.get(authorization); //should be null if user doesn't exist
            //cardIds
            List<String> cardList = this.getObjectMapper().readValue(request.getBody(), new TypeReference<List<String>>(){});

            if (cardList.size() != 4) {
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{\"message\" : \"You must choose 4 cards\"}"
                );
            }
            if (this.deckFacade.updateDeck(user_id, cardList)) {
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ message: \"Deck changed successfully\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

            return new Response(
                    HttpStatus.FORBIDDEN,
                    ContentType.JSON,
                    "{\"message\" : \"Access Denied\"}"
            );
        } catch (CardNotOwnedException e) {
            e.printStackTrace();

            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"Card Not Owned\" }"
            );
        }

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{\"message\" : \"Could not edit deck\"}"
        );
    }
    public Response getCardsInUserDeck(Request request) {
        try {
            //ToDo:add Authorization

            Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
            String authorization = request.getHeaderMap().getHeader("Authorization");
            int user_id = userAuthorization.get(authorization); //should be null if user doesn't exist



            //check if defaultDeck is true
            if (deckFacade.getDefaultDeckBoolean(user_id)) {
                List<Card> cardList = this.deckFacade.getDefaultDeck(user_id);
                String userCardJSON = this.getObjectMapper().writeValueAsString(cardList);

                return new Response(
                        HttpStatus.OK,
                        ContentType.JSON,
                        userCardJSON
                );
            }

            List<Card> cardList = this.deckFacade.getDeck(user_id);
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
