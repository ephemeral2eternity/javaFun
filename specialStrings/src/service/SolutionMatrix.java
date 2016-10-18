package service;
import java.util.Stack;
import impl.ImplSolution;;

public class SolutionMatrix implements ImplSolution{

    /**
     * Get the square of the transition matrix.
     * @param  smat  the square transition matrix
     * @return rst the square of the input matrix
     */
    private long[][] matrixSquare(long[][] smat) {
        int d = smat.length;
        long tmp;
        long[][] rst = new long[d][d];

        for (int i = 0; i < d; i ++) {
            for (int j = 0; j < d; j ++) {
                tmp = 0;
                for (int k = 0; k < d; k ++) {
                    tmp += smat[i][k] * smat[k][j];
                }
                rst[i][j] = tmp;
            }
        }

        return rst;
    }

    /**
     * Get the multiplication of a matrix and a vector.
     * @param mat input matrix.
     * @param vec input vector.
     * @return rst the result vector after multiplication
     */
    private long[] matMulvec(long[][] mat, long[] vec) {
        int d = vec.length;
        long[] rst = new long[d];
        long tmp;

        for (int i = 0; i < d; i ++) {
            tmp = 0;
            for (int j = 0; j < d; j ++) {
                tmp += mat[i][j] * vec[j];
            }
            rst[i] = tmp;
        }

        return rst;
    }

    /**
     * Get the multiplication matrix of two square matrices.
     * @param A the first input square matrix
     * @param B the second input square matrix
     * @return rst the multiplication of square matrices A and B
     */
    private long[][] matrixMult(long[][] A, long[][] B) {
        int d = A.length;
        long[][] rst = new long[d][d];
        long tmp;

        if ((A.length != B.length) || (A[0].length != B[0].length)
                || (A.length != A[0].length) || (B.length != B[0].length)) {
            return rst;
        }

        for (int i = 0; i < d; i ++) {
            for (int j = 0; j < d; j ++) {
                tmp = 0;
                for (int k = 0; k < d; k ++) {
                    tmp += A[i][k] * B[k][j];
                }
                rst[i][j] = tmp;
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
     * @return totalCnt the total number of possible special strings
     */
    public long findNumSpecialStrings(int length){
        long[][] smat = {{0, 1, 1, 0, 1}, {1, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 1, 0}};
        long[][] rst = {{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
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

        strCnts = matMulvec(rst, strCnts);

        totalCnt = strCnts[0] + strCnts[1] + strCnts[2] + strCnts[3] + strCnts[4];

        return totalCnt;
    }
}

