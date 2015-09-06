package de.zsgn.CardGameSim;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardFactory;

public class CardGameSimStart {

    public static void main(String[] args) {
        testCardFactory();
    }

    public static void testCardFactory() {
        for (Card card : CardFactory.getSkatSet()) {
            System.out.println(card);
        }
        
    }

}
