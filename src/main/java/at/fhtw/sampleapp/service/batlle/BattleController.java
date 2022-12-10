package at.fhtw.sampleapp.service.batlle;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;

public class BattleController extends Controller {
    private BattleFacade battleFacade;

    public BattleController(BattleFacade battleFacade) {
        this.battleFacade = battleFacade;
    }

    public Response startBattle(Request request) {
        System.out.println("Start Battle");
            //check if waiting room is empty or not

            //check if user is already in waiting Room

            //if it is empty -> create a thread
            //one thread sends to the user that you are waiting
            //other thread is in
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    "{\"message\" : \"Bad Request\"}"
            );
    }
}
