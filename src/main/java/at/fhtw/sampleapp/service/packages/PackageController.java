package at.fhtw.sampleapp.service.packages;

import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.service.packages.PackageDAL;
import com.fasterxml.jackson.core.JsonProcessingException;
import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class PackageController extends Controller{

    private PackageDAL packageDAL;
    public PackageController(PackageDAL packageDAL) {
        this.packageDAL = packageDAL;
    }

    public Response addPackage(Request request) {
        List<Card> cardList;

        try {
            cardList = this.getObjectMapper().readValue(request.getBody(), new TypeReference<List<Card>>(){});

            if (this.packageDAL.addPackageDAL(cardList)) {
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ message: \"Login successful\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Response(
                HttpStatus.FORBIDDEN,
                ContentType.JSON,
                "{\"message\" : \"Access Denied\"}"
        );
    }
}
