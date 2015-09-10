package de.zsgn.CardGameSim.players.schwimmen;

import java.util.ArrayList;

import de.zsgn.CardGameSim.cards.Card;
import de.zsgn.CardGameSim.cards.CardExchange;

public class PlayerSchwimmenMAE2 extends PlayerSchwimmen {

	protected PlayerSchwimmenMAE2(String name) {
		super(name);
		// TODO Auto-generated constructor stub
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
