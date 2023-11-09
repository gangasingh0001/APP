package Orders;

import Constants.ApplicationConstants;
import Models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This is the advance class which implements IOrders interface
 */
public class Advance implements IOrders{
    /**
     * the number of country used to advance
     */
    private int d_numberOfArmiesToAdvance;
    /**
     * the name of country attacked
     */
    private final String d_targetCountryName ;
    /**
     * teh name of country attacking
     */
    private final String d_sourceConuntryName;
    /**
     * the player creating this order
     */
    private Player d_SourcePlayer;
    /**
     * world map
     */
    private final IWorldMap d_WorldMap;
    /**
     * the country attacking
     */
    private Country d_sourceCountry;
    /**
     * the country attacked
     */
    private Country d_targetCountry;
    /**
     * the hashmap between Country and player who owned this country
     */
    private HashMap<Country,Player> d_countryOwnerMap;

    /**
     * get logging records
     */
    private Logger d_logger;


    /**
     * constructor for Advance order
     * @param p_sourceConuntryName teh name of country attacking
     * @param p_targetCountryName the name of country attacked
     * @param p_numberOfArmiesToDeploy the number of country used to advance
     * @param p_SourcePlayer the player creating this order
     * @param p_WorldMap world map
     * @param p_countryOwnerMap the hashmap between Country and player who owned this country
     */
    public Advance(String p_sourceConuntryName, String p_targetCountryName, int p_numberOfArmiesToDeploy, Player p_SourcePlayer, IWorldMap p_WorldMap, HashMap<Country,Player> p_countryOwnerMap)
    {
        d_numberOfArmiesToAdvance = p_numberOfArmiesToDeploy;
        d_targetCountryName = p_targetCountryName;
        d_sourceConuntryName = p_sourceConuntryName;
        d_SourcePlayer = p_SourcePlayer;
        d_WorldMap = p_WorldMap;
        d_countryOwnerMap = p_countryOwnerMap;
    }

    /**
     * Advance class constructor with logging records
     * @param p_sourceConuntryName source country
     * @param p_targetCountryName target country
     * @param p_numberOfArmiesToDeploy number of arimes
     * @param p_SourcePlayer source player
     * @param p_WorldMap world map
     * @param p_countryOwnerMap country owned map
     * @param p_logger logging records
     */
    public Advance(String p_sourceConuntryName, String p_targetCountryName, int p_numberOfArmiesToDeploy, Player p_SourcePlayer, IWorldMap p_WorldMap, HashMap<Country,Player> p_countryOwnerMap, Logger p_logger)
    {
        d_numberOfArmiesToAdvance = p_numberOfArmiesToDeploy;
        d_targetCountryName = p_targetCountryName;
        d_sourceConuntryName = p_sourceConuntryName;
        d_SourcePlayer = p_SourcePlayer;
        d_WorldMap = p_WorldMap;
        d_countryOwnerMap = p_countryOwnerMap;
        d_logger = p_logger;
    }
    /**
     * number of armies assigned each term
     */
    private int d_numberOfArmiesToDeploy;


    /**
     * target country ID
     */
    private String d_targetCountryID ;



    /**
     * override method of execute orders from players
     */
    @Override
    public void execute() {
        if (valid())
        {
            d_logger.severe("Advance order starts!");
            d_sourceCountry.setD_Armies(d_sourceCountry.getD_Armies()-this.d_numberOfArmiesToAdvance);
            d_logger.severe(d_sourceCountry.getName() + " left " + (d_sourceCountry.getD_Armies()-this.d_numberOfArmiesToAdvance) + " armies in its own territory.");
            if(d_countryOwnerMap.get(d_targetCountry).equals(d_SourcePlayer)) {
                d_logger.severe("Target to this player's own country, advance order changes to deploy!");
                 d_targetCountry.setD_Armies(d_targetCountry.getD_Armies()+this.d_numberOfArmiesToAdvance);
            } else {
                while (this.d_numberOfArmiesToAdvance != 0 && d_targetCountry.getD_Armies() != 0) {
                    d_logger.severe("Attack begins!");
                    Random random = new Random();
                    int l_attcakRandom = random.nextInt(0, 10);
                    int l_defenceRandom = random.nextInt(0, 10);
                    if (l_attcakRandom <= 5) {
                        d_logger.severe(this.d_sourceCountry.getName() + " loses one army by attacking!");
                        this.d_numberOfArmiesToAdvance--;
                    }
                    if (l_defenceRandom <= 4) {
                        d_logger.severe(d_targetCountry.getName() + " loses one army by defending!");
                        d_targetCountry.setD_Armies(d_targetCountry.getD_Armies()-1);
                    }

                }

                if (d_targetCountry.getD_Armies() == 0 && this.d_numberOfArmiesToAdvance!=0) {
                    if(!d_targetCountry.isD_NeutralCountry()) {
                        d_logger.severe(d_targetCountry.getName() + " defeated, now this country owned by " + d_SourcePlayer.getD_playerName());
                        d_countryOwnerMap.put(d_targetCountry, d_SourcePlayer);
                        d_targetCountry.setD_Armies(this.d_numberOfArmiesToAdvance);
                        d_logger.severe(d_SourcePlayer.getD_playerName() + " newly owned country " + d_targetCountry.getName() + " now has " + this.d_numberOfArmiesToAdvance + " armies.");
                        Player d_TargetPlayer = d_countryOwnerMap.get(d_targetCountry);
                        d_TargetPlayer.removeAcquiredCountry(d_targetCountry);
                        d_SourcePlayer.addAcquiredCountry(d_targetCountry);
                        d_countryOwnerMap.put(d_targetCountry, d_SourcePlayer);
                    } else {
                        d_logger.severe("Now " + d_sourceCountry.getName() + " remains " + (d_sourceCountry.getD_Armies()+this.d_numberOfArmiesToAdvance) + " armies.");
                        d_sourceCountry.setD_Armies(d_sourceCountry.getD_Armies()+this.d_numberOfArmiesToAdvance);
                    }
                }
            }
        }
    }

