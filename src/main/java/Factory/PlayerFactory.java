package Factory;

import Constants.ApplicationConstants;
import Models.Player;
import Strategy.AggressiveStrategy;
import Strategy.BenevolentStrategy;
import Strategy.CheaterStrategy;
import Strategy.RandomStrategy;

public class PlayerFactory {
    public Player createPlayer(String p_playerName, int type) {
        return switch (type) {
            case 1 -> new Player(p_playerName, new AggressiveStrategy());
            case 2 -> new Player(p_playerName, new BenevolentStrategy());
            case 3 -> new Player(p_playerName, new CheaterStrategy());
            case 4 -> new Player(p_playerName, new RandomStrategy());
            case 5 -> new Player(p_playerName, true);
            default -> throw new IllegalArgumentException("Unknown player type: " + type);
        };
    }
}
