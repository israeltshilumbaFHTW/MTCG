package at.fhtw.sampleapp.service.batlles.battleLogic;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.batlles.battleLogic.documentation.Documentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BattleRules {
    public BattleRules() {
    }

    public double getEffect(Card card1, Card card2) {
        //if no spell cards are being used return 1
        if (!Objects.equals(card1.getCard_type(), "spell") || !Objects.equals(card2.getCard_type(), "spell")) {
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
        Documentation battleDocumentation = Documentation.getDocumentation();

        if (Objects.equals(cardClass1, "goblin") && Objects.equals(cardClass2, "dragon")) {
            System.out.printf("Goblin is too afraid to attack dragon\n");

            String interactionMessage = "Goblin is too afraid too attack dragon";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass2;
        }

        if (Objects.equals(cardClass1, "dragon") && Objects.equals(cardClass2, "goblin")) {
            System.out.printf("Goblin is too afraid to attack dragon\n");

            String interactionMessage = "Goblin is too afraid too attack dragon";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "ork") && Objects.equals(cardClass2, "wizard")) {
            System.out.printf("Ork is being mind controlled and cannot attack\n");

            String interactionMessage = "Ork is being mind controlled and cannot attack";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass2;
        }

        if (Objects.equals(cardClass1, "wizard") && Objects.equals(cardClass2, "ork")) {
            System.out.printf("Ork is being mind controlled and cannot attack\n");

            String interactionMessage = "Ork is being mind controlled and cannot attack";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "knight") && Objects.equals(cardClass2, "waterSpell")) {
            System.out.printf("Knight drowns due to his heavy armor\n");

            String interactionMessage = "Knight drowns due to his heavy armor";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass2;
        }

        if (Objects.equals(cardClass1, "waterSpell") && Objects.equals(cardClass2, "knight")) {
            System.out.printf("Knight drowns due to his heavy armor\n");

            String interactionMessage = "Knight drowns due to his heavy armor";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "kraken") && Objects.equals(cardClass2, "spell")) {
            System.out.printf("Kraken is immune against spells\n");

            String interactionMessage = "Kraken is immune against spells";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "spell") && Objects.equals(cardClass2, "kraken")) {
            System.out.printf("Kraken is immune against spells\n");

            String interactionMessage = "Kraken is immune against spells";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass2;
        }

        if (Objects.equals(cardClass1, "fireElf") && Objects.equals(cardClass2, "dragon")) {
            System.out.printf("FireElves cannot be hit by dragons\n");

            String interactionMessage = "FireElves cannot be hit by dragons";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass1;
        }

        if (Objects.equals(cardClass1, "dragon") && Objects.equals(cardClass2, "fireElf")) {
            System.out.printf("FireElves cannot be hit by dragons\n");

            String interactionMessage = "FireElves cannot be hit by dragons";
            battleDocumentation.addBattleLog(interactionMessage);
            return cardClass2;
        }

        return "no interaction";
    }

    public List<Card> buffCard(Card card1, Card card2) {

        List<Card> cardList = dragonInteraction(card1, card2);
        card1 = cardList.get(0);
        card2 = cardList.get(1);

        cardList = wizardInteraction(card1, card2);
        card1 = cardList.get(0);
        card2 = cardList.get(1);

        cardList = goblinInteraction(card1, card2);

        return cardList;
    }

    private List<Card> goblinInteraction(Card card1, Card card2) {
        Documentation battledocumentation = Documentation.getDocumentation();

        List<Card> cardList = new ArrayList<>();

        if (Objects.equals(card1.getCard_class(), "goblin")) {
            //mimic
            card1.setCard_element(card2.getCard_element());
            cardList.add(card1);
            cardList.add(card2);

            String interactionMessage = "Player 1 Goblin changes element to: " + card2.getCard_element() + "\n";
            battledocumentation.addBattleLog(interactionMessage);

            return cardList;
        }

        if (Objects.equals(card2.getCard_class(), "goblin")) {
            card2.setCard_element(card1.getCard_element());

            cardList.add(card1);
            cardList.add(card2);

            String interactionMessage = "Player 2 Goblin changes element to: " + card1.getCard_element() + "\n";
            battledocumentation.addBattleLog(interactionMessage);
            return cardList;
        }
        cardList.add(card1);
        cardList.add(card2);

        return cardList;
    }

    private List<Card> wizardInteraction(Card card1, Card card2) {

        Documentation battleDocumentation = Documentation.getDocumentation();
        String interaction = "";
        if (Objects.equals(card1.getCard_class(), "wizard")) {

            int randomNumber;
            randomNumber = (int) (Math.random() * 10) % 20;
            if (randomNumber == 0) {
                interaction =  "Wizard changed type to fire";
                card1.setCard_element("fire");
            }

            if (randomNumber == 1) {
                interaction =  "Wizard changed type to normal";
                card1.setCard_element("normal");
            }

            if (randomNumber == 2) {
                interaction =  "Wizard changed type to water";
                card1.setCard_element("water");
            }
            List<Card> cardList = new ArrayList<>();
            cardList.add(card1);
            cardList.add(card2);

            if(!Objects.equals("", interaction)) {
                battleDocumentation.addBattleLog(interaction);
            }

            return cardList;
        }

        if (Objects.equals(card2.getCard_class(), "wizard")) {

            int randomNumber;
            randomNumber = (int) (Math.random() * 10) % 10;
            if (randomNumber == 0) {
                interaction =  "Player 2 Wizard changed type to fire";
                card2.setCard_element("fire");
            }

            if (randomNumber == 1) {
                interaction =  "Player 2 Wizard changed type to normal";
                card2.setCard_element("normal");
            }

            if (randomNumber == 2) {
                interaction =  "Player 2 Wizard changed type to water";
                card2.setCard_element("water");
            }
            List<Card> cardList = new ArrayList<>();
            cardList.add(card1);
            cardList.add(card2);

            if(!Objects.equals("", interaction)) {
                battleDocumentation.addBattleLog(interaction);
            }

            return cardList;
        }

        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);

        return cardList;
    }

    private List<Card> dragonInteraction(Card card1, Card card2) {
        Documentation battleDocumentation = Documentation.getDocumentation();
        String interaction = "";
        if (Objects.equals(card1.getCard_class(), "dragon")) {
            //loses 1 damage per Round if it gets unlucky
            int randomNumber;
            randomNumber = (int) (Math.random() * 10) % 10;
            if (randomNumber == 0) {
                interaction = "Player 1 dragon got a 2x damage buff";
                battleDocumentation.addBattleLog(interaction);
                card1.setCard_damage(card1.getCard_damage() * 2);
            } else {
                interaction = "Player 1 dragon lost 5 health";
                battleDocumentation.addBattleLog(interaction);
                card1.setCard_damage(card1.getCard_damage() - 5);
            }
            List<Card> cardList = new ArrayList<>();
            cardList.add(card1);
            cardList.add(card2);
            return cardList;
        }
        if (Objects.equals(card2.getCard_class(), "dragon")) {
            //loses 1 damage per Round if it gets unlucky
            int randomNumber;
            randomNumber = (int) (Math.random() * 10) % 10;
            if (randomNumber == 29) {
                interaction = "Player 2 dragon got a 2x damage buff ";
                battleDocumentation.addBattleLog(interaction);
                card2.setCard_damage(card1.getCard_damage() * 2);
            } else {
                interaction = "Player 2 dragon lost 5 health";
                battleDocumentation.addBattleLog(interaction);
                card2.setCard_damage(card1.getCard_damage() - 5);
            }
            List<Card> cardList = new ArrayList<>();
            cardList.add(card1);
            cardList.add(card2);
            return cardList;
        }

        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        return cardList;
    }
}
