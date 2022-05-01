package lesson11;

public class GoodPairs {

    public int numberOfGoodPairs(int[] nor) {
        int count = 0;
        if (nor!=null) {
            for (int i=0; i<nor.length; i++) {
                for (int j=i+1; j<nor.length; j++) {
                    if (nor[i] == nor[j]) {
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
