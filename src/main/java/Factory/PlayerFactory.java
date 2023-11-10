package Factory;

import Constants.ApplicationConstants;
import Models.Player;
import Strategy.AggressiveStrategy;
import Strategy.BenevolentStrategy;
import Strategy.CheaterStrategy;
import Strategy.RandomStrategy;

public class PlayerFactory {
    public Player createPlayer(String p_playerName, String type) {
        return switch (type) {
            case ApplicationConstants.AGGRESSIVE -> new Player(p_playerName, new AggressiveStrategy());
            case "defensive" -> new Player(p_playerName, new BenevolentStrategy()); //TODO: Change "defensice" string with applicationConstant"
            case "Cheater" -> new Player(p_playerName, new CheaterStrategy()); //TODO: Change "Cheater" string with applicationConstant"
            case "Random" -> new Player(p_playerName, new RandomStrategy()); //TODO: Change "Random" string with applicationConstant"
            default -> throw new IllegalArgumentException("Unknown player type: " + type);
        };
    }
}
