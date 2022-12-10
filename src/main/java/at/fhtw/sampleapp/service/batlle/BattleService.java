package at.fhtw.sampleapp.service.batlle;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;
import at.fhtw.sampleapp.service.batlle.BattleController;
import at.fhtw.sampleapp.service.packages.PackageController;
import at.fhtw.sampleapp.service.packages.PackageFacade;

public class BattleService implements Service {
    private final BattleController battleController;

    public BattleService() {
        this.battleController = new BattleController(new BattleFacade());
    }

    @Override
    public Response handleRequest(Request request) {
        if (request.getMethod() == Method.POST) {
            return this.battleController.startBattle(request);
        }

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "[]"
        );
    }
}
