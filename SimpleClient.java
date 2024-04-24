import java.io.*;
import java.net.*;

/**
 * This class represents a simple client program that connects to a server running on localhost
 * and communicates with it over a TCP socket.
 */
public class SimpleClient {
	
	/**
     * The main method of the SimpleClient class.
     * This method establishes a connection to the server, sends a message to the server, and receives a response.
     */
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int PORT = 1254;

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String messageFromServer = in.readUTF();
            System.out.println("Message from server: " + messageFromServer);

            String messageToServer;
            do {
                System.out.print("Enter message to send to server: ");
                messageToServer = reader.readLine();
                out.writeUTF(messageToServer);
            } while (!messageToServer.equals("finish")); // Terminate when "finish" message sent

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}