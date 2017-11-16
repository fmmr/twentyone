package no.rodland.twentyone;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void example_from_doc_with_initial_deal_from_actual_implementation_still_without_game() {
        Deck deck = new Deck("CA, D5, H9, HQ, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        game.initialDeal();
        assertEquals(1, deck.getNumberOfCards());
        assertEquals("sam: CA, H9", SAM.toString());
        assertEquals("dealer: D5, HQ", DEALER.toString());
    }

    @Test
    public void sam_wins_when_both_have_21() {
        Deck deck = new Deck("CA, DA, HK, HQ, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(SAM, winner);
    }

    @Test
    public void sam_wins_when_dealt_21_initially() {
        Deck deck = new Deck("CA, DA, HK, H7, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(SAM, winner);
    }

    @Test(expected = NoSuchElementException.class)
    public void no_such_element_exception_when_deck_is_empty_before_winner_is_found() {
        Deck deck = new Deck("CK, DA, H6, H7, S4,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        game.play();
    }

    @Test
    public void sam_is_dealt_one_more_card_if_score_is_16() {
        Deck deck = new Deck("CK, DA, H6, H7, S4, S2, S3, H8");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        game.play();
        assertEquals("sam: CK, H6, S4", SAM.toString());
    }

    @Test
    public void sam_is_not_dealt_anymore_if_score_is_17() {
        Deck deck = new Deck("CK, DA, S7, H7, S4, S2");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        game.play();
        assertEquals("sam: CK, S7", SAM.toString());
    }

    @Test
    public void dealer_is_dealt_one_more_card_if_score_is_below_sam() {
        Deck deck = new Deck("CK, DK, H8, H7, S4, S2");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        game.play();
        assertEquals("dealer: DK, H7, S4", DEALER.toString());
    }

    @Test
    public void dealer_is_dealt_one_more_card_if_score_is_equal_to_sam() {
        Deck deck = new Deck("CK, DK, S7, H7, S4, S2");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        game.play();
        assertEquals("dealer: DK, H7, S4", DEALER.toString());
    }

    @Test
    public void dealer_is_not_dealt_card_if_score_is_above_sam() {
        Deck deck = new Deck("CK, DK, S7, H8, S4, S2");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        game.play();
        assertEquals("dealer: DK, H8", DEALER.toString());
    }

    @Test
    public void dealer_wins_when_both_have_22() {
        Deck deck = new Deck("CA, DA, HA, SA, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(DEALER, winner);
    }

    @Test
    public void dealer_wins_when_dealt_21_initially() {
        Deck deck = new Deck("C3, DA, HA, SK, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(DEALER, winner);
    }

    @Test
    public void example_from_doc() {
        Deck deck = new Deck("CA, D5, H9, HQ, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(SAM, winner);
        assertEquals(20, SAM.getScore());
        assertEquals(23, DEALER.getScore());
    }

    @Test
    public void example_from_doc_other_way_around() {
        Deck deck = new Deck("D5, CA,  HQ, H9, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(DEALER, winner);
        assertEquals(23, SAM.getScore());
        assertEquals(20, DEALER.getScore());
    }

    @Test
    public void many_cards_dealt() {
        final List<Card> orderedDeck = Arrays.stream(Value.values())
                                             .map(Value::allCards)
                                             .flatMap(Collection::stream)
                                             .collect(Collectors.toList());
        Deck deck = new Deck(orderedDeck);
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(DEALER, winner);
        assertEquals(20, SAM.getScore());
        assertEquals(21, DEALER.getScore());
    }

}