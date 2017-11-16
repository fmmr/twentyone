package no.rodland.twentyone;

public enum Value {
    Two("2", 2), Three("3", 3), Four("4", 4), Five("5", 5),
    Six("6", 6), Seven("7", 7), Eight("8", 8), Nine("9", 9),
    Ten("10", 10), Jack("J", 10), Queen("Q", 10), King("K", 10),
    Ace("A", 11);

    private final String s;
    private final int value;

    Value(String s, int value) {
        this.s = s;
        this.value = value;
    }

    @Override
    public String toString() {
        return s;
    }

    public boolean greaterThan(Value otherValue) {
        return value > otherValue.value;
    }

    public boolean greaterThanOrEqual(Value otherValue) {
        return value >= otherValue.value;
    }

    public int sum(Value otherValue) {
        return value + otherValue.value;
    }
}
