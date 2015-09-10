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

    public GameSchwimmen(PlayerSchwimmen[] players) {
        super(players, CardFactory.getSkatSet(), 5);
    }

    @Override
    public Player startGame(int rounds) throws Exception {
        for (int round = 0; round < rounds; round++) {
            cardsontable= giveHands(rand.nextInt(players.length));
            printGameInfo();
            boolean gameruning=true;
            while(gameruning){
                for (int j = 0; j < getPlayersforSchwimmen().length; j++) {
                    PlayerSchwimmen player = getPlayersforSchwimmen()[j];
                    printDebug("--------------------------");
                    Thread.sleep(1000);
                    printDebug("Current Player: "+player);
                    if(player.equals(playerthatpassed)){
                        printDebug("DONE!");
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
        printDebug("Player changed Cards:");
        if(cardex.isCompleteChange()){
            printDebug("Complete change!");
        }else{
            printDebug("Wants "+cardex.getTableCard()+" for "+cardex.getHandCard());
        }
        for (PlayerSchwimmen playernotify : getPlayersforSchwimmen()) {
            playernotify.playerChangedCards(cardex, player);
        }

    }

    protected void playerPassed(PlayerSchwimmen player) {
        printDebug("Player passed!");
        for (PlayerSchwimmen playernotify : getPlayersforSchwimmen()) {
            playernotify.playerPassed(player);
        }
    }

    public void printGameInfo() {
        System.out.println("Auf dem Tisch:");

        for (Card card : cardsontable) {
            System.out.println(card);
        }
        System.out.println("Value on table: "+ GameSchwimmenHelper.getHandValue(cardsontable.toArray(new Card[]{})));
        System.out.println();
        for (PlayerSchwimmen player : getPlayersforSchwimmen()) {
            System.out.println("Player: "+player.name);
            for (Card card : player.getHand()) {
                System.out.println(card);
            }
            System.out.println("Value in Hand: "+ GameSchwimmenHelper.getHandValue(player.getHand().toArray(new Card[]{})));
            System.out.println();
        }

    }

    protected ArrayList<Card> giveHands(int playerthatchoses) {
        printDebug("Giving Cards...");
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
        printDebug("Done Giving Cards");
        return extrahand;
    } 

    protected void printDebug(String string) {
        System.out.println(string);

    }

    protected PlayerSchwimmen[] getPlayersforSchwimmen(){
        if(players instanceof PlayerSchwimmen[]){
            return (PlayerSchwimmen[])players;
        }
        return null;
    }

}
