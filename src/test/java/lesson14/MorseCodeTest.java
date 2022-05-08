package lesson14;

import org.junit.Assert;
import org.junit.Test;


public class MorseCodeTest {

    @Test
    public void numberOfDifferentTransformations() {
        String[][] test = {
                {"gin", "zen", "gig", "msg", "agr"},    // answer = 3
                {"GIn", "zen", "giG", "mSg", "Agr"},    // answer = 3
                {"fun", "f4n"},                         // answer = -1
                {"fun", null},                          // answer = -1
                null,                                   // answer = -1
                {"fun", ""}                             // answer = -1
        };
        int[] answer = {3, 3, -1, -1, -1, -1};
        MorseCode morseCode = new MorseCode();
        for (int i=0; i<answer.length; i++){
            Assert.assertEquals(answer[i], morseCode.numberOfDifferentTransformations(test[i]));
        }
    }
}