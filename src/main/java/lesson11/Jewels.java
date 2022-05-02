package lesson11;

public class Jewels {

    public int numberOfJewels(String jewels, String stones) {
        int count = 0;
        if (jewels!=null && stones!=null) {
            for (int i=0; i<jewels.length(); i++) {
                for (int j=0; j<stones.length(); j++) {
                    if (jewels.charAt(i) == stones.charAt(j)) {
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
