package SquareTenTree;
/**
 * Created by Chen on 10/19/16.
 */
public class SquareTenTree {

    void inOrderPrint(SquareTenNode root) {
        if (root.left != null) {
            inOrderPrint(root.left);
        }

        System.out.printf("%d %s\n", root.level, root.blkCnt);

        if (root.right != null) {
            inOrderPrint(root.right);
        }
    }

    static public void main(String[] args) {
        // String L = "800003083030000000000000050500090000000000000000000078000001000000100000180150002050000000000000";
        // String R = "523830000000480000000090020070900300098000000000003000002000000190007000000000004200400020000000008";

        // String L = "42";
        // String R = "1024";

        String L = "2";
        String R = "1000";

        SquareTenTree curTr = new SquareTenTree();
        SquareTenNode root = new SquareTenNode(L, R);

        System.out.println(root.size);
        curTr.inOrderPrint(root);

    }
}
