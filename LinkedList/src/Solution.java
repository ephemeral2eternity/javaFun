import java.util.HashSet;

/**
 * Created by Chen Wang on 10/5/2016.
 */
public class Solution {
    void removeDups(Node head) {
        HashSet<Integer> uniqData = new HashSet<Integer>();
        Node previous = null;
        Node n = head;
        while (n != null) {
            if (uniqData.contains(n.data)) {
                previous.next = n.next;
            }
            else {
                uniqData.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    void removeDupsNoBuffer(Node head) {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                }
                else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    Node returnKthToLast(Node head, int K) {
        Node current = head;
        Node kAdvance = current;
        for (int i = 0; i < K; i ++) {
            if (kAdvance.next != null) {
                kAdvance = kAdvance.next;
            }
            else {
                return null;
            }
        }

        while (kAdvance != null) {
            current = current.next;
            kAdvance = kAdvance.next;
        }

        return current;
    }

    // Recursive implementation
    int printKthToLast(Node head, int K) {
        if (head == null) {return 0;}

        int index = printKthToLast(head.next, K) + 1;
        if (index == K) {
            System.out.printf("The %dth node to the last is: %d\n", K, head.data);
        }

        return index;
    }

    boolean deleteNode(Node n) {
        if ((n == null) || (n.next == null)) {
            return false;
        }
        Node next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    public static void main(String[] args) {
        int[] vals = {1, 2, 3, 3, 2, 1, 4, 7};
        Node head = new Node(vals);
        System.out.print("The list is: ");
        head.printList();

        Solution sol = new Solution();
        int K = 4;
        sol.printKthToLast(head, K);
        /*Node kthToLast = sol.returnKthToLast(head, K);
        if (kthToLast == null) {
            System.out.printf("Cannot find the %d th node on the above list! Either K is 0 or it is larger than the list length!", K);
        }
        else {
            System.out.printf("The %dth node to the last is: %d\n", K, kthToLast.data);
        }*/

        sol.removeDupsNoBuffer(head);
        System.out.print("The list after removing the duplicated elements is: ");
        head.printList();

        Node toDelete = head.next.next.next.next;
        int toDeleteVal = toDelete.data;
        boolean deleted = sol.deleteNode(toDelete);
        if (deleted) {
            System.out.printf("Successfully delete node with value %d.\nThe list becomes:", toDeleteVal);
            head.printList();
        }
        else {
            System.out.println("Cannot delete the node as it is either a null or the last node!");
        }
    }
}
