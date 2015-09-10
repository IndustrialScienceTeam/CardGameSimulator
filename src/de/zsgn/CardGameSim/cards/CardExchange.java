package de.zsgn.CardGameSim.cards;

public class CardExchange {
    protected final boolean completechange;
    protected final Card handcard;
    protected final Card tablecard;
    public CardExchange(boolean completechange,Card handcard, Card tablecard) {
        this.completechange=completechange;
        this.handcard=handcard;
        this.tablecard=tablecard;   
    }

    public boolean isCompleteChange() {
        return completechange;
    }

    public Card getTableCard() {
        return tablecard;
    }

    public Card getHandCard() {
        return handcard;
    }

}
