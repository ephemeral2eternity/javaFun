/**
 * Created by Chen Wang on 10/28/2016.
 */
import java.io.*;

public class QuoteServer {
    public static void main(String[] args) throws IOException {
        new QuoteServerThread().start();
    }
}
