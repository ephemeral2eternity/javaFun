import java.util.Scanner;

/**
 * Created by Chen Wang on 10/20/2016.
 */
public class TestTree {
    public TreeNode init_bank_tree() {
        Scanner sc = new Scanner(System.in);
        String line;
        int parentID, childID;
        TreeNode child, parent;
        String[] lineArr;
        int N = Integer.parseInt(sc.nextLine());
        TreeNode root;

        // Create root node first
        line = sc.nextLine();
        lineArr = line.split(",");
        parentID = Integer.parseInt(lineArr[0]);
        childID = Integer.parseInt(lineArr[1]);

        root = new TreeNode(parentID, null);
        child = new TreeNode(childID, root);
        root.children.add(child);

        for (int i = 1; i < N - 1; i ++) {
            line = sc.nextLine();
            lineArr = line.split(",");
            parentID = Integer.valueOf(lineArr[0]);
            childID = Integer.valueOf(lineArr[1]);

            parent = root.findNode(parentID);
            child = new TreeNode(childID, parent);
            parent.children.add(child);
        }

        return root;
    }
    static public void main(String[] args) {
        TestTree test = new TestTree();

        TreeNode root = test.init_bank_tree();

        int dist = root.findDistance(1, 3);
        System.out.printf("The distance between node 1 and 3 is %d\n", dist);

        dist = root.findDistance(2, 3);
        System.out.printf("The distance between node 2 and 3 is %d\n", dist);

        dist = root.findDistance(5, 9);
        System.out.printf("The distance between node 5 and 9 is %d\n", dist);
    }
}
