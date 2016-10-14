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

import java.util.*;
public class Solution {
    void inOrderPrint(Node root) {
        if (root != null) {
            inOrderPrint(root.left);
            System.out.printf("%d ", root.data);
            inOrderPrint(root.right);
        }
    }

    Node swapTree(Node root, int K) {
        if (root == null) return null;

        if (root.depth % K == 0) {
            Node tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }

        root.left = swapTree(root.left, K);
        root.right = swapTree(root.right, K);

        return root;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        Solution sol = new Solution();
        Node root = new Node(1, 1);
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        int N = scan.nextInt();
        int left, right, K, T, ht, h;

        // Construct the tree first.
        for (int i = 0; i < N; i ++) {
            if (!queue.isEmpty()) {
                Node current = queue.remove();
                left = scan.nextInt();
                right = scan.nextInt();
                if (left != -1) {
                    current.left = new Node(left, current.depth + 1);
                    queue.add(current.left);
                }
                if (right != -1) {
                    current.right = new Node(right, current.depth + 1);
                    queue.add(current.right);
                }
            }
        }

        sol.inOrderPrint(root);
        System.out.println();

        T = scan.nextInt();
        for (int i = 0; i < T; i ++) {
            K = scan.nextInt();
            root = sol.swapTree(root, K);
            sol.inOrderPrint(root);
            System.out.println();
        }

    }
}
