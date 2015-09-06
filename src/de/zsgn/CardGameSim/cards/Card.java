package de.zsgn.CardGameSim.cards;

public class Card {
protected final CardValue value;
protected final FrenchColor color;
protected Card(CardValue value, FrenchColor color) {
    super();
    this.value = value;
    this.color = color;
}
public CardValue getValue() {
    return value;
}
public FrenchColor getColor() {
    return color;
}
@Override
public String toString() {
return value.name()+" "+color.name();
}
}
