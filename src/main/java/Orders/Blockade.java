package Orders;

import Models.*;

import java.util.HashMap;
import java.util.Map;

public class Blockade implements IOrders{

    /**
     * worldmap variable
     */
    private WorldMap d_WorldMap;

    /**
     * target country
     */
    private Country d_sourceCountry;

    /**
     * country ID
     */

    private Player d_player;

    private String d_sourceCountryName;

    private HashMap<Country,Player> d_countryOwnerMap;

    /**
     * Parameterized Constructor for blockade card
     * @param p_sourceConuntryName target country ID
     */
    public Blockade(String p_sourceConuntryName, Player p_player, HashMap<Country,Player> p_countryOwnerMap){
//        Card card = new Card();
//        card.setCardType(CardType.BLOCKADE);
        d_sourceCountryName = p_sourceConuntryName;
        d_player = p_player;
        d_countryOwnerMap = p_countryOwnerMap;
    }


    /**
     * method to apply blockade to a country
     * parameter of player object
     */
    @Override
    public void execute() {
        //while (!d_player.getD_PlayerCards().isEmpty()){
            if (valid()){
                d_sourceCountry.setD_Armies(d_sourceCountry.getD_Armies() * 3);
                d_sourceCountry.setD_NeutralCountry(true);
                d_player.removeCard(CardType.BLOCKADE);
               // break;
            }

       // }
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
     * override method to print the order from players
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
