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
    public void deck_should_be_passed_in_as_string() {
        Deck deck = new Deck(
                "DK, H3, SQ, SK, HA, CA, C10, H2, C3, CJ, D4, S4, CK, SA, C2, C4, H8, D10, H6, S10, D7, C9, HK, C5, S6, HQ, H5, H9, D3, C7, D8, S8, H4, H7, HJ, D2, H10, C8, D9, DQ, S7, DA, SJ, S5, CQ, S3, D6, D5, S9, DJ, S2, C6");
        final Collection<Card> cards = deck.getCards();
        List<Card> list = new ArrayList<>(cards);
        assertThat(new Card(Suit.Spades, Value.King), is(list.get(3)));
        assertEquals(52, deck.getNumberOfCards());
    }

    @Test
    public void empty_elements_are_skipped() {
        Deck deck = new Deck("DK, H3, SQ, ,,,    SK,     HA,,, , , ,  CA, C10, H2, C3, CJ");
        final Collection<Card> cards = deck.getCards();
        List<Card> list = new ArrayList<>(cards);
        assertThat(new Card(Suit.Spades, Value.King), is(list.get(3)));
        assertEquals(10, deck.getNumberOfCards());
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
    public void a_card_cannot_be_present_twice_in_a_deck() {
        new Deck("DK, H3, H3, SK, HA, CA, C10, H2");
    }

    @Test
    public void check_to_see_if_a_default_deck_contains_some_cards() {
        final Deck deck = new Deck();
        final Collection<Card> cards = deck.getCards();
        assertThat(cards, hasItem(new Card(Suit.Diamonds, Value.Jack)));
        assertThat(cards, hasItem(new Card(Suit.Spades, Value.Ace)));
        assertThat(cards, hasItem(new Card(Suit.Hearts, Value.Two)));
        assertThat(cards, hasItem(new Card(Suit.Clubs, Value.Jack)));
    }

    @Test
    public void length_of_tostring_for_a_full_deck_of_cards_should_be_212() {
        final Deck deck = new Deck();
        final String s = deck.toString();
        int l = 13 * 4 * 2 + 4 + 51 * 2 + 2;
        assertEquals(l, s.length());
    }

    @Test
    public void a_default_deck_should_contain_52_cards() {
        final Deck deck = new Deck();
        final Collection<Card> cards = deck.getCards();
        assertEquals(52, deck.getNumberOfCards());
        assertEquals(52, cards.size());
    }

    @Test
    public void popping_a_deck_reduces_its_size_by_one() {
        Deck deck = new Deck("DK, H3, SQ, SK, HA, CA, C10, H2, C3, CJ");
        assertEquals(10, deck.getNumberOfCards());
        final Card pop = deck.pop();
        assertEquals(9, deck.getNumberOfCards());
    }

    @Test
    public void the_leftmost_card_in_input_is_popped_first() {
        Deck deck = new Deck("DK, H3, SQ, SK");
        final Card pop = deck.pop();
        assertEquals(new Card("DK"), pop);
    }

    @Test(expected = IllegalStateException.class)
    public void duplicate_cards_in_a_deck_is_not_allowed_and_should_throw_an_illegalstate_exception() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Suit.Spades, Value.Three));
        list.add(new Card(Suit.Hearts, Value.Three));
        list.add(new Card(Suit.Clubs, Value.Three));
        list.add(new Card(Suit.Spades, Value.Two));
        list.add(new Card(Suit.Spades, Value.Three));  // Duplicate
        list.add(new Card(Suit.Diamonds, Value.Three));
        new Deck(list);
    }
}
