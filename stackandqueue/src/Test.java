import java.util.*;

/**
 * Created by Chen Wang on 10/6/2016.
 */
public class Test {
    static public void main(String[] args) {
        int[] dataToTest = {1, 2, 3, 4, 5, 6};

        // Test code for MyStack Class
        MyStack<Integer> myst = new MyStack<Integer>();

        int popVal;

        for (int val : dataToTest) {
            myst.push(val);
        }

        try {
            System.out.printf("The elements in the stack is: ");
            while (true) {
                popVal = myst.pop();
                System.out.printf("%d ", popVal);
            }
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }

        // Test code for MyQueue Class.
        MyQueue<Integer> myQ = new MyQueue<Integer>();
        int removeVal;

        for (int val : dataToTest) {
            myQ.add(val);
        }

        try {
            System.out.printf("The elements in the queue is: ");
            while(true) {
                removeVal = myQ.remove();
                System.out.printf("%d ", removeVal);
            }

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
