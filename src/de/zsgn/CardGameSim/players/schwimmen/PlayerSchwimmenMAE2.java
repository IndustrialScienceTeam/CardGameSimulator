package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;
import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardExchange;
import de.zsgn.CardGameSim.games.GameSchwimmenHelper;

public class PlayerSchwimmenMAE2 extends PlayerSchwimmen {
	private int C_TakeThreshold = 16;
	private int C_PassThreshold = 28;
	public PlayerSchwimmenMAE2(String name) {
		super(name);

	}

	@Override
	public CardExchange getCardExchange(ArrayList<Card> cardsontable) {
		printDebug("Round: " + GameSchwimmenHelper.getRounds() , 2);
		printDebug("My Cards("+ Double.toString(GameSchwimmenHelper.getHandValue(this.getHand().toArray(new Card[]{}))) +"):",2);
		for(Card c : this.getHand()){
			printDebug("	" +c.toString(), 2);
		}
		printDebug("Table(" + Double.toString(GameSchwimmenHelper.getHandValue(cardsontable.toArray(new Card[]{})))+ "):",2);
		for(Card c : cardsontable){
			printDebug("	" +c.toString(), 2);
		}
		double highestPossible = 0;

		if(GameSchwimmenHelper.getHandValue(cardsontable.toArray(new Card[]{})) > GameSchwimmenHelper.getHandValue(this.getHand().toArray(new Card[]{}))){
			highestPossible = GameSchwimmenHelper.getHandValue(cardsontable.toArray(new Card[]{}));
			printDebug("ACTION: taking ALL"  ,2);
			return new CardExchange(true, null, null);
			
		}
		Card desiredReturnCard = null;
		Card desiredReceivedCard = null;

		for(int i = 0; i<3;i++){
			for(int j = 0; i<3;i++){
				Card[] testingStack= new Card[3];
				testingStack = this.getHand().toArray(new Card[]{});
				testingStack[i] = cardsontable.toArray(new Card[]{})[j];
				if(GameSchwimmenHelper.getHandValue(testingStack) > highestPossible) {


					desiredReturnCard = this.getHand().toArray(new Card[]{})[i];
					desiredReceivedCard = cardsontable.toArray(new Card[]{})[j];
				}
			}
		}
		printDebug("ACTION: taking:'" + desiredReceivedCard.toString() + "' ||| giving:'"+ desiredReturnCard + "'"  ,2);
		
		return new CardExchange(false,desiredReturnCard,desiredReceivedCard);



	}

	@Override
	protected boolean wanttotakeThisHand(ArrayList<Card> hand) {
		printDebug("I am beginning, have to choose if I want the following cards:",2);
		for(Card c : hand){
			printDebug(c.toString(), 2);
		}
		printDebug("The value of these cards is: " + Double.toString(GameSchwimmenHelper.getHandValue(hand.toArray(new Card[]{}))), 2);
		if(GameSchwimmenHelper.getHandValue(hand.toArray(new Card[]{})) > C_TakeThreshold){
			printDebug("BETTER than " + C_TakeThreshold + " , guess I will take this",2);
			return true;
		}else {
			printDebug("WORSE than " + C_TakeThreshold + " ,will not take this",2);
			
			
			return false;
		}
			

		
	}

	@Override
	public boolean wantToPass(ArrayList<Card> cardsontable) {
		printDebug("I have to decide wether to pass or not",2);
		printDebug("The value of my cards: " + Double.toString(GameSchwimmenHelper.getHandValue(this.getHand().toArray(new Card[]{}))), 2);
		if(GameSchwimmenHelper.getHandValue(this.getHand().toArray(new Card[]{})) >= C_PassThreshold){
			printDebug("PASS , it is above "+ C_PassThreshold,2);
			return true;
		}else
			printDebug("NO PASS, it is under "+ C_PassThreshold,2);
		return false;
	}

	@Override
	public void playerChangedCards(CardExchange cardex, PlayerSchwimmen player) {
		printDebug("MAE doesn´t care either",0);

	}

	@Override
	public void playerPassed(PlayerSchwimmen player) {
		printDebug("MAE doesn´t care either",0);
		

	}
	
	 

}
