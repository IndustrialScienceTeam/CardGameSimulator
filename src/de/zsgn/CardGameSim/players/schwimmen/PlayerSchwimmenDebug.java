package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.players.Player;

public class PlayerSchwimmenDebug extends PlayerSchwimmen {

    public PlayerSchwimmenDebug(String name) {
        super(name);
        
    }

    @Override
    public void OnPlayerGotCard(PlayerSchwimmen player, Card card) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void OnPlayerExchangedCards(PlayerSchwimmen player, Card card) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean takeThisHand(ArrayList<Card> hand) {
        return true;
    }

}
