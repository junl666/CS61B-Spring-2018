package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */


        int[] numInBucket = new int[M];
        int bucketNum;
        for (Oomage o: oomages) {
            bucketNum = Math.floorMod((o.hashCode() & 0x7FFFFFFF), M);
            numInBucket[bucketNum] += 1;
        }
        int N = oomages.size();
        for (int num: numInBucket) {
            if (num * 50 < N || num * 2.5 > N) {
                return false;
            }
        }
        return true;
    }
}
