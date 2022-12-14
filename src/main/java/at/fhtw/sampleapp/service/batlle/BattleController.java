package at.fhtw.sampleapp.service.batlle;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.CustomExceptions.PlayerAlreadyInQueueException;
import at.fhtw.sampleapp.CustomExceptions.WaitTimeoutException;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.service.UserAuthorizationMap;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class BattleController extends Controller {
    private BattleFacade battleFacade;

    public BattleController(BattleFacade battleFacade) {
        this.battleFacade = battleFacade;
    }

    public Response startBattle(Request request) {
        System.out.println("Start Battle");

        try {

            Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
            String authorization = request.getHeaderMap().getHeader("Authorization");
            int user_id = userAuthorization.get(authorization);

            String message = battleFacade.initBattle(user_id);
            String messageJson = this.getObjectMapper().writeValueAsString(message);


            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    messageJson
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
        } catch (WaitTimeoutException e) {
            e.printStackTrace();

            return new Response(
                    HttpStatus.NO_CONTENT,
                    ContentType.JSON,
                    "{ \"message\" : \"not opponent found\" }"
            );
        } catch (PlayerAlreadyInQueueException e) {
            e.printStackTrace();

            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.PLAIN_TEXT,
                    "{ \"message\" : \"player is already in queue\" }"
            );
        }
    }
}
