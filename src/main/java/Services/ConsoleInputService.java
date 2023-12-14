package Services;

import Middleware.Middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleInputService implements InputService {

    private BufferedReader reader;
    private Scanner scanner;

    public ConsoleInputService() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Middleware readCommand() {
        try {
            return new Middleware(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public int readInt() {
        return scanner.nextInt();
    }

    // Remember to close the BufferedReader when it's no longer needed
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
