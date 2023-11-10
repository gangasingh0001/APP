package Controllers;

import Core.Game;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void start() {

        while (true) {
            game.play();
        }
    }
}
