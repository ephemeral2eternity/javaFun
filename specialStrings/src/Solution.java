import java.lang.*;
import impl.*;
import factory.*;

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
    public static void main(String[] args) {
        int length = 20;
        long startTime, endTime, totalCnts;

        System.out.printf("Testing three versions of finding the number of possible special strings with string length %d!\n", length);

        for (int solType = 1; solType < 4; solType++)
        {
            ImplSolution sol = SolFactory.createSolution(solType);

            startTime = System.currentTimeMillis();
            totalCnts = sol.findNumSpecialStrings(length);
            endTime = System.currentTimeMillis();

            System.out.printf("Algorithm %d: total count = %d; running time: %d milliseconds\n", solType, totalCnts, endTime - startTime);
        }
    }

}
