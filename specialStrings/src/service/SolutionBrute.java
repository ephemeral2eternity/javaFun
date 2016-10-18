package service;

import java.util.ArrayList;
import impl.ImplSolution;


public class SolutionBrute implements ImplSolution{
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
     * Algorithm 3
     * Get the number of possible special strings with the denoted length according to the question.
     * Time complexity: O(K^N)
     * Spatial complexity: 5 nodes
     * @param length the length of the special string
     * @return totalCnt the total number of possible special strings
     */
    public long findNumSpecialStrings(int length) {
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

}

