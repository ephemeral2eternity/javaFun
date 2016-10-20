package SquareTenTree;

import com.sun.deploy.util.StringUtils;
import com.sun.jdi.connect.Connector;

import java.util.*;

/**
 * Created by Chen on 10/19/16.
 */
public class SquareTenNode {
    public String leftBound;
    public String rightBound;
    public int level;
    public String blkCnt;
    public int size = 1;

    public SquareTenNode left;
    public SquareTenNode right;

    public SquareTenNode(String left, String right) {
        this.leftBound = left;
        this.rightBound = right;
        // System.out.printf("Node range [%s, %s]\n", left, right);
        this.level = getLevel(left, right);
        int stepDigit = 1;

        if (this.level > 0) {
            stepDigit = (int) Math.pow(2, this.level - 1);
        }

        // System.out.printf("Level: %d\n", this.level);
        // System.out.printf("stepDigits: %d\n", stepDigit);
        // System.out.printf("Node range [%s, %s]\n", left, right);

        left = fillLeadingZero(left, right.length());

        // System.out.println(left);
        // System.out.println(right);

        String blkLeftPrefix = findLeftBlkPrefix(left, stepDigit);
        String blkRightPrefix = findRightBlkPrefix(right, stepDigit);
        String leftBoundarySuffix = genBoundaryStr(stepDigit, true);
        String rightBoundarySuffix = genBoundaryStr(stepDigit, false);

        this.blkCnt = removeLeadingZero(strMinus(blkRightPrefix, blkLeftPrefix));
        // System.out.println(this.blkCnt);

        if (this.blkCnt.equals("0") || (this.blkCnt.startsWith("-"))) {
            this.blkCnt = removeLeadingZero(strAddOne(strMinus(right, left)));
            // System.out.printf("Block count on current node : %s\n", this.blkCnt);
            this.left = null;
            this.right = null;
        }
        else {
            // System.out.printf("Block count on current node : %s\n", this.blkCnt);
            String leftRightBound = removeLeadingZero(blkLeftPrefix + rightBoundarySuffix);
            String rightLeftBound = removeLeadingZero(blkRightPrefix + leftBoundarySuffix);

            if (compare(this.leftBound, leftRightBound) >= 0) {
                this.left = null;
            }
            else {
                // System.out.printf("Left node bound [%s, %s]", this.leftBound, leftRightBound);
                this.left = new SquareTenNode(this.leftBound, leftRightBound);
                this.size += this.left.size;
            }

            if (compare(this.rightBound,rightLeftBound) <= 0) {
                this.right = null;
            }
            else{
                // System.out.printf("Right node bound [%s, %s]", rightLeftBound, this.rightBound);
                this.right = new SquareTenNode(rightLeftBound, this.rightBound);
                this.size += this.right.size;
            }

        }
    }

    public String removeLeadingZero(String str) {
        int i = 0;
        while ((i < str.length()) && (str.charAt(i) == '0')) {
            i ++;
        }

        // System.out.printf("Non leading zero digit for %s starts at %d\n", str, i);
        if (i == str.length()) {
            return "0";
        }

        return str.substring(i);
    }

    public String fillLeadingZero(String str, int length) {
        int zerosToFill = length - str.length();
        String zeroPrefix = genBoundaryStr(zerosToFill, false);
        return zeroPrefix + str;
    }

    public String findRightBlkPrefix(String right, int digits) {
        return right.substring(0, right.length() - digits);
    }

    public String findLeftBlkPrefix(String left, int digits) {
        String leftPrefix = left.substring(0, left.length() - digits);
        String leftSuffix = left.substring(left.length() - digits);
        String leftBoundSuffix = genBoundaryStr(digits, true);

        // System.out.printf("Left: %s + %s\n", leftPrefix, leftSuffix);

        if (compare(leftSuffix,leftBoundSuffix) <= 0) {
            return leftPrefix;
        }
        else {
            char[] leftPrefixArr = leftPrefix.toCharArray();
            // System.out.printf("Left Boundary Prefix Length: %d\n", leftPrefixArr.length);
            int digitPos = leftPrefix.length() - 1, digitVal;
            boolean isGreater = false;
            while (!isGreater) {
                digitVal = (int) (leftPrefixArr[digitPos] - '0');
                if (digitVal == 9) {
                    leftPrefixArr[digitPos] = '0';
                    digitPos --;
                }
                else {
                    leftPrefixArr[digitPos] = (char) (digitVal + 1 + '0');
                    // System.out.printf("Left Boundary Prefix Last Digit: %c\n", leftPrefixArr[digitPos]);
                    isGreater = true;
                }
            }
            // System.out.printf("Left Boundary Prefix: %s\n", new String(leftPrefixArr));
            return new String(leftPrefixArr);
        }
    }

    public String genBoundaryStr(int num, boolean isLeft) {
        char[] chArr = new char[num];
        for (int i = 0; i < num; i ++) {
            chArr[i] = '0';
        }

        if (isLeft) {
            chArr[num - 1] = '1';
        }

        String strZeros = new String(chArr);
        return strZeros;
    }

    public int compare(String A, String B) {
        String diff = removeLeadingZero(strMinus(A, B));

        if (diff.startsWith("-")) {
            return -1;
        }
        else if (diff.equals("0")) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public String strAddOne(String str) {
        int strLen = str.length();
        char[] strArr = str.toCharArray();
        int i = strLen - 1;

        while(i >= 0) {
            if (strArr[i] != '9') {
                strArr[i] = (char) (strArr[i] + 1);
                break;
            }
            else {
                strArr[i] = '0';
                i --;
            }
        }
        return new String(strArr);
    }

    public String strMinus(String big, String small) {
        int sLen = small.length();
        int bLen = big.length();
        int smallDigit, bigDigit;
        boolean borrowed = false;

        if (sLen > bLen) {
            return "-1";
        }

        ArrayList<Integer> diffArr = new ArrayList<Integer>();

        for (int i = bLen - 1; i >= 0; i --) {
            smallDigit = (int)(small.charAt(i) - '0');
            bigDigit = (int)(big.charAt(i) - '0');

            if ((smallDigit <= bigDigit) && (!borrowed)) {
                diffArr.add(bigDigit - smallDigit);
            }
            else {
                if (borrowed) {
                    if (smallDigit < bigDigit) {
                        diffArr.add(bigDigit - smallDigit - 1);
                        borrowed = false;
                    }
                    else {
                        diffArr.add(bigDigit + 9 - smallDigit);
                    }
                }
                else {
                    borrowed = true;
                    diffArr.add(bigDigit + 10 - smallDigit);
                }
            }
        }

        if (borrowed) {
            return "-1";
        }


        int diffLen = diffArr.size();
        char[] diffChArr = new char[diffLen];
        for (int j = diffLen - 1; j >= 0; j --) {
            diffChArr[diffLen - j - 1] = (char) (diffArr.get(j) + '0');
        }

        return new String(diffChArr);
    }

    public int getLevel(String left, String right) {
        int leftLen = left.length();
        int rightLen = right.length();

        left = fillLeadingZero(left, right.length());
        String diff = removeLeadingZero(strAddOne(strMinus(right, left)));
        // System.out.printf("Difference is %s\n", diff);
        int diffLen = diff.length();
        int i = 0, level;

        if (diffLen == 1){
            level = 0;
        }
        else {
            level = (int) (Math.log(diffLen - 1)/ Math.log(2)) + 1;
        }

        return level;
    }
}
