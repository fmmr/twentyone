package no.rodland.twentyone;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Player SAM;
    private Player DEALER;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Before
    public void setup() {
        SAM = new Player("sam");
        DEALER = new Player("dealer");
    }

    @Test
    public void example_from_doc() {
        Deck deck = new Deck("CA, D5, H9, HQ, S8,");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(SAM, winner);
        assertEquals(20, SAM.getScore());
        assertEquals(23, DEALER.getScore());
        TwentyOne.report(SAM, DEALER, SAM);
        assertEquals("sam\n" +
                             "sam: CA, H9\n" +
                             "dealer: D5, HQ, S8\n", outContent.toString());

    }

}
