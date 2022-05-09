package lesson14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Stream;

public class MorseCode {
    private final HashMap<String, String> lettersAndMorse = new HashMap<>(26);

    public MorseCode() {
        String[] morseLetters = {
                ".-",
                "-...",
                "-.-.",
                "-..",
                ".",
                "..-.",
                "--.",
                "....",
                "..",
                ".---",
                "-.-",
                ".-..",
                "--",
                "-.",
                "---",
                ".--.",
                "--.-",
                ".-.",
                "...",
                "-",
                "..-",
                "...-",
                ".--",
                "-..-",
                "-.--",
                "--.."
        };
        String[] letters = {
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z"
        };
        for (int i = 0; i< letters.length; i++) {
            lettersAndMorse.put(letters[i], morseLetters[i]);
        }
    }

    public int numberOfDifferentTransformations (String[] str) {
        try {
            for (int i=0; i<str.length; i++){
                str[i] = str[i].toLowerCase(Locale.ROOT);
            }
        }
        catch (NullPointerException e) {
            return -1;
        }
        String[] transformations = new String[str.length];
        Arrays.fill(transformations, "");
        for (int i=0; i<str.length; i++){
            if (!str[i].equals("")){
                for (int j=0; j<str[i].length(); j++){
                    if (Character.isLetter(str[i].charAt(j))){
                        transformations[i] += lettersAndMorse.get(str[i].substring(j,j+1));
                    }
                    else {
                        return -1;
                    }
                }
            }
            else {
                return -1;
            }
        }
        //System.out.println(Arrays.toString(transformations));
        int count = 0;
        Arrays.sort(transformations);
        for (int i=0; i<transformations.length-1; i++){
            if (!transformations[i].equals(transformations[i+1])){
                count++;
            }
        }
        return count+1;
    }

    public int numberOfDifferentTransformations2 (String[] str) {
        try {
            Stream<String> streamOfArray = Arrays.stream(str);
            return (int) streamOfArray.
                    map(String::toLowerCase).
                    map(s -> s.split("")).
                    map(Arrays::stream).
                    map(stringStream -> stringStream.
                            map(lettersAndMorse::get).
                            map(s -> s.toLowerCase(Locale.ROOT)).               // exception if null (not for reduce())
                            reduce((acc, s) -> acc + s).orElseThrow()
                    ).
                    distinct().
                    count();
        } catch (NullPointerException e) {
            return -1;
        }
    }
}
