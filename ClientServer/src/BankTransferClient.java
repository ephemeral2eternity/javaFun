/**
 * Created by Chen Wang on 10/20/2016.
 */
import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.*;

public class BankTransferClient {
    Socket clientSocket;
    InputStream clientInputStream;
    OutputStream clientOutputStream;
    static TreeNode root;


    BankTransferClient(Socket socket) throws IOException{
        clientSocket = socket;
        clientInputStream = clientSocket.getInputStream();
        clientOutputStream = clientSocket.getOutputStream();
    }

    public static void init_bank_tree() {
        Scanner sc = new Scanner(System.in);
        String line;
        int parentID, childID;
        TreeNode child, parent;
        String[] lineArr;
        int N = sc.nextInt();

        // Create root node first
        line = sc.nextLine();
        lineArr = line.split(",");
        parentID = Integer.parseInt(lineArr[0]);
        childID = Integer.parseInt(lineArr[1]);

        root = new TreeNode(parentID, null);
        child = new TreeNode(childID, root);
        root.children.add(child);

        for (int i = 1; i < N; i ++) {
            line = sc.nextLine();
            lineArr = line.split(",");
            parentID = Integer.valueOf(lineArr[0]);
            childID = Integer.valueOf(lineArr[1]);

            parent = root.findNode(parentID);
            child = new TreeNode(childID, parent);
            parent.children.add(child);
        }
    }

    /*
    This function is called only once before any client connection is accepted by the server.
    Read any global datasets or configurations here
    */
    public static void init_server() {
        System.out.println("Initializing server");
        init_bank_tree();
    }

    /*
Write your code here
This function is called everytime a new connection is accepted by the server
*/
    public void run() {
        do {
            String message = null;

            /* read message */
            try {
                message = read_string_from_socket();
            } catch (IOException ex) {
                System.out.println("Exception: " + ex);
            }

            /* End of operation on this client */
            if (message.equals("END"))
                break;

            System.out.println("Received = " + message);

            Scanner scan = new Scanner(message);
            int src = scan.nextInt();
            int dst = scan.nextInt();
            int th = scan.nextInt();

            int dist = root.findDistance(src, dst);
            if (dist <= th) {
                message = "YES";
            }
            else {
                message = "NO";
            }

            /* write message */
            try {
                write_string_to_socket(message);
            } catch (IOException ex) {
                System.out.println("Exception: " + ex);
            }
        } while(true);

        // Send end of communication. Very important!
        try {
            write_string_to_socket("END");
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }

        try {
            close_socket();
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }

        return;
    }

    /*
    This function encapsulates the communication protocol.
    Do not edit this function
    */
    public String read_string_from_socket() throws IOException {
        // Read payload
        byte[] len_network_order = new byte[4];
        clientInputStream.read(len_network_order);

        // Get message length from payload
        ByteBuffer bb = ByteBuffer.wrap(len_network_order);
        int message_length = bb.getInt();

        // Read message length bytes
        byte[] message_bytes = new byte[message_length];
        clientInputStream.read(message_bytes);

        // Convert bytes to string and return
        String message = new String(message_bytes, "UTF-8");
        return message;
    }

    /*
    This function encapsulates the communication protocol.
    Do not edit this function
    */
    public void write_string_to_socket(String message) throws IOException {
        // Read length of message and write message header
        int messageLength = message.length();
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(messageLength);
        bb.flip();
        clientOutputStream.write(bb.array());

        // Now write the message itself
        byte[] message_bytes = message.getBytes();
        bb = ByteBuffer.allocate(messageLength);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.put(message_bytes);
        bb.flip();
        clientOutputStream.write(bb.array());
    }

    /*
    This function encapsulates the communication protocol.
    Do not edit this function
    */
    public void close_socket() throws IOException {
        clientInputStream.close();
        clientOutputStream.close();
        clientSocket.close();
    }
}
