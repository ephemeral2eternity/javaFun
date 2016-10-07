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
        Queue<BTreeNode> currentLevel = new LinkedList<BTreeNode>();
        Queue<BTreeNode> nextLevel = new LinkedList<BTreeNode>();
        BTreeNode<T> node;
        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            Iterator<BTreeNode> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                node = iter.next();
                node.print();
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<BTreeNode>();
        }
    }

    void preOrderPrint(BTreeNode<T> root){
        if (root != null) {
            root.print();
            preOrderPrint(root.left);
            preOrderPrint(root.right);
        }
    }

    void inOrderPrint(BTreeNode<T> root){
        if (root != null) {
            inOrderPrint(root.left);
            root.print();
            inOrderPrint(root.right);
        }
    }

    void postOrderPrint(BTreeNode<T> root){
        if (root != null) {
            postOrderPrint(root.left);
            postOrderPrint(root.right);
            root.print();
        }
    }

    static public void main(String[] args) {
        Integer[] ids = {8, 4, 10, 2, 6, 20};
        MyBinaryTree<Integer> myTree = new MyBinaryTree<Integer>();
        BTreeNode<Integer> root = myTree.generateBinaryTree(ids);

        System.out.println("Print the tree level by level: ");
        myTree.printBranch(root);
        System.out.print("Print the node values in the tree's preorder traversal:");
        myTree.preOrderPrint(root);
        System.out.println();
        System.out.print("Print the node values in the tree's inorder traversal:");
        myTree.inOrderPrint(root);
        System.out.println();
        System.out.print("Print the node values in the tree's postorder traversal:");
        myTree.postOrderPrint(root);
    }

}
