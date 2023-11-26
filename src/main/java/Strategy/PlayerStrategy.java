package Strategy;

import Models.Player;


public interface PlayerStrategy {
    public void deploy(Player player);
    public void attack(Player player);
    public void moveArmies(Player player);
    public void execute();
}
