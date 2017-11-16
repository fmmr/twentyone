package no.rodland.twentyone;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CardTest {

    @Test
    public void check_tostring_returns_code_as_spec() {
        Card c2 = new Card(Suit.Clubs, Value.Two);
        assertEquals("C2", c2.toString());
    }

    @Test
    public void test_equal_cards() {
        Card card1 = new Card(Suit.Clubs, Value.Two);
        Card card2 = new Card(Suit.Clubs, Value.Two);
        assertEquals(card1, card2);
        assertEquals(card1, card1);
    }

    @Test
    public void test_unequal() {
        Card ck = new Card(Suit.Clubs, Value.King);
        Card c2 = new Card(Suit.Clubs, Value.Two);
        assertNotEquals(c2, ck);
        assertNotEquals(ck, c2);
        assertNotEquals(ck, c2);
        assertNotEquals(ck, null);
        assertNotEquals(null, ck);
        assertNotEquals(ck, "");
        assertNotEquals("", ck);
    }

    @Test(expected = NullPointerException.class)
    public void npe_should_be_thrown_if_suit_is_null() {
        new Card(null, Value.King);
    }

    @Test(expected = NullPointerException.class)
    public void npe_should_be_thrown_if_value_is_null() {
        new Card(Suit.Clubs, null);
    }

    @Test(expected = NullPointerException.class)
    public void npe_should_be_thrown_if_both_suit_and_value_are_null() {
        new Card(Suit.Clubs, null);
    }
}