import java.util.*;
import java.lang.*;

/**
 * Question:
 * Find the number of possible "special" strings of a given length. A string is special if it meets the following conditions:
 *
 * Each letter is one of {a, e, i, o, u}.
 "a" must only be followed by "e".
 "e" must only be followed by "a" or "i".
 "i" must only be followed by "a", "e", "o", or "u".
 "o" must only be followed by "i" or "u".
 "u" must only be followed by "a".


 Implement the following function:

 long findNumSpecialStrings(int length);

 Constraints

 0 < length < 10^5

 Examples:

 length = 1
 Output = 5 {"a", "e", "i", "o", "u"}

 length = 2
 output = 10 {"ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou", "ua"}

 length = 3
 Output = 19 {"aea", "aei", "eae", "eia", "eie", "eio", "eiu", "iae", "iea", "iei", "ioi", "iou", "iua", "oia", "oie", "oio", "oiu", "oua", "uae"}

 length = 4
 Output = 35 {"aeae", "aeia", "aeie", "aeio", "aeiu", "eaea", "eaei", "eiae", "eiea", "eiei", "eioi", "eiou", "eiua", "iaea", "iaei", "ieae", "ieia",
 "ieie", "ieio", "ieiu", "ioia", "ioie", "ioio", "ioiu", "ioua", "iuae", "oiae", "oiea", "oiei", "oioi", "oiou", "oiua", "ouae", "uaea", "ouei"}
 ----
 * Created by Chen Wang on 10/7/2016.
 */
public class Solution {

    /**
     * Multiply the vector by the transit matrix to obtain the number of special
     * strings with one character longer that end with each vowel.
     * @param  arr  the array consisting of number of special strings ending with each vowel
     *
     */
    public static void updateArray(long[] arr) {
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
     * Get the square of the transition matrix.
     * @param  smat  the square transition matrix
     * @return rst the square of the input matrix
     */
    public long[][] matrixSquare(long[][] smat) {
        int d = smat.length;
        long[][] rst = new long[d][d];

        for (int i = 0; i < d; i ++) {
            for (int j = 0; j < d; j ++) {
               rst[i][j] += smat[i][j] * smat[j][i];
            }
        }

        return rst;
    }

    /**
     * Get the multiplication matrix of two square matrices.
     * @param A the first input square matrix
     * @param B the second input square matrix
     * @return rst the multiplication of square matrices A and B
     */
    public long[][] matrixMult(long[][] A, long[][] B) {
        int d = A.length;
        long[][] rst = new long[d][d];

        if ((A.length != B.length) || (A[0].length != B[0].length)
                || (A.length != A[0].length) || (B.length != B[0].length)) {
            return rst;
        }

        for (int i = 0; i < d; i ++) {
            for (int j = 0; j < d; j ++) {
                rst[i][j] += A[i][j] * B[j][i];
            }
        }

        return rst;
    }

    /**
     * Get the exponents of the base 2 expanded form of an integer.
     * @param N the input number
     * @return Stack<Integer> exponents the exponents of the base 2 expanded form of the input number N.
     *                        the exponents in the stack would be popped from the minimum to the maximum.
     */
    public Stack<Integer> getExFormBase2Exponents(int N) {
        Stack<Integer> exponents = new Stack<Integer>();

        int num = N, k;
        while (num > 0) {
            k = (int) (Math.log(num) / Math.log(2));
            exponents.push(k);
            num = num - (int) Math.pow(2, k);
        }

        return exponents;
    }

    /**
     * Algorithm 1
     * Get the number of possible special strings with the denoted length according to the question.
     * Time complexity: O(logN)
     * Spatial complexity: two 5X5 matrix
     * @param length the length of the special string
     * @return Stack<Integer> exponents the exponents of the base 2 expanded form of the input number N.
     *                        the exponents in the stack would be popped from the minimum to the maximum.
     */
    public long findNumSpecialStringsV1(int length) {
        long[][] smat = {{0, 1, 1, 0, 1}, {1, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 1, 0}};
        long[][] rst = {{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        long[][] tmp = smat.clone();
        int pk = 0, k;

        Stack<Integer> exponents = new Stack<Integer>();
        exponents = getExFormBase2Exponents(length);
        while(!exponents.isEmpty()) {
            k = exponents.pop();
            if (k == 0) {
                rst = matrixMult(rst, smat);
            }
            else {
                for (int i = 0; i < k - pk; i ++) {
                    tmp = matrixSquare(tmp);
                }
                pk = k;
                rst = matrixMult(rst, tmp);
            }
        }



    }




    public static void main(String[] args) {
        int level = 4;
        long[] arr = {1, 1, 1, 1, 1};
        long count;
        for (int i = 1; i < level; i ++) {
            updateArray(arr);
            count = arr[0] + arr[1] + arr[2] + arr[3] + arr[4];
            System.out.printf("Level %d: [%d %d %d %d %d] Count: %d\n", i, arr[0], arr[1], arr[2], arr[3], arr[4], count);
        }
    }
}
