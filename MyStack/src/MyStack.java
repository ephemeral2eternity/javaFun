/**
 * Created by Chen on 10/6/16.
 */
import java.lang.Throwable;
import java.util.*;

public class MyStack {
    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }

        private StackNode<T> top;

        public T pop() {
            if (top == null) throw new EmptyStackException();
            T item = top.data;
            top = top.next;
            return item;
        }
    }
}
