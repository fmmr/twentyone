package no.rodland.twentyone;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

enum Suit {
    Clubs("C"), Diamonds("D"), Hearts("H"), Spades("S");

    private static final Function<Suit, List<Card>> ALL_CARDS = s -> Arrays.stream(Value.values())
                                                                           .map(v -> new Card(s, v))
                                                                           .collect(Collectors.toList());
    private final String code;

    Suit(String code) {
        this.code = code;
    }

    public String toString() {
        return code;
    }

    public List<Card> allCards() {
        return ALL_CARDS.apply(this);
    }
}
