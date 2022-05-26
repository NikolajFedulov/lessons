package lesson20;

import org.junit.Test;
import static org.junit.Assert.*;

public class RomanNumeralsConverterTest {

    private final RomanNumeralsConverter romanNumeralsConverter = new RomanNumeralsConverter();

    @Test
    public void converter() {
        RomanNumerals[][] test = {
                {
                        RomanNumerals.M,
                        RomanNumerals.C,
                        RomanNumerals.M,
                        RomanNumerals.X,
                        RomanNumerals.C,
                        RomanNumerals.I,
                        RomanNumerals.V
                },
                {
                        RomanNumerals.L,
                        RomanNumerals.V,
                        RomanNumerals.I,
                        RomanNumerals.I,
                        RomanNumerals.I
                },
                {
                        RomanNumerals.L,
                        RomanNumerals.V,
                        RomanNumerals.I,
                        null,
                        RomanNumerals.I
                },
                {
                        null
                }
        };
        int[] expected = {
                1994,
                58,
                -1,
                -1
        };

        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], romanNumeralsConverter.converter(test[i]));
        }
    }

    @Test
    public void parseRomanNumerals() {
        String[] test = {
                "MCMXCIV",
                "LVIII",
                "LVAII",
                null
        };
        RomanNumerals[][] expected = {
                {
                        RomanNumerals.M,
                        RomanNumerals.C,
                        RomanNumerals.M,
                        RomanNumerals.X,
                        RomanNumerals.C,
                        RomanNumerals.I,
                        RomanNumerals.V
                },
                {
                        RomanNumerals.L,
                        RomanNumerals.V,
                        RomanNumerals.I,
                        RomanNumerals.I,
                        RomanNumerals.I
                },
                null,
                null
        };
        for (int i=0; i<test.length; i++) {
            assertArrayEquals(expected[i], romanNumeralsConverter.parseRomanNumerals(test[i]));
        }
    }

    @Test
    public void parseRomanNumerals2() {
        String[] test = {
                "MCMXCIV",
                "LVIII",
                "LVAII",
                null
        };
        RomanNumerals[][] expected = {
                {
                        RomanNumerals.M,
                        RomanNumerals.C,
                        RomanNumerals.M,
                        RomanNumerals.X,
                        RomanNumerals.C,
                        RomanNumerals.I,
                        RomanNumerals.V
                },
                {
                        RomanNumerals.L,
                        RomanNumerals.V,
                        RomanNumerals.I,
                        RomanNumerals.I,
                        RomanNumerals.I
                },
                null,
                null
        };
        for (int i=0; i<test.length; i++) {
            assertArrayEquals(expected[i], romanNumeralsConverter.parseRomanNumerals2(test[i]));
        }
    }

    @Test
    public void testConverter() {
        String[] test = {
                "MCMXCIV",
                "LVIII",
                "LVAII",
                null
        };
        int[] expected = {
                1994,
                58,
                -1,
                -1
        };
        for (int i=0; i<test.length; i++){
            assertEquals(expected[i], romanNumeralsConverter.converter(test[i]));
        }
    }
}