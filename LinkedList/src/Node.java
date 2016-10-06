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

    public Node(int[] vals) {
        if (vals.length > 0) {
            this.data = vals[0];
            Node n = this;
            for (int i = 1; i < vals.length; i++) {
                n.next = new Node(vals[i]);
                n = n.next;
            }
        }
    }

    public void printList() {
        Node current = this;
        if (current == null) {
            System.out.println("NULL");
        }
        else {
            System.out.printf("%d --> ", current.data);
            while (current.next != null) {
                current = current.next;
                System.out.printf("%d --> ", current.data);
            }
            System.out.println("NULL");
        }
    }
}
