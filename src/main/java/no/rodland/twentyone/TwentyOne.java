package no.rodland.twentyone;


public class TwentyOne {
    private final Deck deck;
    private final Player sam;
    private final Player dealer;


    public boolean isWinnerFound() {
        return winnerFound;
    }

    public String getWinner() {
        return winner;
    }

    private boolean winnerFound = false;
    private String winner = null;

    TwentyOne(Deck deck, Player sam, Player dealer) {
        this.deck = deck;
        this.sam = sam;
        this.dealer = dealer;
    }

    public String play() {
        initialDeal();
        return "";
    }


    void initialDeal() {
        sam.deal(deck.pop());
        dealer.deal(deck.pop());
        sam.deal(deck.pop());
        dealer.deal(deck.pop());
    }

    public static void main(String[] args) {
    }

}
