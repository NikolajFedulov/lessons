package lesson13;

import java.util.Arrays;

public class Lesson13Task {

    public int productOfMaximalNumbers(int[] intsArray) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Arrays.sort(intsArray);
        return (intsArray[intsArray.length-1]-1) * (intsArray[intsArray.length-2]-1);
    }

    public int[] arrayOfTheSquares(int[] intsArray) throws NullPointerException{
        Arrays.sort(intsArray);
        for (int i=0; i<intsArray.length; i++) {
            intsArray[i] *= intsArray[i];
        }
        Arrays.sort(intsArray);
        return intsArray;
    }
}
