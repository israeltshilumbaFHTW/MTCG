package at.fhtw.sampleapp.service.batlles.battleLogic;

import at.fhtw.sampleapp.model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleRulesTest {

    @Test
    void noSpellCards() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("water");

        Card card2 = new Card();
        card2.setCard_element("fire");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(1, multiplier);
    }
    @Test
    void attackerWaterDefenderWater() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("water");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("water");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(1, multiplier);
    }

    @Test
    void attackerWaterDefenderNormal() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("water");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("normal");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(0.5, multiplier);
    }

    @Test
    void attackerWaterDefenderFire() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("water");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("fire");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(2, multiplier);
    }

    @Test
    void attackerFireDefenderFire() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("fire");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("fire");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(1, multiplier);
    }



    @Test
    void attackerFireDefenderNormal() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("fire");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("normal");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(2, multiplier);
    }
    @Test
    void attackerFireDefenderWater() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("fire");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("water");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(0.5, multiplier);
    }
    @Test
    void attackerNormalDefenderNormal() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("normal");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("normal");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(1, multiplier);
    }

    @Test
    void attackerNormalDefenderFire() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("normal");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("fire");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(0.5, multiplier);
    }
    @Test
    void attackerNormalDefenderWater() {
        BattleRules battleRules = new BattleRules();
        Card card1 = new Card();
        card1.setCard_element("normal");
        card1.setCard_type("spell");

        Card card2 = new Card();
        card2.setCard_element("water");
        card2.setCard_type("spell");

        double multiplier = battleRules.getEffect(card1, card2);
        assertEquals(2, multiplier);
    }

    @Test
    void goblinDragonInteraction() {
        BattleRules battleRules = new BattleRules();

        String winner = battleRules.getSpecialInteraction("goblin", "dragon");
        assertEquals("dragon", winner);

        winner = battleRules.getSpecialInteraction("dragon", "goblin");
        assertEquals("dragon", winner);
    }
    @Test
    void wizardOrkInteraction() {
        BattleRules battleRules = new BattleRules();

        String winner = battleRules.getSpecialInteraction("wizard", "ork");
        assertEquals("wizard", winner);

        winner = battleRules.getSpecialInteraction("ork", "wizard");
        assertEquals("wizard", winner);
    }

    @Test
    void waterSpellKnightInteraction() {
        BattleRules battleRules = new BattleRules();

        String winner = battleRules.getSpecialInteraction("knight", "waterSpell");
        assertEquals("waterSpell", winner);

        winner = battleRules.getSpecialInteraction("waterSpell", "knight");
        assertEquals("waterSpell", winner);
    }
    @Test
    void krakenSpellInteraction() {
        BattleRules battleRules = new BattleRules();

        String winner = battleRules.getSpecialInteraction("kraken", "spell");
        assertEquals("kraken", winner);

        winner = battleRules.getSpecialInteraction("spell", "kraken");
        assertEquals("kraken", winner);
    }

    @Test
    void fireElfDragonInteraction() {
        BattleRules battleRules = new BattleRules();

        String winner = battleRules.getSpecialInteraction("dragon", "fireElf");
        assertEquals("fireElf", winner);

        winner = battleRules.getSpecialInteraction("fireElf", "dragon");
        assertEquals("fireElf", winner);
    }

    @Test
    void fireElfSpellInteraction() {
        //should result in no interaction

        BattleRules battleRules = new BattleRules();

        String winner = battleRules.getSpecialInteraction("spell", "fireElf");
        assertEquals("no interaction", winner);

        winner = battleRules.getSpecialInteraction("fireElf", "spell");
        assertEquals("no interaction", winner);
    }
}