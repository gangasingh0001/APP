package Factory;

import Constants.ApplicationConstants;
import Models.Player;
import Strategy.AggressiveStrategy;
import Strategy.BenevolentStrategy;
import Strategy.CheaterStrategy;
import Strategy.RandomStrategy;
import Services.PlayerService;
public class PlayerFactory {
    private PlayerService d_playerService;
    public PlayerFactory(PlayerService l_PlayerService)
    {
        d_playerService=l_PlayerService;
    }
    public Player createPlayer(String p_playerName, int type) {
        return switch (type) {
            case 1 -> new Player(p_playerName, new AggressiveStrategy(d_playerService));
            case 2 -> new Player(p_playerName, new BenevolentStrategy());
            case 3 -> new Player(p_playerName, new CheaterStrategy(d_playerService));
            case 4 -> new Player(p_playerName, new RandomStrategy(d_playerService));
            case 5 -> new Player(p_playerName, true);
            default -> throw new IllegalArgumentException("Unknown player type: " + type);
        };
    }
}
