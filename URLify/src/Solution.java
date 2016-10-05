import java.util.Arrays;

/**
 * Created by Chen Wang on 10/5/2016.
 */
public class Solution {
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

    public static void main(String[] args){
        String str = "Mr John Smith";
        int strLen = 13;

        Solution sol = new Solution();
        String urlifyStr = sol.urlify(str, strLen);
        System.out.println(urlifyStr);
    }
}
