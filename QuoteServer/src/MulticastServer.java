/**
 * Created by Chen Wang on 10/28/2016.
 */
public class MulticastServer {
    public static void main(String[] args) throws java.io.IOException {
        new MulticastServerThread().start();
    }
}