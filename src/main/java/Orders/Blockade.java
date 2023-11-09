package Orders;

import Models.*;
import java.util.HashMap;
import java.util.Map;

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
     * method to apply blockade to a country
     */
    @Override
    public void execute() {
            if (valid()){
                d_sourceCountry.setD_Armies(d_sourceCountry.getD_Armies() * 3);
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
        boolean sourceCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet()) {
            Country l_country=entry.getKey();
            if (l_country.getName().equals(d_sourceCountryName)){
                d_sourceCountry=l_country;
                sourceCountryFind=true;
                break;
            }
        }
        if (!sourceCountryFind){;System.out.println("source country"+d_sourceCountryName+" is not exist");return false;}
        if(!d_countryOwnerMap.get(d_sourceCountry).equals(d_player))
        {
            System.out.println("the country "+d_sourceCountryName+" is not belong to "+d_player.getD_playerName()+"so blockade is not valid");
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
     if (valid())
     {
        System.out.println("player "+d_player.getD_playerName()+" will blockade "+d_sourceCountryName);
     }
     else
     {
         System.out.println("the blockade is not valid");
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
