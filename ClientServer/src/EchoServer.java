import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class EchoServer {

    // NOTE: Use this path to create the UDS Server socket
    // NOTE: Use this path to create the UDS Server socket
    static String SERVER_SOCKET_PATH = "./socket";

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8615);
        System.out.println("server: " + server);

        while (true) {
            // a "blocking" call which waits until a connection is requested
            Socket client = server.accept();
            System.err.println("Accepted connection from client");
            EchoHandler handler = new EchoHandler(client);
            handler.start();
        }
    }
}