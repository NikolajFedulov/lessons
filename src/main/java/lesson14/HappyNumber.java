package lesson14;

import java.util.ArrayList;
import java.util.List;

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
}
