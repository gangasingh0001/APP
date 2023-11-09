package Orders;

import Models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This is the Blockade class which implements IOrders interface
 */
public class Blockade implements IOrders{

    /**
     * source country
     */
    private Country d_sourceCountry;

    /**
     * the player who create this order
     */
    private Player d_player;
    /**
     * the name of country blockaded
     */

    private String d_sourceCountryName;
    /**
     * the hashmap between Country and player who owned this country
     */

    private HashMap<Country,Player> d_countryOwnerMap;

    /**
     * get logging records
     */
    private Logger d_logger;

    /**
     * Parameterized Constructor for blockade card
     * @param p_sourceConuntryName the name of country blockaded
     * @param p_player  the player who create this order
     * @param p_countryOwnerMap the hashmap between Country and player who owned this country
     */
    public Blockade(String p_sourceConuntryName, Player p_player, HashMap<Country,Player> p_countryOwnerMap){
        d_sourceCountryName = p_sourceConuntryName;
        d_player = p_player;
        d_countryOwnerMap = p_countryOwnerMap;
    }

    /**
     * Blockade card constructor with logging records
     * @param p_sourceConuntryName source country name
     * @param p_player player
     * @param p_countryOwnerMap country owned map
     * @param p_logger logging records
     */
    public Blockade(String p_sourceConuntryName, Player p_player, HashMap<Country,Player> p_countryOwnerMap, Logger p_logger){
        d_sourceCountryName = p_sourceConuntryName;
        d_player = p_player;
        d_countryOwnerMap = p_countryOwnerMap;
        d_logger = p_logger;
    }


    /**
     * method to apply blockade to a country
     */
    @Override
    public void execute() {
            if (valid()){
                d_logger.severe("Blockade card is playing!");
                d_sourceCountry.setD_Armies(d_sourceCountry.getD_Armies() * 3);
                d_logger.severe("Target country " + d_sourceCountry + " now is a neutral country and now has " + d_sourceCountry.getD_Armies() * 3 + " armies.");
                d_sourceCountry.setD_NeutralCountry(true);
                d_player.removeCard(CardType.BLOCKADE);
            }
    }
    /**
     * boolean method to check the game state
     * @return if it's a valid game state
     */
    @Override
    public boolean valid() {
        d_logger.severe("Check if blockade card is valid...");
        boolean sourceCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet()) {
            Country l_country=entry.getKey();
            if (l_country.getName().equals(d_sourceCountryName)){
                d_sourceCountry=l_country;
                sourceCountryFind=true;
                break;
            }
        }
        if (!sourceCountryFind){
            d_logger.severe("source country"+d_sourceCountryName+" is not exist");
            System.out.println("source country"+d_sourceCountryName+" is not exist");
            return false;
        }
        if(!d_countryOwnerMap.get(d_sourceCountry).equals(d_player))
        {
            d_logger.severe("The country "+d_sourceCountryName+" is not belong to "+d_player.getD_playerName()+", so blockade is not valid");
            System.out.println("The country "+d_sourceCountryName+" is not belong to "+d_player.getD_playerName()+", so blockade is not valid");
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * override method to print the order
     */
    @Override
    public void printOrder() {
        d_logger.severe("Blockade card validating...");
     if (valid())
     {
         d_logger.severe("Player "+d_player.getD_playerName()+" will blockade "+d_sourceCountryName);
        System.out.println("player "+d_player.getD_playerName()+" will blockade "+d_sourceCountryName);
     }
     else
     {
         d_logger.severe("The blockade card is not valid");
         System.out.println("the blockade card is not valid");
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
