package lesson20;

import java.util.*;

public class RomanNumeralsConverter {

    private final RomanNumerals[] romanNumerals = {
            RomanNumerals.I,
            RomanNumerals.V,
            RomanNumerals.X,
            RomanNumerals.L,
            RomanNumerals.C,
            RomanNumerals.D,
            RomanNumerals.M
    };

    private final Subtract[] subtract = {
            Subtract.IV,
            Subtract.IX,
            Subtract.IL,
            Subtract.IC,
            Subtract.ID,
            Subtract.IM,
            Subtract.VX,
            Subtract.VL,
            Subtract.VC,
            Subtract.VD,
            Subtract.VM,
            Subtract.XL,
            Subtract.XC,
            Subtract.XD,
            Subtract.XM,
            Subtract.LC,
            Subtract.LD,
            Subtract.LM,
            Subtract.CD,
            Subtract.CM,
            Subtract.DM
    };

    private final Map<String, RomanNumerals> mapOfRN = new HashMap<>();

    public RomanNumeralsConverter() {
        for (RomanNumerals rn: romanNumerals){
            mapOfRN.put(rn.symbol, rn);
        }
    }

    public int romanNumberToArabic (String romanNumberAsString){
        // String to RomanNumerals[]
        RomanNumerals[] romanNumerals;
        if (romanNumberAsString != null && !romanNumberAsString.equals("")) {
            String[] romanNumberAsStringArray = romanNumberAsString.toUpperCase(Locale.ROOT).split("");
            romanNumerals = new RomanNumerals[romanNumberAsStringArray.length];
            for (int i=0; i<romanNumberAsStringArray.length; i++) {
                for (int j=0; j<this.romanNumerals.length; j++) {
                    if (romanNumberAsStringArray[i].equals(this.romanNumerals[j].symbol)) {
                        romanNumerals[i] = this.romanNumerals[j];
                        break;
                    }
                    if (j== this.romanNumerals.length-1) {    // not found
                        return -1;
                    }
                }
            }
        }
        else {
            return -1;
        }
        // RomanNumerals[] to int
        int result = 0;
        int numberDigit = 5;
        for (int i=0; i<romanNumerals.length; i++) {
            if (romanNumerals[i].numberDigit < numberDigit){
                numberDigit = romanNumerals[i].numberDigit;
                int count = 0;
                result += romanNumerals[i].value;
                if (romanNumerals[i].isOne){        // filter for I, X, C, M
                    for (int j=i+1; j<romanNumerals.length; j++) {
                        if (10 * romanNumerals[i].value  == romanNumerals[j].value) {       // filter for IX, XC, CM
                            result += romanNumerals[j].value - 2 * romanNumerals[i].value;
                            i = j;
                            break;
                        }
                        if (5 * romanNumerals[i].value  == romanNumerals[j].value) {        // filter for IV, XL, CD
                            result += romanNumerals[j].value - 2 * romanNumerals[i].value;
                            i = j;
                            break;
                        }
                        if (romanNumerals[i] == romanNumerals[j]) {     // filter for I, II, III, X, XX, XXX ....
                            count++;
                            result += romanNumerals[j].value;
                            if (count == 2) {
                                i += count;
                                break;
                            }
                        }
                        else {
                            i += count;
                            break;
                        }
                    }
                }
                else {      // filter for V, L, D
                    for (int j=i+1; j<romanNumerals.length; j++) {
                        if (romanNumerals[j].value == romanNumerals[i].value / 5) {     // filter for VI, VII, VIII, LX ...
                            count++;
                            result += romanNumerals[j].value;
                            if (count == 3) {
                                i += count;
                                break;
                            }
                        }
                        else {
                            i += count;
                            break;
                        }
                    }
                }
            }
            else {
                return -1;
            }
        }
        return result;
    }

    private RomanNumerals[] stringToEnum (String romanNumeralsAsString){
        if (romanNumeralsAsString != null && !romanNumeralsAsString.equals("")) {
            String[] romanNumeralsAsStringArrays = romanNumeralsAsString.toUpperCase().split("");
            RomanNumerals[] result = Arrays.stream(romanNumeralsAsStringArrays)
                    .map(s -> mapOfRN.get(s))
                    .toArray(RomanNumerals[]::new);
            return Arrays.stream(result).anyMatch(Objects::isNull) ? null : result;
        }
        return null;
    }

