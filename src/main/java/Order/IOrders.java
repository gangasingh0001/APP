package Order;

import Models.Player;

/**
 * Interface IOrder
 */
public interface IOrders {
    /**
     * Execute the current order
     *
     * @param player
     */
    public void execute(Player player);

    /**
     * valid the states of game
     * @param p_gameState show the states of game
     * @return boolean
     */

    public boolean valid(int p_gameState);

    /**
     * print out the current command
     *
     */

    public void printOrder();

    /**
     * get the name of order
     * @return the name of current order
     */

    public String getOrderName();

    /**
     * get the target country name of current order
     * @return the target country name of current order
     */

    public String getTargetCountryName();

    /**
     * get the target country ID of current order
     * @return the target country ID of current order
     */
    public String getTargetCountryID();

    /**
     * get the number of armies of current order
     * @return the number of armies of current order
     */
    public int getNumberOfArmies();
}
