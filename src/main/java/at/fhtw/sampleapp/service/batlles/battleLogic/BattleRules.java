package at.fhtw.sampleapp.service.batlles.battleLogic;

import at.fhtw.sampleapp.model.Card;

import java.util.Map;
import java.util.Objects;

public class BattleRules {
    public BattleRules() {
    }

    public double getEffect(Card card1, Card card2) {
        //if no spell cards are being used return 1
        if(!Objects.equals(card1.getCard_type(), "spell") || !Objects.equals(card2.getCard_type(), "spell")) {
            return 1;
        }

        String attacker = card1.getCard_element();
        String defender = card2.getCard_element();

        if (Objects.equals(attacker, defender)) {
            return 1;
        }

        if (Objects.equals(attacker, "normal") && Objects.equals(defender, "water")) {
            return 2;
        }

        if (Objects.equals(attacker, "normal") && Objects.equals(defender, "fire")) {
            return 0.5;
        }
        if (Objects.equals(attacker, "water") && Objects.equals(defender, "fire")) {
            return 2;
        }

        if (Objects.equals(attacker, "fire") && Objects.equals(defender, "water")) {
            return 0.5;
        }

        if (Objects.equals(attacker, "water") && Objects.equals(defender, "normal")) {
            return 0.5;
        }

        if (Objects.equals(attacker, "fire") && Objects.equals(defender, "normal")) {
            return 2;
        }

        return -255;
    }

    public String getSpecialInteraction(String cardClass1, String cardClass2) {
        if (Objects.equals(cardClass1, "goblin") && Objects.equals(cardClass2, "dragon")) {
            System.out.printf("Goblin is too afraid to attack dragon\n");
            return cardClass2;
        }

        if (Objects.equals(cardClass1, "dragon") && Objects.equals(cardClass2, "goblin")) {
            System.out.printf("Goblin is too afraid to attack dragon\n");
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "wizard") && Objects.equals(cardClass2, "ork")) {
            System.out.printf("Ork is being mind controlled and cannot attack\n");
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "ork") && Objects.equals(cardClass2, "wizard")) {
            System.out.printf("Ork is being mind controlled and cannot attack\n");
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "knight") && Objects.equals(cardClass2, "waterSpell")) {
            System.out.printf("Knight drowns due to his heavy armor\n");
            return cardClass2;
        }

        if (Objects.equals(cardClass2, "waterSpell") && Objects.equals(cardClass1, "knight")) {
            System.out.printf("Knight drowns due to his heavy armor\n");
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "kraken") && Objects.equals(cardClass2, "spell")) {
            System.out.printf("Kraken is immune against spells\n");
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "spell") && Objects.equals(cardClass2, "kraken")) {
            System.out.printf("Kraken is immune against spells\n");
            return cardClass2;
        }

        if (Objects.equals(cardClass1, "fireElf") && Objects.equals(cardClass2, "dragon")) {
            System.out.printf("FireElves cannot be hit by dragons\n");
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "dragon") && Objects.equals(cardClass2, "fireElf")) {
            System.out.printf("FireElves cannot be hit by dragons\n");
            return cardClass2;
        }
        return "no interaction";
    }
}
