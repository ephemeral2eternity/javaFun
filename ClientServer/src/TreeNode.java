import java.util.*;

/**
 * Created by Chen Wang on 10/20/2016.
 */
public class TreeNode {
    int nodeID;
    TreeNode parent;
    ArrayList<TreeNode> children;

    public TreeNode(int id, TreeNode parent) {
        this.parent = parent;
        this.nodeID = id;
        this.children = new ArrayList<TreeNode>();
    }

    public TreeNode findNode(int nID) {
        if (this.nodeID == nID) {
            return this;
        }
        else {
            for (TreeNode tmp : children) {
                TreeNode nd = tmp.findNode(nID);
                if (nd != null) {
                    return nd;
                }
            }
        }
        return null;
    }

    public int findDistance(int src, int dst) {
        TreeNode srcNode = findNode(src);
        TreeNode dstNode = findNode(dst);

        int dist = 0;
        while (srcNode.nodeID != dstNode.nodeID) {
            if (srcNode.nodeID > dstNode.nodeID) {
                dist ++;
                srcNode = srcNode.parent;
            }
            else {
                dist ++;
                dstNode = dstNode.parent;
            }
        }
        return dist;
    }
}
