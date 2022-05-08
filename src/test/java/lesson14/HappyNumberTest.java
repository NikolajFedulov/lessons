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
}