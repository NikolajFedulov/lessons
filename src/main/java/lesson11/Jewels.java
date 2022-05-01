package lesson11;

public class Jewels {

    public int numberOfJewels(String jewels, String stones) {
        int count = 0;
        if (jewels!=null && stones!=null) {
            char[] jewelsArray = jewels.toCharArray();
            char[] stonesArray = stones.toCharArray();

            for (int i=0; i<jewelsArray.length; i++) {
                for (int j=0; j<stonesArray.length; j++) {
                    if (jewelsArray[i] == stonesArray[j]) {
                        count++;
                    }
                }
            }
        }
        else {
           count = -1;
        }
        return count;
    }
}
