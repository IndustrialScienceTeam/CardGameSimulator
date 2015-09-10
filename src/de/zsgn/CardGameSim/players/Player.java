package de.zsgn.CardGameSim.players;

import de.zsgn.CardGameSim.games.Game;

public abstract class Player {
    public final String name;

    protected Player(String name) {
        super();
        this.name = name;
    }
    protected void printDebug(String string) {
        System.out.println(this+": "+string);
    }
    @Override
    public String toString() {
        return "Player " + name;
    }

   
    

}
