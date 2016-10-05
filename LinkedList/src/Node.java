/**
 * Created by Chen Wang on 10/5/2016.
 */
public class Node {
    Node next = null;
    int data;

    public Node(int data) {
        this.data = data;
    }

    void appendToTail(int val) {
        if (this.next == null) {
            Node end = new Node(val);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }
    }
}
