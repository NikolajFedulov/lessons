package lesson14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class HappyNumber {

    public boolean isHappyNumber(int num) {
        List<Integer> allNumbers = new ArrayList<>();
        int sum = num;
        while (!allNumbers.contains(sum)){
            if (!allNumbers.contains(sum)) {
                allNumbers.add(sum);
            }
            String numByString = String.valueOf(sum);
            char[] numByChars = numByString.toCharArray();
            int[] numByNumbers = new int[numByChars.length];
            sum = 0;
            for (int i=0; i<numByNumbers.length; i++) {
                numByNumbers[i] = Character.digit(numByChars[i], 10);
                numByNumbers[i] *= numByNumbers[i];
                sum += numByNumbers[i];
            }
        }
        return allNumbers.contains(1);
    }

    private final List<Integer> allNumbers2 = new ArrayList<>();

    public boolean isHappyNumber2(int num) {
        int sum = num;
        while (!allNumbers2.contains(sum = streamOfHappyNumber2(sum))){
            allNumbers2.add(sum);
        }
        boolean answer = allNumbers2.contains(1);
        allNumbers2.clear();
        return answer;
    }

    private int streamOfHappyNumber2(int num) {
        return Stream.of(num).
                map(String::valueOf).
                map(s -> s.split("")).
                flatMap(Arrays::stream).
                mapToInt(s -> Character.digit(s.charAt(0), 10)).
                map(s -> s*s).
                reduce(0, Integer::sum);
    }
}
