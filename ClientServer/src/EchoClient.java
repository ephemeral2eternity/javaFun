/**
 * Created by Chen Wang on 10/28/2016.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8615;

        Scanner scan = new Scanner(System.in);

        Socket socket = new Socket(host, port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        String line = null;

        while(!(line = scan.nextLine()).equals("END")) {
            writer.println(line);
            System.out.println(reader.readLine());
        }
        writer.close();
        reader.close();
        socket.close();
    }
}
