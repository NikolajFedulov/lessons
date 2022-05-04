package lesson12;

import org.junit.Assert;
import org.junit.Test;

public class GoodPairsTest {

    @Test
    public void goodPairsQuantity() {
        GoodPairs goodPairs = new GoodPairs();
        final int numberOfCycles = 2;
        int numberOfDigitsInCycle = 5;
        int[] forTest = new int[numberOfCycles * numberOfDigitsInCycle];
        for (int i = 0; i < forTest.length; i++) {  //{0, 1, ... n[i] = n[i-1]+1, 0, 1, ... n[i] = n[i-1]+1}
            forTest[i] = i % numberOfDigitsInCycle;
        }
        Assert.assertEquals(numberOfDigitsInCycle, goodPairs.numberOfGoodPairs(forTest));
        int[] forTest2 = new int[numberOfCycles * numberOfDigitsInCycle];
        for (int i = 0; i < forTest.length; i++) {
            forTest2[i] = i;
        }
        Assert.assertNotEquals(numberOfDigitsInCycle,goodPairs.numberOfGoodPairs(forTest2));
        int argumentIsNull = -1;
        Assert.assertEquals(argumentIsNull, goodPairs.numberOfGoodPairs(null));
    }
}