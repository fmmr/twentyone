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
    public void other_name() {
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
    public void to_string_test_second() {
        // verify we create a new SAM each test
        SAM.deal(new Card(Suit.Spades, Value.Three));
        SAM.deal(new Card(Suit.Hearts, Value.King));
        SAM.deal(new Card(Suit.Clubs, Value.King));
        assertEquals("sam: S3, HK, CK", SAM.toString());
    }
}
