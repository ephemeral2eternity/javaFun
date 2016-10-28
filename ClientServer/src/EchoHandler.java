import java.io.*;
import java.net.*;

/**
 * Created by Chen Wang on 10/28/2016.
 */
class EchoHandler extends Thread {
    Socket client;
    EchoHandler (Socket client) {
        this.client = client;
    }

    public void run () {
        /*
        try {
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = null;

            while ((line = reader.readLine()) != null) {
                if(line.trim().equals("END")) {
                    System.out.println("Stop the client!");
                    break;
                }
                System.out.println(line);
                writer.println(line);
            }
            reader.close();
            writer.close();
            client.close();
        }
        catch (Exception e) {
            System.err.println("Exception caught: client disconnected.");
        }
         */
        try {
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int prePreChar = 0;
            int preChar = 0;
            int currChar;

            while (true) {
                currChar = is.read();

                bos.write(currChar);
                // The three chars are "END"
                if (prePreChar == 69 && preChar == 78 && currChar == 68) {
                    break;
                }

                prePreChar = preChar;
                preChar = currChar;
            }

            // Write exactly the same chars to the output.
            os.write(bos.toByteArray(), 0, bos.toByteArray().length - 3);
            os.flush();
            os.close();
            is.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
