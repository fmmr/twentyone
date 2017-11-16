package no.rodland.twentyone;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class TwentyOneTest {
    private Player SAM;
    private Player DEALER;

    @Before
    public void setup() {
        SAM = new Player("sam");
        DEALER = new Player("dealer");
    }

    @Test
    public void example_from_doc_with_initial_deal_but_without_game() {
        Deck deck = new Deck("CA, D5, H9, HQ, S8,");
        assertEquals(5, deck.getNumberOfCards());
        SAM.deal(deck.pop());
        DEALER.deal(deck.pop());
        SAM.deal(deck.pop());
        DEALER.deal(deck.pop());
        assertEquals(1, deck.getNumberOfCards());
        assertEquals("sam: CA, H9", SAM.toString());
        assertEquals("dealer: D5, HQ", DEALER.toString());
    }

    @Test
    public void initial_deal_example_from_doc() {
        Deck deck = new Deck("CA, D5, H9, HQ, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        game.initialDeal();
        assertEquals(1, deck.getNumberOfCards());
        assertEquals("sam: CA, H9", SAM.toString());
        assertEquals("dealer: D5, HQ", DEALER.toString());
        assertFalse(game.isWinnerFound());
        assertNull(game.getWinner());
    }

}