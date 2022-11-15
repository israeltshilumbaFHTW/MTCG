package at.fhtw.sampleapp.service.weather;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;
import at.fhtw.sampleapp.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherService implements Service {
    private final WeatherController weatherController;

    public WeatherService() {
        this.weatherController = new WeatherController(new WeatherDAL());
    }

    @Override
    public Response handleRequest(Request request) {
        if (request.getMethod() == Method.GET &&
            request.getPathParts().size() > 1) {
            //successful database query
            return this.weatherController.getWeather(request.getPathParts().get(1));
        } else if (request.getMethod() == Method.GET) {
            //successful database query
            return this.weatherController.getWeather();
        } else if (request.getMethod() == Method.POST) {
            //successful database query
            return this.weatherController.addWeather(request);
        }

        //unsuccessful database query
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "[]"
        );
    }
}
