package lesson23;

import org.junit.Assert;
import org.junit.Test;

public class WordPatternTest {


    @Test
    public void isThisAPattern() {
        String pattern = "abba";
        String testTrue = "dog cat cat dog";
        String[] testFalse = {
                "dog cat cat fish",
                "dog cat cat dog cat",
                null
        };
        WordPattern wordPattern = new WordPattern(pattern);

        Assert.assertTrue(wordPattern.isThisAPattern(testTrue));
        for (String str: testFalse) {
            Assert.assertFalse(wordPattern.isThisAPattern(str));
        }
        Assert.assertFalse(new WordPattern("aaaa").isThisAPattern(testTrue));
    }
}