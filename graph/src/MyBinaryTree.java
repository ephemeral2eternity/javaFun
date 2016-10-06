/**
 * Created by Chen Wang on 10/6/2016.
 */
import java.util.*;
public class MyBinaryTree<T extends Comparable> {
    BTreeNode<T> searchLeafNode(BTreeNode root, T val) {
        BTreeNode<T> leaf;
        if (val.compareTo(root.id) < 0) {
            if (root.left != null) {
                leaf = searchLeafNode(root.left, val);
            }
            else {
                leaf = root;
            }
        }
        else {
            if (root.right != null) {
                leaf = searchLeafNode(root.right, val);
            }
            else {
                leaf = root;
            }
        }
        return leaf;
    }

    BTreeNode<T> generateBinaryTree(T[] vals) {
        if (vals.length <= 0) {return null;}

        T root_val = vals[0];
        BTreeNode<T> root = new BTreeNode(root_val);
        BTreeNode<T> current;
        for (int i = 1; i < vals.length; i ++) {
            current = root;
            BTreeNode<T> child = new BTreeNode(vals[i]);
            BTreeNode<T> leaf = searchLeafNode(current, vals[i]);
            if (vals[i].compareTo(leaf.id) < 0) {
                leaf.left = child;
            }
            else
            {
                leaf.right = child;
            }
        }
        return root;
    }

    void printBranch(BTreeNode<T> root) {
        Queue<BTreeNode> queue = new LinkedList<BTreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BTreeNode<T> node = queue.remove();
            node.print();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            // System.out.println();
        }
    }

    static public void main(String[] args) {
        Integer[] ids = {8, 4, 10, 2, 6, 20};
        MyBinaryTree<Integer> myTree = new MyBinaryTree<Integer>();
        BTreeNode<Integer> root = myTree.generateBinaryTree(ids);

        myTree.printBranch(root);

    }

}
