/**
 * Created by Chen Wang on 10/7/2016.
 */
public class Node {
    public int data;
    public int depth;
    public Node left = null;
    public Node right = null;

    public Node(int d, int h) {
        data = d;
        depth = h;
    };
}