package lesson20;

public class Main {
    public static void main(String[] args) {
        String s1 = "MCMXCIV";
        RomanNumerals[] romanNumerals = {
                RomanNumerals.M,
                RomanNumerals.C,
                RomanNumerals.M,
                RomanNumerals.X,
                RomanNumerals.C,
                RomanNumerals.I,
                RomanNumerals.V
        };
        RomanNumeralsConverter romanNumeralsConverter = new RomanNumeralsConverter();
        int number = romanNumeralsConverter.converter(romanNumerals);
        System.out.println(number);

        RomanNumerals[] romanNumerals1 = romanNumeralsConverter.parseRomanNumerals(s1);
        int number1 = romanNumeralsConverter.converter(romanNumerals1);
        System.out.println(number1);

        RomanNumerals[] romanNumerals2 = romanNumeralsConverter.parseRomanNumerals2(s1);
        int number2 = romanNumeralsConverter.converter(romanNumerals2);
        System.out.println(number2);

        int number3 = romanNumeralsConverter.converter(s1);
        System.out.println(number3);
    }
}
