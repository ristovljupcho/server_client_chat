import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2134)) {
            System.out.println("Server started on port 2134");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    writer.write(
                            "Hello " + clientSocket.getInetAddress() + " on port " + clientSocket.getPort() + "\r\n");
                    writer.write(
                            "This is " + clientSocket.getLocalAddress() + " on port " + clientSocket.getLocalPort() +
                                    "\r\n");
                    writer.flush();

                    String message;
                    while ((message = reader.readLine()) != null && !message.contains("end")) {
                        System.out.println("Client: " + message);
                    }

                    System.out.println("Connection with client " + clientSocket.getInetAddress() + " closed!");
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error in Server: " + e.getMessage());
        }
    }
}
