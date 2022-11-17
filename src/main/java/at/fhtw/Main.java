package at.fhtw;

import at.fhtw.httpserver.utils.Router;
import at.fhtw.httpserver.server.Server;
import at.fhtw.sampleapp.db.DbInit;
import at.fhtw.sampleapp.service.echo.EchoService;
import at.fhtw.sampleapp.service.session.SessionService;
import at.fhtw.sampleapp.service.user.UserService;
import at.fhtw.sampleapp.service.weather.WeatherService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(10001, configureRouter());
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        //Todo: elegantere Lösung finden
        try {
            DbInit dbInit = new DbInit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 */
    }

    private static Router configureRouter()
    {
        Router router = new Router();
        router.addService("/weather", new WeatherService());
        router.addService("/echo", new EchoService());
        router.addService("/users", new UserService());
        router.addService("/sessions", new SessionService());
        router.addService("/packages", new PackageService());

        return router;
    }
}
