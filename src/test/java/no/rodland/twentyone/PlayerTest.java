package no.rodland.twentyone;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    private Player SAM;
    private Player DEALER;

    @Before
    public void setup() {
        SAM = new Player("sam");
        DEALER = new Player("dealer");
    }

    @Test
    public void test_player_name() {
        assertEquals("sam", SAM.getName());
        assertEquals("dealer", DEALER.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void only_sam_and_dealer_are_allowed_as_players() {
        new Player("Fredrik");
    }

    @Test
    public void to_string_test() {
        SAM.deal(new Card(Suit.Spades, Value.Three));
        SAM.deal(new Card(Suit.Hearts, Value.King));
        SAM.deal(new Card(Suit.Clubs, Value.King));
        assertEquals("sam: S3, HK, CK", SAM.toString());
    }

    @Test
    public void score_should_be_zero_when_no_cards_have_been_dealt() {
        assertEquals(0, SAM.getScore());
    }

    @Test
    public void score_with_three_king_and_king_should_be_23() {
        SAM.deal(new Card(Suit.Spades, Value.Three));
        SAM.deal(new Card(Suit.Hearts, Value.King));
        SAM.deal(new Card(Suit.Clubs, Value.King));
        assertEquals(23, SAM.getScore());
    }

    @Test
    public void score_with_three_two_and_ace_should_be_16() {
        SAM.deal(new Card(Suit.Spades, Value.Three));
        SAM.deal(new Card(Suit.Hearts, Value.Two));
        SAM.deal(new Card(Suit.Clubs, Value.Ace));
        assertEquals(16, SAM.getScore());
    }
}
