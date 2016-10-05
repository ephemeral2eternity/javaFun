/**
 * Created by Chen Wang on 10/5/2016.
 */
public class Solution {
    // Return True if the String str has unique characters.
    boolean isUniqueChars(String str) {
        int MAX_CHAR = 128;

        if (str.length() > MAX_CHAR) {return false;}

        boolean[] char_exist = new boolean[MAX_CHAR];

        for (int i = 0; i < str.length(); i ++) {
            char c = str.charAt(i);
            int val = c;
            // System.out.printf("Char: %c; Value: %d\n", c, val);
            if (char_exist[val]) {
                return false;
            }
            char_exist[val] = true;
        }

        return true;
    }

    boolean isUniqueCharsOpt(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i ++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker = checker | (1 << val);
            System.out.printf("Char: %d; Value: %s\n", val, Long.toBinaryString(checker));
        }
        return true;
    }

    public static void main(String[] args)
    {
        // String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`~!@#$%^&*()-_=+[{]}|;:',<.>/?\"\\";
        String str = "abcdefghijklmnopqrstuvwxyz";

        Solution sol = new Solution();
        // boolean isUnique = sol.isUniqueChars(str);
        boolean isUnique = sol.isUniqueCharsOpt(str);
        if (isUnique) {
            System.out.printf("The string %s has unique characters!", str);
        }
        else {
            System.out.printf("The string %s does not have unique characters!", str);
        }
    }
}
