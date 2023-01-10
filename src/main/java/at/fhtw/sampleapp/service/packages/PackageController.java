package at.fhtw.sampleapp.service.packages;

import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.customExceptions.DBAccessException;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.DatabaseConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Objects;

public class PackageController extends Controller {

    private PackageFacade packageFacade;

    public PackageController(PackageFacade packageFacade) {
        this.packageFacade = packageFacade;
    }

    public Response addPackage(Request request) {
        List<Card> cardList;
        String authorisation = request.getHeaderMap().getHeader("Authorization");
        if (!Objects.equals(authorisation, "Basic admin-mtcgToken")) {

            return new Response(
                    HttpStatus.FORBIDDEN,
                    ContentType.JSON,
                    "{\"message\" : \"Access Denied\"}"
            );
        }
        try {
            cardList = this.getObjectMapper().readValue(request.getBody(), new TypeReference<List<Card>>() {
            });

            if (this.packageFacade.addPackage(cardList)) {

                DatabaseConnection.commitTransaction();
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ \"message\": \"Package added successfully\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DatabaseConnection.rollbackTransaction();
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{\"message\" : \"Bad Request\"}"
        );
    }
}
