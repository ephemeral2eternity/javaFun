/**
 * Created by Chen Wang on 10/6/2016.
 */
public class BTreeNode<T extends Comparable> {
    public T data;
    public BTreeNode<T> left = null;
    public BTreeNode<T> right = null;

    public BTreeNode() {};

    public BTreeNode(T data) {
        this.data = data;
    }

    public void print() {
        System.out.printf("%s ", data);
    }
}
