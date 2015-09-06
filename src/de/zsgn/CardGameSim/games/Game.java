package de.zsgn.CardGameSim.games;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.players.Player;

public abstract class Game {
protected final Player[] players;
protected final Card[] cardset;
protected final int maxplayers;

protected Game(Player[] players, Card[] cardset, int maxplayers) {
    super();
    this.players = players;
    this.cardset = cardset;
    this.maxplayers=maxplayers;
    if(players.length>maxplayers)
        throw new InstantiationError("Too many Players");
}
public abstract Player startGame(int rounds);
}
