package Services;

import Utils.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputService implements InputService {

    private BufferedReader reader;

    public ConsoleInputService() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public Commands readLine() {
        try {
            return new Commands(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Remember to close the BufferedReader when it's no longer needed
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
