package at.fhtw.sampleapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Card {
    @JsonAlias({"Id"})
    private String card_id;

    @JsonAlias({"Name"})
    private String card_name;


    @JsonAlias({"Damage"})
    private int card_damage;

    @JsonAlias({"Class"})
    private String card_class;

    @JsonAlias({"Type"})
    private String card_type;

    @JsonAlias({"Element"})
    private String card_element;

    public Card(){}

    public Card(String card_id, String card_name, int card_damage, String card_class, String card_type, String card_element) {
        this.card_id = card_id;
        this.card_name = card_name;
        this.card_damage = card_damage;
        this.card_class = card_class;
        this.card_type = card_type;
        this.card_element = card_element;
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

    public String getCard_class() {
        return card_class;
    }

    public void setCard_class(String card_class) {
        this.card_class = card_class;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_element() {
        return card_element;
    }

    public void setCard_element(String card_element) {
        this.card_element = card_element;
    }
}
