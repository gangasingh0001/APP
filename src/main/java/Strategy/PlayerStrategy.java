package Strategy;

import Models.Player;
import Orders.IOrders;


public interface PlayerStrategy {
    public void deploy(Player player);
    public void attack(Player player);
    public void moveArmies(Player player);
    public void execute(Player player);
}
