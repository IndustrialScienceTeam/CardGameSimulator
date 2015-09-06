package de.zsgn.CardGameSim.cards;

public class CardFactory {
protected final static CardValue[] SKATVALUES={CardValue.SEVEN,CardValue.EIGHT,CardValue.NINE,CardValue.TEN,CardValue.VALET, CardValue.QUEEN, CardValue.KING, CardValue.ACE};

public static Card[] getSkatSet(){
    Card[] SkatSet=new Card[32];
    int i=0;
    for (FrenchColor color : FrenchColor.values()) {
        for (CardValue value : SKATVALUES) {
            SkatSet[i]=new Card(value, color);
            i++;
        }
    }
    return SkatSet;
}
}
