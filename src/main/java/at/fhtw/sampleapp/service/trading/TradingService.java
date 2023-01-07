package at.fhtw.sampleapp.service.trading;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class TradingService implements Service {
   private final TradingController tradingController;

   public TradingService(){
       this.tradingController = new TradingController(new TradingFacade());
   }

   @Override
    public Response handleRequest(Request request) {

       try{
           if(request.getMethod() == Method.GET) {
               return this.tradingController.getTradingList();
           } else if(request.getMethod() == Method.POST && request.getPathParts().size() > 1) {//Try to trade with an existing card
               return this.tradingController.commitTrade(request);
           } else if(request.getMethod() == Method.POST) {
               return this.tradingController.addTrade(request);
           } else if(request.getMethod() == Method.DELETE) {
               return this.tradingController.deleteTrade(request);
           }

       } catch (NullPointerException e) {
           e.printStackTrace();
           System.err.println("NULLPOINTER IM USER SERVICE");
       }
       return new Response(
               HttpStatus.BAD_REQUEST,
               ContentType.JSON,
               "[]"
       );
   }
}
