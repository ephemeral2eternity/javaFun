/**
 * Created by Chen Wang on 10/6/2016.
 */
public class BTreeNode<T extends Comparable> {
    public T id;
    public BTreeNode<T> left = null;
    public BTreeNode<T> right = null;

    public BTreeNode(T data) {
        id = data;
    }

    public void print() {
        System.out.printf("%s ", id);
    }
}
