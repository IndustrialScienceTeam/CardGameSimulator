package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;
import java.util.Collections;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardExchange;
import de.zsgn.CardGameSim.players.Player;

public abstract class PlayerSchwimmen extends Player {
    private ArrayList<Card> hand=new ArrayList<Card>();
    protected PlayerSchwimmen(String name) {
        super(name);
    }

    public ArrayList<Card> giveHand(ArrayList<Card> hand, ArrayList<Card> extrahand) {
        if(extrahand!=null){
            if(!wanttotakeThisHand(hand)){
                this.hand=extrahand;
              return hand;  
            }
        }
        this.hand=hand;
        return extrahand;
        
    }
    
    public abstract CardExchange getCardExchange(ArrayList<Card> cardsontable);
    protected abstract boolean wanttotakeThisHand(ArrayList<Card> hand);
    public abstract boolean wantToPass(ArrayList<Card> cardsontable);
    public abstract void playerChangedCards(CardExchange cardex, PlayerSchwimmen player);
    public abstract void playerPassed(PlayerSchwimmen player) ;

    public final ArrayList<Card> getHand() {
        return ( ArrayList<Card>)hand.clone();
    }

    public final void setHand(ArrayList<Card> newHand) {
        this.hand=newHand;
        
    }

}
