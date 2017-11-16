package no.rodland.twentyone;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

class TwentyOne {
    private final Deck deck;
    private final Player sam;
    private final Player dealer;

    TwentyOne(Deck deck, Player sam, Player dealer) {
        this.deck = deck;
        this.sam = sam;
        this.dealer = dealer;
    }

    /**
     * The game should be able to read a file containing a deck of cards, taking the reference to the files as a command line
     * argument, as a starting point. If no file is provided, a new shuffled deck should be initialized.
     *
     * @param args optional filename as first (and only) argument
     * @throws IOException if file is not found
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        // • create a single deck of playing cards
        Deck deck = args.length == 0 ? new Deck() : readDeckFromFile(args[0]);
        // • two players (called Sam and the Dealer) who will play against each other
        final Player sam = new Player("sam");
        final Player dealer = new Player("dealer");
        TwentyOne game = new TwentyOne(deck, sam, dealer);
        final Player winner = game.play();
        report(sam, dealer, winner);
    }

    /**
     * At the end, the solution should print the name of the winner to standard out, together with the hands of both the dealer
     * and Sam. Using the following format
     */
    private static void report(Player sam, Player dealer, Player winner) {
        System.out.println(winner.getName());
        System.out.println(sam);
        System.out.println(dealer);
    }

    static Deck readDeckFromFile(String filename) throws IOException, URISyntaxException {
        try {
            return new Deck(new String(Files.readAllBytes(Paths.get(filename))));
        } catch (NoSuchFileException e) {
            // FMR: try reading from classpath
            final URL resource = filename.getClass().getResource("/" + filename);
            return new Deck(new String(Files.readAllBytes(Paths.get(resource.toURI()))));
        }
    }

    public Player play() {
        // • each player is given two cards from the top of a shuffled deck of cards
        initialDeal();

        // • check if either player has blackjack (21) with their initial hand and wins the game
        // • Sam wins when both players starts with 21
        if (sam.getScore() == 21) {
            return sam;
        }
        // • Dealer wins when both players starts with 22
        if (dealer.getScore() >= 21) {
            return dealer;
        }

        //• if neither player has blackjack then Sam can start drawing cards from the top of the deck
        dealToSam();

        // • Sam has lost the game if their total is higher than 21
        if (sam.getScore() > 21) {
            return dealer;
        }
        // • when Sam has stopped drawing cards the Dealer can start drawing cards from the top of the deck
        dealToDealer();

        // • the Dealer has lost the game if their total is higher than 21
        // • determine which player wins the game  (FMR: really no need to check any more)
        return dealer.getScore() <= 21 ? dealer : sam;
    }

    private void dealToSam() {
        // • Sam should stop drawing cards from the deck if their total reaches 17 or higher
        while (sam.getScore() < 17) {
            sam.deal(deck.pop());
        }
    }

    private void dealToDealer() {
        // • the Dealer should stop drawing cards when their total is higher than Sam.
        while (dealer.getScore() <= sam.getScore()) {
            dealer.deal(deck.pop());
        }
    }

    void initialDeal() {
        // • each player is given two cards from the top of a shuffled deck of cards
        sam.deal(deck.pop());
        dealer.deal(deck.pop());
        sam.deal(deck.pop());
        dealer.deal(deck.pop());
    }
}
