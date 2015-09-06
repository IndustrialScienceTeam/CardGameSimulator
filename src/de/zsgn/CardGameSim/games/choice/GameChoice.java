package de.zsgn.CardGameSim.games.choice;

public class GameChoice {
    protected final int id;
    public GameChoice(int i) {
        this.id=i;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GameChoice)
            return this.id==((GameChoice)obj).getId();
        return false;
    }
    public int getId() {
        return id;
    }

}
