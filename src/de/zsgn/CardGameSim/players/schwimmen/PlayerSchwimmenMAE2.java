package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;
import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardExchange;
import de.zsgn.CardGameSim.games.GameSchwimmenHelper;

public class PlayerSchwimmenMAE2 extends PlayerSchwimmen {

	public PlayerSchwimmenMAE2(String name) {
		super(name);

	}

	@Override
	public CardExchange getCardExchange(ArrayList<Card> cardsontable) {
		double highestPossible = 0;

		if(GameSchwimmenHelper.getHandValue(cardsontable.toArray(new Card[]{})) > GameSchwimmenHelper.getHandValue(this.getHand().toArray(new Card[]{}))){
			highestPossible = GameSchwimmenHelper.getHandValue(cardsontable.toArray(new Card[]{}));
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

		return new CardExchange(false,desiredReturnCard,desiredReceivedCard);



	}

	@Override
	protected boolean wanttotakeThisHand(ArrayList<Card> hand) {
		if(GameSchwimmenHelper.getHandValue(this.getHand().toArray(new Card[]{})) > 17){
			printDebug("MAE: Its better than 17, guess I will take this");
			return true;
		}

		return false;

	}

	@Override
	public boolean wantToPass(ArrayList<Card> cardsontable) {
		if(GameSchwimmenHelper.getHandValue(this.getHand().toArray(new Card[]{})) > 29){
			printDebug("MAE: I have enough");
			return true;
		}

		return false;
	}

	@Override
	public void playerChangedCards(CardExchange cardex, PlayerSchwimmen player) {
		printDebug("MAE doesn´t care either");

	}

	@Override
	public void playerPassed(PlayerSchwimmen player) {
		printDebug("MAE doesn´t care either");

	}

}
