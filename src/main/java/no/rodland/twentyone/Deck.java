package no.rodland.twentyone;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Deck {
    private final LinkedList<Card> cards;

    Deck(String cardAsCode) {
        final List<Card> allCards = Arrays.stream(cardAsCode.split("[, ]+"))
                                          .filter(str -> !str.isEmpty())
                                          .map(Card::new)
                                          .collect(Collectors.toList());
        cards = new LinkedList<>();
        cards.addAll(allCards);
        checkUniquness(cards);
    }

    Deck() {
        final List<Card> allCards = getAllCards();
        Collections.shuffle(allCards);
        cards = new LinkedList<>();
        cards.addAll(allCards);
        checkUniquness(cards);
    }

    public Card pop() {
        return cards.pop();
    }

    static void checkUniquness(List<Card> cards) {
        Set<Card> set = new HashSet<>(cards);
        if (set.size() != cards.size()) {
            throw new IllegalStateException("Duplicate cards not allowed in a deck");
        }
    }

    private static List<Card> getAllCards() {
        return Arrays.stream(Suit.values())
                     .map(Suit::allCards)
                     .flatMap(Collection::stream)
                     .collect(Collectors.toList());

    }

    public int getNumberOfCards() {
        return cards.size();
    }

    public Collection<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
