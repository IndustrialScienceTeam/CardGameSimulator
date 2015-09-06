package de.zsgn.CardGameSim.games;

import de.zsgn.CardGameSim.cards.Card;

public class GameSchwimmenHelper {
	public double getHandValue(Card[] hand)
	{

		if(hand.length!= 3 ) 
		{
			return -1;
		}else
		{
		double sum = getValue(hand[0]) + getValue(hand[1]) + getValue(hand[2]);
		
		return sum;
		}
	}
	public double getValue(Card singelCard) {
		int cardNumber = singelCard.getValue().ordinal();

		switch(cardNumber) {

		case 0: 
			return 11;
		case 6:
			return 7;
		case 7: 
			return 8;
		case 8: 
			return 9;
		case 9: 
			return 10;
		case 10: 
			return 10;
		case 11: 
			return 10;
		case 12: 
			return 10;

		default:
			System.err.println("WTF!");
			return -42;

		}

	}

}
