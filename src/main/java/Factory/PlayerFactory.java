package Factory;

import Constants.ApplicationConstants;
import Models.Player;
import Strategy.*;

public class PlayerFactory {
    public void createPlayer(String p_playerName, int type) {
//        return switch (type) {
////            case 1 -> new Player(p_playerName, new AggressiveStrategy());
////            case 2 -> new Player(p_playerName, new BenevolentStrategy());
////            case 3 -> new Player(p_playerName, new CheaterStrategy());
////            case 4 -> new Player(p_playerName, new RandomStrategy());
////            case 5 -> new Player(p_playerName,new HumanStrategy());
//            default -> throw new IllegalArgumentException("Unknown player type: " + type);
//        };
    }
}
