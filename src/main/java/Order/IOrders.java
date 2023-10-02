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
     *
     * @return the name of current order
     */

    public String getOrderName();

    public String getTargetCountryName();

    public String getTargetCountryID();

    public int getNumberOfArmies();
}
