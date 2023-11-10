package Services;

public class ConsoleOutputService implements OutputService{
    /**
     * @param message
     */
    @Override
    public void print(String message) {
        System.out.print(message);
    }
}
