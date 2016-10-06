/**
 * Created by Chen Wang on 10/6/2016.
 */
import java.util.*;
public class Node<T> {
    public T id;
    public ArrayList<Node> children;

    public Node(T data) {
        id = data;
        children = new ArrayList<Node>();
    }
}
