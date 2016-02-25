/**
 * Created by Chen Wang on 2/25/2016.
 */
public class TestSolution {
    public static void main(String[] args)
    {
        int ar1[] = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18};
        int ar2[] = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

        Solution sol = new Solution();
        double median = sol.findMedianSortedArrays(ar1, ar2);
        System.out.printf("Median is %f", median);
    }
}