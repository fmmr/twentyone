package no.rodland.twentyone;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Card {
    private final Suit suit;
    private final Value value;

    private static final Map<String, Suit> reverseSuits = Arrays.stream(Suit.values())
                                                                .collect(Collectors.toMap(Suit::toString, s -> s));
    private static final Map<String, Value> reverseValues = Arrays.stream(Value.values())
                                                                  .collect(Collectors.toMap(Value::toString, v -> v));

    public Card(String s) {
        this(s.substring(0, 1), s.substring(1));
    }

    private Card(String s, String v) {
        this(reverseSuits.get(s), reverseValues.get(v));
    }

    Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        if (suit == null || value == null) {
            throw new NullPointerException("A card must have a suit and a value - neither can be null");
        }
    }


    @Override
    public String toString() {
        return suit.toString() + value.toString();
    }

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && value == card.value;
    }

    @Override
    public int hashCode() {
        return 31 * suit.hashCode() + value.hashCode();
    }

    public boolean greaterThan(Card otherCard) {
        return value.greaterThan(otherCard.value);
    }

    public boolean greaterThanOrEqual(Card otherCard) {
        return value.greaterThanOrEqual(otherCard.value);
    }
}
