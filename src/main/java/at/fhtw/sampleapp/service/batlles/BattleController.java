package at.fhtw.sampleapp.service.batlles;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.customExceptions.DBAccessException;
import at.fhtw.sampleapp.customExceptions.PlayerAlreadyInQueueException;
import at.fhtw.sampleapp.customExceptions.WaitTimeoutException;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.model.UserCardModel;
import at.fhtw.sampleapp.service.DatabaseConnection;
import at.fhtw.sampleapp.service.UserAuthorizationMap;
import at.fhtw.sampleapp.service.batlles.battleLogic.documentation.Documentation;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

            Documentation documentation = Documentation.getDocumentation();

            //delete later:
            if (!documentation.isListEmpty()) {
                String documentationJson = this.getObjectMapper().writeValueAsString(documentation);

                String textMessage = documentation.getBattleLog().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n"));

                DatabaseConnection.commitTransaction();
                return new Response(
                        HttpStatus.OK,
                        ContentType.PLAIN_TEXT,
                        textMessage
                );
            }

            DatabaseConnection.commitTransaction();
            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    messageJson
            );

        } catch (JsonProcessingException e) {
            System.err.println("JSON processing error");
            e.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );

        } catch (NullPointerException e) {
            e.printStackTrace();
            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"User is unauthorized\" }"
            );
        } catch (WaitTimeoutException e) {
            e.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.NO_CONTENT,
                    ContentType.JSON,
                    "{ \"message\" : \"no opponent found\" }"
            );
        } catch (PlayerAlreadyInQueueException e) {
            e.printStackTrace();


            DatabaseConnection.commitTransaction();
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.PLAIN_TEXT,
                    "{ \"message\" : \"player is already in queue\" }"
            );
        }
    }

}
