package lesson23;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    private final String pattern;
    private final Map<String, String> map = new HashMap<>();

    public WordPattern(@NotNull String pattern) {
        this.pattern = pattern.toLowerCase();
    }

    public boolean isThisAPattern(String string) {
        if (string != null && pattern != null) {
            String[] patternElement = pattern.split("");
            String[] stringElement = string.toLowerCase().split(" ");
            if (patternElement.length == stringElement.length) {
                for (int i=0; i<patternElement.length; i++) {
                    String returnValue = map.put(patternElement[i], stringElement[i]);
                    if ( returnValue != null && !stringElement[i].equals(returnValue)) return false;
                }
                return true;
            }
        }
        return false;
    }
}
