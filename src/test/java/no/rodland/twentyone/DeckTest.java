package no.rodland.twentyone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DeckTest {
    @Test
    public void test_give_deck_as_arg() {
        Deck deck = new Deck(
                "DK, H3, SQ, SK, HA, CA, C10, H2, C3, CJ, D4, S4, CK, SA, C2, C4, H8, D10, H6, S10, D7, C9, HK, C5, S6, HQ, H5, H9, D3, C7, D8, S8, H4, H7, HJ, D2, H10, C8, D9, DQ, S7, DA, SJ, S5, CQ, S3, D6, D5, S9, DJ, S2, C6");
        final Collection<Card> cards = deck.getCards();
        List<Card> list = new ArrayList<>(cards);
        assertThat(new Card(Suit.Spades, Value.King), is(list.get(3)));
        assertEquals(52, deck.getNumberOfCards());
    }

    @Test
    public void test_small_deck_just_to_track_behaviour() {
        Deck deck = new Deck(
                "DK, H3, SQ, SK, HA, CA, C10, H2");
        final Collection<Card> cards = deck.getCards();
        List<Card> list = new ArrayList<>(cards);
        assertThat(new Card(Suit.Spades, Value.King), is(list.get(3)));
        assertEquals(8, deck.getNumberOfCards());
    }

    @Test(expected = IllegalStateException.class)
    public void no_cheeting() {
        Deck deck = new Deck(
                "DK, H3, H3, SK, HA, CA, C10, H2");
        final Collection<Card> cards = deck.getCards();
        List<Card> list = new ArrayList<>(cards);
        assertThat(new Card(Suit.Spades, Value.King), is(list.get(3)));
        assertEquals(8, deck.getNumberOfCards());
    }

    @Test
    public void test_a_card_is_in_the_deck() {
        final Deck deck = new Deck();
        final Collection<Card> cards = deck.getCards();
        System.out.println("deck = " + deck);
        assertThat(cards, hasItem(new Card(Suit.Diamonds, Value.Jack)));
    }

    @Test
    public void to_string() {
        final Deck deck = new Deck();
        final String s = deck.toString();
        int l = 13 * 4 * 2 + 4 + 51 * 2 + 2;
        assertEquals(l, s.length());
    }

    @Test
    public void test_deck_size() {
        final Deck deck = new Deck();
        final Collection<Card> cards = deck.getCards();
        assertEquals(52, deck.getNumberOfCards());
        assertEquals(52, cards.size());
    }

    @Test
    public void test_popping() {
        Deck deck = new Deck("DK, H3, SQ, SK, HA, CA, C10, H2, C3, CJ");
        assertEquals(10, deck.getNumberOfCards());
        final Card pop = deck.pop();
        assertEquals(9, deck.getNumberOfCards());
        assertEquals(new Card("DK"), pop);
    }

    @Test(expected = IllegalStateException.class)
    public void test_uniqueness() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Suit.Spades, Value.Three));
        list.add(new Card(Suit.Hearts, Value.Three));
        list.add(new Card(Suit.Clubs, Value.Three));
        list.add(new Card(Suit.Spades, Value.Two));
        list.add(new Card(Suit.Spades, Value.Three));  // Duplicate
        list.add(new Card(Suit.Diamonds, Value.Three));
        Deck.checkUniquness(list);
    }


}
