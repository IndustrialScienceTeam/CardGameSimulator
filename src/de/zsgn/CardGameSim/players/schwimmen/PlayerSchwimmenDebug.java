package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;
import java.util.Random;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardExchange;
import de.zsgn.CardGameSim.players.Player;

public class PlayerSchwimmenDebug extends PlayerSchwimmen {

    public PlayerSchwimmenDebug(String name) {
        super(name);
        
    }

    @Override
    public CardExchange getCardExchange(ArrayList<Card> cardsontable) {
       if(new Random().nextBoolean()){
           return new CardExchange(true, null, null);
       }else{
           return new CardExchange(false,this.getHand().get(0),cardsontable.get(0));
       }
    }

    @Override
    protected boolean wanttotakeThisHand(ArrayList<Card> hand) {
        return new Random().nextBoolean(); 
    }

    @Override
    public boolean wantToPass(ArrayList<Card> cardsontable) {
        return new Random().nextInt(8)==0;
    }

    @Override
    public void playerChangedCards(CardExchange cardex, PlayerSchwimmen player) {
        printDebug("I do not care!!!",0);
        
    }

    @Override
    public void playerPassed(PlayerSchwimmen player) {
        printDebug("I do not care!!!",0);
        
    }

}
