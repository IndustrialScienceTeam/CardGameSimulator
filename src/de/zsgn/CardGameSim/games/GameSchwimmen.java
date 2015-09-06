package de.zsgn.CardGameSim.games;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardFactory;
import de.zsgn.CardGameSim.games.choice.GameChoice;
import de.zsgn.CardGameSim.players.Player;
import de.zsgn.CardGameSim.players.schwimmen.PlayerSchwimmen;

public class GameSchwimmen extends Game {

    protected GameSchwimmen(PlayerSchwimmen[] players) {
        super(players, CardFactory.getSkatSet(), 5);
    }

    @Override
    public Player startGame() {
        ArrayList<Card> cardslist=new ArrayList<Card>();
        for (Card card : cardset) {
            cardslist.add(card);
        }
        Collections.shuffle(cardslist);
        ArrayDeque<Card> deck=new ArrayDeque<>(cardslist);
        for (PlayerSchwimmen player : getPlayersforSchwimmen()) {
        }
        return null;
    }

    protected PlayerSchwimmen[] getPlayersforSchwimmen(){
        if(players instanceof PlayerSchwimmen[]){
            return (PlayerSchwimmen[])players;
        }
        return null;
    }

}
