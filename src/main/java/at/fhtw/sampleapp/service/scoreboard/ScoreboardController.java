package at.fhtw.sampleapp.service.scoreboard;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.model.Stats;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class ScoreboardController extends Controller {

    private ScoreboardFacade scoreboardFacade;
    public ScoreboardController(ScoreboardFacade scoreboardFacade) {
        this.scoreboardFacade = scoreboardFacade;
    }

    public Response getScoreboard() {
        try  {

            List<Stats> scoreboard = scoreboardFacade.getScoreboard();
            String scoreboardJSON = this.getObjectMapper().writeValueAsString(scoreboard);

            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    scoreboardJSON
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }
}
