import java.util.Arrays;
import java.lang.*;

/**
 * Created by chenw on 1/26/17.
 */
public class StringSolutions {

    // Use the int value of a character to build a hash map for all characters in a string.
    // Check if the hash value has been checked
    boolean isUniqueChars(String str) {
        if (str.length() > 128) {
            return false;
        }

        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i ++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }

        return true;
    }

    // Use one bit as a key for each character. Check if a bit has been set before.
    boolean isUniqueCharsBit(String str){
        int checker = 0;
        for (int i = 0; i < str.length(); i ++) {
            int val = str.charAt(i);
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }

        return true;
    }

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

    String urlify(String str, int len) {
        // First count how many spaces totally there are.
        char[] strArry = str.toCharArray();
        int spaceCnt = 0, i, index;
        for (i = 0; i < len; i ++) {
            if (strArry[i] == ' ') {
                spaceCnt ++;
            }
        }

        // System.out.printf("The total length of the char array: %d\n", strArry.length);

        index = len + 2*spaceCnt;

        // Checking if there are more spaces left after the string, end the string with len.
        if (len < strArry.length) {strArry[len] = '\0';}
        // Check if the string array have not enough space to fill in %20.
        if (strArry.length < index) {
            strArry = Arrays.copyOf(strArry, index);
        }
        for (i = len - 1; i >= 0; i --){
            // System.out.printf("Index position: %d\n", index);
            if (strArry[i] == ' ') {
                strArry[index-1] = '0';
                strArry[index-2] = '2';
                strArry[index-3] = '%';
                index = index - 3;
            }
            else {
                strArry[index - 1] = strArry[i];
                index --;
            }
        }
        return new String(strArry);
    }

    public static void main(java.lang.String[] args){
        StringSolutions sol = new StringSolutions();

        /*
        // Testing the isUniqueChars128
        String str = "abcdefg()|";
        Boolean isUnique = sol.isUniqueCharsBit(str);
        if (isUnique) {
            System.out.printf("The string %s has unique characters!", str);
        }
        else {
            System.out.printf("The string %s has repetitive characters!", str);
        }
        */

        // Testing if one string is the permutation of the other
        String s = "abcdefg";
        String t = "gfedcba";
        if (sol.permutationOpt(s, t)) {
            System.out.printf("%s is a permutation of %s.\n", s, t);
        }
        else {
            System.out.printf("%s is not a permutation of %s.\n", s, t);
        }

        /*
        String str = "Mr John Smith";
        int strLen = 13;


        String urlifyStr = sol.urlify(str, strLen);
        System.out.println(urlifyStr);
        */
    }

}
