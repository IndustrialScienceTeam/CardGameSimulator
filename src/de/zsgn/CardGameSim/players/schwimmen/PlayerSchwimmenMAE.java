package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;

import de.zsgn.CardGameSim.cards.*;
import de.zsgn.CardGameSim.games.ActionObject;
import de.zsgn.CardGameSim.games.GameSchwimmenHelper;

public class PlayerSchwimmenMAE extends PlayerSchwimmen {

	
	private Card[] myCards;

	//at the moment I dont know how the methods will change , so I will write some AI code in some of them, will change later
	public PlayerSchwimmenMAE(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	//needs a method to obtain its own cards , at the moment I will use Card[] myCards  , seems like there will be a action object, will use this later
	public ActionObject  pleaseChooseOrSomethingLikeThisMethod(Card [] middle)
	{
		ActionObject action = new ActionObject();
		if(GameSchwimmenHelper.getHandValue(myCards) > 29){
			//pass 
			action.pass = true;
			return action;
		}
		
		double highestPossible = 0;
		
		if(GameSchwimmenHelper.getHandValue(middle) > GameSchwimmenHelper.getHandValue(myCards)){
			highestPossible = GameSchwimmenHelper.getHandValue(middle);
			action.changeAll = true;
		}
		
		for(int i = 0; i<3;i++){
			for(int j = 0; i<3;i++){
				Card[] testingStack= new Card[3];
				testingStack = myCards;
				testingStack[i] = middle[j];
				if(GameSchwimmenHelper.getHandValue(testingStack) > highestPossible) {
					action.pass = false;
					action.changeAll = false;
					action.IGiveYouThisCard = myCards[i];
					action.IWantThisCard = middle[j];
				}
			}
		}
		
		
		return action;
	}
	@Override
	public CardExchange getCardExchange(ArrayList<Card> cardsontable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected boolean wanttotakeThisHand(ArrayList<Card> hand) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean wantToPass(ArrayList<Card> cardsontable) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void playerChangedCards(CardExchange cardex, PlayerSchwimmen player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void playerPassed(PlayerSchwimmen player) {
		// TODO Auto-generated method stub
		
	}	
	
	

	
	
	
	
	
	
	
	
}
