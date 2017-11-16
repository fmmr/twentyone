package no.rodland.twentyone;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CardTest {

    @Test
    public void test_card() {
        Card c2 = new Card(Suit.Clubs, Value.Two);
        assertEquals("C2", c2.toString());
    }

    @Test
    public void test_greater_than() {
        Card c2 = new Card(Suit.Clubs, Value.Two);
        Card h5 = new Card(Suit.Hearts, Value.Five);
        assertTrue(h5.greaterThan(c2));
        assertFalse(c2.greaterThan(h5));
    }

    @Test
    public void test_greater_than_or_equal() {
        Card c2 = new Card(Suit.Clubs, Value.Two);
        Card h5 = new Card(Suit.Hearts, Value.Five);
        Card c5 = new Card(Suit.Clubs, Value.Five);
        assertTrue(h5.greaterThanOrEqual(c2));
        assertTrue(h5.greaterThanOrEqual(c5));
        assertFalse(c2.greaterThanOrEqual(h5));
    }

    @Test
    public void test_equals() {
        Card ck = new Card(Suit.Clubs, Value.King);
        Card c2 = new Card(Suit.Clubs, Value.Two);
        Card otherC2 = new Card(Suit.Clubs, Value.Two);
        assertEquals(c2, otherC2);
        assertEquals(c2, c2);
        assertNotEquals(c2, ck);
        assertNotEquals(ck, c2);
        assertNotEquals(ck, c2);
        assertNotEquals(ck, null);
        assertNotEquals(null, ck);
        assertNotEquals(ck, "");
        assertNotEquals("", ck);
    }

    @Test(expected = NullPointerException.class)
    public void null_suit() {
        new Card(null, Value.King);
    }

    @Test(expected = NullPointerException.class)
    public void null_value() {
        new Card(Suit.Clubs, null);
    }

    @Test(expected = NullPointerException.class)
    public void null_both() {
        new Card(Suit.Clubs, null);
    }
}