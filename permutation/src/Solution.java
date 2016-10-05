/**
 * Created by Chen Wang on 10/5/2016.
 */
public class Solution {
    String sort(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    boolean permutation(String s, String t) {
        if (s.length() != t.length() ) {
            return false;
        }

        return sort(s).equals(sort(t));
    }

    boolean permutationOpt(String s, String t) {
        // Check if the length of two strings are equal.
        if (s.length() != t.length() ) { return false;}

        int[] charCnt = new int[128];
        for (int i = 0; i < s.length(); i ++) {
            int val = s.charAt(i);
            charCnt[val] ++;
        }

        for (int i = 0; i < t.length(); i ++) {
            int val = t.charAt(i);
            charCnt[val] --;
            if (charCnt[val] < 0) {
                return false;
            }
        }

        return true; // If the length of two strings are equal, then when there is no negative counts there should not be positive counts either.
    }


    public static void main(String[] args) {
        String s = "abcdefg";
        String t = "gfedcba";

        Solution sol = new Solution();
        if (sol.permutationOpt(s, t)) {
            System.out.printf("%s is a permutation of %s.\n", s, t);
        }
        else {
            System.out.printf("%s is not a permutation of %s.\n", s, t);
        }
    }
}