    private boolean validation (RomanNumerals[] romanNumerals) {
        Boolean[] condition = new Boolean[16];
        String mm = "MM";
        String mm2 = "MCM";
        String mmm = "MMM";
        String cc = "CC";
        String cc2 = "CXC";
        String cc3 = "CMXC";
        String ccc = "CCC";
        String xx = "XX";
        String xx2 = "XIX";
        String xx3 = "XCIX";
        String xxx = "XXX";
        String ii = "II";
        String iii = "III";
        if (romanNumerals != null) {
            String romanNumberAsString = Arrays.stream(romanNumerals)
                    .map(romanNumerals1 -> romanNumerals1.symbol)
                    .reduce("", (acc, s) -> acc + s);
            condition[0] = Arrays.stream(romanNumerals)
                    .filter(romanNumerals1 -> romanNumerals1 == RomanNumerals.V)
                    .count() <= 1;
            condition[1] = Arrays.stream(romanNumerals)
                    .filter(romanNumerals1 -> romanNumerals1 == RomanNumerals.L)
                    .count() <= 1;
            condition[2] = Arrays.stream(romanNumerals)
                    .filter(romanNumerals1 -> romanNumerals1 == RomanNumerals.D)
                    .count() <= 1;
            int amountI = (int) Arrays.stream(romanNumerals)
                    .filter(romanNumerals1 -> romanNumerals1 == RomanNumerals.I)
                    .count();
            condition[3] = amountI <= 3;
            int amountX = (int) Arrays.stream(romanNumerals)
                    .filter(romanNumerals1 -> romanNumerals1 == RomanNumerals.X)
                    .count();
            condition[4] = amountX <= 3;
            int amountC = (int) Arrays.stream(romanNumerals)
                    .filter(romanNumerals1 -> romanNumerals1 == RomanNumerals.C)
                    .count();
            condition[5] = amountC <= 3;
            int amountM = (int) Arrays.stream(romanNumerals)
                    .filter(romanNumerals1 -> romanNumerals1 == RomanNumerals.M)
                    .count();
            condition[6] = amountM <= 3;
            condition[7] = amountI != 2 || romanNumberAsString.contains(ii);
            condition[8] = amountI != 3 || romanNumberAsString.contains(iii);
            condition[9] = amountX != 2 || romanNumberAsString.contains(xx) || romanNumberAsString.contains(xx2) || romanNumberAsString.contains(xx3);
            condition[10] = amountX != 3 || romanNumberAsString.contains(xxx);
            condition[11] = amountC != 2 || romanNumberAsString.contains(cc) || romanNumberAsString.contains(cc2) || romanNumberAsString.contains(cc3);
            condition[12] = amountC != 3 || romanNumberAsString.contains(ccc);
            condition[13] = amountM != 2 || romanNumberAsString.contains(mm) || romanNumberAsString.contains(mm2);
            condition[14] = amountM != 3 || romanNumberAsString.contains(mmm);
            condition[15] = Arrays.stream(romanNumerals)
                    .distinct()
                    .mapToInt(romanNumerals1 -> romanNumerals1.numberDigit)
                    .reduce(5, (acc, i) -> acc >= i ? i : 0) > 0;
            return Arrays.stream(condition)
                    .allMatch(c -> c);
        }
        return false;
    }

    private int enumToInt (RomanNumerals[] romanNumerals) {
        if (romanNumerals != null) {
            String romanNumberAsString = Arrays.stream(romanNumerals)
                    .map(romanNumerals1 -> romanNumerals1.symbol)
                    .reduce("", (acc, s) -> acc + s);
            int positiveResult = Arrays.stream(romanNumerals)
                    .mapToInt(r -> r.value)
                    .reduce(0, Integer::sum);
            int negativeResult = Arrays.stream(subtract)
                    .filter(twoRomanNumeral -> romanNumberAsString.contains(twoRomanNumeral.symbol))
                    .mapToInt(twoRomanNumeral -> twoRomanNumeral.value)
                    .reduce(0, Integer::sum);
            return positiveResult + 2 * negativeResult;
        }
        return -1;
    }

    public int romanNumberToArabic2 (String romanNumberAsString){
        RomanNumerals[] romanNumerals = stringToEnum(romanNumberAsString);
        if (romanNumerals != null) {
            if (validation(romanNumerals)) {
                return enumToInt(romanNumerals);
            }
            return -1;
        }
        return -1;
    }
}
