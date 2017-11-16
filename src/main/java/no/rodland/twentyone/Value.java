package no.rodland.twentyone;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Value {
    Two("2", 2), Three("3", 3), Four("4", 4), Five("5", 5),
    Six("6", 6), Seven("7", 7), Eight("8", 8), Nine("9", 9),
    Ten("10", 10), Jack("J", 10), Queen("Q", 10), King("K", 10),
    Ace("A", 11);

    private static final Function<Value, List<Card>> ALL_CARDS = v -> Arrays.stream(Suit.values())
                                                                            .map(s -> new Card(s, v))
                                                                            .collect(Collectors.toList());
    private final String s;
    /**
     * Numbered cards are their point score. Jack, Queen and King count as 10 and Ace counts as 11.
     */
    private final int score;


    Value(String s, int score) {
        this.s = s;
        this.score = score;
    }

    @Override
    public String toString() {
        return s;
    }

    public boolean greaterThan(Value otherValue) {
        return score > otherValue.score;
    }

    public boolean greaterThanOrEqual(Value otherValue) {
        return score >= otherValue.score;
    }

    public int sum(Value otherValue) {
        return score + otherValue.score;
    }

    public int getScore() {
        return score;
    }

    public List<Card> allCards() {
        return ALL_CARDS.apply(this);
    }
}
