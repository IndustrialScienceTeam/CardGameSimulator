package de.zsgn.CardGameSim.games;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardFactory;
import de.zsgn.CardGameSim.players.Player;
import de.zsgn.CardGameSim.players.schwimmen.PlayerSchwimmen;

public class GameSchwimmen extends Game {
    public final static int CARDSONHAND=3;
    public final static Random rand=new Random();
    ArrayList<Card> cardsontable=null;

    public GameSchwimmen(PlayerSchwimmen[] players) {
        super(players, CardFactory.getSkatSet(), 5);
    }

    @Override
    public Player startGame(int rounds) {
        for (int i = 0; i < rounds; i++) {
            cardsontable= giveHands(rand.nextInt(players.length));
            printGameInfo();
        }
        return null;

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
            System.out.println("Value on table: "+ GameSchwimmenHelper.getHandValue(player.getHand().toArray(new Card[]{})));
            System.out.println();
        }
        
    }

    protected ArrayList<Card> giveHands(int playerthatchoses) {
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
        return extrahand;
    } 

    protected PlayerSchwimmen[] getPlayersforSchwimmen(){
        if(players instanceof PlayerSchwimmen[]){
            return (PlayerSchwimmen[])players;
        }
        return null;
    }

}
