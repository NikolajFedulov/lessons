package lesson20;

public enum RomanNumerals {
    I (1, "I", true, 1),
    V (5, "V", false, 1),
    X (10, "X", true, 2),
    L (50, "L", false, 2),
    C (100, "C", true, 3),
    D (500, "D", false, 3),
    M (1000, "M", true, 4);

    public final int value;
    public final String symbol;
    public final boolean isOne;
    public final int numberDigit;

    RomanNumerals (int value, String symbol, boolean isOne, int numberDigit){
        this.value = value;
        this.symbol = symbol;
        this.isOne = isOne;
        this.numberDigit = numberDigit;
    }
}
