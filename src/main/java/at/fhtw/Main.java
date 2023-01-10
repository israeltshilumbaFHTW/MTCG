package at.fhtw;

import at.fhtw.httpserver.utils.Router;
import at.fhtw.httpserver.server.Server;
import at.fhtw.sampleapp.service.batlles.BattleService;
import at.fhtw.sampleapp.service.cards.CardService;
import at.fhtw.sampleapp.service.decks.DeckService;
import at.fhtw.sampleapp.service.echo.EchoService;
import at.fhtw.sampleapp.service.packages.PackageService;
import at.fhtw.sampleapp.service.scoreboard.ScoreboardService;
import at.fhtw.sampleapp.service.session.SessionService;
import at.fhtw.sampleapp.service.stats.StatsService;
import at.fhtw.sampleapp.service.trading.TradingService;
import at.fhtw.sampleapp.service.transaction.TransactionService;
import at.fhtw.sampleapp.service.user.UserService;
import at.fhtw.sampleapp.service.weather.WeatherService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(10001, configureRouter());
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Router configureRouter()
    {
        Router router = new Router();
        router.addService("/weather", new WeatherService());
        router.addService("/echo", new EchoService());
        router.addService("/users", new UserService());
        router.addService("/sessions", new SessionService());
        router.addService("/packages", new PackageService());
        router.addService("/transactions/packages", new TransactionService());
        router.addService("/transactions", new TransactionService());
        router.addService("/cards", new CardService());
        router.addService("/deck", new DeckService());
        router.addService("/stats", new StatsService());
        router.addService("/score", new ScoreboardService());
        router.addService("/battles", new BattleService());
        router.addService("/tradings", new TradingService());
        return router;
    }
}
