package Cards;

import Models.*;
import Orders.IOrders;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a bomb class which implements the bomb order card
 */
public class Bomb implements IOrders {
    /**
     * the hashmap between Country and player who owned this country
     */
    private  HashMap<Country, Player> d_playerOwnedCountriesMap;
    /**
     * worldmap variable
     */
    private IWorldMap d_worldMap;
    /**
     * target country
     */
    private Country d_TargetCountry;
    /**
     * The name of country the armies deployed to
     */
    private String d_targetCountryName ;
    /**
     * the player creating this order
     */
    private Player d_player;
    /**
     * Parameterized Constructor for bomb card
     */
    public Bomb(String p_sourceConuntryName, Player p_player, IWorldMap p_worldMap,HashMap<Country, Player> p_playerOwnedCountriesMap)
    {
        d_targetCountryName = p_sourceConuntryName;
        d_player = p_player;
        d_worldMap=p_worldMap;
        d_playerOwnedCountriesMap=p_playerOwnedCountriesMap;
    }
    /**
     * override method of execute orders from players
     * bomb method to check conditions, if all met
     * destroy half of the armies located on an opponent’s territory
     */
    @Override
    public void execute() {
            if (valid()){
                int l_newArmies = d_TargetCountry.getD_Armies() / 2;
                d_TargetCountry.setD_Armies(l_newArmies);
                d_player.removeCard(CardType.BOMB);
            }
    }
    /**
     * check if the order is valid
     * @return true if the current order is valid,else return false
     */
    @Override
    public boolean valid() {

        boolean targetCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_playerOwnedCountriesMap.entrySet())
        {
            Country temp=entry.getKey();
            if (temp.getName().equals(d_targetCountryName)){
                d_TargetCountry=temp;
                targetCountryFind=true;
                break;
            }
        }
        if (!targetCountryFind){System.out.println("target country is not exist");return false;}
        boolean isConnectedwithSourcePlayerCountry=false;
        for (Map.Entry<Country, Player> entry : d_playerOwnedCountriesMap.entrySet())
        {
            Country l_country=entry.getKey();
            Player l_player=entry.getValue();
            if (l_player.equals(d_player)&&d_worldMap.getNeighborsOfCountry(l_country).contains(d_TargetCountry))
            {
                isConnectedwithSourcePlayerCountry=true;
                break;
            }

        }
        if (!isConnectedwithSourcePlayerCountry){System.out.println("target country is not connected with source player's countries");return false;}
        return true;
    }

    /**
     * override method to print the order from players
     */
    @Override
    public void printOrder() {

        if (valid())
        {
            System.out.println("the Player "+d_player.getD_playerName()+" will bomb "+d_targetCountryName);
        }
        else
        {
            System.out.println("the current order is invalid "+"the Player "+d_player.getD_playerName()+" will bomb "+d_targetCountryName);
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
