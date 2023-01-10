package at.fhtw.sampleapp.service.trading;

import at.fhtw.sampleapp.customExceptions.CustomException;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.model.Trading;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;
import at.fhtw.sampleapp.service.repoCollection.RepoTrading;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoCardDecks;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserCards;

import java.util.List;

public class TradingFacade {

    public List<Trading> getTradingList(){
        RepoTrading repoTrading = new RepoTrading();

        List<Trading> tradingList = repoTrading.getAllTrades();
        return tradingList;
    }

    public boolean addTrade(int user_id,Trading trade) throws CustomException {
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
                throw new CustomException("Trade already exists");
            }
        }
        //remove Card from Deck and UserCardList
        RepoDecks repoDecks = new RepoDecks();
        RepoCardDecks repoCardDecks = new RepoCardDecks();
        RepoUserCards repoUserCards = new RepoUserCards();

        List<String> cardIdList = repoCardDecks.getCardsInDeck(repoDecks.getDeckIdWithUserId(user_id));

        int iterator = 0;
        for(String card_id : cardIdList) {
            if(card_id.equals(cardIdList.get(iterator))) {
                //remove Card
               repoDecks.removeCardFromDeck(iterator + 1, user_id);
            }
        }

        return repoTrading.addTrade(user_id, trade);
    }


    public boolean startTrade(String tradeId, String cardUserWantsToTrade, int user_id) throws CustomException {
        RepoTrading repoTrading = new RepoTrading();
        RepoUserCards repoUserCards = new RepoUserCards();
        RepoCard repoCard = new RepoCard();

        //check if I am trading with myself
        int tradeUserId = repoTrading.getUserIdFromTradingId(tradeId);
        if (user_id == tradeUserId ) {
            throw new CustomException("Cannot Trade with yourself");
        }
        //get Card I want to trade
        Card cardUserTrades = repoCard.getCard(cardUserWantsToTrade);

        //check if my cardUserTrades has the minimum damage
        Trading trading = repoTrading.getTrade(tradeId);
        if(trading.getTrading_minimum_damage() > cardUserTrades.getCard_damage()) {
            throw new CustomException("Card is not strong enough");
        }

        //if yes: remove cardUserTrades from player -> add new player cardUserTrades
        if(!repoUserCards.deleteUserCard(user_id, cardUserWantsToTrade)) {
            return false;
        }
        if(!repoUserCards.deleteUserCard(tradeUserId, trading.getCard_to_trade())) {
            return false;
        }
        if (!repoUserCards.addUserCard(tradeUserId, cardUserWantsToTrade)) {
            return false;
        }
        if (!repoUserCards.addUserCard(user_id, trading.getCard_to_trade())) {
            return false;
        }
        //delete trade
        repoTrading.deleteTrade(tradeId);
        return true;
    }

    public boolean deleteTrade(int user_id, String trading_id) throws CustomException {
        //check if I am authorized to delete the trade
        RepoTrading repoTrading = new RepoTrading();
        int trading_user_id;

        trading_user_id = repoTrading.getUserIdFromTradingId(trading_id);

        if(trading_user_id == -1) {
            throw new CustomException("Trade does not exist");
        }

        if(trading_user_id != user_id) {
            throw new CustomException("User is unauthorized");
        }

        repoTrading.deleteTrade(trading_id);
        return true;
    }
}
