package Order;

import Models.Player;

public interface IOrders {
    public void execute(Player player);

    public boolean valid(int p_gameState);

    public void printOrder();

    public String getOrderName();

    public String getTargetCountryName();

    public String getTargetCountryID();

    public int getNumberOfArmies();
}
