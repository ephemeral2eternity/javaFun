package service;
import impl.ImplSolution;
/**
 * Created by Chen Wang on 10/18/2016.
 */
public class SolutionArray implements ImplSolution{

    /**
     * Multiply the vector by the transit matrix to obtain the number of special
     * strings with one character longer that end with each vowel.
     * @param  arr  the array consisting of number of special strings ending with each vowel
     */
    private static void updateArray(long[] arr) {
        long a = arr[1] + arr[2] + arr[4];
        long e = arr[0] + arr[2];
        long i = arr[1] + arr[3];
        long o = arr[2];
        long u = arr[2] + arr[3];

        arr[0] = a;
        arr[1] = e;
        arr[2] = i;
        arr[3] = o;
        arr[4] = u;
    }



    /**
     * Algorithm 2
     * Get the number of possible special strings with the denoted length according to the question.
     * Time complexity: O(N)
     * Spatial complexity: two 5 dimensional vectors
     * @param length the length of the special string
     * @return totalCnt the total number of possible special strings
     */
    public long findNumSpecialStrings(int length) {
        long[] arr = {1, 1, 1, 1, 1};
        long count;
        for (int i = 1; i < length; i ++) {
            updateArray(arr);
            // count = arr[0] + arr[1] + arr[2] + arr[3] + arr[4];
            // System.out.printf("Level %d: [%d %d %d %d %d] Count: %d\n", i, arr[0], arr[1], arr[2], arr[3], arr[4], count);
        }
        count = arr[0] + arr[1] + arr[2] + arr[3] + arr[4];

        return count;
    }

}
