package Services;

import Models.Player;
import Order.IOrders;
import Utils.Commands;

import java.util.ArrayList;

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
    public void addPlayer(Commands p_commands);

    /**
     * check the current parameter is player, if it is player then removr it form player list
     * @param p_commands
     * @return boolean
     */
    public boolean isPlayerRemoved(Commands p_commands);

    public void issue_order();
    //public IOrders next_order();

    /**
     * assign country to the player
     * @param commands assign country to players
     */
    public void assignCountries(Commands commands);

    /**
     * used to execute deploy order
     */
    public void next_order();
}
