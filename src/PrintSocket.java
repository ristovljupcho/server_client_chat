import java.io.BufferedReader;
import java.io.IOException;

public class PrintSocket extends Thread {
    private final BufferedReader reader;

    public PrintSocket(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            String message;
            while (!Thread.currentThread().isInterrupted() && (message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Error in PrintSocket: " + e.getMessage());
        }
    }
}
