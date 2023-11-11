package Services;

import Models.Country;
import Models.Player;
import Middleware.Middleware;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * interface for PlayerService
 */

public interface IPlayerService {
    /**
     * get all the palyers in this map
     * @return all the players int this map
     */
    public ArrayList<Player> getPlayersList();

    /**
     *  add new players to ArrayList<Player>
     * @param p_commands command used to add new players
     */
    public void addPlayer(Middleware p_commands);

    /**
     * check the current parameter is player, if it is player then removr it form player list
     * @param p_commands
     * @return boolean
     */
    public boolean isPlayerRemoved(Middleware p_commands);

    /**
     * used to create deploy order
     */

    public void issue_order();
    //public IOrders next_order();

    /**
     * assign country to the player
     */
    public void assignCountries();

    /**
     * used to execute deploy order for all the players
     */
    public void next_order();

    public HashMap<Country,Player> getD_playerOwnedCountriesMap();
}
