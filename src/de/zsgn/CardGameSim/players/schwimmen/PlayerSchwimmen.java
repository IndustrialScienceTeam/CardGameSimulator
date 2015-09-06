package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.players.Player;

public abstract class PlayerSchwimmen extends Player {
    protected ArrayList<Card> hand=new ArrayList<Card>();
    protected PlayerSchwimmen(String name) {
        super(name);
    }

    public void giveHand(ArrayList<Card> hand) {
        this.hand=hand;
        
    }

}
