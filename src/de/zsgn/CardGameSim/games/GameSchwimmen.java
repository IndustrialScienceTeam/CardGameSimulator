package de.zsgn.CardGameSim.games;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardExchange;
import de.zsgn.CardGameSim.cards.CardFactory;
import de.zsgn.CardGameSim.players.Player;
import de.zsgn.CardGameSim.players.schwimmen.PlayerSchwimmen;

public class GameSchwimmen extends Game {
    public final static int CARDSONHAND=3;
    public final static Random rand=new Random();
    protected ArrayList<Card> cardsontable=null;
    protected PlayerSchwimmen playerthatpassed=null;
    public final static  boolean DEBUGMODE = false;
    
    public final static int LOGGER_MISCS = 0;
    public final static boolean LOGGER_MISCS_ENABLED = false;
    public final static int LOGGER_GAMELOG = 1;
    public final static boolean LOGGER_GAMELOG_ENABLED = true;
    public final static int LOGGER_MAEAI = 2;
    public final static boolean LOGGER_MAEAI_ENABLED = true;
    
    public GameSchwimmen(PlayerSchwimmen[] players) {
        super(players, CardFactory.getSkatSet(), 5);
    }

    
    @Override
    public Player startGame() throws Exception {
            cardsontable= giveHands(rand.nextInt(players.length));
            printGameInfo();
            boolean gameruning=true;
            GameSchwimmenHelper.setRounds(0);
            while(gameruning){
            	GameSchwimmenHelper.setRounds(GameSchwimmenHelper.getRounds() +1 );
                for (int j = 0; j < getPlayersforSchwimmen().length; j++) {
                    PlayerSchwimmen player = getPlayersforSchwimmen()[j];
                    printDebug("--------------------------",0);
                    //Thread.sleep(500);
                    printDebug("Current Player: "+player ,0);
                    if(player.equals(playerthatpassed)){
                        printDebug("DONE!",0);
                        gameruning=false;
                        break;
                    }
                    if(playerthatpassed==null&&player.wantToPass(cardsontable)){
                        playerthatpassed=player;
                        playerPassed(playerthatpassed);
                    }else{
                        CardExchange cardex=player.getCardExchange(cardsontable);
                        if(cardex.isCompleteChange()){ 
                            ArrayList<Card> oldcardsontable=new ArrayList<Card>(cardsontable);
                            cardsontable.clear();
                            cardsontable.addAll(player.getHand());
                            player.setHand(oldcardsontable);   
                        }else {
                            if(cardsontable.contains(cardex.getTableCard())&&player.getHand().contains(cardex.getHandCard())){
                                cardsontable.remove(cardex.getTableCard());
                                cardsontable.add(cardex.getHandCard());
                                ArrayList<Card> newHand =new ArrayList<Card>(player.getHand());
                                newHand.remove(cardex.getHandCard());
                                newHand.add(cardex.getTableCard());
                                player.setHand(newHand);
                            }else{
                                throw new Exception("WTF, DAS GEHT NICHT! "+cardex);
                            }
                        }
                        playerChangedCards(cardex,player);
                    }
                }
            }
        printGameInfo();
        //No lives at the moment, kein Unentschieden
        PlayerSchwimmen winningplayer=getPlayersforSchwimmen()[0];
        for (int i = 1; i < getPlayersforSchwimmen().length; i++) {
            PlayerSchwimmen player = getPlayersforSchwimmen()[i];
            if(GameSchwimmenHelper.getHandValue(winningplayer.getHand().toArray(new Card[]{}))<GameSchwimmenHelper.getHandValue(player.getHand().toArray(new Card[]{}))){
                winningplayer=player;
            }
        }
        return winningplayer;

    }
    protected void playerChangedCards(CardExchange cardex, PlayerSchwimmen player) {
        printDebug("Player changed Cards:",0);
        if(cardex.isCompleteChange()){
            printDebug("Complete change!",0);
        }else{
            printDebug("Wants "+cardex.getTableCard()+" for "+cardex.getHandCard(),0);
        }
        for (PlayerSchwimmen playernotify : getPlayersforSchwimmen()) {
            playernotify.playerChangedCards(cardex, player);
        }

    }

    protected void playerPassed(PlayerSchwimmen player) {
        printDebug("Player passed!",0);
        for (PlayerSchwimmen playernotify : getPlayersforSchwimmen()) {
            playernotify.playerPassed(player);
        }
    }

    public void printGameInfo() {
        printDebug("Auf dem Tisch(" + GameSchwimmenHelper.getHandValue(cardsontable.toArray(new Card[]{})) + ":)",1);

        for (Card card : cardsontable) {
        	printDebug(card.toString(),1);
        }
        
        for (PlayerSchwimmen player : getPlayersforSchwimmen()) {
        	printDebug("Player: "+player.name,0);
            for (Card card : player.getHand()) {
            	printDebug(card.toString(),0);
            }
            printDebug("Value in Hand: "+ GameSchwimmenHelper.getHandValue(player.getHand().toArray(new Card[]{})),0);
            printDebug("",0);
        }

    }

    protected ArrayList<Card> giveHands(int playerthatchoses) {
        printDebug("Giving Cards...",0);
        //Erstelle Kartenliste zum Mischen
        ArrayList<Card> cardslist=new ArrayList<Card>();
        for (Card card : cardset) {
            cardslist.add(card);
        }
        //Mische
        Collections.shuffle(cardslist);
        //Stapel für Objekte
        ArrayDeque<Card> deck=new ArrayDeque<Card>(cardslist);
        //Erzeuge Extra Hand
        ArrayList<Card>  extrahand= new ArrayList<Card>(CARDSONHAND);
        for (int j = 0; j < CARDSONHAND; j++) {
            extrahand.add(deck.poll());
        }
        //Gebe den Spielern ihre Handkarten
        int i=0;
        for (PlayerSchwimmen player: getPlayersforSchwimmen()) {
            ArrayList<Card>  hand= new ArrayList<>(CARDSONHAND);
            for (int j = 0; j < CARDSONHAND; j++) {
                hand.add(deck.poll());
            }
            if(i==playerthatchoses){
                extrahand= player.giveHand(hand, extrahand);
            }else{
                player.giveHand(hand, null);
            }
            i++;
        }
        printDebug("Done Giving Cards", 0);
        return extrahand;
    } 

    protected void printDebug(String string,int mode) {
    	switch (mode) {
		case LOGGER_MISCS: 
			if(LOGGER_MISCS_ENABLED){System.out.println(string);}
			break;
		case LOGGER_GAMELOG:  
			if(LOGGER_GAMELOG_ENABLED){System.out.println("GAMELOG: "+string);}
			break;
		case 2:
			if(LOGGER_MAEAI_ENABLED){System.out.println("MAE AI: " +string);}
			break;
		default:
			break;
		}
    	
    	

    }

    protected PlayerSchwimmen[] getPlayersforSchwimmen(){
        if(players instanceof PlayerSchwimmen[]){
            return (PlayerSchwimmen[])players;
        }
        return null;
    }

}
