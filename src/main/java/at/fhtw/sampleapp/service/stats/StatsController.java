package at.fhtw.sampleapp.service.stats;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.model.Stats;
import at.fhtw.sampleapp.service.DatabaseConnection;
import at.fhtw.sampleapp.service.UserAuthorizationMap;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class StatsController extends Controller {
    private StatsFacade statsFacade;

    public StatsController(StatsFacade statsFacade) {
        this.statsFacade = statsFacade;
    }

    public Response getUserStats(Request request) {

        if(request.getHeaderMap().getHeader("Authorization") == null) {

            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"User is unauthorized\" }"
            );
        }


        Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
        String authorisation = request.getHeaderMap().getHeader("Authorization");
        int user_id = userAuthorization.get(authorisation);

        try {
            Stats userStats = this.statsFacade.getUserStats(user_id);
            String userStatsJSON = this.getObjectMapper().writeValueAsString(userStats);

            DatabaseConnection.commitTransaction();
            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    userStatsJSON
            );

        } catch (JsonProcessingException e) {
            e.printStackTrace();

            DatabaseConnection.rollbackTransaction();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }
}
