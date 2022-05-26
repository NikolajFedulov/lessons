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

    private final TwoRomanNumeral[] twoRomanNumerals = {
            TwoRomanNumeral.IV,
            TwoRomanNumeral.IX,
            TwoRomanNumeral.IL,
            TwoRomanNumeral.IC,
            TwoRomanNumeral.ID,
            TwoRomanNumeral.IM,
            TwoRomanNumeral.VX,
            TwoRomanNumeral.VL,
            TwoRomanNumeral.VC,
            TwoRomanNumeral.VD,
            TwoRomanNumeral.VM,
            TwoRomanNumeral.XL,
            TwoRomanNumeral.XC,
            TwoRomanNumeral.XD,
            TwoRomanNumeral.XM,
            TwoRomanNumeral.LC,
            TwoRomanNumeral.LD,
            TwoRomanNumeral.LM,
            TwoRomanNumeral.CD,
            TwoRomanNumeral.CM,
            TwoRomanNumeral.DM
    };

    private final Map<String, RomanNumerals> mapOfRN = new HashMap<>();

    public RomanNumeralsConverter() {
        for (RomanNumerals rn: romanNumerals){
            mapOfRN.put(rn.symbol, rn);
        }
    }

    public int converter (RomanNumerals[] romanNumerals){
        int result = 0;
        if (romanNumerals != null) {
            for (RomanNumerals rn: romanNumerals) {
                if (rn == null) {
                    return -1;
                }
            }
            for (int i=0; i<romanNumerals.length; i++) {
                if (i<romanNumerals.length-1) {
                    result += romanNumerals[i].value >= romanNumerals[i+1].value ? romanNumerals[i].value : -romanNumerals[i].value;
                }
                else {
                    result += romanNumerals[i].value;
                }
            }
            return result;
        }
        return -1;
    }

    public RomanNumerals[] parseRomanNumerals (String romanNumeralsAsString) {
        if (romanNumeralsAsString != null) {
            String[] romanNumeralsAtStringArrays = romanNumeralsAsString.split("");
            RomanNumerals[] result = new RomanNumerals[romanNumeralsAtStringArrays.length];
            for (int i=0; i<romanNumeralsAtStringArrays.length; i++) {
                for (int j=0; j<romanNumerals.length; j++) {
                    if (romanNumeralsAtStringArrays[i].equalsIgnoreCase(romanNumerals[j].symbol)) {
                        result[i] = romanNumerals[j];
                        break;
                    }
                    if (j==romanNumerals.length-1) {
                        result[i] = null;
                    }
                }
            }
            return Arrays.stream(result).anyMatch(Objects::isNull) ? null : result;
        }
        return null;
    }

    public RomanNumerals[] parseRomanNumerals2 (String romanNumeralsAsString) {
        if (romanNumeralsAsString != null) {
            String[] romanNumeralsAsStringArrays = romanNumeralsAsString.toUpperCase().split("");
            RomanNumerals[] result = Arrays.stream(romanNumeralsAsStringArrays)
                    .map(s -> mapOfRN.getOrDefault(s, null))
                    .toArray(RomanNumerals[]::new);
            return Arrays.stream(result).anyMatch(Objects::isNull) ? null : result;
        }
        return null;
    }

    public int converter(String romanNumeralsAsString){
        if (romanNumeralsAsString != null) {
            int negativeResult = Arrays.stream(twoRomanNumerals)
                    .filter(twoRomanNumeral -> romanNumeralsAsString.toUpperCase(Locale.ROOT).contains(twoRomanNumeral.symbol))
                    .mapToInt(twoRomanNumeral -> twoRomanNumeral.value)
                    .reduce(0, Integer::sum);
            String[] romanNumeralsAsStringArrays = romanNumeralsAsString.toUpperCase().split("");
            RomanNumerals[] romanNumerals = Arrays.stream(romanNumeralsAsStringArrays)
                    .map(s -> mapOfRN.getOrDefault(s,null))
                    .toArray(RomanNumerals[]::new);
            if (Arrays.stream(romanNumerals).anyMatch(Objects::isNull)) {
                return -1;
            }
            int positiveResult = Arrays.stream(romanNumerals)
                    .mapToInt(r -> r.value)
                    .reduce(0, Integer::sum);
            return positiveResult + 2 * negativeResult;
        }
        return -1;
    }
}
