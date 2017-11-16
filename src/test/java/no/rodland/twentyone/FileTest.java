package no.rodland.twentyone;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileTest {
    private Player SAM;
    private Player DEALER;

    @Before
    public void setup() {
        SAM = new Player("sam");
        DEALER = new Player("dealer");
    }

    @Test
    public void read_from_classpath() throws IOException, URISyntaxException {
        final Deck deck = TwentyOne.readDeckFromFile("test_deck_1.txt");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(DEALER, winner);
    }

    @Test
    public void read_from_test_classpath() throws IOException, URISyntaxException {
        final Deck deck = TwentyOne.readDeckFromFile("test_deck_2.txt");
        TwentyOne game = new TwentyOne(deck, SAM, DEALER);
        final Player winner = game.play();
        assertEquals(SAM, winner);
    }
}
