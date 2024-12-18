import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 2134);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            PrintSocket printSocket = new PrintSocket(reader);
            UserSocket userSocket = new UserSocket(writer);

            printSocket.start();
            userSocket.start();

            userSocket.join(); // Wait for the UserSocket thread to finish
            printSocket.interrupt(); // Interrupt the PrintSocket thread
            printSocket.join(); // Wait for the PrintSocket thread to finish

        } catch (IOException | InterruptedException e) {
            System.err.println("Error in Chat: " + e.getMessage());
        }
    }
}