    /**
     * boolean method to check the game state
     * @return true if the current order can be executed, else return false
     */
    @Override
    public boolean valid() {
        d_logger.severe("Check if Advance order is valid...");
        boolean sourceCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet()) {
            Country temp=entry.getKey();
            if (temp.getName().equals(d_sourceConuntryName)){
                d_sourceCountry=temp;
                sourceCountryFind=true;
                break;
            }
        }
        if (!sourceCountryFind){
            d_logger.severe("source country is not exist");
            System.out.println("source country is not exist");
            return false;}
        boolean targetCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet())
        {
            Country temp=entry.getKey();
            if (temp.getName().equals(d_targetCountryName)){
                d_targetCountry=temp;
                targetCountryFind=true;
                break;
            }
        }
        if (!targetCountryFind){
            d_logger.severe("target country is not exist");
            System.out.println("target country is not exist");
            return false;
        }
        if (!targetCountryFind){System.out.println("target country is not exist");return false;}

     if(!d_WorldMap.getNeighborsOfCountry(d_targetCountry).contains(d_sourceCountry))
     {
         System.out.println("Two countries is not connected");
     }
        if (d_SourcePlayer.getD_diplomacyWith().contains(d_countryOwnerMap.get(d_targetCountry).getD_playerName()))
        {
            d_logger.severe("The advance order is invalid, because two players have diplomacy");
            System.out.println("The advance order is invalid, because two players have diplomacy");
            return false;
        }
     if(d_countryOwnerMap.get(d_sourceCountry).equals(d_SourcePlayer)&&this.d_numberOfArmiesToAdvance<=d_sourceCountry.getD_Armies()-1)
     {
         return true;
     }
     else if (!d_countryOwnerMap.get(d_sourceCountry).equals(d_SourcePlayer))
     {
         d_logger.severe("Current Country is not belong to Source Player");
         System.out.println("Current Country is not belong to Source Player");
         return false;
     }
     else
     {
         d_logger.severe("We Do not Have Enough Armies to Attack");
         System.out.println("We Do not Have Enough Armies to Attack");
         return false;
     }
    }
    /**
     * override method to print the order from players
     */
    @Override
    public void printOrder() {
        d_logger.severe("Advance order validating...");
        if (!valid()){
            d_logger.severe("The current order is invalid");
            System.out.println("The current order is invalid");
        }
        else
        {
            d_logger.severe(d_SourcePlayer.getD_playerName()+" will attack country "+d_targetCountry.getName()+" form country "+d_sourceCountry.getName()+" with the number of armies "+d_numberOfArmiesToAdvance);
            System.out.println(d_SourcePlayer.getD_playerName()+" will attack country "+d_targetCountry.getName()+" form country "+d_sourceCountry.getName()+" with the number of armies "+d_numberOfArmiesToAdvance);
        }
    }
    /**
     * override method to get the order name
     */
    @Override
    public String getOrderName() {
        return ApplicationConstants.ADVANCE;
    }
    /**
     * get Target Country Name
     * @return Target Country Name
     */
    @Override
    public String getTargetCountryName() {
        return d_targetCountryName;
    }
    /**
     * get Target Country ID
     * @return Target Country ID
     */
    @Override
    public String getTargetCountryID() {
        return d_targetCountryName;
    }
    /**
     * get Number Of Armies
     * @return Number Of Armies
     */
    @Override
    public int getNumberOfArmies() {
        return d_numberOfArmiesToAdvance;
    }
}
