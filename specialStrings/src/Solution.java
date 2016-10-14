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
     * Node Class for Vowels
     * Model each Vowel as a node in a graph and add allowed following vowels as its children
     **/
    public static class Node {
        public char data;
        public ArrayList<Node> children;

        public Node(char a) {
            this.data = a;
            this.children = new ArrayList<Node>();
        }

        /**
         * Recursively traverse through all possible path from current node.
         * @param  level  the depth of the count function will be called, namely the length of the path that will traversal.
         * @return totalCnt the total number of path with length level in the graph starting from the current node
         */
        public int count(int level) {
            if (level == 1) {
                return 1;
            }
            else {
                int totalCnt = 0;
                for (Node current : children) {
                    totalCnt += current.count(level - 1);
                }
                return totalCnt;
            }
        }
    }

    /**
     * Multiply the vector by the transit matrix to obtain the number of special
     * strings with one character longer that end with each vowel.
     * @param  arr  the array consisting of number of special strings ending with each vowel
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
     * Get the multiplication of a matrix and a vector.
     * @param mat input matrix.
     * @param vec input vector.
     * @param rst the result vector after multiplication
     */
    public void matMulvec(long[][] mat, long[] vec, long[] rst) {
        int d = vec.length;

        for (int i = 0; i < d; i ++) {
            for (int j = 0; j < d; j ++) {
                rst[i] += mat[i][j] * vec[j];
            }
        }
    }

    /**
     * Get the multiplication matrix of two square matrices.
     * @param A the first input square matrix
     * @param B the second input square matrix
     * @return rst the multiplication of square matrices A and B
     */
    public void matrixMult(long[][] A, long[][] B, long[][] rst) {
        int d = A.length;

        if ((A.length != B.length) || (A[0].length != B[0].length)
                || (A.length != A[0].length) || (B.length != B[0].length)) {
            return;
        }

        for (int i = 0; i < d; i ++) {
            for (int j = 0; j < d; j++) {
                for (int k = 0; k < d; k++) {
                    rst[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    /**
     * Get the square of the transition matrix.
     * @param  smat  the square transition matrix
     * @param rst the square of the input matrix
     */
    public void matrixSquare(long[][] smat, long[][] rst) {
        matrixMult(smat, smat, rst);
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
     * @return totalCnt the total number of possible special strings
     */
    public long findNumSpecialStringsV1(int length) {
        long[][] smat = {{0, 1, 1, 0, 1}, {1, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 1, 0}};
        long[][] rst = {{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        long[][] tmprst = rst.clone();
        long[][] tmp = smat.clone();
        long[] strCnts = {1, 1, 1, 1, 1};
        long totalCnt;
        int pk = 0, k;

        Stack<Integer> exponents = new Stack<Integer>();
        exponents = getExFormBase2Exponents(length - 1);
        while(!exponents.isEmpty()) {
            k = exponents.pop();
            // System.out.printf("The value of k: %d and pk: %d\n", k, pk);
            if (k == 0) {
                matrixMult(rst, smat, tmprst);
                rst = tmprst.clone();
            }
            else {
                for (int i = 0; i < k - pk; i ++) {
                    matrixSquare(tmp, tmprst);
                    tmp = tmprst.clone();
                }
                pk = k;
                matrixMult(rst, tmp, tmprst);
                rst = tmprst.clone();
            }
        }

        matMulvec(rst, strCnts);

        totalCnt = strCnts[0] + strCnts[1] + strCnts[2] + strCnts[3] + strCnts[4];

        return totalCnt;
    }

    /**
     * Algorithm 2
     * Get the number of possible special strings with the denoted length according to the question.
     * Time complexity: O(N)
     * Spatial complexity: two 5 dimensional vectors
     * @param length the length of the special string
     * @return totalCnt the total number of possible special strings
     */
    public long findNumSpecialStringsV2(int length) {
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

    /**
     * Algorithm 3
     * Get the number of possible special strings with the denoted length according to the question.
     * Time complexity: O(K^N)
     * Spatial complexity: 5 nodes
     * @param length the length of the special string
     * @return totalCnt the total number of possible special strings
     */
    public long findNumSpecialStringsV3(int length) {
        Node a = new Node('a');
        Node e = new Node('e');
        Node i = new Node('i');
        Node o = new Node('o');
        Node u = new Node('u');

        a.children.add(e);
        e.children.add(a);
        e.children.add(i);
        i.children.add(a);
        i.children.add(e);
        i.children.add(o);
        i.children.add(u);
        o.children.add(i);
        o.children.add(u);
        u.children.add(a);

        long totalCnt = a.count(length) + e.count(length) + i.count(length) + o.count(length) + u.count(length);
        // System.out.printf("Total number of strings for level %d is %d.\n", level, totalCnt);
        return totalCnt;
    }

    public static void main(String[] args) {
        int length = 20;
        long startTime, endTime, totalCnts;
        Solution sol = new Solution();

        System.out.printf("Testing three versions of finding the number of possible special strings with string length %d!\n", length);

        startTime = System.currentTimeMillis();
        totalCnts = sol.findNumSpecialStringsV1(length);
        endTime = System.currentTimeMillis();

        System.out.printf("Algorithm 1: total count = %d; running time: %d milliseconds\n", totalCnts, endTime - startTime);

        startTime = System.currentTimeMillis();
        totalCnts = sol.findNumSpecialStringsV2(length);
        endTime = System.currentTimeMillis();

        System.out.printf("Algorithm 2: total count = %d; running time: %d milliseconds\n", totalCnts, endTime - startTime);

        startTime = System.currentTimeMillis();
        totalCnts = sol.findNumSpecialStringsV3(length);
        endTime = System.currentTimeMillis();

        System.out.printf("Algorithm 3: total count = %d; running time: %d milliseconds\n", totalCnts, endTime - startTime);

    }

}
