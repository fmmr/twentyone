package no.rodland.twentyone;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValueTest {

    @Test
    public void greaterThan() {
        assertTrue(Value.Ace.greaterThan(Value.King));
        assertTrue(Value.Five.greaterThan(Value.Four));
        assertFalse(Value.Five.greaterThan(Value.Five));
        assertFalse(Value.Four.greaterThan(Value.Five));
    }

    @Test
    public void greaterThanKing() {
        assertFalse(Value.King.greaterThan(Value.Queen));
        assertFalse(Value.Queen.greaterThan(Value.King));
    }

    @Test
    public void greaterThanOrEqual() {
        assertTrue(Value.Five.greaterThanOrEqual(Value.Four));
        assertTrue(Value.Five.greaterThanOrEqual(Value.Five));
        assertFalse(Value.Four.greaterThanOrEqual(Value.Five));
    }

    @Test
    public void greaterThanOrEqualKing() {
        assertTrue(Value.King.greaterThanOrEqual(Value.Queen));
        assertTrue(Value.Queen.greaterThanOrEqual(Value.King));
    }

    @Test
    public void sum_simple() {
        assertEquals(9, Value.Five.sum(Value.Four));
    }

    @Test
    public void sum_king() {
        assertEquals(19, Value.King.sum(Value.Nine));
    }

    @Test
    public void sum_king_ace() {
        assertEquals(21, Value.King.sum(Value.Ace));
    }
}