package no.rodland.twentyone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private List<Card> hand = new ArrayList<>();


    public void deal(Card card) {
        hand.add(card);
    }


    Player(String name) {
        this.name = name;
        if (!"sam".equals(name) && !"dealer".equals(name)) {
            throw new IllegalArgumentException("Only sam and dealer are allowed players");
        }
    }

    //    public int sumOfHand() {
//        return hand.stream().mapToInt(Card::getValue).sum();
//    }
//
    @Override
    public String toString() {
        return name + ": " + (hand.stream().map(Card::toString).collect(Collectors.joining(", ")));
    }

    public String getName() {
        return name;
    }
}
