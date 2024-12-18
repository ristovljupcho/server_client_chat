import java.io.*;

public class UserSocket extends Thread {
    private final PrintWriter writer;
    private final BufferedReader consoleInput;

    public UserSocket(PrintWriter writer) {
        this.writer = writer;
        this.consoleInput = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            String input;
            while ((input = consoleInput.readLine()) != null && !input.equals("end")) {
                writer.println(input);
            }
        } catch (IOException e) {
            System.err.println("Error in UserSocket: " + e.getMessage());
        }
    }
}

