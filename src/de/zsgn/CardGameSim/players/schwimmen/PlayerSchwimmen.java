package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.players.Player;

public abstract class PlayerSchwimmen extends Player {
    protected ArrayList<Card> hand=new ArrayList<Card>();
    protected PlayerSchwimmen(String name) {
        super(name);
    }

    public ArrayList<Card> giveHand(ArrayList<Card> hand, ArrayList<Card> extrahand) {
        if(extrahand!=null){
            if(!takeThisHand(hand)){
                this.hand=extrahand;
              return hand;  
            }
        }
        this.hand=hand;
        return extrahand;
        
    }
    public abstract void OnPlayerGotCard(PlayerSchwimmen player, Card card);
    public abstract void OnPlayerExchangedCards(PlayerSchwimmen player, Card card);
    protected abstract boolean takeThisHand(ArrayList<Card> hand);

    public ArrayList<Card> getHand() {
        return hand;
    }
    

}
