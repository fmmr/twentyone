package no.rodland.twentyone;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

class Deck {
    private final LinkedList<Card> cards;

    Deck() {
        this(getShuffledDeck());
    }

    Deck(String cardAsCode) {
        this(getCardsFromString(cardAsCode));
    }

    Deck(Collection<Card> cards) {
        this.cards = new LinkedList<>(cards);
        checkUniqueness();
    }

    private static Collection<Card> getCardsFromString(String cardAsCode) {
        return Arrays.stream(cardAsCode.split("[, ]+"))
                     .filter(str -> !str.isEmpty())
                     .map(Card::new)
                     .collect(Collectors.toList());
    }

    private void checkUniqueness() {
        Set<Card> set = new HashSet<>(cards);
        if (set.size() != cards.size()) {
            throw new IllegalStateException("Duplicate cards not allowed in a deck");
        }
    }

    private static Collection<Card> getShuffledDeck() {
        final List<Card> collect = Arrays.stream(Suit.values())
                                         .map(Suit::allCards)
                                         .flatMap(Collection::stream)
                                         .collect(Collectors.toList());
        Collections.shuffle(collect);
        return collect;

    }

    public Card pop() {
        final Card pop;
        try {
            pop = cards.pop();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Deck is empty - probably started out with a non-full deck of cards.");
        }
        return pop;
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
