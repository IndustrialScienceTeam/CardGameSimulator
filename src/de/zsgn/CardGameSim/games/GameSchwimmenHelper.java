package de.zsgn.CardGameSim.games;

import de.zsgn.CardGameSim.cards.Card;

public class GameSchwimmenHelper {
	public static double getHandValue(Card[] hand)
	{

		if(hand.length!= 3 ) 
		{
			return -1;
		}
		double highestSum = 0;
		double[] sum = new double[4];
		for(int color = 0 ; color < 4 ; color++) {
			sum[color] = getValueIfGivenColor(hand[0],color) + getValueIfGivenColor(hand[1],color) +getValueIfGivenColor(hand[2],color);

			if (sum[color] > highestSum) {
				highestSum = sum[color];

			}
		}
		if ((hand[0].getValue().ordinal() == hand[1].getValue().ordinal()) && (hand[0].getValue().ordinal() == hand[2].getValue().ordinal())) {
			highestSum = 30.5;
		}
		return highestSum;




	}
	public static double getValueIfGivenColor(Card singelCard , int color) {
		if(singelCard.getColor().ordinal() == color) {
			return getValue(singelCard);
		}
		return 0;

	}

	public static double getValue(Card singelCard) {
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
