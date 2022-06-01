package lesson20;

public enum Subtract {
    IV (-1, "IV"),
    IX (-1, "IX"),
    IL (-1, "IL"),
    IC (-1, "IC"),
    ID (-1, "ID"),
    IM (-1, "IM"),
    VX (-5, "VX"),
    VL (-5, "VL"),
    VC (-5, "VC"),
    VD (-5, "VD"),
    VM (-5, "VM"),
    XL (-10, "XL"),
    XC (-10, "XC"),
    XD (-10, "XD"),
    XM (-10, "XM"),
    LC (-50, "LC"),
    LD (-50, "LD"),
    LM (-50, "LM"),
    CD (-100, "CD"),
    CM (-100, "CM"),
    DM (-500, "DM");

    public final int value;
    public final String symbol;

    Subtract(int value, String symbol){
        this.value = value;
        this.symbol = symbol;
    }
}
