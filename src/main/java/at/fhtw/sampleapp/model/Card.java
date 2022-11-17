package at.fhtw.sampleapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Card {
    @JsonAlias({"Id"})
    private String card_id;

    @JsonAlias({"Name"})
    private String card_name;


    @JsonAlias({"Damage"})
    private int card_damage;

    public Card(){}

    public Card(String card_id, String card_name, int card_damage) {
        this.card_id = card_id;
        this.card_name = card_name;
        this.card_damage = card_damage;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public int getCard_damage() {
        return card_damage;
    }

    public void setCard_damage(int card_damage) {
        this.card_damage = card_damage;
    }
}
