/**
 * Created by Chen Wang on 10/6/2016.
 */
import java.util.*;
public class MyBinaryTree<T extends Comparable> {
    BTreeNode<T> searchLeafNode(BTreeNode root, T val) {
        BTreeNode<T> leaf;
        if (val.compareTo(root.data) < 0) {
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
            if (vals[i].compareTo(leaf.data) < 0) {
                leaf.left = child;
            }
            else
            {
                leaf.right = child;
            }
        }
        return root;
    }

    BTreeNode<T> Insert(BTreeNode<T> root, T value) {
        if (root == null) {
            root = new BTreeNode(value);
        }
        else {
            if (value.compareTo(root.data) < 0) {
                root.left = Insert(root.left, value);
            }
            else {
                root.right = Insert(root.right, value);
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

    void LevelOrder(BTreeNode<T> root)
    {
        Queue<BTreeNode<T>> queue = new LinkedList<BTreeNode<T>>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BTreeNode<T> n = queue.remove();
            System.out.printf("%d ", n.data);
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
    }

    int height(BTreeNode<T> root)
    {
        if (root == null) {return -1;}

        int lheight = height(root.left);
        int rheight = height(root.right);

        return lheight > rheight ? lheight + 1 : rheight + 1;
    }

    void top_view(BTreeNode<T> root)
    {
        if (root != null) {
            if (root.left != null) {
                root.left.right = null;
                top_view(root.left);
            }

            System.out.printf("%d ", root.data);

            if (root.right != null) {
                root.right.left = null;
                top_view(root.right);
            }
        }
    }


    static public void main(String[] args) {
        Integer[] ids = {8, 4, 10, 2, 6, 3, 1, 9, 12, 20};
        MyBinaryTree<Integer> myTree = new MyBinaryTree<Integer>();
        BTreeNode<Integer> root = myTree.generateBinaryTree(ids);

        // Level Order Traversal
        System.out.println("Print the tree level by level: ");
        myTree.printBranch(root);

        // Pre-order Traversal
        System.out.print("Print the node values in the tree's preorder traversal:");
        myTree.preOrderPrint(root);
        System.out.println();

        // In-order Traversal
        System.out.print("Print the node values in the tree's inorder traversal:");
        myTree.inOrderPrint(root);
        System.out.println();

        // Post-order Traversal
        System.out.print("Print the node values in the tree's postorder traversal:");
        myTree.postOrderPrint(root);
        System.out.println();

        // Level-order Traversal (Breadth first traversal)
        System.out.print("Print the node values in the tree's level order traversal:");
        myTree.LevelOrder(root);
        System.out.println();

        // Print the height of the tree
        int ht = myTree.height(root);
        System.out.printf("The height of the tree is: %d.\n", ht);

        // Print the top view of the tree
        System.out.printf("The top view of the tree is: ");
        myTree.top_view(root);

    }

}
