package lesson13;

import org.junit.Assert;
import org.junit.Test;

public class Lesson13TaskTest {

    @Test
    public void productOfMaximalNumbers() {
        int[][] test ={
                {1},                    // exceptions
                {6, 4, 3, 5, 9, 9, 9},  // (9-1)*(9-1) = 64
                {12, 0, -7, 9, 1},      // (9-1)*(12-1) = 88
                null                    // exceptions
        };
        int[] answer = {0, 64, 88, 0};
        Lesson13Task lesson13Task = new Lesson13Task();
        for (int i=0; i<answer.length; i++) {
            try {
                Assert.assertEquals(answer[i],lesson13Task.productOfMaximalNumbers(test[i]));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Exceptions is catch: array length must be greater than 1");
            }
            catch (NullPointerException e) {
                System.out.println("Exceptions is catch: array is null");
            }
        }
    }

    @Test
    public void arrayOfTheSquares() {
        int[][] test = {
                null,           // exceptions
                {2, 5, 1, -6}   // {2*2, 5*5, 1*1, (-6)+(-6)} = {4, 25, 1, 36} -> {1, 4, 25, 36}
        };
        int[][] answer = {
                {},
                {1, 4, 25, 36}
        };
        Lesson13Task lesson13Task = new Lesson13Task();
        for (int i=0; i<answer.length; i++) {
            try {
                Assert.assertArrayEquals(answer[i], lesson13Task.arrayOfTheSquares(test[i]));
            }
            catch (NullPointerException e) {
                System.out.println("Exceptions is catch: array is null");
            }
        }
    }
}