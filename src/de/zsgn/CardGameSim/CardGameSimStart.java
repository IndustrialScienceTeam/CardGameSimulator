package de.zsgn.CardGameSim;

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
        try {
            Player winner=schwimmen.startGame(1);
            System.out.println("Gewinner: "+winner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testCardFactory() {
        for (Card card : CardFactory.getSkatSet()) {
            System.out.println(card);
        }
        
    }

}
