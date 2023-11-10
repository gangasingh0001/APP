package Controllers;

import Core.Game;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void start() {
        game.initializeGame(); // Initialize game before starting the loop

        while (true) {


            if ("exit".equalsIgnoreCase(command)) {
                break;
            }

            game.processCommand(command);
        }
    }
}
