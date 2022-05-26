package lesson20;

public enum RomanNumerals {
    I (1, "I"),
    V (5, "V"),
    X (10, "X"),
    L (50, "L"),
    C (100, "C"),
    D (500, "D"),
    M (1000, "M");

    public final int value;
    public final String symbol;

    RomanNumerals (int value, String symbol){
        this.value = value;
        this.symbol = symbol;
    }
}
