package at.fhtw.sampleapp.service.trading;

import at.fhtw.sampleapp.customExceptions.TradeAlreadyExistsException;
import at.fhtw.sampleapp.model.Trading;
import at.fhtw.sampleapp.service.repoCollection.RepoTrading;

import java.util.List;

public class TradingFacade {

    public List<Trading> getTradingList(){
        RepoTrading repoTrading = new RepoTrading();

        List<Trading> tradingList = repoTrading.getAllTrades();
        return tradingList;
    }

    public boolean addTrade(int user_id,Trading trade) throws TradeAlreadyExistsException{
        //check if Trade already exists
        RepoTrading repoTrading = new RepoTrading();
        List<Trading> tradingList = repoTrading.getAllTrades();
        List<Trading> userTradingList = repoTrading.getTradeFromUser(user_id);

        if(tradingList.isEmpty()) {
            //remove card from user
            return repoTrading.addTrade(user_id, trade);
        }

        //check if User already has this entry

        for (Trading tradeEntry : userTradingList) {
            if (tradeEntry.getTrading_id().equals(trade.getTrading_id() ) ) { //
                throw new TradeAlreadyExistsException("Trade already exists");
            }
        }
        return repoTrading.addTrade(user_id, trade);
    }

    /*

    public boolean startTrade(String wantedCard, String tradeCard, int user_id) {
        //get

    }
     */
}
