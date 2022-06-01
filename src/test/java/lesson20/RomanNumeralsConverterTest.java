package lesson20;

import org.junit.Test;
import static org.junit.Assert.*;

public class RomanNumeralsConverterTest {

    private final RomanNumeralsConverter romanNumeralsConverter = new RomanNumeralsConverter();
    private final String[] test = {
            "MCMXCIV",
            "mcmxciv",
            "LXXVIII",
            "VIIII",
            "IVII",
            "VVV",
            "IVXM",
            "MCMXCIC",
            "MCMACIG",
            "",
            null
    };
    private final int[] expected = {
            1994,
            1994,
            78,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1
    };

    @Test
    public void romanNumberToArabic() {
        for (int i=0; i<test.length; i++){
            assertEquals(expected[i], romanNumeralsConverter.romanNumberToArabic(test[i]));
        }
    }

    @Test
    public void romanNumberToArabic2() {
        for (int i=0; i<test.length; i++){
            assertEquals(expected[i], romanNumeralsConverter.romanNumberToArabic2(test[i]));
        }
    }
}