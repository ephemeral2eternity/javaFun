/**
 * Created by Chen Wang on 10/5/2016.
 */
public class Solution {
    // Character Count Version
    boolean isPermutationOfPalindroms(String str) {
        int[] charCnt = buildCharCnt(str);
        return hasOneOdd(charCnt);
    }

    boolean isPermutaionOfPalindromsOpt(String str) {
        int bitVector = createBitVector(str);
        return (bitVector == 0) || checkOneBitSet(bitVector);
    }

    int createBitVector(String str){
        int bitVector = 0, index, mask;
        for (char c : str.toCharArray()) {
            index = getCharVal(c);
            if (index != -1) {
                mask = 1 << index;
                if ((bitVector & mask) == 0) {
                    bitVector |= mask;
                } else {
                    bitVector &= ~mask;
                }
            }
        }
        return bitVector;
    }

    // If the bitvector only has one "1", bitvector - 1 must have "1" in different digits.
    // If the bitvector has more than one digit with "1", bitvector -1 must have at least one same digit with "1" as well.
    boolean checkOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

    // Check if there is only one Odd count
    boolean hasOneOdd(int[] charCnt) {
        boolean foundOdd = false;
        for (int cnt : charCnt) {
            if (cnt % 2 == 1){
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    int[] buildCharCnt(String str) {
        int[] charCnt = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        int charVal;
        for (int i = 0; i < str.length(); i ++) {
            charVal = getCharVal(str.charAt(i));

            if (charVal != -1) {
                charCnt[charVal]++;
            }
        }
        return charCnt;
    }

    // Assuming that characters are not case sensitive and map the same characters into the same int.
    int getCharVal(char c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if ((val >= a) && (val <= z)){
            return val;
        }
        else {
            return -1;          // Ignore other characters such as space, etc.
        }
    }

    public static void main(String[] args){
        String s = "aabccbc";
        Solution sol = new Solution();
        if (sol.isPermutaionOfPalindromsOpt(s)) {
            System.out.printf("%s is a permutation of a palindrom!\n", s);
        }
        else {
            System.out.printf("%s is not a permutation of a palindrom!\n", s);
        }
    }
}
