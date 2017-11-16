package no.rodland.twentyone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    Player(String name) {
        this.name = name;
        if (!"sam".equals(name) && !"dealer".equals(name)) {
            throw new IllegalArgumentException("Only sam and dealer are allowed players");
        }
    }

    public void deal(Card card) {
        hand.add(card);
    }

    public int getScore() {
        return hand.stream().mapToInt(Card::getScore).sum();
    }

    @Override
    public String toString() {
        return name + ": " + (hand.stream().map(Card::toString).collect(Collectors.joining(", ")));
    }

    public String getName() {
        return name;
    }
}
