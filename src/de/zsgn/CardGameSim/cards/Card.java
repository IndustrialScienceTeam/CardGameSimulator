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
@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((color == null) ? 0 : color.hashCode());
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
}
@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (!(obj instanceof Card)) {
        return false;
    }
    Card other = (Card) obj;
    if (color != other.color) {
        return false;
    }
    if (value != other.value) {
        return false;
    }
    return true;
}
}
