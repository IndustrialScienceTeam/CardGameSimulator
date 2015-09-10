package de.zsgn.CardGameSim;

import java.util.Hashtable;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardFactory;
import de.zsgn.CardGameSim.games.Game;
import de.zsgn.CardGameSim.games.GameSchwimmen;
import de.zsgn.CardGameSim.players.Player;
import de.zsgn.CardGameSim.players.schwimmen.PlayerSchwimmen;
import de.zsgn.CardGameSim.players.schwimmen.PlayerSchwimmenDebug;

public class CardGameSimStart {

    public static void main(String[] args) {
        Game schwimmen=new GameSchwimmen(new PlayerSchwimmen[]{new PlayerSchwimmenDebug("DICK"),new PlayerSchwimmenDebug("DOOF")});
        Hashtable<Player, Integer> wins=new Hashtable<Player, Integer>();
        try {
            for (int i = 0; i < 3; i++) {
                Player winner=schwimmen.startGame();
                int oldwins=0;
                if(wins.get(winner)!=null){
                oldwins=wins.get(winner);
                }
                wins.put(winner, oldwins++);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Player player : wins.keySet()) {
            System.out.println(player+" - "+wins.get(player));
        }
    }

    public static void testCardFactory() {
        for (Card card : CardFactory.getSkatSet()) {
            System.out.println(card);
        }
        
    }

}
