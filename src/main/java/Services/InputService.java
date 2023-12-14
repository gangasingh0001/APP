package Services;

import Middleware.Middleware;

public interface InputService {
    Middleware readCommand();
    int readInt();
    String readLine();
}
