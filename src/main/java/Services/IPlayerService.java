package Services;

import Models.Player;
import Utils.Commands;

import java.util.ArrayList;

public interface IPlayerService {
    public ArrayList<Player> getPlayersList();
    public void addPlayer(Commands p_commands);
    public boolean isPlayerRemoved(Commands p_commands);
    public void issue_order();
    //public IOrders next_order();
    public void assignCountries(Commands commands);
    public void next_order();
}
