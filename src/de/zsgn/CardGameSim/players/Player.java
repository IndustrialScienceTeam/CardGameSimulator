package de.zsgn.CardGameSim.players;

import de.zsgn.CardGameSim.games.Game;

public abstract class Player {
	public final static  boolean DEBUGMODE = false;
    public final String name;

    protected Player(String name) {
        super();
        this.name = name;
    }
    protected void printDebug(String string, int mode ) {
       
       
       switch (mode) {
		case 0:  //unnamed mode
			if(DEBUGMODE){
				if(DEBUGMODE) {System.out.println(this+": "+string);}
			}
			break;
		case 1:  //game log
			System.out.println(string);
			break;
		case 2: //MAE AI debug prints
			System.out.println("MAE AI: " +string);
			break;
		default:
			break;
		}
    	   
       
    }
    @Override
    public String toString() {
        return "Player " + name;
    }

   
    

}
