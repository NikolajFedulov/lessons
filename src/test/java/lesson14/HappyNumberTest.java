package lesson14;

import org.junit.Assert;
import org.junit.Test;


public class HappyNumberTest {

    @Test
    public void isHappyNumber() {
        HappyNumber happyNumber = new HappyNumber();
        Assert.assertTrue(happyNumber.isHappyNumber(19));
        Assert.assertFalse(happyNumber.isHappyNumber(2));
    }

    @Test
    public void isHappyNumber2() {
        HappyNumber happyNumber = new HappyNumber();
        Assert.assertTrue(happyNumber.isHappyNumber2(19));
        Assert.assertFalse(happyNumber.isHappyNumber2(2));
    }
}