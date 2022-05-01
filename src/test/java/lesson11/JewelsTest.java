package lesson11;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class JewelsTest {

    @Test
    public void jewelsQuantity() {
        Jewels jewels = new Jewels();
        int numberOfStones = 10; // Quantity stones = quantity jewels in stones
        char jewelsChar = 'a';
        char[] jewelsArray = {jewelsChar};
        char[] stonesArray = new char[numberOfStones];
        Arrays.fill(stonesArray, jewelsChar);
        String jewelsString = new String(jewelsArray);  // "a"
        String stonesString = new String(stonesArray);  // "aaa...a"
        Assert.assertEquals(numberOfStones, jewels.numberOfJewels(jewelsString, stonesString));
        char jewelsChar2 = 'b';
        char[] jewelsArray2 = {jewelsChar2};
        String jewelsString2 = new String(jewelsArray2);
        Assert.assertNotEquals(numberOfStones, jewels.numberOfJewels(jewelsString2, stonesString));
        int argumentIsNull = -1;
        Assert.assertEquals(argumentIsNull, jewels.numberOfJewels(jewelsString, null));
    }
}