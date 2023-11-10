package Factory;

import Constants.ApplicationConstants;
import Models.Player;
import Strategy.AggressiveStrategy;
import Strategy.BenevolentStrategy;

public class PlayerFactory {
    public Player createPlayer(String type) {
        switch (type) {
            case ApplicationConstants.AGGRESSIVE:
                return new Player(new AggressiveStrategy());
            case "defensive":
                return new Player(new BenevolentStrategy());
            // Add more cases for different player types
            default:
                throw new IllegalArgumentException("Unknown player type: " + type);
        }
    }
}
