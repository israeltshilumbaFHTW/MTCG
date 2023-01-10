package at.fhtw.sampleapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Trading {

    @JsonAlias({"Id"})
    private String trading_id;

    @JsonAlias({"CardToTrade"})
    private String card_to_trade;

    @JsonAlias({"MinimumDamage"})
    private int trading_minimum_damage;

    @JsonAlias({"Type"})
    private String card_type;

    public Trading(){}
    public Trading(String trading_id, String card_to_trade, String card_type, int trading_minimum_damage) {

        this.trading_id = trading_id;
        this.card_to_trade = card_to_trade;
        this.trading_minimum_damage = trading_minimum_damage;
        this.card_type = card_type;
    }

    public String getTrading_id() {
        return trading_id;
    }

    public void setTrading_id(String trading_id) {
        this.trading_id = trading_id;
    }

    public String getCard_to_trade() {
        return card_to_trade;
    }

    public void setCard_to_trade(String card_to_trade) {
        this.card_to_trade = card_to_trade;
    }

    public int getTrading_minimum_damage() {
        return trading_minimum_damage;
    }

    public void setTrading_minimum_damage(int trading_minimum_damage) {
        this.trading_minimum_damage = trading_minimum_damage;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }
}
