package Orders;

import Models.CardType;
import Models.Player;
import java.util.ArrayList;

/**
 * This is the Diplomacy class which implements IOrders interface
 */
public class Diplomacy implements IOrders{
    /**
     * all players information
     */
    private ArrayList<Player> d_Players;

    /**
     * target player's name
     */
    private String d_TargetPlayerToNegotiate;
    /**
     * the player creating this order
     */
    private Player d_sourcePlayer;
    /**
     * the player who source player negotiate with
     */
    private  Player d_targetPlayer;

    /**
     * execute this order
     * @param p_TargetPlayerToNegotiate the player who source player negotiate with
     * @param p_sourcePlayer the player creating this order
     * @param p_Players all players information
     */

    public Diplomacy(String p_TargetPlayerToNegotiate,Player p_sourcePlayer,ArrayList<Player> p_Players){

        d_TargetPlayerToNegotiate = p_TargetPlayerToNegotiate;
        d_sourcePlayer=p_sourcePlayer;
        d_Players=p_Players;
    }
    /**
     * execute the current order
     */
    @Override
    public void execute() {
        if (valid())
        {
            d_sourcePlayer.getD_diplomacyWith().add(d_TargetPlayerToNegotiate);
            d_targetPlayer.getD_diplomacyWith().add(d_sourcePlayer.getD_playerName());
            d_sourcePlayer.removeCard(CardType.DIPLOMACY);
        }

    }

    /**
     * check if the game state valid to execute the current order
     * @return if it's valid game state
     */
    @Override
    public boolean valid() {
        boolean l_targetPlayerExist=false;
        for(Player l_player:d_Players)
        {
            if(l_player.getD_playerName().equals(d_TargetPlayerToNegotiate))
            {
                d_targetPlayer=l_player;
                l_targetPlayerExist=true;
                break;
            }
        }
        if(l_targetPlayerExist==true) return true;
        else
        {
            System.out.println("the player "+d_TargetPlayerToNegotiate+" is not exist");
            return false;
        }
    }
    /**
     * override method to print the order from players
     */
    @Override
    public void printOrder() {
        if (valid())
        {
            System.out.println("player "+d_sourcePlayer.getD_playerName()+" will diplomacy with player "+d_TargetPlayerToNegotiate);
        }
        else
        {
            System.out.println("the diplomacy card is invalid: "+"player "+d_sourcePlayer.getD_playerName()+" will diplomacy with player "+d_TargetPlayerToNegotiate);
        }

    }

    /**
     * override method to get the order name
     * @return order name
     */
    @Override
    public String getOrderName() {
        return null;
    }

    /**
     * override method to get country name
     * @return country name
     */
    @Override
    public String getTargetCountryName() {
        return null;
    }

    /**
     * override method to get target country id
     * @return target country ID
     */
    @Override
    public String getTargetCountryID() {
        return null;
    }

    /**
     * override method to get number of armies for current country
     * @return number of armies
     */
    @Override
    public int getNumberOfArmies() {
        return 0;
    }
}
