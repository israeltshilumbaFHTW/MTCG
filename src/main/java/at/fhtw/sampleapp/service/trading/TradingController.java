package at.fhtw.sampleapp.service.trading;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.customExceptions.CustomException;
import at.fhtw.sampleapp.model.Trading;
import at.fhtw.sampleapp.service.UserAuthorizationMap;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public class TradingController extends Controller {
    private TradingFacade tradingFacade;

    public TradingController(TradingFacade tradingFacade) {
        this.tradingFacade = tradingFacade;
    }

    public Response getTradingList() {
        //not necessary
        try {
            List<Trading> tradingList = this.tradingFacade.getTradingList();

            if(tradingList.size() == 0) {

                return new Response(
                        HttpStatus.OK,
                        ContentType.JSON,
                        "{ \"message\" : \"No trades available\" }"
                );
            }

            String tradingListJSON = this.getObjectMapper().writeValueAsString(tradingList);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    tradingListJSON
            );

        } catch (JsonProcessingException e) {
            System.err.println("JSON processing Error");
            e.printStackTrace();

            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }

    public Response addTrade(Request request) {

        Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
        String authorization = request.getHeaderMap().getHeader("Authorization");
        int user_id = userAuthorization.get(authorization);

        try {
            Trading trade = this.getObjectMapper().readValue(request.getBody(), Trading.class);
            if(this.tradingFacade.addTrade(user_id, trade)) {
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ \"message\": \"Trade added\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.err.println("JSON Derulo");
        } catch (CustomException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());

            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    "{ \"message\" : \"Entry already exists\" }"
            );
        } catch (NullPointerException e ) {
            e.printStackTrace();

            return new Response(
                    HttpStatus.UNAUTHORIZED,
                    ContentType.JSON,
                    "{ \"message\" : \"User is unauthorized\" }"
            );
        }

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{ \"message\" : \"Internal Server Error\" }"
        );
    }


    public Response commitTrade(Request request) {

        Map<String, Integer> userAuthorization = UserAuthorizationMap.getAuthorization();
        String authorization = request.getHeaderMap().getHeader("Authorization");
        int user_id = userAuthorization.get(authorization);


        try {
            String tradeId = request.getPathParts().get(1);
            String tradeCard = this.getObjectMapper().readValue(request.getBody(), String.class);

            if(this.tradingFacade.startTrade(tradeId, tradeCard, user_id)) {
                return new Response(
                        HttpStatus.OK,
                        ContentType.JSON,
                        "{ \"message\": \"Trade Successful\" }"
                );
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("User not authorized");
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing error");
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }."
            );
        } catch (CustomException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());

            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    "{ \"message\" : \"" + e.getMessage() + "\" }"
            );
        }
        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "{ \"message\" : \"Bad Request\" }"
        );
    }

}
