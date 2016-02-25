/**
 * Created by Chen Wang on 2/25/2016.
 */
public class Solution {
    public double findMedianSortedArrays(int[] A, int[] B)
    {
        int m = A.length;
        int n = B.length;

        if ((m + n) % 2 != 0) {
            return (double) findKth(A, B, (m+n)/2, 0, m-1, 0, n-1);
        }
        else {
            return ( findKth(A, B, (m+n)/2, 0, m-1, 0, n-1)
                    + findKth(A, B, (m+n)/2 - 1, 0, m-1, 0, n-1) ) * 0.5;
        }
    }

    public static int findKth(int A[], int B[], int k, int aStart, int aEnd, int bStart, int bEnd)
    {
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        if (aLen == 0)
            return B[bStart + k];
        if (bLen == 0)
            return A[aStart + k];
        if (k == 0)
            return A[aStart] < B[bStart] ? A[aStart] : B[bStart];

        int aMid = aLen * k / (aLen + bLen);
        int bMid = k - aMid - 1;

        aMid = aMid + aStart;
        bMid = bMid + bStart;

        if (A[aMid] > B[bMid]) {
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        } else
        {
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        }

        System.out.printf("Updated k: %d\n", k);
        System.out.printf("Updated range for array A: %d, %d\n", aStart, aEnd);
        System.out.printf("Updated range for array B: %d, %d\n", bStart, bEnd);
        return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
    }
}
